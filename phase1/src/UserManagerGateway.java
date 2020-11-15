import java.io.*;

/**
 * A gateway class for serializing and deserializing UserManagers.
 * @author Zachariah Vincze
 */
public class UserManagerGateway implements IGateway<UserManager> {
    /**
     * Deserializes a UserManager. Creates a new one iff the given filepath does not exist.
     * @param filepath the filepath which points to the serialized object.
     * @return a deserialized UserManager or a new UserManager if the filepath does not exist.
     */
    @Override
    public UserManager read(String filepath) {
        try {
            InputStream file = new FileInputStream(filepath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            UserManager um = (UserManager) input.readObject();
            input.close();
            return um;
        } catch (IOException | ClassNotFoundException e) {
            return new UserManager();
        }
    }

    /**
     * Serializes a UserManager.
     * @param userManager the UserManager to serialize.
     * @param filepath the filepath you wish to write the serialized object to.
     * @throws IOException throws an IOException if the object cannot be written.
     */
    @Override
    public void write(UserManager userManager, String filepath) throws IOException {
        OutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(userManager);
        output.close();
    }
}
