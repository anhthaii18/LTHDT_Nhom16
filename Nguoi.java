import java.io.Serializable;

public class Nguoi implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String id;
    protected String hoTen;
    protected String sdt;
    protected String email;

    public Nguoi() {}

    public Nguoi(String id, String hoTen, String sdt, String email) {
        this.id = id;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
    }

    public String getId() { return id; }
    public String getHoTen() { return hoTen; }
    public String getSdt() { return sdt; }
    public String getEmail() { return email; }

    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s", id, hoTen, sdt, email);
    }
}

