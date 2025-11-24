import java.io.Serializable;

public class Phim implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maPhim;
    private String tenPhim;
    private int thoiLuong; // phút
    private String moTa;

    public Phim() {}

    public Phim(String maPhim, String tenPhim, int thoiLuong, String moTa) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.thoiLuong = thoiLuong;
        this.moTa = moTa;
    }

    public String getMaPhim() { return maPhim; }
    public String getTenPhim() { return tenPhim; }
    public int getThoiLuong() { return thoiLuong; }
    public String getMoTa() { return moTa; }

    @Override
    public String toString() {
        return String.format("%s - %s (%d phút) - %s", maPhim, tenPhim, thoiLuong, moTa);
    }
}
