/**
 * @author Justin Chan
 */

public class OrganizerPresenter {
    private final UserManager um;
    private final RoomManager rm;

    OrganizerPresenter(UserManager um, RoomManager rm) {
        this.um = um;
        this.rm = rm;
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
                "viewspeakers - See available Speakers\n" + "viewrooms - See available Rooms\n" + "viewevents - See available events in specified room\n" +
                "sendmessage - Send a message to an Attendee or Speaker\nmessageallattendees - Send a message to all Attendees\nmessageallspeakers" +
                " - Send a message to all Speakers\n" +
                "logout - Return to Login screen\n" + "quit - Quit program");
    }

    public void commandNotRecognized(String command) {
        System.out.println("Command not recognized: " + command);
    }

    public void rescheduleEventWelcome() {
        System.out.println("Rescheduling an event. To cancel the process, enter \"-1\" in any input.");
    }
    public void rescheduleEventStatus(boolean status) {
        if (status) {
            System.out.println("Event has been successfully rescheduled");
        } else {
            System.out.println("The event was unable to be rescheduled: the Speaker may be speaking in another room, or the Room may be in use at this time");
        }
    }

    public void cancelEventWelcome() {
        System.out.println("Removing an event. To cancel the process, enter \"-1\" in any input.");
    }

    public void cancelEventStatus() {
        System.out.println("The event has been removed.");
    }

    public void createSpeakerWelcome() {
        System.out.println("Creating a Speaker. To cancel the process, enter \"-1\" in any input.");
    }

    public void createSpeakerStatus(String speakerName) {
        System.out.println("Speaker \"" + speakerName + "\" created successfully.");
    }

    public void createRoomStatus(int numRooms) {
        System.out.print("Creating a new room: Room " + numRooms + "\n");
    }

    public void createEventWelcome() {
        System.out.println("Creating an event. To cancel the process, enter \"-1\" in any input.");
    }

    public void createEventTitlePrompt() {
        System.out.print("Event Title: ");
    }

    public void createEventStatus(boolean status, String event) {
        if (status) {
            System.out.println("Event scheduled: " + event);
        } else {
            System.out.println("The event was unable to be created: the Speaker may be speaking in another room, or the Room may be in use at this time");
        }
    }

    public void printSpeakerEvents(String speakerName) {
        rm.stringEventsOfSpeaker(um, speakerName);
    }

    public void printRoomEvents(int roomNumber) {
        System.out.print(rm.stringEventsOfRoom(roomNumber));
    }

    public void printAllEvents() {
        System.out.print(rm.stringEventInfoAll());
    }

    public void printAvailableSpeakers() {
        System.out.print(um.stringAvailableSpeakers());
    }

    public void cancelNotification() {
        System.out.println("Process cancelled.");
    }

    public void printAvailableRooms() {
        System.out.print("Rooms available: ");
        for (int room = 1; room <= rm.getNumRooms(); room++) {
            System.out.print("Room " + room);
            if (room < rm.getNumRooms()) {
                System.out.print(", ");
            }
        } System.out.print('\n');
    }
    public void printSpeakerOrAttendeeName(){
        System.out.println("Type in the username of the Speaker or Attendee:");
    }

    public void printMessageContentPrompt(){
        System.out.println("Enter message content:");
    }

    public void printMessageNotSent() {
        System.out.println("Message could not be sent. Does the intended recipient exist?");
    }

    public void printMessageSent() {
        System.out.println("Message sent successfully!");
    }

    public void printUserNotFound() { System.out.println("The username you entered was not found."); }

    public void printNotSpeakerOrAttendee() {
        System.out.println("The username you entered does not belong to a speaker or attendee.");
    }
}
