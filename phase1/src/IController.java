/**
 * Common interface for any controller classes.
 * @author Zachariah Vincze
 */
public interface IController {
    /**
     * This method is run once per iteration.
     */
    public void run();

    /**
     *
     * @return True iff this state has requested the application to quit.
     */
    public boolean isQuitting();

    /**
     *
     * @return True iff this controller has requested to change application states.
     */
    public boolean isChangingState();

    /**
     *
     * @return the ID of the state this controller wishes to change to.
     */
    public int getNewState();
}
