import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpeakerPresenter {
    private final UserManager userMan;
    private final RoomManager roomMan;
    private final MessageManager msgMan;

    SpeakerPresenter(UserManager userMan, RoomManager roomMan, MessageManager msgMan) {
        this.userMan = userMan;
        this.roomMan = roomMan;
        this.msgMan = msgMan;
    }

    public void welcomePrompt() {
        System.out.println("This is the SpeakerPanel. Type \"help\" to see a list of commands.");
    }

    public void commandPrompt() {
        System.out.print("command: ");
    }

    public void commandHelp() {
        System.out.println("view all events - view all scheduled events\n" +
                "view speaking events - view all events you are speaking at\n" +
                "view messages - view all messages that have been sent to you\n" +
                "send direct message - send a message to a specific user\n" +
                "send message to event attendees - send a message to all attendees of an event or events you are speaking at\n" +
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

    public void viewMessages(ArrayList<UUID> allusers, UUID currUser){
        for (UUID id : allusers){
            List<String> messages = msgMan.getMessageContentsFromUser(userMan, currUser, id);
            if (messages.size() > 0){
                System.out.println(userMan.getUsername(id)+": "+ messages);
            }

        }
    }

    public void messageSelectedTalks(ArrayList talks){ System.out.println("Currently Selected Talks: " + talks + ", enter another talk to add them to the recipient list, a to accept, or q to quit"); }

    public void messagePrompt(){ System.out.println("Enter your message:"); }

    public void messageSuccess(UUID msgid, UUID event){ System.out.println("Success! Your message was sent to the attendees of "+ roomMan.stringEvent(event));}
            // +"\nMessageID: "+ msgid); }

    public void errorNoSpeakingEvents(){ System.out.println("Whoops, it looks like you aren't speaking at any events"); }

    public void errorInvalidInput(String command){ System.out.println("Whoops, it looks like '" + command + "' isn't a valid input"); }

    public void errorInvalidInput(int command){ System.out.println("Whoops, it looks like '" + command + "' isn't a valid input"); }

    public void errorNoAttendees(){ System.out.println("Whoops, it looks like there's nobody signed up for that event!"); }

    public void errorGeneral(){ System.out.println("Whoops, it looks like something went wrong!"); }

    public void errorUserNotFound(){ System.out.println("Whoops, it looks like that user can't be found!"); }

    public void dmPrompt(){
        System.out.println("Enter a username, a to view all users, or q to return to the menu");
    }

    public void typeMsgPrompt(){
        System.out.println("Enter your message:");
    }

    public void msgSentPrompt(String username){
        System.out.println("Message successfully sent to "+username);
    }

    public void listAllUsers() {
        System.out.println("All possible recipients:");
        for (String user: userMan.getUsernames()){
            System.out.println(user);
        }
    }
}
