import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuanLyRapChieu {
    private ArrayList<Phim> dsPhim;
    private ArrayList<SuatChieu> dsSuat;
    private ArrayList<KhachHang> dsKhach;
    private ArrayList<GiaoDich> dsGiaoDich;

    private final String FILE_PHIM = "phim.dat";
    private final String FILE_SUAT = "suat.dat";
    private final String FILE_KH = "khachhang.dat";
    private final String FILE_GD = "giaodich.dat";

    public QuanLyRapChieu() {
        dsPhim = FileHelper.readFile(FILE_PHIM);
        dsSuat = FileHelper.readFile(FILE_SUAT);
        dsKhach = FileHelper.readFile(FILE_KH);
        dsGiaoDich = FileHelper.readFile(FILE_GD);
    }

    // ---- Phim ----
    public void themPhim(Phim p) {
        dsPhim.add(p);
        luuPhim();
    }
    public List<Phim> getDsPhim() { return dsPhim; }
    public Optional<Phim> timPhimTheoMa(String ma) {
        return dsPhim.stream().filter(p -> p.getMaPhim().equalsIgnoreCase(ma)).findFirst();
    }
    private void luuPhim() { FileHelper.writeFile(FILE_PHIM, dsPhim); }

    // ---- Suất ----
    public void themSuat(SuatChieu s) {
        dsSuat.add(s);
        luuSuat();
    }
    public List<SuatChieu> getDsSuat() { return dsSuat; }
    public Optional<SuatChieu> timSuatTheoMa(String ma) {
        return dsSuat.stream().filter(s -> s.getMaSuat().equalsIgnoreCase(ma)).findFirst();
    }
    private void luuSuat() { FileHelper.writeFile(FILE_SUAT, dsSuat); }

    // ---- Khách hàng ----
    public void themKhachHang(KhachHang k) {
        dsKhach.add(k);
        luuKhach();
    }
    public List<KhachHang> getDsKhach() { return dsKhach; }
    public Optional<KhachHang> timKhachTheoId(String id) {
        return dsKhach.stream().filter(k -> k.getId().equalsIgnoreCase(id)).findFirst();
    }
    private void luuKhach() { FileHelper.writeFile(FILE_KH, dsKhach); }

    // ---- Giao dịch ----
    public boolean datVe(String khId, String maSuat, int soLuong) {
        Optional<KhachHang> ok = timKhachTheoId(khId);
        Optional<SuatChieu> os = timSuatTheoMa(maSuat);
        if (!ok.isPresent() || !os.isPresent()) return false;
        SuatChieu suat = os.get();
        if (suat.getGheTrong() < soLuong) return false;
        boolean kq = suat.datVe(soLuong);
        if (!kq) return false;
        GiaoDich gd = new GiaoDich(ok.get(), suat, soLuong);
        dsGiaoDich.add(gd);
        luuSuat();
        luuGd();
        return true;
    }
    public List<GiaoDich> getDsGiaoDich() { return dsGiaoDich; }
    private void luuGd() { FileHelper.writeFile(FILE_GD, dsGiaoDich); }

    public long tinhTongDoanhThu() {
        long tongDoanhThu = 0;
        for (GiaoDich gd : dsGiaoDich) {
            // Giả định lớp GiaoDich có phương thức getThanhTien()
            tongDoanhThu += gd.getThanhTien();
        }
        return tongDoanhThu;
    }
    // ---- Dữ liệu mẫu nhanh (giúp demo) ----
    public void taoDuLieuMau() {
        if (!dsPhim.isEmpty() || !dsSuat.isEmpty() || !dsKhach.isEmpty()) return;
        Phim p1 = new Phim("P001", "Hành Trình Kỳ Diệu", 120, "Phiêu lưu");
        Phim p2 = new Phim("P002", "Tình Yêu Mùa Hè", 105, "Lãng mạn");
        themPhim(p1); themPhim(p2);

        SuatChieu s1 = new SuatChieu("S001", p1, LocalDateTime.now().plusDays(1).withHour(14).withMinute(0), 100, 80000);
        SuatChieu s2 = new SuatChieu("S002", p2, LocalDateTime.now().plusDays(1).withHour(16).withMinute(30), 80, 70000);
        themSuat(s1); themSuat(s2);

        KhachHang kh1 = new KhachHang("KH001", "Nguyen Van A", "090900900", "a@example.com");
        themKhachHang(kh1);
    }

}
