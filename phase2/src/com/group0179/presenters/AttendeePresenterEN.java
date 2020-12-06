package com.group0179.presenters;

public class AttendeePresenterEN implements IAttendeePresenter{
    /**
     * @return Attendee Panel label
     */
    @Override
    public String AttendeePanel() {
        return "Attendee Panel";
    }
    /**
     * @return Recipient does not exist.
     */
    @Override
    public String recipientDNE() {
        return "The recipient does not exist";
    }

    /**
     * @return message sent successfully.
     */
    @Override
    public String sentSuccess() {
        return "Message sent successfully";
    }

    /**
     * @return the word message.
     */
    @Override
    public String messageWord() {
        return "Message";
    }

    /**
     * @return user not found.
     */
    @Override
    public String userNotFound() {
        return "User not found";
    }

    /**
     * @return no events availiable.
     */
    @Override
    public String noEventsAvailiable() {
        return "no events availiable";
    }

    /**
     * @return joining or leaving
     */
    @Override
    public String joinOrLeave() {
        return "Please type either j for joining or l for leaving.";
    }

    /**
     * @return invalid room number
     */
    @Override
    public String invalidRoom() {
        return "Invalid room number";
    }

    /**
     * @return no event found
     */
    @Override
    public String noEventsFound() {
        return "No events found";
    }

    /**
     * @return successfully joined the event
     */
    @Override
    public String joinEventSuccess() {
        return "Successfully joined the event";
    }

    /**
     * @return successfully left the event.
     */
    @Override
    public String leaveEventSuccess() {
        return "Successfully left the event";
    }

    /**
     * @return unable to join the event
     */
    @Override
    public String joinEventFail() {
        return "Unable to join the event";
    }

    /**
     * @return unable to leave the event.
     */
    @Override
    public String leaveEventFail() {
        return "Unable to leave the event.";
    }

    /**
     * AttendeeScene strings.
     * @return Send message
     */
    @Override
    public String sendMessage() {
        return "Send Message";
    }

    /**
     * @return Enter username of who you would like to message.
     */
    @Override
    public String enterUsername() {
        return "Enter username of who you would like to message.";
    }

    /**
     * @return Enter message content
     */
    @Override
    public String enterMsgContent() {
        return "Enter message content";
    }

    /**
     * @return Send
     */
    @Override
    public String send() {
        return "Send";
    }

    /**
     * @return View messages
     */
    @Override
    public String viewMessages() {
        return "View Messages";
    }

    /**
     * @return Enter username of who's messages they want to see.
     */
    @Override
    public String usersToSee() {
        return "Enter username of who's messages you would like to see, enter 'all' for all.";
    }

    /**
     * @return Submit
     */
    @Override
    public String submit() {
        return "Submit";
    }

    /**
     * @return View all events
     */
    @Override
    public String viewAllEvents() {
        return "View All Events";
    }

    /**
     * @return View signed up events.
     */
    @Override
    public String viewAllSignedUpevents() {
        return "View All Signed Up Events";
    }

    /**
     * @return join/leave event button
     */
    @Override
    public String joinLeaveButtonText() {
        return "Join/Leave Event";
    }

    /**
     * @return Join or leave text label.
     */
    @Override
    public String joinOrLeaveLabel() {
        return "Would you like to join or leave the event? ";
    }

    /**
     * @return Enter room number of event.
     */
    @Override
    public String enterRoomNumberLabel() {
        return "Enter room number of event:";
    }

    /**
     * @return Enter name of event.
     */
    @Override
    public String enterEventNameLabel() {
        return "Enter name of event:";
    }

    /**
     * @return Attempt join.
     */
    @Override
    public String attemptJoinButton() {
        return "Attempt Join";
    }

    /**
     * @return Logout
     */
    @Override
    public String logoutButton() {
        return "Logout";
    }

    /**
     * @return Last login label
     */
    @Override
    public String lastLogin() {
        return "Last login: ";
    }

    /**
     * @return Average login time label
     */
    @Override
    public String avgLoginTime() {
        return "Average login time: ";
    }

    /**
     * @return Total login time
     */
    @Override
    public String totalLoginTime() {
        return "Total time logged in: ";
    }

    /**
     * @return Max and min login times
     */
    @Override
    public String maxLoginTime() {
        return "Longest time logged in: ";
    }

    /**
     * @return Min login time
     */
    @Override
    public String minLoginTime() {
        return "Shortest time logged in: ";
    }

    @Override
    public String userStats() {return "User Stats";
    }

    /**
     * @return How many logins ago
     */
    @Override
    public String howManyLoginsAgo() {
        return "How many logins ago";
    }

    /**
     * @return Logged in time
     */
    @Override
    public String loggedInTime() {
        return "Logged in time";
    }

    /**
     * @return Past login durations
     */
    @Override
    public String pastLoginDurations() {
        return "Past login durations";
    }

    /**
     * @return Last updated on login.
     */
    @Override
    public String updateInfo() {
        return "(updated on login)(all time in seconds)";
    }
}
