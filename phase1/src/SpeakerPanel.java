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
        while (!(decision.equals("Logout") || decision.equals("Quit app"))){
            switch (decision) {
                case "help":
                    speakerPres.commandHelp(); break;

                case "View messages":
                    //viewMessages(allUserIds, currUserID);
                    break;

                case "View all events":
                    viewAllEvents();
                    break;

                case "View speaking events":
                    //viewSignedUpEvents(currUserID);
                    break;

                case "Join event":
                    //nextMove= joinEvent(currUserID, this.userMan);
                    //if (nextMove != null){return nextMove;}
                    break;

                case "Leave event":
                    //nextMove= leaveEvent();
                    //if (nextMove != null){return nextMove;}
                    break;

                default:
                    speakerPres.commandNotRecognized(decision); break;
            }
            decision = input.nextLine();
        }

        // Go back to LoginSystem or quits app if user types the command for either
        if (decision.equals("Logout")){return Definitions.LOGIN_SYSTEM;}
        return Definitions.QUIT_APP;
    }

    private void viewAllEvents(){
        speakerPres.viewAllEvents(roomMan.stringEventInfoAll());
    }


}
