import java.util.*;

/**
 * @author Justin Chan, Tanuj Devjani
 */
public class OrganizerPanel implements IController {
    private final Scanner sc = new Scanner(System.in);
    private final UserManager um;
    private final RoomManager rm;
    private final MessageManager mm;
    private final InputFilter filter;
    private final OrganizerPresenter op;

    private final int START_HOUR_EARLIEST = 9;
    private final int START_HOUR_LATEST = 16;

    /**
     * Allows Organizer to perform their respective functions/duties
     * @param rm the RoomManager
     * @param um the Usermanager
     * Last modified: Justin Chan
     */
    OrganizerPanel(UserManager um, RoomManager rm, MessageManager mm, InputFilter inputFilter) {
        this.um = um;
        this.rm = rm;
        this.mm = mm;
        this.filter = inputFilter;
        op = new OrganizerPresenter(um, rm);
    }

    /**
     * Author: Zachariah Vincze
     * Prompts the organizer to send a message to either an attendee or a speaker.
     */
    private void sendMessage() {
        op.printSpeakerOrAttendeeName();
        String username = sc.nextLine();
        if (cancelRequested(username)) return;
        op.printMessageContentPrompt();
        String messageContent = sc.nextLine();
        if (cancelRequested(messageContent)) return;
        UUID userID = um.getUserID(username);
        if (userID == null) {
            op.printUserNotFound();
            return;
        }
        if (!um.userType(username).equals("speaker") && !um.userType(username).equals("attendee")) {
            op.printNotSpeakerOrAttendee();
            return;
        }
        if (mm.sendMessage(um, um.getCurrentUser(), userID, messageContent) == null) {
            op.printMessageNotSent();
            return;
        }
        op.printMessageSent();
    }

    private void messageAllSpeakers() {
        op.printMessageContentPrompt();
        String messageContent = sc.nextLine();
        if (cancelRequested(messageContent)) return;
        if (mm.sendMessageToAllSpeakers(um, um.getCurrentUser(), messageContent) == null) {
            op.printMessageNotSent();
            return;
        }
        op.printMessageSent();
    }

    private void messageAllAttendees() {
        op.printMessageContentPrompt();
        String messageContent = sc.nextLine();
        if (cancelRequested(messageContent)) return;
        if (mm.sendMessageToAllAttendees(um, um.getCurrentUser(), messageContent) == null) {
            op.printMessageNotSent();
            return;
        }
        op.printMessageSent();
    }
    
    /**
     * Author: Justin Chan
     * @param input the validated input, which is either valid or -1
     * @return true if the input is a cancel request and false if it is not
     */
    private boolean cancelRequested(String input) {
        if (input.equals("-1")) {
            op.cancelNotification();
            return true;
        } return false;
    }

    /**
     * Author: Justin Chan
     * Creates a new event based on arguments inputted by the organizer
     */
    private void createEvent() {
        op.createEventWelcome();
        op.printAvailableRooms();

        // User inputs room number
        int roomNumber = filter.inputRoom(); // take room input from user and make sure it's correct
        if (cancelRequested(Integer.toString(roomNumber))) {
            return; // quit and return when the user requests to terminate the method
        } op.printRoomEvents(roomNumber);

        // User inputs event title
        op.createEventTitlePrompt();
        String title = sc.nextLine();

        // User inputs the username of the speaker
        op.printAvailableSpeakers();
        String speakerName = filter.inputSpeakerUsername();
        if (cancelRequested(speakerName)) {
            return;
        } op.printSpeakerEvents(speakerName);

        // User inputs the year of the event
        int year = filter.inputYear();
        if (cancelRequested(Integer.toString(year))) {
            return;
        }

        // User inputs the month of the event
        int month = filter.inputMonth(year);
        if (cancelRequested(Integer.toString(month))) {
            return;
        }

        // User inputs the day of the event
        int day = filter.inputDay(year, month);
        if (cancelRequested(Integer.toString(day))) {
            return;
        }

        // User inputs the hour of the event
        int hour = filter.inputHour(year, month, day);
        if (cancelRequested(Integer.toString(hour))) {
            return;
        }

        // User inputs the minute of the event
        int minute;
        if (hour != START_HOUR_LATEST) {
            minute = filter.inputMinute();
            if (cancelRequested(Integer.toString(minute))) {
                return;
            }
        } else {
            minute = 0; // prevent out of bound time
        }

        // Create event
        if (rm.newEventValid(title, speakerName, new GregorianCalendar(year, month, day, hour, minute, 0), new GregorianCalendar(year, month, day, hour + 1, minute, 0), roomNumber, um)) {
            UUID eventID = rm.newEvent(title, speakerName, new GregorianCalendar(year, month, day, hour, minute, 0), new GregorianCalendar(year, month, day, hour + 1, minute, 0), roomNumber, um);
            op.createEventStatus(true, rm.stringEvent(eventID));
            return;
        } op.createEventStatus(false, "");
    }

    /**
     * Author: Justin Chan
     * Creates a new room
     */
    private void createRoom() {
        rm.newRoom();
        op.createRoomStatus(rm.getNumRooms());
        op.printAvailableRooms();
    }

    /**
     * Author: Justin Chan
     * Creates a new speaker based on given username
     */
    private void createSpeaker() {
        op.createSpeakerWelcome();
        String speakerName = filter.inputNewSpeakerUsername();
        if (cancelRequested(speakerName)) {
            return;
        }
        op.createSpeakerStatus(speakerName);
        op.printAvailableSpeakers();
    }

    /**
     * Author: Tanuj Devjani
     * Cancels an event chosen by the Organizer
     */
    private void cancelEvent(){
        op.cancelEventWelcome();

        // Find the room of the event
        op.printAvailableRooms();
        int roomNumber = filter.inputRoom();
        if (cancelRequested(Integer.toString(roomNumber))) {
            return;
        }

        // Find the event itself
        op.printRoomEvents(roomNumber);
        int eventNumber = filter.inputEventNumber(roomNumber);
        if (cancelRequested(Integer.toString(eventNumber))) {
            return;
        } rm.removeEvent(um, roomNumber, eventNumber);
        op.cancelEventStatus();
        op.printRoomEvents(roomNumber);
    }

    /**
     * Author: Tanuj Devjani
     * Reschedules an event as per the requirements of the organizer
     */
    private void rescheduleEvent() {
        op.rescheduleEventWelcome();

        op.printAvailableRooms();
        int roomNumber = filter.inputRoom();
        if (cancelRequested(Integer.toString(roomNumber))) {
            return;
        }
        // Find the event
        op.printRoomEvents(roomNumber);
        int eventNumber = filter.inputEventNumber(roomNumber);
        if (cancelRequested(Integer.toString(eventNumber))) {
            return;
        }
        int year = filter.inputYear();
        if (cancelRequested(Integer.toString(year))) {
            return;
        }

        // User inputs the month of the event
        int month = filter.inputMonth(year);
        if (cancelRequested(Integer.toString(month))) {
            return;
        }

        // User inputs the day of the event
        int day = filter.inputDay(year, month);
        if (cancelRequested(Integer.toString(day))) {
            return;
        }

        // User inputs the hour of the event
        int hour = filter.inputHour(year, month, day);
        if (cancelRequested(Integer.toString(hour))) {
            return;
        }

        // User inputs the minute of the event
        int minute;
        if (hour != START_HOUR_LATEST) {
            minute = filter.inputMinute();
            if (cancelRequested(Integer.toString(minute))) {
                return;
            }
        } else {
            minute = 0; // prevent out of bound time
        }

        op.rescheduleEventStatus(rm.rescheduleEvent(um, roomNumber, eventNumber, new GregorianCalendar(year, month, day, hour, minute, 0), new GregorianCalendar(year, month, day, hour + 1, minute, 0)));
        op.printRoomEvents(roomNumber);
    }

    /**
     * Author: Justin Chan
     * This is the method to call when we are running an Organizer.
     */
    @Override
    public int run() {
        String command = "";
        op.welcomePrompt();
        while (!(command.equalsIgnoreCase("logout") || command.equalsIgnoreCase("quit"))){
            op.commandPrompt();
            command = sc.nextLine();
            switch (command) {
                case "createspeaker":
                    createSpeaker(); break;
                case "createroom":
                    createRoom(); break;
                case "createevent":
                    createEvent(); break;
                case "rescheduleevent":
                    rescheduleEvent();break;
                case "cancelevent":
                    cancelEvent(); break;
                case "viewspeakers":
                    op.printAvailableSpeakers(); break;
                case "viewrooms":
                    op.printAvailableRooms(); break;
                case "viewevents":
                    op.printAllEvents(); break;
                case "messageallattendees":
                    messageAllAttendees(); break;
                case "sendmessage":
                    sendMessage(); break;
                case "messageallspeakers":
                    messageAllSpeakers(); break;
                case "help":
                    op.commandHelp(); break;
                case "logout":
                    return Definitions.BACK;
                case "quit":
                    return Definitions.QUIT;
                default:
                    op.commandNotRecognized(command);
            }
        }
        return Definitions.QUIT;
    }
}
