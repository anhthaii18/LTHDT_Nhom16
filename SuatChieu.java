import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SuatChieu implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maSuat;
    private Phim phim;
    private LocalDateTime thoiGian;
    private int tongGhe;
    private int gheTrong;
    private double giaVe;

    public SuatChieu() {}

    public SuatChieu(String maSuat, Phim phim, LocalDateTime thoiGian, int tongGhe, double giaVe) {
        this.maSuat = maSuat;
        this.phim = phim;
        this.thoiGian = thoiGian;
        this.tongGhe = tongGhe;
        this.gheTrong = tongGhe;
        this.giaVe = giaVe;
    }

    public String getMaSuat() { return maSuat; }
    public Phim getPhim() { return phim; }
    public LocalDateTime getThoiGian() { return thoiGian; }
    public int getTongGhe() { return tongGhe; }
    public int getGheTrong() { return gheTrong; }
    public double getGiaVe() { return giaVe; }

    public boolean datVe(int soLuong) {
        if (soLuong <= 0 || soLuong > gheTrong) return false;
        gheTrong -= soLuong;
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%s - %s - %s - Ghế trống: %d - Giá: %.0f", maSuat, phim.getTenPhim(), thoiGian.format(fmt), gheTrong, giaVe);
    }
}
