package com.group0179.presenters;

public class AttendeePresenterCH implements IAttendeePresenter{
    /**
     * @return Attendee Panel Label.
     */
    @Override
    public String AttendeePanel() {
        return "参与者面板";
    }
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
        return "消息发送成功。";
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
        return "未找到该用户";
    }

    /**
     * @return no events availiable.
     */
    @Override
    public String noEventsAvailiable() {
        return "无活动存在";
    }

    /**
     * @return joining or leaving
     */
    @Override
    public String joinOrLeave() {
        return "请输入'j'来加入或者'l'退出。";
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
        return "未找到该活动";
    }

    /**
     * @return successfully joined the event
     */
    @Override
    public String joinEventSuccess() {
        return "成功参加活动！";
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
        return "无法参加活动";
    }

    /**
     * @return unable to leave the event.
     */
    @Override
    public String leaveEventFail() {
        return "无法退出活动";
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
        return "请输入消息内容";
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
        return "请输入你想查看消息的用户名, 输入 'all' 以查看全部消息.";
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
        return "查看所有已注册的活动";
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
        return null;
    }

    /**
     * @return Average login time
     */
    @Override
    public String avgLoginTime() {
        return null;
    }

    /**
     * @return Total login time
     */
    @Override
    public String totalLoginTime() {
        return null;
    }

    /**
     * @return Max login time
     */
    @Override
    public String maxLoginTime() {
        return null;
    }

    /**
     * @return Min login time
     */
    @Override
    public String minLoginTime() {
        return null;
    }

    @Override
    public String userStats() {return "用户数据";
    }

    /**
     * @return How many logins ago
     */
    @Override
    public String howManyLoginsAgo() {
        return null;
    }

    /**
     * @return Logged in time
     */
    @Override
    public String loggedInTime() {
        return null;
    }

    /**
     * @return Past login durations
     */
    @Override
    public String pastLoginDurations() {
        return null;
    }

    /**
     * @return Last updated on login.
     */
    @Override
    public String updateInfo() {
        return null;
    }
}
