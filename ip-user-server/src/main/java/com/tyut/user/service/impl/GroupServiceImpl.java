package com.tyut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.aspect.ServiceLog;
import com.tyut.core.pojo.Group;
import com.tyut.core.pojo.GroupMembers;
import com.tyut.core.response.ServerResponse;
import com.tyut.user.repostory.GroupMemRepostory;
import com.tyut.user.repostory.GroupRepostory;
import com.tyut.user.service.GroupService;
import com.tyut.user.service.NewsService;
import com.tyut.user.vo.GroupVo;
import com.tyut.user.vo.GroupsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Fant.J.
 * 2018/5/31 19:20
 */
@Service(version = "2.0.0")
@Slf4j
public class GroupServiceImpl implements GroupService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private GroupRepostory groupRepostory;
    @Autowired
    private GroupMemRepostory memRepostory;
    @Autowired
    private NewsService newsService;
    /**
     * 创建队伍
     *
     * @param group
     */
    @ServiceLog
    @Override
    @Transactional
    public ServerResponse create(Group group) {

        group.setGroupStatus(0);
        group.setGroupCreateTime(new Date());
        group.setGroupKey(UUID.randomUUID().toString().replace("-",""));
        Group save = groupRepostory.save(group);
        if (save == null){
            return ServerResponse.createByErrorMessage("创建队伍失败");
        }
        log.info("存储后拿到的id是{}",save.getGroupId());
        log.info("存储后拿到的key是{}",save.getGroupKey());
        GroupMembers members = new GroupMembers();
        members.setGroupId(save.getGroupId());
        members.setUserId(group.getGroupHeaderId());
        members.setUserIdentity(1);
        members.setUserStatus(1);
        members.setGroupType(group.getGroupType());
        members.setGroupName(save.getGroupName());
        memRepostory.save(members);
        newsService.addNews(group.getGroupHeaderId(),"队伍创建成功,队名:"+group.getGroupName()+",口令:"+save.getGroupKey()+",快通知你的小伙伴吧!");
        return ServerResponse.createBySuccess(save.getGroupKey(),"创建队伍成功");
    }

    /**
     * 根据key 查询队伍
     *
     * @param key
     */
    @Override
    public ServerResponse selectByKey(String key) {
        Query nativeQuery = entityManager.createNativeQuery("select g.group_id,g.group_name from ip_group g WHERE g.group_key=?");
        Object singleResult = nativeQuery.setParameter(1, key).getSingleResult();
            Object[] rowArray = (Object[]) singleResult;
            GroupVo groupVo = new GroupVo();
            groupVo.setGroupId(((Integer) rowArray[0]));
            groupVo.setGroupName((String) rowArray[1]);

        if (groupVo == null){
            return ServerResponse.createByErrorMessage("该队伍不存在");
        }
//        log.info(list.toString());
        return ServerResponse.createBySuccess(groupVo);
    }

    /**
     * 通过用户id查询  用户所在的 队伍列表
     *
     * @param userId
     */
    @Override
    public ServerResponse selectGroupList(String userId) {
        Query nativeQuery = entityManager.createNativeQuery("select u.user_name,u.user_phone,m.group_name,m.group_id,m.user_identity,m.id,c.cpt_name,g.group_key from ip_user as u,ip_group_members m,ip_competition c,ip_group g   where u.user_id=m.user_id and m.user_status =1 and m.group_type=c.cpt_id  and g.group_id = m.group_id and u.user_id = ? GROUP BY m.group_id ORDER BY m.group_id DESC");
        List<Object> resultList = nativeQuery.setParameter(1, userId).getResultList();
        List<GroupsVo> list = new ArrayList<>();
        for (Object o : resultList) {
            Object[] rowArray = (Object[]) o;
            GroupsVo view = new GroupsVo();
            view.setUserName((String) rowArray[0]);
            view.setUserPhone((String) rowArray[1]);
            view.setGroupName((String) rowArray[2]);
            view.setGroupId((Integer) rowArray[3]);
            view.setUserIdentity((Integer) rowArray[4]);
            view.setId((Integer) rowArray[5]);
            view.setCptName((String)rowArray[6]);
            view.setGroupKey((String)rowArray[7]);
            list.add(view);
        }
        list.forEach(p-> System.out.println(p.toString()));
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 查看队伍名字是否存在
     *
     * @param groupName
     */
    @Override
    public ServerResponse isGroupNameExist(String groupName) {
        List<Group> exsitGroupName = groupRepostory.isExsitGroupName(groupName);
        log.info("使用这个队名{}的有{}个",groupName,exsitGroupName.size());
        if (exsitGroupName.size()==0) {
            return ServerResponse.createBySuccessMessage("队名可以使用");
        }
        return ServerResponse.createByErrorMessage("队名已占用");
    }
}
