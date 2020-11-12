public class InputPresenter {
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

    public void invalidInputNotification() {
        System.out.println("Invalid input - please try again.");
    }
}
