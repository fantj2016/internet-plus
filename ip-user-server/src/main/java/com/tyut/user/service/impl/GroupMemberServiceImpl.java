package com.tyut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.pojo.GroupMembers;
import com.tyut.core.response.ServerResponse;
import com.tyut.user.dao.GroupMembersMapper;
import com.tyut.user.dao.NewsMapper;
import com.tyut.user.repostory.GroupMemRepostory;
import com.tyut.user.service.GroupMemberService;
import com.tyut.user.vo.GroupMemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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




    /**
     * 加入队伍
     */
    @Override
    @Transactional
    public ServerResponse join(Integer groupId, String userId, String groupName) {
        //用key 先查询出 队伍的id，然后插入一条 groupid，userid，队员信息
//        GroupMembers members = new GroupMembers(groupId,userId,0,0,groupName);
        //判断该用户是否已在队伍中
        boolean exsitSomeone = isExsitSomeone(userId, groupId);
        if (exsitSomeone){
            return ServerResponse.createByErrorMessage("无需重复提交!");
        }
        GroupMembers members = new GroupMembers();
        members.setGroupId(groupId);
        members.setUserId(userId);
        members.setUserIdentity(0);
        members.setUserStatus(0);
        members.setGroupName(groupName);
        GroupMembers save = memRepostory.save(members);
        if (save == null){
            return ServerResponse.createBySuccessMessage("申请失败");
        }
        newsService.addNews(userId,"申请已通知队长，请等待队长同意");
        return ServerResponse.createBySuccessMessage("申请成功");
    }

    /**
     * 根据groupid 查询队伍
     */
    @Override
    public ServerResponse findGroupsByGroupId(Integer groupId) {
        Query nativeQuery = em.createNativeQuery("select g.group_name,u.user_name,u.user_phone,g.user_identity,g.user_status from ip_group_members as g,ip_user as u where g.user_id=u.user_id and g.group_id=? ORDER BY g.user_identity desc,g.user_status desc");
        List<Object> objects = nativeQuery.setParameter(1, groupId).getResultList();
        List<GroupMemVo> list = new ArrayList<>();
        for (Object o : objects) {
            Object[] rowArray = (Object[]) o;
            GroupMemVo view = new GroupMemVo();
            view.setGroupName((String) rowArray[0]);
            view.setUserName((String) rowArray[1]);
            view.setUserPhone((String) rowArray[2]);
            view.setUserIdentity((Integer) rowArray[3]);
            view.setUserStatus((Integer) rowArray[4]);
            list.add(view);
        }
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
        int i = 0;
        try {
            i = membersMapper.selectIndentity(headId, groupId);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("参数不和要求");
        }
        log.info("******* 组id{},用户id{}权限信息是{}",groupId,headId,i);
        if (i == 0){
            //无同意权限
            return ServerResponse.createByErrorMessage("该用户没有权限");
        }
        int i1 = membersMapper.updateStatus(groupId, userId);
        if (i1 == 0){
            return ServerResponse.createByErrorMessage("用户加入失败");
        }
        newsService.addNews(userId,"队长已同意你加入队伍,请刷新查看我的队伍");
        return ServerResponse.createBySuccessMessage("用户加入成功");
    }

    /**
     * 移除某位队员
     */
    @Override
    @Transactional
    public ServerResponse removeSomeone(Integer groupId, String headId, String userId) {
        int i = 0;
        try {
            i = membersMapper.selectIndentity(headId, groupId);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("参数不和要求");
        }
        log.info("******* 组id{},用户id{}权限信息是{}",groupId,headId,i);
        if (i == 0){
            //无同意权限
            return ServerResponse.createByErrorMessage("该用户没有权限");
        }
        int i1 = membersMapper.deleteSomeone(groupId, userId);
        if (i1 == 0){
            return ServerResponse.createByErrorMessage("用户移除失败");
        }
        newsService.addNews(userId,"队长已把你移除队伍");
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
        if (exsitSomeone != 0){
            return true;
        }
        return false;
    }
}
