package com.group0179.presenters;

public interface IAttendeePresenter {
    /**
     * AttendePresenter strings.
     * @return Recipient does not exist.
     */
    String recipientDNE();

    /**
     * @return message sent successfully.
     */
    String sentSuccess();

    /**
     * @return the word message.
     */
    String messageWord();

    /**
     * @return user not found.
     */
    String userNotFound();

    /**
     * @return no events availiable.
     */
    String noEventsAvailiable();

    /**
     * @return joining or leaving
     */
    String joinOrLeave();

    /**
     * @return invalid room number
     */
    String invalidRoom();

    /**
     * @return no event found
     */
    String noEventsFound();

    /**
     * @return successfully joined the event
     */
    String joinEventSuccess();

    /**
     * @return successfully left the event.
     */
    String leaveEventSuccess();

    /**
     * @return unable to join the event
     */
    String joinEventFail();

    /**
     * @return unable to leave the event.
     */
    String leaveEventFail();

    /**
     * AttendeeScene strings.
     * @return Send message
     */
    String sendMessage();

    /**
     * @return Enter username of who you would like to message.
     */
    String enterUsername();

    /**
     * @return Enter message content
     */
    String enterMsgContent();

    /**
     * @return Send
     */
    String send();

    /**
     * @return View messages
     */
    String viewMessages();

    /**
     * @return Enter username of who's messages they want to see.
     */
    String usersToSee();

    /**
     * @return Submit
     */
    String submit();

    /**
     * @return View all events
     */
    String viewAllEvents();

    /**
     * @return View signed up events.
     */
    String viewAllSignedUpevents();

    /**
     * @return join/leave event button
     */
    String joinLeaveButtonText();

    /**
     * @return Join or leave text label.
     */
    String joinOrLeaveLabel();

    /**
     * @return Enter room number of event.
     */
    String enterRoomNumberLabel();

    /**
     * @return Enter name of event.
     */
    String enterEventNameLabel();

    /**
     * @return Attempt join.
     */
    String attemptJoinButton();

    /**
     * @return Logout
     */
    String logoutButton();




}
