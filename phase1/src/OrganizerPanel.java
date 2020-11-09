/**
 * @author Justin Chan, Tanuj Devjani, Zachariah Vincze
 */
public class OrganizerPanel implements IController {
    private boolean quitting = false;

    public boolean createEvent(){
        return true;
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
