import java.io.*;

public class MessageManagerGateway implements IGateway<MessageManager> {
    @Override
    public MessageManager read(String filepath) {
        try {
            InputStream file = new FileInputStream(filepath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            MessageManager mm = (MessageManager) input.readObject();
            input.close();
            return mm;
        } catch (IOException | ClassNotFoundException e) {
            return new MessageManager();
        }
    }

    @Override
    public void write(MessageManager messageManager, String filepath) throws IOException {
        OutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(messageManager);
        output.close();
    }
}
