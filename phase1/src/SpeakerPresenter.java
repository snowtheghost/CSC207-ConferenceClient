import java.util.UUID;

public class SpeakerPresenter {
    private final UserManager userMan;
    private final RoomManager roomMan;

    SpeakerPresenter(UserManager userMan, RoomManager roomMan) {
        this.userMan = userMan;
        this.roomMan = roomMan;
    }

    public void welcomePrompt() {
        System.out.println("This is the SpeakerPanel. Type \"help\" to see a list of commands.");
    }

    public void commandPrompt() {
        System.out.print("command: ");
    }

    public void commandHelp() {
        System.out.println("view events - view all scheduled events\n" +
                "view speaking events - view all events you are speaking at\n" +
                "send message to event attendees - send a message to all attendees of an event you are speaking at\n" +
                "logout - return to the main login screen\n" +
                "quit - Log out as Speaker ");
    }

    public void commandNotRecognized(String command) {
        System.out.println("Command not recognized: " + command);
    }

    public void viewAllEvents() { System.out.println(roomMan.stringEventInfoAll()); }

    public void viewSpeakingEvents(UUID speakerID) {
        System.out.println(roomMan.stringEventsOfSpeaker(userMan, userMan.getUsername(speakerID)));
    }

    public void sendAllAttendeesIntro(UUID speakerID) {
        //System.out.println("You are speaking at the following events:");
        System.out.println(roomMan.stringEventsOfSpeaker(userMan, userMan.getUsername(speakerID)));
        int numEvents = userMan.getSpeakerEventIDs(speakerID).size();
        if (numEvents == 1){
            System.out.println("Please enter number 1 to select an event or enter q to return to the menu ");
        }
        else{
            System.out.println("Please enter number between 1 and " + numEvents + " or enter q to return to the menu ");
        }

    }

    public void messagePrompt(){ System.out.println("Enter your message:"); }

    public void messageSuccess(UUID id){ System.out.println("Success! Your message was sent! Message ID: "+ id); }

    public void errorNoSpeakingEvents(){ System.out.println("Whoops, it looks like you aren't speaking at any events"); }

    public void errorInvalidInput(String command){ System.out.println("Whoops, it looks like '" + command + "' isn't a valid input"); }

    public void errorInvalidInput(int command){ System.out.println("Whoops, it looks like '" + command + "' isn't a valid input"); }

    public void errorNoAttendees(){ System.out.println("Whoops, it looks like there's nobody signed up for that event!"); }

    public void errorGeneral(){ System.out.println("Whoops, it looks like something went wrong!"); }

}
