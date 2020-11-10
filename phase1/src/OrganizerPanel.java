import java.util.*;

/**
 * @author Justin Chan, Tanuj Devjani
 */
public class OrganizerPanel implements IController {
    private final boolean quitting = false;
    private final Scanner sc = new Scanner(System.in);
    private final UserManager um;
    private final RoomManager rm;

    private final int START_HOUR_EARLIEST = 9;
    private final int START_HOUR_LATEST = 16;

    /**
     * @param rm the RoomManager
     * Last modified: Justin Chan
     */
    OrganizerPanel(UserManager um, RoomManager rm) {
        this.um = um;
        this.rm = rm;
    }

    private void invalidInputNotification() {
        System.out.println("Invalid input - please try again.");
    }

    private void cancelNotification() {
        System.out.println("Process cancelled.");
    }

    private boolean cancelRequested(String input) {
        if (input.equals("-1")) {
            cancelNotification();
            return true;
        } return false;
    }

    private void printAvailableRooms() {
        System.out.print("Rooms available: ");
        for (int room = 1; room <= rm.getRooms().size(); room++) {
            System.out.print("Room " + room);
            if (room < rm.getRooms().size()) {
                System.out.print(", ");
            }
        } System.out.print('\n');
    }

    private void printAvailableSpeakers() {
        System.out.print("Speakers available: ");
        ArrayList<Speaker> speakers = um.getSpeakers();
        for (int i = 0; i < speakers.size(); i++) {
            System.out.print(speakers.get(i).getUsername());
            if (i < speakers.size() - 1) {
                System.out.print(", ");
            }
        } System.out.print('\n');
    }

    private int inputRoomFiltered(int numRooms) {
        while (true) {
            System.out.print("Room: "); // for user to read
            String roomRaw = sc.nextLine(); // get the desired roomNumber from the user
            try {
                int roomNumber = Integer.parseInt(roomRaw);
                if (roomNumber == -1) {
                    return -1; // send cancel signal
                } else {
                    if (0 < roomNumber && roomNumber <= numRooms) {
                        return roomNumber - 1;
                    } else {
                        System.out.println("Room does not exist - please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                invalidInputNotification();
            }
        }
    }

    public String inputSpeakerUsernameFiltered() {
        while (true) {
            System.out.print("Speaker username: ");
            String speakerName = sc.nextLine();
            if (speakerName.equals("-1")) {
                return speakerName;
            }

            if (um.getUsernames().contains(speakerName)) {
                if (um.getUser(speakerName).isSpeaker()) {
                    return speakerName;
                } System.out.println(speakerName + " is not a Speaker - please try again.");
            } System.out.println(speakerName + " does not exist - please try again.");
        }
    }

    public int inputYearFiltered() {
        while (true) {
            System.out.print("Year: ");
            try {
                int year = Integer.parseInt(sc.nextLine());
                if (year == -1) {
                    return -1;
                } if (year >= Calendar.getInstance().get(Calendar.YEAR)) {
                    return year;
                } System.out.println("Invalid year - please try again.");
            } catch (NumberFormatException e) {
                invalidInputNotification();
            }
        }
    }

    public int inputMonthFiltered(int year) {
        while (true) {
            System.out.print("Month (1-12): ");
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
                } System.out.println(month + " is not a valid month - please try again.");
            } catch (NumberFormatException e) {
                invalidInputNotification();
            }
        }
    }

    public int inputDayFiltered(int year, int month) {
        while (true) {
            System.out.print("Day: ");
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
                    } System.out.println(day + " is not a valid day - please try again.");
                } else {
                    try {
                        GregorianCalendar check = new GregorianCalendar(year, month, day);
                        check.get(Calendar.MONTH);
                        return day;
                    } catch (IllegalArgumentException e) {
                        System.out.println(day + " is not a valid day - please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                invalidInputNotification();
            }
        }
    }

    public int inputHourFiltered(int year, int month, int day) {
        while (true) {
            System.out.print("Start hour (" + START_HOUR_EARLIEST + "-" + START_HOUR_LATEST + "): ");
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
                } System.out.println(hour + " is not a valid hour - please try again.");
            } catch (NumberFormatException e) {
                invalidInputNotification();
            }
        }
    }

    public int inputMinuteFiltered() {
        while (true) {
            System.out.print("Start minute (0-59): ");
            try {
                int minute = Integer.parseInt(sc.nextLine());
                if (-1 <= minute && minute < 60) {
                    return minute;
                } System.out.println(minute + " is not a valid minute");
            } catch (NumberFormatException e) {
                invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @return true if the event was created successfully or false if the user cancelled during the creation process
     */
    public boolean createEvent() {
        System.out.println("Creating an event. To cancel the process, enter \"-1\" in any input.");
        printAvailableRooms();

        // User inputs room number
        int roomNumber = inputRoomFiltered(rm.getRooms().size()); // take room input from user and make sure it's correct
        if (cancelRequested(Integer.toString(roomNumber))) {
            return false; // quit and return false when the user requests to terminate the method
        }

        // User inputs event title
        System.out.print("Event Title: ");
        String title = sc.nextLine();

        // User inputs the username of the speaker
        printAvailableSpeakers();
        String speakerName = inputSpeakerUsernameFiltered();
        if (cancelRequested(speakerName)) {
            return false;
        }

        // User inputs the year of the event
        int year = inputYearFiltered();
        if (cancelRequested(Integer.toString(year))) {
            return false;
        }

        // User inputs the month of the event
        int month = inputMonthFiltered(year);
        if (cancelRequested(Integer.toString(month))) {
            return false;
        }

        // User inputs the day of the event
        int day = inputDayFiltered(year, month);
        if (cancelRequested(Integer.toString(day))) {
            return false;
        }

        // User inputs the hour of the event
        int hour = inputHourFiltered(year, month, day);
        if (cancelRequested(Integer.toString(hour))) {
            return false;
        }

        // User inputs the minute of the event
        int minute;
        if (hour != START_HOUR_LATEST) {
            minute = inputMinuteFiltered();
            if (cancelRequested(Integer.toString(minute))) {
                return false;
            }
        } else {
            minute = 0; // prevent out of bound time
        }

        // Create event
        Speaker speaker = (Speaker) um.getUsernameToUser().get(speakerName);
        rm.newEvent(title, speaker, new GregorianCalendar(year, month, day, hour, minute, 0), new GregorianCalendar(year, month, day, hour + 1, minute, 0), rm.getRooms().get(roomNumber));

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
