import java.util.*;

/**
 * @author Liam Ogilvie
 */

public class SpeakerPanel implements IController {
    private final UserManager userMan;
    private final RoomManager roomMan;
    private final MessageManager msgMan;
    private final SpeakerPresenter speakerPres;
    private final Scanner input = new Scanner(System.in);

    /**
     * @param roomMan: the RoomManager
     * @param userMan: the UserManager
     */
    public SpeakerPanel(UserManager userMan, MessageManager msgMan, RoomManager roomMan) {
        this.msgMan = msgMan;
        this.userMan = userMan;
        this.roomMan = roomMan;
        speakerPres = new SpeakerPresenter(userMan, roomMan);
    }

    /**
     * Author: Liam Ogilvie
     * TODO:
     * Speakers should be able to send a message that automatically goes to all Attendees of their talk or
     * multiple talks that they gave.
     *
     * Attendees should be able to message other Attendees or Speakers.
     * Speakers should be able to respond to a specific Attendee.
     *
     */
    @Override
    public int run() {
        UUID currUserID = this.userMan.getCurrentUser();
        ArrayList<UUID> allUserIds = new ArrayList<>(this.userMan.getAttendeeUUIDs());
        allUserIds.addAll(this.userMan.getOrganizerUUIDs());
        allUserIds.addAll(this.userMan.getSpeakerUUIDs());

        speakerPres.welcomePrompt();
        String decision = input.nextLine();
        while (!(decision.equals("logout") || decision.equals("quit"))){
            switch (decision) {
                case "help":
                    speakerPres.commandHelp(); break;

                case "View messages":
                    //viewMessages(allUserIds, currUserID);
                    break;

                case "view events":
                    viewAllEvents();
                    break;

                case "view speaking events":
                    viewSpeakingEvents(currUserID);
                    break;

                case "send message to event attendees":
                    sendMessageToAttendees(currUserID);
                    break;

                case "Leave event":
                    //nextMove= leaveEvent();
                    //if (nextMove != null){return nextMove;}
                    break;

                default:
                    speakerPres.errorInvalidInput(decision); break;
            }
            decision = input.nextLine();
        }

        // Go back to LoginSystem or quits app if user types the command for either
        if (decision.equals("logout")){return Definitions.LOGIN_SYSTEM;}
        return Definitions.QUIT_APP;
    }

    private void viewAllEvents(){ speakerPres.viewAllEvents(); }

    private void viewSpeakingEvents(UUID speakerID){ speakerPres.viewSpeakingEvents(speakerID); }

    private int sendMessageToAttendees(UUID speakerID) {
        if (userMan.getSpeakerEventIDs(speakerID).size() == 0) {
            speakerPres.errorNoSpeakingEvents();
            speakerPres.welcomePrompt();
            return Definitions.ATTENDEE_PANEL;
        } else {
            speakerPres.sendAllAttendeesIntro(speakerID);
            String decision = input.nextLine();
            try{
                new Integer(decision);
            } catch (NumberFormatException e) {
                speakerPres.errorInvalidInput(decision);
                decision = "999999999"; //a really big number that *should* be larger than the number of events
            }

            if (decision.equals("q")) {
                speakerPres.welcomePrompt();
                return Definitions.ATTENDEE_PANEL;
            }

            else {
                boolean flag = false;
                while (!flag) {
                    while (new Integer(decision) > userMan.getSpeakerEventIDs(speakerID).size()) {
                        decision = input.nextLine();
                        if (decision.contains("q")) {
                            speakerPres.welcomePrompt();
                            return Definitions.ATTENDEE_PANEL;
                        }
                        try {
                            if(new Integer(decision) > userMan.getSpeakerEventIDs(speakerID).size()){
                                speakerPres.errorInvalidInput(decision);
                            }
                        } catch (NumberFormatException e) {
                            speakerPres.errorInvalidInput(decision);
                            decision = "999999999"; //a really big number that *should* be larger than the number of events
                        }


                    }
                    int selection = new Integer(decision);
                    UUID eventid = userMan.getSpeakerEventIDs(speakerID).get(selection - 1);
                    if (roomMan.getEventAttendeeIDs(eventid).size() == 0) {
                        speakerPres.errorNoAttendees();
                        decision = "999999999"; //yes I did it again, yes I know it's a cheap workaround
                                                //if you are able to break it, I will fix it
                        //speakerPres.sendAllAttendeesIntro(speakerID);
                    }
                    else{
                        flag = true;
                        speakerPres.messagePrompt();
                        String message = input.nextLine();
                        UUID status = msgMan.sendMessageToEventAttendees(userMan, roomMan, speakerID, eventid, message);
                        if (status == null){
                            speakerPres.errorGeneral();
                        }
                        else{
                            speakerPres.messageSuccess(status);
                        }
                    }
                }
            }

        }
        speakerPres.welcomePrompt();
        return Definitions.ATTENDEE_PANEL;
    }

}
