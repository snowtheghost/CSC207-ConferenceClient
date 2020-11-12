import java.io.*;

public class RoomManagerGateway implements IGateway<RoomManager> {
    @Override
    public RoomManager read(String filepath) {
        try {
            InputStream file = new FileInputStream(filepath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            RoomManager rm = (RoomManager) input.readObject();
            input.close();
            return rm;
        } catch (IOException | ClassNotFoundException e) {
            return new RoomManager();
        }
    }

    @Override
    public void write(RoomManager roomManager, String filepath) throws IOException {
        OutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(roomManager);
        output.close();
    }
}
