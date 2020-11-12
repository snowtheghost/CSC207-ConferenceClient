import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AttendeePresenter {
    private final UserManager userMan;
    private final MessageManager messageMan;

    /**
     * Presenter controller for AttendeePanel
     * @param um The user manager class it uses to get user names
     * @param mm The message manager class it uses to get the message content
     */
    AttendeePresenter(UserManager um, MessageManager mm) {
        this.userMan = um;
        this.messageMan = mm;
    }

    /**
     * Welcomes to user into the Attendee control panel and tells them how to access the commands.
     */
    public void displayWelcomeMsg() {
        System.out.println("This is the AttendeePanel, type 'Logout' to logout, 'Quit app' to quit app.");
        System.out.println("To get a list of more commands, type 'commands'");
    }

    /**
     * Displays a list of attendee commands
     */
    public void displayCommands(){
        String commands = "Logout|Message|View messages|View all events|View signed up events|Join event|Leave event";
        System.out.println("Here are the available commands: \n" + commands);
    }

    /**
     * Displays how the user can begin messaging someone or go back.
     */
    public void dmPrompt(){
        System.out.println("Enter contact name or type back to go back");
    }

    /**
     * Tells the user to type a message content.
     */
    public void typeMsgPrompt(){
        System.out.println("Please type message:");
    }

    /**
     * Tells the user their message has been successfully sent
     */
    public void msgSentPrompt(){
        System.out.println("Message successfully sent");
    }

    /**
     * Asks the user who's message they would like to see, or if they would like to see all messages.
     */
    public void whosMsgPrompt(){
        System.out.println("Who's messages would you like to see? Type 'all' for all messages.");
    }

    /**
     * Displays the sender and message content of all the messages a user has recieved.
     * @param allUserIds The UUID of all users in the system.
     * @param currUserID The UUID of the current user.
     */
    public void displayMsgs(ArrayList<UUID> allUserIds, UUID currUserID){
        StringBuilder allMsgs = new StringBuilder("Messages:\n");
        for (UUID uuid : allUserIds){
            List<String> msgsFromPerson = this.messageMan.getMessageContentsFromUser(this.userMan, currUserID, uuid);
            if (msgsFromPerson.size()!=0){
                String senderName = this.userMan.getUsername(uuid);
                allMsgs.append(senderName).append(": ");
                for (String msg : msgsFromPerson) {
                    allMsgs.append(msg).append(", ");
                }
                allMsgs.append("\n");
            }
        }
        System.out.println(allMsgs);
    }

    /**
     * Displays all the messages from a specific sender.
     * @param content The sender's name, followed by everything they sent.
     */
    public void displaySpecificUserMsg(List<String> content){
        System.out.println(content);
    }

    /**
     * Displays all the events and their information.
     * @param eventInfo All the event infomation.
     */
    public void displayAllEvents(String eventInfo){
        System.out.println(eventInfo);
    }

    /**
     * Displays all the events that the user is attending and its information.
     * @param eventInfo The information of each event the user is attending.
     */
    public void displayAttendingEvents(String eventInfo){
        System.out.println(eventInfo);
    }

    /**
     * Tells the user to enter the relevant information for either joining or leave the an event.
     * @param joinOrLeave Whether the user is joining or leave, must be either "joining" or "leaving".
     * @param eventOrRoom Must be either "event" or "room".
     */
    public void joinLeaveEventOrRoomPrompt(String joinOrLeave, String eventOrRoom){
        if (joinOrLeave.equals("joining")) {
            System.out.println("Enter the" + eventOrRoom + "number you wish to join or type 'back' to go back");
        } else if (joinOrLeave.equals("leaving")) {
            System.out.println("Enter the " + eventOrRoom + " number you wish to join or type 'back' to go back");
        }
    }

    /**
     * Tells the user whether they were successful in joining our leaving a room.
     * @param joinOrLeave Whether they were "joining" or "leaving", must be in that format.
     */
    public void displayJoinLeaveSuccess(String joinOrLeave){
        if (joinOrLeave.equals("joining")){
            System.out.println("You've successfully joined the event");
        }
        else if (joinOrLeave.equals("leaving")){
            System.out.println("You've successfully left the event");
        }
    }
}