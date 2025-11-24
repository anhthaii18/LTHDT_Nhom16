import java.io.Serializable;

public class QuanTriVien extends Nguoi implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public QuanTriVien() { super(); }

    public QuanTriVien(String id, String hoTen, String sdt, String email, String username, String password) {
        super(id, hoTen, sdt, email);
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public boolean checkPassword(String pw) { return password != null && password.equals(pw); }
}

