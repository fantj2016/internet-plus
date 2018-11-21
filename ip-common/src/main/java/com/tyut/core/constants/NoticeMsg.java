package com.tyut.core.constants;

import java.io.Serializable;

public class NoticeMsg implements Serializable {

    /**
     * 系统类型
     */
    public interface sys{

        /**
         * 您的队伍给我一个吻创建成功！
         */
        static String groupCreateSuccess(String groupName, String groupKey){
            return "<div class=\"news-item-inner news-title\">" +
                      "<p>来自<span class=\"theme-alert-color news-emphasize-text\">系统</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>您的队伍<span class=\"theme-color2 news-emphasize-text\">"+groupName+"</span>创建成功！</p>" +
                        "<p>队伍申请口令为<span class=\"theme-color2 news-emphasize-text\">"+groupKey+"</span>快去通知你的小伙伴吧！</p>" +
                    "</div>";
        }
    }

    /**
     * 个人类型
     */
    public interface ind{
        /**
         * 来自队伍给我一个吻消息：
         * 您的队伍有新的申请啦，快去我的队伍中查看吧！
         */
        static String groupNewApply(String groupName){
            return "<div class=\"news-item-inner news-title\">" +
                        "<p>来自队伍<span class=\"theme-color news-emphasize-text\">"+groupName+"</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>您的队伍有新的申请啦，快去我的队伍中查看吧！</p>" +
                    "</div>";
        }
        /**
         * 来自队伍给我一个吻消息：
         *
         * 队长吴彦祖邀请您加入队伍，请在我的队伍 -> 邀请消息中查看。
         *
         * 如有疑问，请致电18655454453
         */
        static String groupInvide(String groupName, String headerName, String phone){
            return "<div class=\"news-item-inner news-title\">" +
                        "<p>来自队伍<span class=\"theme-color news-emphasize-text\">"+groupName+"</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>队长<span class=\"theme-color2 news-emphasize-text\">"+headerName+"</span>邀请您加入队伍，请在我的队伍 -> 邀请消息中查看。</p>" +
                        "<p>如有疑问，请致电<span class=\"theme-color2 news-emphasize-text\">"+phone+"</span></p>" +
                    "</div>";
        }

        /**
         * 来自队伍给我一个吻消息：
         *
         * 该队伍已被队长吴彦祖解散请重新报名参赛。
         *
         * 如有疑问，请致电18655454453
         */
        static String groupDisband(String groupName, String headerName, String phone){
            return "<div class=\"news-item-inner news-title\">" +
                        "<p>来自队伍<span class=\"theme-color news-emphasize-text\">"+groupName+"</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>该队伍已被队长<span class=\"theme-color2 news-emphasize-text\">"+headerName+"</span>解散请重新报名参赛。</p>" +
                        "<p>如有疑问，请致电<span class=\"theme-color2 news-emphasize-text\">"+phone+"</span></p>" +
                    "</div>";
        }
        /**
         * 来自队伍给我一个吻消息：
         *
         * 您的队员吴彦祖，已退出队伍。
         *
         * 如有疑问，请致电18655454453
         */
        static String groupSomeoneQuit(String groupName, String name, String phone){
            return "<div class=\"news-item-inner news-title\">" +
                        "<p>来自队伍<span class=\"theme-color news-emphasize-text\">"+groupName+"</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>您的队员<span class=\"theme-color2 news-emphasize-text\">"+name+"</span>，已退出队伍。</p>" +
                        "<p>如有疑问，请致电<span class=\"theme-color2 news-emphasize-text\">"+phone+"</span></p>" +
                    "</div>";
        }
        /**
         * 来自队伍给我一个吻消息：
         *
         * 您的申请已提交，请等待队长同意。
         */
        static String groupWaitAgree(String groupName){
            return "<div class=\"news-item-inner news-title\">" +
                        "<p>来自队伍<span class=\"theme-color news-emphasize-text\">"+groupName+"</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>您的申请已提交，请等待队长同意。</p>" +
                    "</div>";
        }
        /**
         * 来自队伍给我一个吻消息：
         *
         * 队长吴彦祖将您移出队伍，请重新报名参赛。
         *
         * 如有疑问，请致电18655454453
         */
        static String groupBequit(String groupName, String headerName, String phone){
            return "<div class=\"news-item-inner news-title\">" +
                        "<p>来自队伍<span class=\"theme-color news-emphasize-text\">"+groupName+"</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>队长<span class=\"theme-color2 news-emphasize-text\">"+headerName+"</span>将您移出队伍，请重新报名参赛。</p>" +
                        "<p>如有疑问，请致电<span class=\"theme-color2 news-emphasize-text\">"+phone+"</span></p>" +
                    "</div>";
        }
        /**
         * 来自队伍给我一个吻消息：
         *
         * 队长吴彦祖拒绝了您的申请。
         *
         * 如有疑问，请致电18655454453
         */
        static String groupBereject(String groupName, String headerName, String phone){
            return "<div class=\"news-item-inner news-title\">" +
                          "<p>来自队伍<span class=\"theme-color news-emphasize-text\">"+groupName+"</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>队长<span class=\"theme-color2 news-emphasize-text\">"+headerName+"</span>拒绝了您的申请。</p>" +
                        "<p>如有疑问，请致电<span class=\"theme-color2 news-emphasize-text\">"+phone+"</span></p>" +
                    "</div>";
        }
        /**
         * 来自队伍给我一个吻消息：
         *
         * 队长吴彦祖同意了您的申请，请在我的队伍中查看。
         */
        static String groupBeAgree(String groupName, String headerName){
            return "<div class=\"news-item-inner news-title\">" +
                        "<p>来自队伍<span class=\"theme-color news-emphasize-text\">"+groupName+"</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>队长<span class=\"theme-color2 news-emphasize-text\">"+headerName+"</span>同意了您的申请，请在我的队伍中查看。</p>" +
                    "</div>";
        }
        /**
         * <p>来自用户<span style="color: #568fdc; margin: 0 10px;">吴彦祖</span>消息：</p>
         * 		<p>用户<span style="color: #fec24f; margin: 0 10px;">吴彦祖</span>拒绝加入您的队伍<span style="color: #fec24f; margin: 0 10px;">给我一个吻</span></p>
         * 		<p>如有疑问，请致电<span style="color: #fec24f; margin: 0 10px;">18655454453</span></p>
         */
        static String groupInviteBeeject(String userName,String phone){
            return "<div class=\"news-item-inner news-title\">" +
                        "<p>来自用户<span class=\"theme-color news-emphasize-text\">"+userName+"</span>消息：</p>" +
                    "</div>" +
                    "<div class=\"news-item-inner news-content\">" +
                        "<p>用户<span class=\"theme-color2 news-emphasize-text\">"+userName+"</span>拒绝加入您的队伍<span class=\"theme-color2 news-emphasize-text\">给我一个吻</span></p>" +
                        "<p>如有疑问，请致电<span class=\"theme-color2 news-emphasize-text\">"+phone+"</span></p>" +
                    "</div>";
        }

    }
}
