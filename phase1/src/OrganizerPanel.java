import java.util.*;

/**
 * @author Justin Chan, Tanuj Devjani
 */
public class OrganizerPanel implements IController {
    private final Scanner sc = new Scanner(System.in);
    private final UserManager um;
    private final RoomManager rm;
    private final OrganizerPresenter op;

    private final int START_HOUR_EARLIEST = 9;
    private final int START_HOUR_LATEST = 16;

    /**
     * @param rm the RoomManager
     * Last modified: Justin Chan
     */
    OrganizerPanel(UserManager um, RoomManager rm) {
        this.um = um;
        this.rm = rm;
        op = new OrganizerPresenter(um, rm);
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

    private void printEventsInRoomInteractive() {
        op.printAvailableRooms();
        int roomNumber = inputRoomFiltered(); // take room input from user and make sure it's correct
        if (cancelRequested(Integer.toString(roomNumber))) {
            return; // quit and return when the user requests to terminate the method
        } op.printRoomEvents(roomNumber);
    }

    /**
     * Author: Justin Chan
     * @return the room index in rm.getRooms(), or -1 if a cancel is requested
     */
    private int inputRoomFiltered() {
        while (true) {
            op.inputRoomPrompt();
            String roomRaw = sc.nextLine(); // get the desired roomNumber from the user
            try {
                int roomNumber = Integer.parseInt(roomRaw);
                if (roomNumber == -1) {
                    return -1; // send cancel signal
                } else {
                    if (0 < roomNumber && roomNumber <= rm.getNumRooms()) {
                        return roomNumber - 1;
                    } else {
                        op.inputRoomStatus();
                    }
                }
            } catch (NumberFormatException e) {
                op.invalidInputNotification();
            }
        }
    }

    private int inputEventNumberFiltered(int roomNumber) {
        while (true) {
            op.inputEventPrompt();
            String eventRaw = sc.nextLine(); // get the desired roomNumber from the user
            try {
                int eventNumber = Integer.parseInt(eventRaw);
                if (eventNumber == -1) {
                    return -1; // send cancel signal
                } else {
                    if (0 < eventNumber && eventNumber <= rm.getNumEventsInRoom(roomNumber)) {
                        return eventNumber - 1;
                    } else {
                        op.inputEventStatus();
                    }
                }
            } catch (NumberFormatException e) {
                op.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @return the validated username (username exists and corresponds to a speaker) or -1 if a cancel is requested
     */
    private String inputSpeakerUsernameFiltered() {
        while (true) {
            op.inputSpeakerNamePrompt();
            String speakerName = sc.nextLine();
            if (speakerName.equals("-1")) {
                return speakerName;
            }

            if (um.userExists(speakerName)) {
                if (um.isSpeaker(speakerName)) {
                    return speakerName;
                } op.inputSpeakerNameStatusNonSpeaker(speakerName);
            } op.inputSpeakerNameStatusDNE(speakerName);
        }
    }

    private String inputNewSpeakerUsernameFiltered() {
        while (true) {
            op.inputNewSpeakerNamePrompt();
            String speakerName = sc.nextLine();
            if (speakerName.equals("-1")) {
                return speakerName;
            }

            if (!um.getUsernames().contains(speakerName)) {
                um.createSpeakerAccount(speakerName);
                return speakerName;
            } op.inputNewSpeakerNameStatus(speakerName);
        }
    }

    /**
     * Author: Justin Chan
     * @return the validated year or -1 if a cancel is requested
     */
    private int inputYearFiltered() {
        while (true) {
            op.inputYearPrompt();
            try {
                int year = Integer.parseInt(sc.nextLine());
                if (year == -1) {
                    return -1;
                } if (year >= Calendar.getInstance().get(Calendar.YEAR)) {
                    return year;
                } op.inputYearStatus();
            } catch (NumberFormatException e) {
                op.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @param year the validated year of the date input
     * @return the validated hour or -1 if a cancel is requested
     */
    private int inputMonthFiltered(int year) {
        while (true) {
            op.inputMonthPrompt();
            try {
                int month = Integer.parseInt(sc.nextLine());
                if (month == -1) {
                    return -1;
                } if (year == Calendar.getInstance().get(Calendar.YEAR)) {
                    if (Calendar.getInstance().get(Calendar.MONTH) <= month - 1 && month <= 12) {
                        return month - 1;
                    }
                } else {
                    if (1 <= month && month <= 12) {
                        return month - 1;
                    }
                } op.inputMonthStatus(month);
            } catch (NumberFormatException e) {
                op.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @param year the validated year of the date input
     * @param month the validated month of the date input
     * @return the validated day or -1 if a cancel is requested
     */
    private int inputDayFiltered(int year, int month) {
        while (true) {
            op.inputDayPrompt();
            try {
                int day = Integer.parseInt(sc.nextLine());
                if (day == -1) {
                    return -1;
                } if (year == Calendar.getInstance().get(Calendar.YEAR) && month == Calendar.getInstance().get(Calendar.MONTH)) {
                    if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) <= day) {
                        try {
                            GregorianCalendar check = new GregorianCalendar(year, month, day);
                            check.setLenient(false);
                            check.get(Calendar.MONTH);
                            return day;
                        } catch (IllegalArgumentException ignored) { }
                    } op.inputDayStatus(day);
                } else {
                    try {
                        GregorianCalendar check = new GregorianCalendar(year, month, day);
                        check.get(Calendar.MONTH);
                        return day;
                    } catch (IllegalArgumentException e) {
                        op.inputDayStatus(day);
                    }
                }
            } catch (NumberFormatException e) {
                op.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @param year the validated year of the date input
     * @param month the validated month of the date input
     * @param day the validated day of the date input
     * @return the validated hour or -1 if a cancel is requested
     */
    private int inputHourFiltered(int year, int month, int day) {
        while (true) {
            op.inputHourPrompt(START_HOUR_EARLIEST, START_HOUR_LATEST);
            try {
                int hour = Integer.parseInt(sc.nextLine());
                if (hour == -1) {
                    return -1;
                }
                if (year == Calendar.getInstance().get(Calendar.YEAR) && month == Calendar.getInstance().get(Calendar.MONTH) && day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                    if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) <= hour && START_HOUR_EARLIEST <= hour && hour <= START_HOUR_LATEST) {
                        return hour;
                    }
                } else {
                    if (START_HOUR_EARLIEST <= hour && hour <= START_HOUR_LATEST) {
                        return hour;
                    }
                } op.inputHourStatus(hour);
            } catch (NumberFormatException e) {
                op.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @return the validated minute or -1 if a cancel is requested
     */
    private int inputMinuteFiltered() {
        while (true) {
            op.inputMinutePrompt();
            try {
                int minute = Integer.parseInt(sc.nextLine());
                if (-1 <= minute && minute < 60) {
                    return minute;
                } op.inputMinuteStatus(minute);
            } catch (NumberFormatException e) {
                op.invalidInputNotification();
            }
        }
    }


    /**
     * Author: Justin Chan
     */
    private void createEvent() {
        op.createEventWelcome();
        op.printAvailableRooms();

        // User inputs room number
        int roomNumber = inputRoomFiltered(); // take room input from user and make sure it's correct
        if (cancelRequested(Integer.toString(roomNumber))) {
            return; // quit and return when the user requests to terminate the method
        } op.printRoomEvents(roomNumber);

        // User inputs event title
        op.createEventTitlePrompt();
        String title = sc.nextLine();

        // User inputs the username of the speaker
        op.printAvailableSpeakers();
        String speakerName = inputSpeakerUsernameFiltered();
        if (cancelRequested(speakerName)) {
            return;
        } op.printSpeakerEvents(speakerName);

        // User inputs the year of the event
        int year = inputYearFiltered();
        if (cancelRequested(Integer.toString(year))) {
            return;
        }

        // User inputs the month of the event
        int month = inputMonthFiltered(year);
        if (cancelRequested(Integer.toString(month))) {
            return;
        }

        // User inputs the day of the event
        int day = inputDayFiltered(year, month);
        if (cancelRequested(Integer.toString(day))) {
            return;
        }

        // User inputs the hour of the event
        int hour = inputHourFiltered(year, month, day);
        if (cancelRequested(Integer.toString(hour))) {
            return;
        }

        // User inputs the minute of the event
        int minute;
        if (hour != START_HOUR_LATEST) {
            minute = inputMinuteFiltered();
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
     */
    private void createRoom() {
        rm.newRoom();
        op.createRoomStatus(rm.getNumRooms());
        op.printAvailableRooms();
    }

    /**
     * Author: Justin Chan
     */
    private void createSpeaker() {
        op.createSpeakerWelcome();
        String speakerName = inputNewSpeakerUsernameFiltered();
        if (cancelRequested(speakerName)) {
            return;
        }
        op.createSpeakerStatus(speakerName);
        op.printAvailableSpeakers();
    }

    private void cancelEvent(){
        op.cancelEventWelcome();

        // Find the room of the event
        op.printAvailableRooms();
        int roomNumber = inputRoomFiltered();
        if (cancelRequested(Integer.toString(roomNumber))) {
            return;
        }

        // Find the event
        op.printRoomEvents(roomNumber);
        int eventNumber = inputEventNumberFiltered(roomNumber);
        if (cancelRequested(Integer.toString(eventNumber))) {
            return;
        } rm.removeEvent(um, roomNumber, eventNumber);
        op.cancelEventStatus();
        op.printRoomEvents(roomNumber);
    }

    private void rescheduleEvent() {
        op.rescheduleEventWelcome();

        op.printAvailableRooms();
        int roomNumber = inputRoomFiltered();
        if (cancelRequested(Integer.toString(roomNumber))) {
            return;
        }
        // Find the event
        op.printRoomEvents(roomNumber);
        int eventNumber = inputEventNumberFiltered(roomNumber);
        if (cancelRequested(Integer.toString(eventNumber))) {
            return;
        }
        int year = inputYearFiltered();
        if (cancelRequested(Integer.toString(year))) {
            return;
        }

        // User inputs the month of the event
        int month = inputMonthFiltered(year);
        if (cancelRequested(Integer.toString(month))) {
            return;
        }

        // User inputs the day of the event
        int day = inputDayFiltered(year, month);
        if (cancelRequested(Integer.toString(day))) {
            return;
        }

        // User inputs the hour of the event
        int hour = inputHourFiltered(year, month, day);
        if (cancelRequested(Integer.toString(hour))) {
            return;
        }

        // User inputs the minute of the event
        int minute;
        if (hour != START_HOUR_LATEST) {
            minute = inputMinuteFiltered();
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
        op.welcomePrompt();
        while (true) {
            op.commandPrompt();
            String command = sc.nextLine();
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
                    printEventsInRoomInteractive(); break;
                case "help":
                    op.commandHelp(); break;
                case "quit":
                    return Definitions.QUIT_APP;
                default:
                    op.commandNotRecognized(command);
            }
        }
    }
}
