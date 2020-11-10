import java.util.*;

/**
 * @author Justin Chan, Tanuj Devjani
 */
public class OrganizerPanel implements IController {
    private final boolean quitting = false;
    private final Scanner sc = new Scanner(System.in);
    private final UserManager um;
    private final RoomManager rm;

    /**
     * @param rm the RoomManager
     * Last modified: Justin Chan
     */
    OrganizerPanel(UserManager um, RoomManager rm) {
        this.um = um;
        this.rm = rm;
    }

    public void createEvent(){
        System.out.print("Room: ");
        int room = Integer.parseInt(sc.nextLine());
        System.out.print("Event Title: ");
        String title = sc.nextLine();
        System.out.print("Speaker username: ");
        String speakerName = sc.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(sc.nextLine());
        System.out.print("Month: ");
        int month = Integer.parseInt(sc.nextLine()) - 1;
        System.out.print("Day: ");
        int day = Integer.parseInt(sc.nextLine());
        System.out.print("Start hour: ");
        int hour = Integer.parseInt(sc.nextLine());

        // Assume checked
        Speaker speaker = (Speaker) um.getUsernameToUser().get(speakerName);
        rm.newEvent(title, speaker, new GregorianCalendar(year, month, day, hour, 0, 0), new GregorianCalendar(year, month, day, hour + 1, 0, 0), rm.getRooms().get(0));
    }

    public boolean deleteEvent(){
        return true;
    }
    public boolean createGroupMessage(){
        return true;
    }
    public void viewGroupAttendees(){
        System.out.println("The list of attendees in the group are ");
    }

    public void createSpeaker(String name){
        System.out.println("Speaker named " + name + "created successfully.");
    }

    @Override
    public int run() {
        System.out.print("This is the OrganizerPanel");
        return Definitions.QUIT_APP;
    }

}
