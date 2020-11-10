import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AttendeePanel implements IController {
    private final UserManager userMan;
    private final MessageManager msgMan;
    private final RoomManager roomMan;

    /**
     * Allows attendees to do attendee things.
     * @param userMan the user manager use case class
     * @param msgMan the user manager use case class
     * @param roomMan the user manager use case class
     */
    public AttendeePanel (UserManager userMan, MessageManager msgMan, RoomManager roomMan) {
        this.userMan = userMan;
        this.msgMan = msgMan;
        this.roomMan = roomMan;
    }

    @Override
    public int run() {
        // common info used by the switch cases
        UUID currUserID = this.userMan.getCurrentUser().getUserID();
        ArrayList<UUID> allUserIds = new ArrayList<>(this.userMan.getAttendeeUUIDs());
        allUserIds.addAll(this.userMan.getOrganizerUUIDs());
        allUserIds.addAll(this.userMan.getSpeakerUUIDs());

        System.out.println("This is the AttendeePanel, type 'Logout' to logout, 'Quit app' to quit app.");
        System.out.println("To get a list of more commands, type 'commands'");
        Scanner input = new Scanner(System.in);
        String decision = input.nextLine();

        while (!(decision.equals("Logout") || decision.equals("Quit app"))){
            switch (decision) {
                case "commands":
                    // displays possible commands
                    String commands = "Logout| Add contact|View contacts|Delete contact|" +
                            "|Message|View messages|View all events|View signed up events|" +
                            "Join event|Leave event";
                    System.out.println("Here are the available commands: \n" + commands);
                    break;

                case "Message":
                    System.out.println("Enter contact name or type back to go back");
                    String response = input.nextLine();
                    if (response.equals("back")){return Definitions.ATTENDEE_PANEL;}
                    //keep asking for input until the input is an existing username or 'back'
                    while (!userExists(response)) {
                        response = input.nextLine();
                        if (response.contains("back")){return Definitions.ATTENDEE_PANEL;}
                    }
                    System.out.println("Please type message:");
                    response = input.nextLine();
                    UUID recipient = this.userMan.getUser(response).getUserID();
                    this.msgMan.sendMessage(this.userMan, currUserID, recipient, response);
                    break;

                case "View messages":
                    System.out.println("Who's messages would you like to see? Type 'all' " +
                            "for all messages.");
                    response = input.nextLine();
                    // keep asking for input until it == 'all' or it == existing user name
                    while (!response.equals("all") && !userExists(response)) {
                        response = input.nextLine();
                        if (response.contains("back")){return Definitions.ATTENDEE_PANEL;}
                    }
                    // if want all messages
                    if (response.equals("all")){
                        StringBuilder allMsgs = new StringBuilder("Messages:\n");
                        for (UUID uuid : allUserIds){
                            // get a list of messages from each user
                            List<String> msgsFromPerson = this.msgMan.
                                    getMessageContentsFromUser(this.userMan, currUserID, uuid);
                            // write the sender name and message contents if recieved at least
                            // 1 message from them
                            if (msgsFromPerson.size()!=0){
                                String senderName = this.userMan.getUser(uuid).getUsername();
                                allMsgs.append(senderName).append(": ");
                                for (String msg : msgsFromPerson) {
                                    allMsgs.append(msg).append(", ");
                                }
                                allMsgs.append("/n");
                            };
                        }
                        System.out.println(allMsgs);
                        break;
                    }
                    // if want message from specific user name
                    recipient = this.userMan.getUser(response).getUserID();
                    System.out.println(this.msgMan.getMessageContentsFromUser(this.userMan,
                            currUserID, recipient));
                    break;

                case "View all events":
                    StringBuilder eventInfo = new StringBuilder("All events: \n");
                    for (Event event : this.roomMan.getEvents()){
                        eventInfo.append(event.getTitle()).append(" ").append(event.getStartTime())
                                .append("-").append(event.getEndTime()).append("\n");
                    }
                    System.out.println(eventInfo);
                    break;

                case "View signed up events":
                    StringBuilder myEventInfo = new StringBuilder("All events: \n");
                    for (Event event : this.roomMan.getEvents()){
                        if (this.roomMan.getEventAttendeeIDs(event).contains(currUserID)){
                            myEventInfo.append(event.getTitle()).append(" ").append(event.getStartTime())
                                    .append("-").append(event.getEndTime()).append("\n");
                        }
                    }
                    System.out.println(myEventInfo);
                    break;

                case "Join event":
                    System.out.println("Enter event name or type 'back' to go back");
                    response = input.nextLine();
                    if (response.equals("back")){return Definitions.ATTENDEE_PANEL;};
                    //TODO: Call UserManager/RoomManager sign user up for event
                    break;

                case "Leave event":
                    System.out.println("Enter event name or type 'back' to go back");
                    response = input.nextLine();
                    if (response.equals("back")){return Definitions.ATTENDEE_PANEL;};
                    //TODO: Call UserManager/RoomManager to remove user from event
                    break;
                default:
                    System.out.println("Invalid command, type 'commands' to see a list of valid" +
                            "commands");
            }
            decision = input.nextLine();
        }

        // logs out back to LoginSystem or quits app if user types it
        if (decision.equals("Logout")){return Definitions.LOGIN_SYSTEM;};
        return Definitions.QUIT_APP;
    };

    /**
     * Takes a username and returns true iff username is from an existing user
     * also prints message telling user the username does not exist.
     * @param username the given username as a string
     * @return whether the username is from an existing user
     */
    private boolean userExists(String username) {
        if (!this.userMan.getUsernames().contains(username)) {
            System.out.println("The recipient does not exist, please try again or " +
                    "type 'back' to go back.");
            return false;
        }
        return true;
    }
}
