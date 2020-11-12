import java.io.IOException;

public interface IGateway<T> {
    T read(String filepath);
    void write(T t, String filepath) throws IOException;
}
