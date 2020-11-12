public class LoginSystemPresenter {
    /**
     * Opening prompt telling the user to Login or Create account.
     */
    public void LoginCreateActPrompt(){
        System.out.println("\"Login\" or \"Create account\"?");
    }
    /**
     * Prompt asking user to enter account name.
     */
    public void enterUsernamePrompt(){
        System.out.println("Enter account name");
    }

    /**
     * Tells user they have option to go back.
     */
    public void goBackOptionPrompt(){
        System.out.println("Or, if you wish to return, type 'back' to go back");
    }

    /**
     * Tells user they logged in to what account type using what username.
     * @param accountType The correct account type.
     * @param userName Their username.
     */
    public void displayLoginSuccess(String accountType, String userName){
        System.out.println("Logged into " + accountType + " account, username: " + userName);
    }

    /**
     * Tells user to enter their account type during account creation.
     */
    public void enterActTypePrompt() {
        System.out.println("Enter account type (speaker/organizer/attendee):");
    }

    /**
     * Tells the user the account type they entered is invalid.
     * @param accountType The invalid account type they entered.
     */
    public void invalidActTypePrompt(String accountType) {
        System.out.println(accountType + " is not a valid account type. Please try again");
    }
    /**
     * Tells user to enter their account name during account creation.
     */
    public void enterActNamePrompt() {
        System.out.println("Enter account name:");
    }
    /**
     * Tells user to the account name they entered in not valid and to try again.
     */
    public void invalidActNamePrompt() {
        System.out.println("Account name taken or invalid name, please try a different name.");
    }
    /**
     * Tells the user they successfully created an account of what type and their username.
     * @param accountType The valid account type they entered.
     * @param accountName The valid account type they entered.
     */
    public void displaySuccCreation(String accountType, String accountName){
        System.out.println("Successfully created " + accountType + " account, username: " + accountName);
    }

    /**
     * Tells user their input is invalid.
     */
    public void invalidInputPrompt(){
        System.out.println("invalid input");
    }
}
