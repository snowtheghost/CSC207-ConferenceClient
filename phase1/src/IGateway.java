import java.io.IOException;

/**
 * A common interface for gateway classes.
 * @param <T> a serializable object type.
 * @author Zachariah Vincze
 */
public interface IGateway<T> {
    /**
     * Deserializes an object and returns its representation.
     * @param filepath the filepath which points to the serialized object.
     * @return the deserialized object iff the filepath was found, create a new object otherwise.
     */
    T read(String filepath);

    /**
     * Serializes an object.
     * @param t the object you wish to serialize (must implement Serializable)
     * @param filepath the filepath you wish to write the serialized object to.
     * @throws IOException throws an IOException if the path cannot be written to.
     */
    void write(T t, String filepath) throws IOException;
}
