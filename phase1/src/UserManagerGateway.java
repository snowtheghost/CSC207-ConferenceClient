import java.io.*;

public class UserManagerGateway implements IGateway<UserManager> {
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

    @Override
    public void write(UserManager userManager, String filepath) throws IOException {
        OutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(userManager);
        output.close();
    }
}
