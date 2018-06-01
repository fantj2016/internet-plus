//package com.tyut.user.service.impl;
//
//import com.alibaba.dubbo.config.annotation.Service;
//import com.tyut.core.pojo.GroupMembers;
//import com.tyut.core.response.ServerResponse;
//import com.tyut.user.repostory.GroupMemJpa;
//import com.tyut.user.repostory.GroupMemRepostory;
//import com.tyut.user.service.GroupMemberService;
//import com.tyut.user.vo.GroupMemVo;
//import org.hibernate.SQLQuery;
//import org.hibernate.transform.Transformers;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Fant.J.
// * 2018/5/26 23:26
// */
//@Service(protocol = {"dubbo"}, version = "2.0.0", timeout = 3000)
//public class GroupMemServiceImplBak implements GroupMemberService,Serializable {
//
//    Logger log = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private GroupMemRepostory memRepostory;
//    @Autowired
//    private GroupMemJpa groupMemJpa;
////    @PersistenceContext
////    private EntityManager entityManager;
//
//    /**
//     * 加入队伍
//     */
//    @Override
//    @Transactional
//    public ServerResponse join(Integer groupId,Integer userId,String groupName) {
//        //用key 先查询出 队伍的id，然后插入一条 groupid，userid，队员信息
////        GroupMembers members = new GroupMembers(groupId,userId,0,0,groupName);
//        GroupMembers members = new GroupMembers();
//        members.setGroupId(groupId);
//        members.setUserId(userId);
//        members.setUserIdentity(0);
//        members.setUserStatus(0);
//        members.setGroupName(groupName);
//        GroupMembers save = groupMemJpa.save(members);
//        if (save == null){
//            return ServerResponse.createBySuccessMessage("申请失败");
//        }
//        return ServerResponse.createBySuccessMessage("申请成功");
//    }
//
//    /**
//     * 根据groupid 查询队伍
//     *
//     * @param groupId
//     */
//    @Override
//    public ServerResponse findAllBygGroupId(Integer groupId) {
//        List<Object> objects = memRepostory.findAllByGroupId(groupId);
//        List<GroupMemVo> list = new ArrayList<>();
//        for (Object o : objects) {
//            Object[] rowArray = (Object[]) o;
//            GroupMemVo view = new GroupMemVo();
//            view.setGroupName((String) rowArray[0]);
//            view.setUserName((String) rowArray[1]);
//            view.setUserPhone((String) rowArray[2]);
//            view.setUserIdentity((Integer) rowArray[3]);
//            view.setUserStatus((Integer) rowArray[4]);
//            list.add(view);
//        }
//        if (!StringUtils.isEmpty(list)){
//            log.info("findAllBygGroupId: *****"+list);
//            return ServerResponse.createBySuccess(list);
//        }
//        return ServerResponse.createByErrorMessage("查找队伍失败");
//    }
//
//    /**
//     * 同意用户加入队伍
//     *
//     * @param headId 队长id
//     * @param userId 用户id
//     */
//    @Override
//    public ServerResponse agreeSomeone(Integer groupId,Integer headId,Integer userId) {
//        //查询用户身份
//        int identity = memRepostory.findByUserId(headId,groupId);
//
//        if (identity == 0){
//            //无同意权限
//            return ServerResponse.createByErrorMessage("该用户没有权限");
//        }
//        int i = memRepostory.updateUserIdentity(groupId, userId);
//        if (i!=0){
//            return ServerResponse.createBySuccess("用户加入成功");
//        }
//        return ServerResponse.createByErrorMessage("用户加入失败");
//    }
//
//
//}
