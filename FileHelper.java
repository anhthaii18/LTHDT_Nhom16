import java.io.*;
import java.util.ArrayList;

public class FileHelper {
    // Ghi object (ArrayList) vào file
    public static <T> void writeFile(String filename, ArrayList<T> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file " + filename + ": " + e.getMessage());
        }
    }

    // Đọc ArrayList từ file, nếu không tồn tại trả về ArrayList rỗng
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> readFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<T>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file " + filename + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
