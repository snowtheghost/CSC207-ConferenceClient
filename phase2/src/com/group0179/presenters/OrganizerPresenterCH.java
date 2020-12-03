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

    public int getRoomNumber(String roomsListSelection) {
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
        return "Organizer Panel: Create Room";
    }

    public String viewEventListTitle() {
        return "Organizer Panel: Manage Events";
    }

    public String createEventFormTitle() {
        return "Organizer Panel: Create Event";
    }

    public String rescheduleEventFormTitle() {
        return "Organizer Panel: Reschedule Event";
    }

    public String usernamePrompt() {
        return "Username:";
    }

    public String roomCapacityPrompt() {
        return "Room Capacity:";
    }

    public String createEventTitlePrompt() {
        return "Event Title:";
    }

    public String createEventSpeakerPrompt() {
        return "Event Speaker:";
    }

    public String eventDatePrompt() {
        return "Date [yyyy/mm/dd]:";
    }

    public String eventTimePrompt() {
        return "Time [hh:mm]:";
    }

    public String createEventCapacityPrompt() {
        return "Event Capacity:";
    }

    public String createSpeakerStatus(boolean status) {
        if (status) {
            return "Speaker created successfully.";
        } return "Username exists/invalid.";
    }

    public String createRoomStatus(boolean status) {
        if (status) {
            return "Room " + rm.getNumRooms() + " created successfully.";
        } return "Invalid room capacity.";
    }

    public String createEventTitleStatus() {
        return "Invalid event title.";
    }

    public String createEventSpeakerStatus() {
        return "Speaker does not exist.";
    }

    public String createEventDateTimeStatus() {
        return "Invalid date or time - may be in conflict.";
    }

    public String createEventCapacityStatus() {
        return "Invalid event capacity.";
    }

    public String createEventStatus() {
        return "Event created successfully.";
    }

    public String rescheduleEventStatus(boolean status) {
        if (status) {
            return "Rescheduled event successfully.";
        } else {
            return "Could not reschedule event - may be in conflict";
        }
    }

    public String messageMenuButtonText() {
        return "Messages";
    }

    public String messageCustomRecipientsFormButtonText() {
        return "Custom Recipients";
    }

    public String messageAttendeesFormButtonText() {
        return "All Attendees";
    }

    public String messageSpeakersFormButtonText() {
        return "All Speakers";
    }

    public String recipientsPrompt() {
        return "Recipient:";
    }

    public String messagePrompt() {
        return "Message:";
    }

    public String messageSendButtonText() {
        return "Send";
    }

    public String messageRecipientExistence() {
        return "Recipient does not exist.";
    }

    public String messageRecipientValidity() {
        return "You can only send messages to speakers/attendees.";
    }

    public String messageRecipientStatus() {
        return "Message sent successfully.";
    }
}


