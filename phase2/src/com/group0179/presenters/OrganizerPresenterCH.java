package com.group0179.presenters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.ArrayList;

public class OrganizerPresenterCH extends Presenter implements IOrganizerPresenter{
    private final UserManager um;
    private final RoomManager rm;
    private final MessageManager mm;

    public OrganizerPresenterCH(UserManager um, RoomManager rm, MessageManager mm) {
        this.um = um;
        this.rm = rm;
        this.mm = mm;
    }

    /**
     * @return a list of string representing speakers' names.
     */
    public ArrayList<String> getSpeakerNames() {
        return um.getSpeakerNames();
    }

    /**
     * @return a list of string representing the rooms' infomation.
     */
    public ArrayList<String> getRoomListArray() {
        ArrayList<String> rooms = new ArrayList<>();
        for (int n = 1; n <= rm.getNumRooms(); n++) {
            rooms.add("房间 " + n + " (容量: " + rm.getRoomCapacity(n - 1) + ")");
        } return rooms;
    }

    private int getRoomNumber(String roomsListSelection) {
        return Integer.parseInt(roomsListSelection.split(" ")[1]) - 1;
    }

    /**
     * @param roomsListSelection a string representing the selection of room
     * @return return a list of string representing the events' information in the room.
     */
    public ArrayList<String> getEvents(String roomsListSelection) {
        return rm.getEventsOfRoom(getRoomNumber(roomsListSelection));
    }

    /**
     * @return a string representing "room/event".
     */
    public String reMenuButtonText() {
        return "房间/活动";
    }

    /**
     * @return a string representing "Speaker".
     */
    public String speakerManagementButtonText() {
        return "演讲者";
    }

    /**
     * @return a string representing "logout".
     */
    public String logoutButtonText() {
        return "退出";
    }

    /**
     * @return a string representing "create speaker"
     */
    public String createSpeakerFormButtonText() {
        return "创建演讲者";
    }

    /**
     * @return a string representing "list of speakers"
     */
    public String speakerManagerButtonText() {
        return "演讲者列表";
    }

    /**
     * @return a string representing "create"
     */
    public String createButtonText() {
        return "创造";
    }

    /**
     * @return a string representing "room/event list".
     */
    public String reManagerButtonText() {
        return "房间/活动列表";
    }

    /**
     * @return a string representing "create room".
     */
    public String createRoomFormButtonText() {
        return "创建房间";
    }

    /**
     * @return a string representing "view events".
     */
    public String viewEventListButtonText() {
        return "查看活动";
    }

    /**
     * @return a string representing "create event".
     */
    public String createEventFormButtonText() {
        return "创建活动";
    }

    /**
     * @return a string representing "confirm".
     */
    public String confirmButtonText() {
        return "确认";
    }

    /**
     * @return a string representing "remove event".
     */
    public String removeEventButtonText() {
        return "移除活动";
    }

    /**
     * @return a string representing "reschedule event"
     */
    public String rescheduleEventFormButtonText() {
        return "重新安排活动";
    }

    /**
     * @return a string representing "reschedule".
     */
    public String rescheduleEventButtonText() {
        return "重新安排";
    }

    /**
     * @return a string representing "organizer panel:room and event list".
     */
    public String reMenuTitle() {
        return "组织者面板: 房间和活动列表";
    }

    /**
     * @return a string representing "organizer panel: speaker".
     */
    public String speakerManagementMenuTitle() {
        return "组织者面板: 演讲者";
    }

    /**
     * @return a string representing "organizer panel: create speaker".
     */
    public String createSpeakerFormTitle() {
        return "组织者面板: 创建演讲者";
    }

    /**
     * @return a string representing "organizer panel: manage speaker".
     */
    public String speakerManagerTitle() {
        return "组织者面板: 管理演讲者";
    }

    /**
     * @return a string representing "organizer panel: Main Menu".
     */
    public String mainSceneTitle() {
        return "组织者面板: 主菜单";
    }

    /**
     * @return a string representing "organizer panel: manage room & event".
     */
    public String reManagerTitle() {
        return "组织者面板: 房间 & 活动管理";
    }

    /**
     * @return a string representing "organizer panel: create room".
     */
    public String createRoomFormTitle() {
        return "组织者面板: 创建房间";
    }

    /**
     * @return a string representing "organizer panel: manage event".
     */
    public String viewEventListTitle() {
        return "组织者面板: 管理活动";
    }

    /**
     * @return a string representing "organizer panel: create event".
     */
    public String createEventFormTitle() {
        return "组织者面板: 创建活动";
    }

    /**
     * @return a string representing "organizer panel: reschedule event".
     */
    public String rescheduleEventFormTitle() {
        return "组织者面板: 重新安排活动";
    }

    /**
     * @return a string representing "username".
     */
    public String usernamePrompt() {
        return "用户名:";
    }

    /**
     * @return a string representing "room capacity".
     */
    public String roomCapacityPrompt() {
        return "房间容量:";
    }

    /**
     * @return a string representing "name of event".
     */
    public String createEventTitlePrompt() {
        return "活动名称:";
    }

    /**
     * @return a string representing "event speaker".
     */
    public String createEventSpeakerPrompt() {
        return "活动演讲者:";
    }

    /**
     * @return a string representing "date [yyyy/mm/dd]:".
     */
    public String eventDatePrompt() {
        return "日期 [yyyy/mm/dd]:";
    }

    /**
     * @return End date [yyyy/mm/dd]:
     */
    @Override
    public String eventEndDatePrompt() {
        return null;
    }

    /**
     * @return End time [hh:mm]:
     */
    @Override
    public String eventEndTimePrompt() {
        return null;
    }

    /**
     * @return a string representing "time [hh:mm]".
     */
    public String eventTimePrompt() {
        return "时间 [hh:mm]:";
    }

    /**
     * @return a string representing "event capacity".
     */
    public String createEventCapacityPrompt() {
        return "活动容量:";
    }

    /**
     * @return a string representing whether the speaker is created.
     */
    public String createSpeakerStatus(boolean status) {
        if (status) {
            return "演讲者创建成功。";
        } return "用户名已存在/无效.";
    }

    /**
     * @return a string representing whether the room is created.
     */
    public String createRoomStatus(boolean status) {
        if (status) {
            return "房间 " + rm.getNumRooms() + " 创建成功.";
        } return "无效的房间容量。";
    }

    /**
     * @return a string representing "invalid event name".
     */
    public String createEventTitleStatus() {
        return "无效的活动名称";
    }

    /**
     * @return a string representing "speaker doesn't exist".
     */
    public String createEventSpeakerStatus() {
        return "演讲者不存在";
    }

    /**
     * @return a string representing "invalid date or time: may be conflict".
     */
    public String createEventDateTimeStatus() {
        return "无效的日期或时间 - 可能有冲突.";
    }

    /**
     * @return a string representing "invalid room capacity".
     */
    public String createEventCapacityStatus() {
        return "无效的活动容量.";
    }

    /**
     * @return a string representing "the event created successfully".
     */
    public String createEventStatus() {
        return "活动创建成功";
    }

    /**
     * @return a string representing whether the event is scheduled.
     */
    public String rescheduleEventStatus(boolean status) {
        if (status) {
            return "活动已重新安排";
        } else {
            return "无法重新安排活动 - 可能有冲突";
        }
    }

    /**
     * @return a string representing "message".
     */
    public String messageMenuButtonText() {
        return "消息";
    }

    /**
     * @return a string representing "custom recipient".
     */
    public String messageCustomRecipientsFormButtonText() {
        return "定制接收者";
    }

    /**
     * @return a string representing "all attendees".
     */
    public String messageAttendeesFormButtonText() {
        return "所有参与者";
    }

    /**
     * @return a string representing "all speakers".
     */
    public String messageSpeakersFormButtonText() {
        return "所有演讲者";
    }

    /**
     * @return a string representing "recipient".
     */
    public String recipientsPrompt() {
        return "接受者:";
    }

    /**
     * @return a string representing "message".
     */
    public String messagePrompt() {
        return "消息:";
    }

    /**
     * @return a string representing "send".
     */
    public String messageSendButtonText() {
        return "发送";
    }

    /**
     * @return a string representing "recipient doesn't exist".
     */
    public String messageRecipientExistence() {
        return "接受者不存在。";
    }

    /**
     * @return a string representing "you can only send message to attendee/speaker".
     */
    public String messageRecipientValidity() {
        return "你只能给参与者/演讲者发消息。";
    }

    /**
     * @return a string representing "message sent successfully".
     */
    public String messageRecipientStatus() {
        return "消息成功发送。";
    }

    /**
     * @return a string representing "vip event".
     */
    @Override
    public String isVipOnlyPrompt() {
        return "vip活动";
    }

    /**
     * @return Statistics
     */
    @Override
    public String staistics() {
        return "统计";
    }

    /**
     * @return Zuckerberg powers
     */
    @Override
    public String zuckerbergPowers() {
        return "Zuckerberg powers";
    }

    /**
     * @return "Accounts created on date:"
     */
    @Override
    public String accountsCreatedOnDate() {
        return "账户在此日期创建";
    }

    /**
     * @return Enter attendee usernames
     */
    @Override
    public String enterAttendeeUsernames() {
        return "输入参与者用户名";
    }

    /**
     * @return Get their info
     */
    @Override
    public String getTheirInfo() {
        return "得到他们的信息";
    }

    /**
     * @return Average login time
     */
    @Override
    public String averageLoginTime() {
        return "平均";
    }

    /**
     * @return Last login:
     */
    @Override
    public String lastLogin() {
        return "上次登录";
    }

    /**
     * @return User not found
     */
    @Override
    public String userNotFound() {
        return "未找到用户";
    }

    /**
     * @return Attendee Account
     */
    @Override
    public String AttendeeAccountPrompt() {
        return "参与者账户";
    }

    /**
     * @return Organizer Account
     */
    @Override
    public String OrganizerAccountPrompt() {
        return "组织者账户";
    }

    /**
     * @return Speaker Account
     */
    @Override
    public String SpeakerAccountPrompt() {
        return "演讲者账户";
    }

    /**
     * @return Vip Attendee Account
     */
    @Override
    public String VipAttendeeAccountPrompt() {
        return "Vip 参与者账户";
    }

    /**
     * @return Create Account
     */
    public String createAccountButtonText(){return "创建账户";}

    /**
     * @param username the username of user
     * @return the user name is taken
     */
    @Override
    public String usernameTaken(String username) {
        return "用户名"+username+"已被使用";
    }

    /**
     * @return account created successfully.
     */
    @Override
    public String accountCreatedSuccess() {
        return "账户成功创建";
    }
}


