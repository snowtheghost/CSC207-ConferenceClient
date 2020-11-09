import java.util.*;

/**
 * @author Justin Chan, Tanuj Devjani, Zachariah Vincze
 */
public class OrganizerPanel implements IController {
    private boolean quitting = false;
    private final Scanner sc = new Scanner(System.in);

    public void createEvent(){
        System.out.print("Room: ");
        String room = sc.nextLine();
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
    public void run() {
        System.out.print("This is the OrganizerPanel");
        this.quitting = true;
    }

    @Override
    public boolean isQuitting() {
        return this.quitting;
    }

    @Override
    public boolean isChangingState() {
        return false;
    }

    @Override
    public int getNewState() {
        return 0;
    }

}
