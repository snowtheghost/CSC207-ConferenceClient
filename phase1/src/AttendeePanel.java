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
        System.out.println("This is the AttendeePanel");
        System.out.println("To get a list of commands, type 'commands'");
        Scanner input = new Scanner(System.in);
        String decision = input.nextLine();
        if (decision.equals("commands")){
            String commands = "Add contact (username)|View contacts (username)|Delete contact (username)|" +
                    "|Message (username), (message content)| View Messages|View All Events|View Signed Up Events|" +
                    "Join Event (event name)|Leave Event(event name)";
            System.out.println(commands);
        } else if (decision.contains("Add contact")) {
            System.out.println("asdas");
        }

        return Definitions.QUIT_APP;
    }
}
