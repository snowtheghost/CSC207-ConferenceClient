import java.util.Scanner;

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
        System.out.println("This is the AttendeePanel, type 'Logout' to logout, 'Quit app' to quit app.");
        System.out.println("To get a list of more commands, type 'commands'");
        Scanner input = new Scanner(System.in);
        String decision = input.nextLine();
        while (!(decision.equals("Logout") || decision.equals("Quit app"))){
            switch (decision) {
                case "commands":
                    String commands = "Logout| Add contact|View contacts|Delete contact|" +
                            "|Message| View messages|View all events|View signed up events|" +
                            "Join event|Leave event";
                    System.out.println("Here are the available commands: \n" + commands);
                    break;

                case "Add contact":
                    System.out.println("Enter contact name or type back to go back");
                    String response = input.nextLine();
                    if (response.equals("back")){return Definitions.ATTENDEE_PANEL;};
                    //TODO: Call MessageManager to add contact with contact name.
                    break;

                case "View contacts":
                    //TODO: Call UserManager to get contacts
                    System.out.println("Call UserManager to get contacts");
                    break;

                case "Delete contact":
                    System.out.println("Enter contact name or type back to go back");
                    response = input.nextLine();
                    if (response.equals("back")){return Definitions.ATTENDEE_PANEL;};
                    //TODO: Call UserManager to delete contact with contact name
                    break;

                case "Message":
                    System.out.println("Enter contact name or type back to go back");
                    response = input.nextLine();
                    if (response.equals("back")){return Definitions.ATTENDEE_PANEL;};
                    //TODO: Call MessageManager to send message to contact
                    break;

                case "View messages":
                    //TODO: Call UserManager to get all of users messages
                    break;

                case "View all events":
                    //TODO: Call RoomManager to see all events
                    break;

                case "View signed up events":
                    //TODO: Call UserManager to see all signed up events
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
            }
            decision = input.nextLine();
        }
        if (decision.equals("Logout")){return Definitions.LOGIN_SYSTEM;};
        return Definitions.QUIT_APP;
    };
}
