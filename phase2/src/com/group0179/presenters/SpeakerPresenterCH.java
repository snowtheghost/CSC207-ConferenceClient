package com.group0179.presenters;

public class SpeakerPresenterCH implements ISpeakerPresenter{
    /**
     * @return Recipient does not exist.
     */
    @Override
    public String recipientDNE() {
        return "接收者不存在";
    }

    /**
     * @return message sent successfully.
     */
    @Override
    public String sentSuccess() {
        return "消息发送成功";
    }

    /**
     * @return the word message.
     */
    @Override
    public String messageWord() {
        return "消息";
    }

    /**
     * @return user not found.
     */
    @Override
    public String userNotFound() {
        return "未找到用户";
    }

    /**
     * @return no events availiable.
     */
    @Override
    public String noEventsAvailiable() {
        return "无可加入活动";
    }

    /**
     * @return joining or leaving
     */
    @Override
    public String joinOrLeave() {
        return "输入'j'加入或者'l'退出。";
    }

    /**
     * @return invalid room number
     */
    @Override
    public String invalidRoom() {
        return "无效的房间号";
    }

    /**
     * @return no event found
     */
    @Override
    public String noEventsFound() {
        return "未找到活动";
    }

    /**
     * @return successfully joined the event
     */
    @Override
    public String joinEventSuccess() {
        return "成功加入活动";
    }

    /**
     * @return successfully left the event.
     */
    @Override
    public String leaveEventSuccess() {
        return "成功退出活动";
    }

    /**
     * @return unable to join the event
     */
    @Override
    public String joinEventFail() {
        return "无法加入该活动";
    }

    /**
     * @return unable to leave the event.
     */
    @Override
    public String leaveEventFail() {
        return "无法退出该活动";
    }

    /**
     * AttendeeScene strings.
     * @return Send message
     */
    @Override
    public String sendMessage() {
        return "发消息";
    }

    /**
     * @return Enter username of who you would like to message.
     */
    @Override
    public String enterUsername() {
        return "输入你想发送消息的用户名";
    }

    /**
     * @return Enter message content
     */
    @Override
    public String enterMsgContent() {
        return "输入消息内容";
    }

    /**
     * @return Send
     */
    @Override
    public String send() {
        return "发送";
    }

    /**
     * @return View messages
     */
    @Override
    public String viewMessages() {
        return "查看消息";
    }

    /**
     * @return Enter username of who's messages they want to see.
     */
    @Override
    public String usersToSee() {
        return "请输入你想查看消息的用户名, 输入 'all' 以查看全部消息。";
    }

    /**
     * @return Submit
     */
    @Override
    public String submit() {
        return "提交";
    }

    /**
     * @return View all events
     */
    @Override
    public String viewAllEvents() {
        return "查看所有活动";
    }

    /**
     * @return View signed up events.
     */
    @Override
    public String viewAllSignedUpevents() {
        return "查看所有加入的活动";
    }

    /**
     * @return join/leave event button
     */
    @Override
    public String joinLeaveButtonText() {
        return "加入或退出活动";
    }

    /**
     * @return Join or leave text label.
     */
    @Override
    public String joinOrLeaveLabel() {
        return "你想要加入还是退出这个活动? ";
    }

    /**
     * @return Enter room number of event.
     */
    @Override
    public String enterRoomNumberLabel() {
        return "输入活动的房间号:";
    }

    /**
     * @return Enter name of event.
     */
    @Override
    public String enterEventNameLabel() {
        return "请输入活动名称:";
    }

    /**
     * @return Attempt join.
     */
    @Override
    public String attemptJoinButton() {
        return "尝试加入";
    }

    /**
     * @return Logout
     */
    @Override
    public String logoutButton() {
        return "退出";
    }

    /**
     * @return Last login info
     */
    @Override
    public String lastLogin() {
        return "上次登录：";
    }

    /**
     * @return Average login time
     */
    @Override
    public String avgLoginTime() {
        return "平均登录时间（分钟）：";
    }

    /**
     * @return Total login time
     */
    @Override
    public String totalLoginTime() {
        return "总登录时间：";
    }

    /**
     * @return Max and min login times
     */
    @Override
    public String maxMinLoginTimes() {
        return null;
    }

    @Override
    public String userStats() {return "用户数据";
    }

    @Override
    public String SpeakerPanel() {
        return "演讲者面板";
    }

    @Override
    public String viewSpeakingEventsButton() {
        return "查看演讲者的活动";
    }

    @Override
    public String noSpeakingEvents() {
        return "你暂时没有活动演讲";
    }

    @Override
    public String eventDNE(){return "活动不存在";}

    @Override
    public String sendmessagetouser(){return "发消息给特定的用户";}

    @Override
    public String sendmessagetoEvent(){return "发消息给改活动的所有参与者。";}


}
