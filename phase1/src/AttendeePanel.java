import java.util.*;

public class AttendeePanel implements IController {
    private final UserManager userMan;
    private final MessageManager msgMan;
    private final RoomManager roomMan;
    private final InputFilter inputFilter;
    private final Scanner input = new Scanner(System.in);

    /**
     * Allows attendees to do attendee things.
     * @param userMan the user manager use case class
     * @param msgMan the user manager use case class
     * @param roomMan the user manager use case class
     */
    public AttendeePanel (UserManager userMan, MessageManager msgMan, RoomManager roomMan, InputFilter inputFilter) {
        this.userMan = userMan;
        this.msgMan = msgMan;
        this.roomMan = roomMan;
        this.inputFilter = inputFilter;
    }

    @Override
    public int run() {
        // common info used by the switch cases
        UUID currUserID = this.userMan.getCurrentUser();
        ArrayList<UUID> allUserIds = new ArrayList<>(this.userMan.getAttendeeUUIDs());
        allUserIds.addAll(this.userMan.getOrganizerUUIDs());
        allUserIds.addAll(this.userMan.getSpeakerUUIDs());

        System.out.println("This is the AttendeePanel, type 'Logout' to logout, 'Quit app' to quit app.");
        System.out.println("To get a list of more commands, type 'commands'");
        String decision = input.nextLine();

        // while the input isn't Logout or Quit app, keep it in AttendeePanel
        while (!(decision.equals("Logout") || decision.equals("Quit app"))){
            switch (decision) {
                case "commands":
                    displayCommands();
                    break;

                case "Message":
                    // if the user wants to go back, nextMove won't be null and we'll go back
                    // otherwise, stay in the AttendeePanel loop
                    Integer nextMove= Message(currUserID);
                    if (nextMove != null){return nextMove;}
                    break;

                case "View messages":
                    nextMove= viewMessages(allUserIds, currUserID);
                    if (nextMove != null){return nextMove;}
                    break;

                case "View all events":
                    viewAllEvents();
                    break;

                case "View signed up events":
                    viewSignedUpEvents(currUserID);
                    break;

                case "Join event":
                    nextMove= joinEvent(currUserID, this.userMan);
                    if (nextMove != null){return nextMove;}
                    break;

                case "Leave event":
                    nextMove= leaveEvent();
                    if (nextMove != null){return nextMove;}
                    break;

                default:
                    System.out.println("Invalid command, type 'commands' to see a list of valid commands");
            }
            decision = input.nextLine();
        }

        // Go back to LoginSystem or quits app if user types the command for either
        if (decision.equals("Logout")){return Definitions.LOGIN_SYSTEM;}
        return Definitions.QUIT_APP;
    }

    /**
     * Prints a list of commands that the user can input
     */
    private void displayCommands(){
        // displaus all possible commands
        String commands = "Logout|Message|View messages|View all events|View signed up events|Join event|Leave event";
        System.out.println("Here are the available commands: \n" + commands);
    }
    /**
     * @param currUserID The current user's userid
     * @return Returns an Integer if user wants to exit, null otherwise
     *
     * TODO: Check fixes for UM
     */
    private Integer Message(UUID currUserID){
        System.out.println("Enter contact name or type back to go back");
        String response = input.nextLine();
        if (response.equals("back")){return Definitions.ATTENDEE_PANEL;}
        //keep asking for input until the input is an existing username or 'back'
        while (userExists(response)) {
            response = input.nextLine();
            if (response.contains("back")){return Definitions.ATTENDEE_PANEL;}
        }

        System.out.println("Please type message:");
        response = input.nextLine();
        UUID recipient = this.userMan.getUserID(response);
        this.msgMan.sendMessage(this.userMan, currUserID, recipient, response);
        System.out.println("Message successfully sent");
        return null;
    }

    /**
     * Prints the messages from a specific user or all users who sent him at least one message.
     * @param allUserIds The UUIDs of all users
     * @param currUserID The UUID of the current user
     * @return Returns an Integer if user wants to exit, null otherwise
     *
     * TODO: Check fixes for UM
     */
    private Integer viewMessages(ArrayList<UUID> allUserIds, UUID currUserID){
        System.out.println("Who's messages would you like to see? Type 'all' for all messages.");
        String response = input.nextLine();
        // keep asking for input until it == 'all' or it == existing user name
        while (!response.equals("all") && userExists(response)) {
            response = input.nextLine();
            if (response.contains("back")){return Definitions.ATTENDEE_PANEL;}
        }
        // if want all messages, get a list of messages from each user and
        // write the sender name and message contents if user received at least 1 message from them
        if (response.equals("all")){
            StringBuilder allMsgs = new StringBuilder("Messages:\n");
            for (UUID uuid : allUserIds){
                List<String> msgsFromPerson = this.msgMan.
                        getMessageContentsFromUser(this.userMan, currUserID, uuid);
                if (msgsFromPerson.size()!=0){
                    String senderName = this.userMan.getUsername(uuid);
                    allMsgs.append(senderName).append(": ");
                    for (String msg : msgsFromPerson) {
                        allMsgs.append(msg).append(", ");
                    }
                    allMsgs.append("/n");
                }
            }
            System.out.println(allMsgs);
            return null;
        }
        // if user wants message from specific user
        UUID recipient = this.userMan.getUserID(response);
        System.out.println(this.msgMan.getMessageContentsFromUser(this.userMan, currUserID, recipient));
        return null;
    }

    /**
     * Prints all existing events
     */
    private void viewAllEvents(){
        System.out.println(roomMan.stringEventInfoAll());
    }

    /**
     * Prints all events the user signed up for
     * @param currUserID The current users UUID
     */
    private void viewSignedUpEvents(UUID currUserID){
        System.out.println(roomMan.stringEventInfoAttending(currUserID));
    }

    /**
     * Asks user for an event name and prints whether it successfully signed up.
     * @return Returns an integer if user wants to go back out of the command, null otherwise
     */
    private Integer joinEvent(UUID currUserID, UserManager userMan){
        // keeps asking user to input room number until valid number or user wants to cancel
        // if user wants to cancel, it'll return -1 and we will go back to attendee panel
        int inputRoomNum = this.inputFilter.inputRoom();
        if (inputRoomNum==-1){return Definitions.ATTENDEE_PANEL;}

        // keeps asking user to input event number until valid number or user wants to cancel
        // if user wants to cancel, it'll return -1 and we will go back to attendee panel
        int inputEventNum = this.inputFilter.inputEventNumber(inputRoomNum);
        if (inputEventNum==-1){return Definitions.ATTENDEE_PANEL;}

        //Signs user up to event in room
        this.roomMan.addEventAttendee(currUserID, inputRoomNum, inputEventNum, userMan);
        return null;
    }
    /**
     * Asks user for an event name and prints whether it successfully left it.
     * @return Returns an integer if user wants to go back out of the command, null otherwise
     */
    private Integer leaveEvent(){
        System.out.println("Enter event name or type 'back' to go back");
        String response = input.nextLine();
        if (response.equals("back")){return Definitions.ATTENDEE_PANEL;}
        //TODO: Call UserManager/RoomManager to remove user from event
        return null;
    }

    /**
     * Takes a username and returns true iff username is from an existing user
     * also prints a message telling user if username does not exist.
     * @param username the given username as a string
     * @return Whether the username is from an existing user
     */
    private boolean userExists(String username) {
        if (this.userMan.getUsernames().contains(username)) {
            return true;
        }
        System.out.println("The recipient does not exist, please try again or type 'back' to go back.");
        return false;
    }
}
