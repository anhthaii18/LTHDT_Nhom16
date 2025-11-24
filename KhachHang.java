import java.io.Serializable;

public class KhachHang extends Nguoi implements Serializable {
    private static final long serialVersionUID = 1L;

    public KhachHang() {
        super();
    }

    public KhachHang(String id, String hoTen, String sdt, String email) {
        super(id, hoTen, sdt, email);
    }
}