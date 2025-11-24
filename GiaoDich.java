import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class GiaoDich implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private KhachHang kh;
    private SuatChieu suat;
    private int soLuong;
    private LocalDateTime thoiGian;

    public GiaoDich() {}

    public GiaoDich(KhachHang kh, SuatChieu suat, int soLuong) {
        this.id = UUID.randomUUID().toString();
        this.kh = kh;
        this.suat = suat;
        this.soLuong = soLuong;
        this.thoiGian = LocalDateTime.now();
    }

    public String getId() { return id; }
    public KhachHang getKh() { return kh; }
    public SuatChieu getSuat() { return suat; }
    public int getSoLuong() { return soLuong; }
    public double getThanhTien() {
        return this.soLuong * this.suat.getGiaVe();
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        double thanhTienHienTai = getThanhTien(); // Lấy giá trị mới nhất
        return String.format("GD-%s | KH: %s | Phim: %s | Suất: %s | SL: %d | Tien: %.0f | %s",
                id.substring(0,8), kh.getHoTen(), suat.getPhim().getTenPhim(),
                suat.getThoiGian().format(fmt), soLuong, thanhTienHienTai, thoiGian.format(fmt));
    }
}


