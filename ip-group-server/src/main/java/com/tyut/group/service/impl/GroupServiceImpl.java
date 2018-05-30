package com.tyut.group.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.pojo.Group;
import com.tyut.core.pojo.GroupMembers;
import com.tyut.core.response.ServerResponse;
import com.tyut.group.vo.GroupVo;
import com.tyut.group.vo.GroupsVo;
import com.tyut.group.repostroy.GroupMemRepostory;
import com.tyut.group.repostroy.GroupRepostory;
import com.tyut.group.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Fant.J.
 * 2018/5/26 23:16
 */
@Slf4j
@Service(version = "2.0.5")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepostory groupRepostory;
    @Autowired
    private GroupMemRepostory memRepostory;

    /**
     * 创建队伍
     */
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
//        log.info("存储后拿到的id是{}",save.getGroupId());
//        log.info("存储后拿到的key是{}",save.getGroupKey());
//        GroupMembers members = new GroupMembers(save.getGroupId(),group.getGroupHeaderId(),1,0,save.getGroupName());
        GroupMembers members = new GroupMembers();
        members.setGroupId(save.getGroupId());
        members.setUserId(group.getGroupHeaderId());
        members.setUserIdentity(1);
        members.setUserStatus(1);
        members.setGroupName(save.getGroupName());
        memRepostory.save(members);
        return ServerResponse.createBySuccess(save.getGroupKey(),"创建队伍成功");
    }

    /**
     * 根据key 查询队伍
     */
    @Override
    public ServerResponse selectByKey(String key) {
        List<Object> object= groupRepostory.selectByKey(key);
        List<GroupVo> list = new ArrayList<>();
        for (Object o : object) {
            Object[] rowArray = (Object[]) o;
            GroupVo groupVo = new GroupVo();
            groupVo.setGroupId(((Integer) rowArray[0]));
            groupVo.setGroupName((String) rowArray[1]);
            list.add(groupVo);
        }

        if (StringUtils.isEmpty(list)){
            return ServerResponse.createByErrorMessage("该队伍不存在");
        }
        log.info(list.toString());
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 通过用户id查询  用户所在的 队伍列表
     *
     * @param userId
     */
    @Override
    public ServerResponse selectGroupList(Integer userId) {
        List<Object> resultList = memRepostory.findAllByUserId(userId);
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
            list.add(view);
        }
        log.info(list.toString());
        if (!StringUtils.isEmpty(list)){
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMessage("查询队员列表失败");
    }
}
