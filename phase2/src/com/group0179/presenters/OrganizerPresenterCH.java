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

    public ArrayList<String> getSpeakerNames() {
        return um.getSpeakerNames();
    }

    public ArrayList<String> getRoomListArray() {
        ArrayList<String> rooms = new ArrayList<>();
        for (int n = 1; n <= rm.getNumRooms(); n++) {
            rooms.add("房间 " + n + " (容量: " + rm.getRoomCapacity(n - 1) + ")");
        } return rooms;
    }

    private int getRoomNumber(String roomsListSelection) {
        return Integer.parseInt(roomsListSelection.split(" ")[1]) - 1;
    }

    public ArrayList<String> getEvents(String roomsListSelection) {
        return rm.getEventsOfRoom(getRoomNumber(roomsListSelection));
    }

    public String reMenuButtonText() {
        return "房间/活动";
    }

    public String speakerManagementButtonText() {
        return "演讲者";
    }

    public String logoutButtonText() {
        return "退出";
    }

    public String createSpeakerFormButtonText() {
        return "创建演讲者";
    }

    public String speakerManagerButtonText() {
        return "演讲者列表";
    }

    public String createButtonText() {
        return "创造";
    }

    public String reManagerButtonText() {
        return "房间/活动列表";
    }

    public String createRoomFormButtonText() {
        return "创建房间";
    }

    public String viewEventListButtonText() {
        return "查看活动";
    }

    public String createEventFormButtonText() {
        return "创建活动";
    }

    public String confirmButtonText() {
        return "确认";
    }

    public String removeEventButtonText() {
        return "移除活动";
    }

    public String rescheduleEventFormButtonText() {
        return "重新安排活动";
    }

    public String rescheduleEventButtonText() {
        return "重新安排";
    }

    public String reMenuTitle() {
        return "组织者面板: 房间和活动列表";
    }

    public String speakerManagementMenuTitle() {
        return "组织者面板: 演讲者";
    }

    public String createSpeakerFormTitle() {
        return "组织者面板: 创建演讲者";
    }

    public String speakerManagerTitle() {
        return "组织者面板: 管理演讲者";
    }

    public String mainSceneTitle() {
        return "组织者面板: 主菜单";
    }

    public String reManagerTitle() {
        return "组织者面板: 房间 & 活动管理";
    }

    public String createRoomFormTitle() {
        return "组织者面板: 创建房间";
    }

    public String viewEventListTitle() {
        return "组织者面板: 管理活动";
    }

    public String createEventFormTitle() {
        return "组织者面板: 创建活动";
    }

    public String rescheduleEventFormTitle() {
        return "组织者面板: 重新安排活动";
    }

    public String usernamePrompt() {
        return "用户名:";
    }

    public String roomCapacityPrompt() {
        return "房间容量:";
    }

    public String createEventTitlePrompt() {
        return "活动名称:";
    }

    public String createEventSpeakerPrompt() {
        return "活动演讲者:";
    }

    public String eventDatePrompt() {
        return "日期 [yyyy/mm/dd]:";
    }

    public String eventTimePrompt() {
        return "时间 [hh:mm]:";
    }

    public String createEventCapacityPrompt() {
        return "活动容量:";
    }

    public String createSpeakerStatus(boolean status) {
        if (status) {
            return "演讲者创建成功。";
        } return "用户名已存在/无效.";
    }

    public String createRoomStatus(boolean status) {
        if (status) {
            return "房间 " + rm.getNumRooms() + " 创建成功.";
        } return "无效的房间容量。";
    }

    public String createEventTitleStatus() {
        return "无效的活动名称";
    }

    public String createEventSpeakerStatus() {
        return "演讲者不存在";
    }

    public String createEventDateTimeStatus() {
        return "无效的日期或时间 - 可能有冲突.";
    }

    public String createEventCapacityStatus() {
        return "无效的活动容量.";
    }

    public String createEventStatus() {
        return "活动创建成功";
    }

    public String rescheduleEventStatus(boolean status) {
        if (status) {
            return "活动已重新安排";
        } else {
            return "无法重新安排活动 - 可能有冲突";
        }
    }

    public String messageMenuButtonText() {
        return "消息";
    }

    public String messageCustomRecipientsFormButtonText() {
        return "定制接收者";
    }

    public String messageAttendeesFormButtonText() {
        return "所有参与者";
    }

    public String messageSpeakersFormButtonText() {
        return "所有演讲者";
    }

    public String recipientsPrompt() {
        return "接受者:";
    }

    public String messagePrompt() {
        return "消息:";
    }

    public String messageSendButtonText() {
        return "发送";
    }

    public String messageRecipientExistence() {
        return "接受者不存在。";
    }

    public String messageRecipientValidity() {
        return "你只能给参与者/演讲者发消息。";
    }

    public String messageRecipientStatus() {
        return "消息成功发送。";
    }
}


