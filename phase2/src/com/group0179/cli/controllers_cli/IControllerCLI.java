package com.group0179.cli.controllers_cli;

/**
 * Common interface for any controller classes.
 * @author Zachariah Vincze
 */
public interface IControllerCLI {
    /**
     * This method is run once per iteration.
     * @return the state ID to change to once this iteration has finished.
     */
    int run();
}
