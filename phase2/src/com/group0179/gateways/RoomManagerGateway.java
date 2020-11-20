package com.group0179.gateways;

import com.group0179.use_cases.RoomManager;

import java.io.*;

/**
 * A gateway class for serializing and deserializing RoomManagers.
 * @author Zachariah Vincze
 */
public class RoomManagerGateway implements IGateway<RoomManager> {
    /**
     * Deserializes a RoomManager. Creates a new one iff the given filepath does not exist.
     * @param filepath the filepath which points to the serialized object.
     * @return a deserialized RoomManager or a new RoomManager if the filepath does not exist.
     */
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

    /**
     * Serializes a RoomManager.
     * @param roomManager the RoomManager to serialize.
     * @param filepath the filepath you wish to write the serialized object to.
     * @throws IOException throws an IOException if the object cannot be written.
     */
    @Override
    public void write(RoomManager roomManager, String filepath) throws IOException {
        OutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(roomManager);
        output.close();
    }
}
