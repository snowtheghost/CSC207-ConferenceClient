package com.group0179.gateways;

import com.group0179.use_cases.MessageManager;

import java.io.*;

/**
 * A gateway class for serializing and deserializing MessageManagers.
 * @author Zachariah Vincze
 */
public class MessageManagerGateway implements IGateway<MessageManager> {
    /**
     * Deserializes a MessageManager. Creates a new one iff the given filepath does not exist.
     * @param filepath the filepath which points to the serialized object.
     * @return a deserialized MessageManager or a new MessageManager if the filepath does not exist.
     */
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

    /**
     * Serializes a MessageManager.
     * @param messageManager the MessageManager to serialize.
     * @param filepath the filepath you wish to write the serialized object to.
     * @throws IOException throws an IOException if the object cannot be written.
     */
    @Override
    public void write(MessageManager messageManager, String filepath) throws IOException {
        OutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(messageManager);
        output.close();
    }
}
