/**
 * Common interface for any controller classes.
 * @author Zachariah Vincze
 */
public interface IController {
    /**
     * This method is run once per iteration.
     * @return the state ID to change to once this iteration has finished.
     */
    int run();
}
