public class SpeakerPresenter {
    private final UserManager userMan;
    private final RoomManager roomMan;

    SpeakerPresenter(UserManager userMan, RoomManager roomMan) {
        this.userMan = userMan;
        this.roomMan = roomMan;
    }

    public void welcomePrompt() {
        System.out.println("This is the OrganizerPanel. Type \"help\" to see a list of commands.");
    }

    public void commandPrompt() {
        System.out.print("command: ");
    }

    public void commandHelp() {
        System.out.println("createspeaker - Create a new Speaker\n" +
                "createroom - Create a new Room\n" + "createevent - Create a new Event\n" + "rescheduleevent - Reschedules an existing event\n"+"cancelevent - Remove an existing Event\n" +
                "viewspeakers - See available Speakers\n" + "viewrooms - See available Rooms\n" + "viewevents - See available events in specified room\n" + "quit - Log out as Organizer");
    }

    public void commandNotRecognized(String command) {
        System.out.println("Command not recognized: " + command);
    }

    public void viewAllEvents(String stringEventInfoAll) { System.out.println(stringEventInfoAll); }
}
