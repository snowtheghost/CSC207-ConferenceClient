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
                "viewspeakers - See available Speakers\n" + "viewrooms - See available Rooms\n" + "viewevents - See available events in specified room\n" + "quit - Log out as Organizer");
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

    public void inputRoomPrompt() {
        System.out.print("Room: ");
    }

    public void inputRoomStatus() {
        System.out.println("Room does not exist - please try again.");
    }

    public void inputEventPrompt() {
        System.out.print("Event Number: ");
    }

    public void inputEventStatus() {
        System.out.println("Event does not exist - please try again.");
    }

    public void inputSpeakerNamePrompt() {
        System.out.print("Speaker username: ");
    }

    public void inputSpeakerNameStatusNonSpeaker(String speakerName) {
        System.out.println(speakerName + " is not a Speaker - please try again.");
    }

    public void inputSpeakerNameStatusDNE(String speakerName) {
        System.out.println(speakerName + " does not exist - please try again.");
    }

    public void inputNewSpeakerNamePrompt() {
        System.out.print("New Speaker username: ");
    }

    public void inputNewSpeakerNameStatus(String speakerName) {
        System.out.println(speakerName + " already exists - please try again.");
    }

    public void inputYearPrompt() {
        System.out.print("Year: ");
    }

    public void inputYearStatus() {
        System.out.println("Invalid year - please try again.");
    }

    public void inputMonthPrompt() {
        System.out.print("Month (1-12): ");
    }

    public void inputMonthStatus(int month) {
        System.out.println(month + " is not a valid month - please try again.");
    }

    public void inputDayPrompt() {
        System.out.print("Day: ");
    }

    public void inputDayStatus(int day) {
        System.out.println(day + " is not a valid day - please try again.");
    }

    public void inputHourPrompt(int startHourEarliest, int startHourLatest) {
        System.out.print("Start hour (" + startHourEarliest + "-" + startHourLatest + "): ");
    }

    public void inputHourStatus(int hour) {
        System.out.println(hour + " is not a valid hour - please try again.");
    }

    public void inputMinutePrompt() {
        System.out.print("Start minute (0-59): ");
    }

    public void inputMinuteStatus(int minute) {
        System.out.println(minute + " is not a valid minute");
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

    public void printAvailableSpeakers() {
        System.out.print(um.stringAvailableSpeakers());
    }

    public void invalidInputNotification() {
        System.out.println("Invalid input - please try again.");
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
}
