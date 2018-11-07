package com.tyut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.pojo.*;
import com.tyut.core.response.ServerResponse;
import com.tyut.user.dao.GroupMapper;
import com.tyut.user.dao.GroupMembersMapper;
import com.tyut.user.dao.UserMapper;
import com.tyut.user.repostory.*;
import com.tyut.user.service.GroupMemberService;
import com.tyut.user.vo.CompetitionTitleListVo;
import com.tyut.user.vo.GroupMemVo;
import com.tyut.user.vo.GroupsVo;
import com.tyut.user.vo.InviteVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Created by Fant.J.
 * 2018/5/31 20:03
 */
@Service(version = "2.0.0")
@Slf4j
public class GroupMemberServiceImpl implements GroupMemberService {


    @Autowired
    private GroupMemRepostory memRepostory;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private GroupMembersMapper membersMapper;
    @Autowired
    private NewsServiceImpl newsService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GroupRepostory groupRepostory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupServiceImpl groupService;
    @Autowired
    private TeacherRepostory teacherRepostory;
    @Autowired
    private CompetitionRepostory competitionRepostory;
    @Autowired
    private GroupMapper groupMapper;


    /**
     * 加入队伍
     */
    @Override
    @Transactional
    public ServerResponse join(Integer groupId, String userId, String groupName,Integer cptId) {
        //用key 先查询出 队伍的id，然后插入一条 groupid，userid，队员信息
//        GroupMembers members = new GroupMembers(groupId,userId,0,0,groupName);
        //判断该用户是否已在队伍中
        boolean exsitSomeone = isExsitSomeone(userId, groupId);
        if (exsitSomeone){
            return ServerResponse.createByErrorMessage("无需重复提交!");
        }

        //判断该用于已经参加过此类活动
        boolean checkResult = querySomeoneHaveJoinOneCpt(userId, cptId);
        log.info("获取到的checkresult值"+checkResult);

        if (!checkResult){
            return ServerResponse.createByErrorMessage("你已参加过本类型的比赛");
        }

        GroupMembers members = new GroupMembers();
        members.setGroupId(groupId);
        members.setUserId(userId);
        members.setUserIdentity(0);
        members.setUserStatus(0);
        members.setGroupType(cptId);
        members.setGroupName(groupName);
//        redisTemplate.opsForValue().append("GroupMembers"+groupId,members.toString());
        GroupMembers save = memRepostory.save(members);
        if (save == null){
            return ServerResponse.createBySuccessMessage("申请失败");
        }
        /**
         * 通知队长
         */
        String headerUserId = getHeaderUserId(groupId);
        newsService.addNews(headerUserId,"你创建的团队:"+groupName+"又有新申请啦！");
        newsService.addNews(userId,"申请已通知队长，请等待队长同意");
        return ServerResponse.createBySuccessMessage("申请成功");
    }

    /**
     * 根据groupid 查询队伍
     */
    @Override
    public ServerResponse findGroupsByGroupId(Integer groupId) {
        List<GroupMemVo> list = findMembrsBygroupId(groupId);
        if (!StringUtils.isEmpty(list)){
            log.info("findAllBygGroupId: *****"+list);
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMessage("查找队伍失败");
    }

    /**
     * 同意用户加入队伍
     */
    @Override
    @Transactional
    public ServerResponse agreeSomeone(Integer groupId, String headId, String userId) {

        boolean isHeader = isHeader(headId, groupId);
        if (!isHeader){
            //无同意权限
            return ServerResponse.createByErrorMessage("该用户没有权限");
        }
        int i1 = membersMapper.updateAgreeStatus(groupId, userId);
        if (i1 == 0){
            return ServerResponse.createByErrorMessage("用户加入失败");
        }
        newsService.addNews(userId,"队长已同意你加入队伍,请刷新查看我的队伍");
        return ServerResponse.createBySuccessMessage("用户加入成功");
    }

    /**
     * 拒绝用户加入队伍
     */
    @Override
    @Transactional
    public ServerResponse rejectSomeone(Integer groupId, String headId, String userId) {
        String headerUserId = getHeaderUserId(groupId);
        User header = userMapper.selectByPrimaryKey(headerUserId);
        newsService.addNews(userId,"队长:"+header.getUserName()+"拒绝你加入队伍,他的联系方式:"+header.getUserPhone());
        return  removeSomeone(groupId, headId, userId);
    }

    /**
     * 移除某位队员
     */
    @Override
    @Transactional
    public ServerResponse removeSomeone(Integer groupId, String headId, String userId) {

        boolean isHeader = isHeader(headId, groupId);
        if (!isHeader){
            //无同意权限
            return ServerResponse.createByErrorMessage("该用户没有权限");
        }
        if (headId.equals(userId)){
            return ServerResponse.createByErrorMessage("不能移除本人!");
        }
        int i1 = membersMapper.deleteSomeone(groupId, userId);
        if (i1 == 0){
            return ServerResponse.createByErrorMessage("用户移除失败");
        }
        String headerUserId = getHeaderUserId(groupId);
        User header = userMapper.selectByPrimaryKey(headerUserId);
        newsService.addNews(userId,"队长:"+header.getUserName()+"已把你移除队伍,他的联系方式:"+header.getUserPhone());
        return ServerResponse.createBySuccessMessage("用户移除成功");
    }

    /**
     * 查询某位用户是否存在在某个队伍
     * false表示不存在，true表示已存在
     */
    @Override
    public boolean isExsitSomeone(String userId, Integer groupId) {
        int exsitSomeone = membersMapper.isExsitSomeone(groupId, userId);
        log.info("*** 该用户是否存在某个队伍"+exsitSomeone);
        return exsitSomeone != 0;
    }

    /**
     * 邀请某人加入
     */
    @Override
    @Transactional
    public ServerResponse inviteSomeone(Integer groupId,String userName, String userPhone) {
        // 获取用户的 uuid
        String userId = userMapper.queryIdByNameAndPhone(userName, userPhone);

        log.info("拿到的userId是{}",userId);
        if (StringUtils.isEmpty(userId)){
            return ServerResponse.createByErrorMessage("该用户还未注册");
        }
        Group one = groupRepostory.findOne(groupId);
        GroupMembers groupMembers = new GroupMembers();
        groupMembers.setGroupId(groupId);
        groupMembers.setGroupType(one.getGroupType());
        groupMembers.setGroupName(one.getGroupName());
        groupMembers.setUserIdentity(0);
        groupMembers.setUserStatus(-1);
        groupMembers.setUserId(userId);
        boolean checkResult = querySomeoneHaveJoinOneCpt(userId, one.getGroupType());
        if (!checkResult){
            return ServerResponse.createByErrorMessage("该用户已参加过本类型的比赛");
        }
        GroupMembers check = groupRepostory.save(groupMembers);
        if (check == null){
            return ServerResponse.createByErrorMessage("该用户信息拉取失败,请重试");
        }
        User header = userMapper.queryMemberSimpleInfo(one.getGroupHeaderId());
//        User header = userRepository.findOne(one.getGroupHeaderId());
        //发通知
        newsService.addNews(userId,"队长: "+header.getUserName() +" 邀请你加入他的队伍: "+one.getGroupName()+" 请去我的队伍页面进行确认。 ");

        return ServerResponse.createBySuccessMessage("邀请成功,请耐心等待同意.");
    }

    /**
     * 查询是否有被邀请信息
     */
    @Override
    public ServerResponse queryBeInvited(String userId) {
        List<GroupMembers> groupMembers = membersMapper.queryInfoByUserId(userId);
        if (groupMembers == null){
            return ServerResponse.createBySuccessMessage("没有关于你的消息。");
        }
        try {
            List<InviteVO> list = new ArrayList<>();
            for (GroupMembers g : groupMembers) {
                InviteVO inviteVO = new InviteVO();
                Integer groupId = g.getGroupId();
                Group group = groupMapper.selectByPrimaryKey(groupId);
                String groupHeaderId = group.getGroupHeaderId();
                log.info("获取到队长的id:{}",groupHeaderId);
                User header = userMapper.selectByPrimaryKey(groupHeaderId);
                log.info(" "+header);
                inviteVO.setGroupName(group.getGroupName());
                inviteVO.setCptName(competitionRepostory.findOne(group.getGroupType()).getCptName());
                inviteVO.setGroupId(groupId);
                inviteVO.setGroupPhone(group.getGroupPhone());
                inviteVO.setHeadName(header.getUserName());
                list.add(inviteVO);
            }
            return ServerResponse.createBySuccess(list);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("查询邀请信息异常");
        }
    }

    /**
     * 退出队伍
     */
    @Override
    public ServerResponse quitGroup(String userId, Integer groupId) {
        int i = membersMapper.deleteSomeone(groupId, userId);
        //获取队长id
        Group group = groupMapper.selectByPrimaryKey(groupId);
        String groupHeaderId = group.getGroupHeaderId();
        User one = userRepository.findOne(userId);
        newsService.addNews(groupHeaderId,one.getUserName()+"退出了你的战队");
        if (i != 1){
            return ServerResponse.createByErrorMessage("退出失败");
        }
        return ServerResponse.createBySuccessMessage("成功退出");
    }

    @Override
    public ServerResponse rejectInvite(String userId, Integer groupId) {
        int i = membersMapper.deleteSomeone(groupId, userId);
        //获取队长id
        Group group = groupMapper.selectByPrimaryKey(groupId);
        String groupHeaderId = group.getGroupHeaderId();
        User one = userRepository.findOne(userId);
        newsService.addNews(groupHeaderId,one.getUserName()+"拒绝了你的邀请");
        if (i != 1){
            return ServerResponse.createByErrorMessage("退出失败");
        }
        return ServerResponse.createBySuccessMessage("成功退出");
    }

    @Override
    public ServerResponse ignoreInvite(String userId, Integer groupId) {
        int i = membersMapper.deleteSomeone(groupId, userId);
        if (i != 1){
            return ServerResponse.createByErrorMessage("失败");
        }
        return ServerResponse.createBySuccessMessage("成功忽略此消息");
    }

    /**
     * 接受邀请加入队伍
     */
    @Override
    public ServerResponse updateInvite(String userId, Integer groupId) {
        List<GroupMembers> groupMembers = membersMapper.queryInfoByUserId(userId);
        if (groupMembers != null){
            for (GroupMembers groupMember: groupMembers){
                if (groupMember.getGroupId().equals(groupId)){
                    //改变用户状态
                    membersMapper.updateAgreeStatus(groupId,userId);
                    return ServerResponse.createBySuccessMessage("加入成功");
                }
            }
        }
        return ServerResponse.createBySuccessMessage("加入失败(未被邀请)");
    }

    /**
     * 添加指导教师
     */
    @Override
    public ServerResponse addTeacher(Integer groupId, String headId, String teacherName, String teacherPhone) {
        //校验 header 身份
        boolean header = isHeader(headId, groupId);
        if (!header){
            return ServerResponse.createByErrorMessage("非队长不能操作");
        }
        Teacher teacher = new Teacher();
        teacher.setGroupId(groupId);
        teacher.setTeacherName(teacherName);
        teacher.setTeacherPhone(teacherPhone);
        try {
            Teacher save = teacherRepostory.save(teacher);
            if (save == null){
                return ServerResponse.createByErrorMessage("添加失败");
            }
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("添加失败");
        }

        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @Override
    public ServerResponse removeTeacher(Integer teacherId, String userId, Integer groupId) {
        // 校验队长身份
        boolean header = isHeader(userId, groupId);
        if (!header){
            return ServerResponse.createByErrorMessage("没有权限");
        }
        try {
            teacherRepostory.delete(teacherId);
        }catch (Exception e) {

        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    @Transactional
    public ServerResponse disbandGroup(String headerId, String groupKey) {
        Group allByGroupKey = groupRepostory.findAllByGroupKey(groupKey);
        Integer groupId = allByGroupKey.getGroupId();
        boolean header = isHeader(headerId, groupId);
        if (!header){
            return ServerResponse.createByErrorMessage("该用户没有权限");
        }
        List<GroupMemVo> list = findMembrsBygroupId(groupId);
        //拿出id,批量删除
        List<String> removeList = list.stream().map(GroupMemVo::getUserId).collect(Collectors.toList());
        //批量发通知
        Group group = groupMapper.selectByPrimaryKey(groupId);
        System.out.println(group.toString());
        String groupName = group.getGroupName();
        String groupPhone  = group.getGroupPhone();
        for (int j = 0;j< removeList.size();j++){
            String userId = removeList.get(j);
            if (headerId.equals(userId)){
                newsService.addNews(userId,"你的队伍"+groupName+"已被你解散");
            }
            newsService.addNews(userId,"你的队伍"+groupName+"已被队长解散,联系方式:"+groupPhone);
        }
        int i = membersMapper.deleteByUserIdList(groupId, removeList);
        groupMapper.deleteByPrimaryKey(groupId);
        if (i!=0){
            return ServerResponse.createBySuccessMessage("成功解散队伍");
        }
        return ServerResponse.createByErrorMessage("解散队伍失败");
    }


    /**
     * 根据groupId 查询队长 的userID
     */
    public String getHeaderUserId(Integer groupId){
        return membersMapper.getHeaderUserId(groupId);
    }
    /**
     * 查询该用户是否已经参加过该比赛
     */
    public boolean isJoinCpt(String userId,Integer cptId){
        int joinCpt = membersMapper.isJoinCpt(userId, cptId);
        return joinCpt == 0;
    }
    /**
     * 查询身份是否是队长
     */
    public boolean isHeader(String headId, Integer groupId){
       int  i = membersMapper.selectIndentity(headId, groupId);
        return i != 0;
    }
    /**
     * 查询该学生是否已参加过本类型的比赛,每个学生每一场比赛只能参加一次
     */
    @Transactional
    boolean querySomeoneHaveJoinOneCpt(String userId, Integer cptType){
        List<GroupsVo> groupMembers = groupService.selectGroupAllListByUserId(userId);
//        log.info("获取到的list"+groupMembers.size());
        // 获取当前所有使用的cptid
        List<CompetitionTitleListVo> listVos = competitionRepostory.selectTitleList();
//        listVos.forEach(e -> System.out.println(e.getCptId()));
//        groupMembers.forEach(s -> System.out.println(s.getCptId()));
        for (GroupsVo member : groupMembers){
            for (CompetitionTitleListVo v:listVos) {
                if (member.getCptId().equals(cptType) || v.getCptId().equals(member.getCptId())){
                    return false;
                }
            }
        }
        return true;
    }

    public List<GroupMemVo> findMembrsBygroupId(Integer groupId){
        Query nativeQuery = em.createNativeQuery(
                "select g.group_name,u.user_name,u.user_phone,g.user_identity,g.user_status as user_status_for_group,u.user_id," +
                        "u.user_portrait,s.school_name,u.user_status " +
                        "from ip_group_members as g,ip_user as u, ip_school s " +
                        "where s.id=u.user_school_id and g.user_id=u.user_id and g.group_id=? and g.user_status!=2 " +
                        "ORDER BY g.user_identity desc,g.user_status desc");
        List<Object> objects = nativeQuery.setParameter(1, groupId).getResultList();
        List<GroupMemVo> list = new ArrayList<>();
        for (Object o : objects) {
            Object[] rowArray = (Object[]) o;
            GroupMemVo view = new GroupMemVo();
            view.setGroupName((String) rowArray[0]);
            view.setUserName((String) rowArray[1]);
            view.setUserPhone((String) rowArray[2]);
            view.setUserIdentity((Integer) rowArray[3]);
            view.setUserStatusForGroup((Integer) rowArray[4]);
            view.setUserId((String) rowArray[5]);
            view.setUserPortrait((String) rowArray[6]);
            view.setUserSchool((String) rowArray[7]);
            view.setUserStatus((Integer) rowArray[8]);
            list.add(view);
        }
        //在这里添加老师信息
        try {
            Teacher byGroupId = teacherRepostory.findByGroupId(groupId);
            if (byGroupId != null ) {
                GroupMemVo groupMemVo = new GroupMemVo();
                groupMemVo.setUserName(byGroupId.getTeacherName());
                groupMemVo.setUserPhone(byGroupId.getTeacherPhone());
                groupMemVo.setUserIdentity(2);
                groupMemVo.setUserStatusForGroup(1);
                groupMemVo.setUserId(byGroupId.getTeacherId().toString());
                list.add(groupMemVo);
            }
        }catch(Exception e){}
        return list;
    }
}
