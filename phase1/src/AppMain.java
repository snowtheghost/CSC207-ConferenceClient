/**
 * The app's entrypoint.
 * @author Zachariah Vincze
 */
public class AppMain {

    public static void main(String args[]) {
        boolean applicationRunning = true;

        UserManager userManager = new UserManager();

        AltLoginSystem loginSystem = new AltLoginSystem(userManager);
        AttendeePanel attendeePanel = new AttendeePanel();
        OrganizerPanel organizerPanel = new OrganizerPanel();
        IController currentController = loginSystem;

        while (applicationRunning) {
            currentController.run();

            if (currentController.isQuitting()) applicationRunning = false;

            else if (currentController.isChangingState()) {
                switch (currentController.getNewState()) {
                    case 0:
                        currentController = loginSystem;
                        break;
                    case 1:
                        currentController = attendeePanel;
                        break;
                    case 2:
                        currentController = organizerPanel;
                        break;
                }
            }
        }
    }
}
