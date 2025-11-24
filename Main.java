import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static QuanLyRapChieu ql = new QuanLyRapChieu();
    // Admin tạm: username admin / password admin
    static final String ADMIN_USER = "admin";
    static final String ADMIN_PASS = "admin";

    public static void main(String[] args) {
        // tạo dữ liệu mẫu nếu rỗng để dễ demo
        ql.taoDuLieuMau();

        while (true) {
            System.out.println("\n=== HỆ THỐNG QUẢN LÝ RẠP CHIẾU PHIM ===");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Khách hàng (đăng ký / đặt vé)");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String ch = sc.nextLine();
            if (ch.equals("1")) adminMenu();
            else if (ch.equals("2")) userMenu();
            else if (ch.equals("0")) {
                System.out.println("Thoát chương trình. Tạm biệt!");
                break;
            } else System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // ---------- ADMIN ----------
    static void adminMenu() {
        System.out.print("Username: "); String u = sc.nextLine();
        System.out.print("Password: "); String p = sc.nextLine();
        if (!ADMIN_USER.equals(u) || !ADMIN_PASS.equals(p)) {
            System.out.println("Đăng nhập thất bại.");
            return;
        }
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Thêm phim");
            System.out.println("2. Danh sách phim");
            System.out.println("3. Thêm suất chiếu");
            System.out.println("4. Danh sách suất chiếu");
            System.out.println("5. Danh sách giao dịch");
            System.out.println("6. Tính tổng doanh thu 💰");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn: ");
            String c = sc.nextLine();
            switch (c) {
                case "1": themPhim(); break;
                case "2": inPhim(); break;
                case "3": themSuat(); break;
                case "4": inSuat(); break;
                case "5": inGiaoDich(); break;
                case "6": tinhDoanhThu(); break;
                case "0": return;
                default: System.out.println("Không hợp lệ."); break;
            }
        }
    }

    static void themPhim() {
        System.out.print("Mã phim: "); String ma = sc.nextLine();
        System.out.print("Tên phim: "); String ten = sc.nextLine();
        System.out.print("Thời lượng (phút): "); int tl = Integer.parseInt(sc.nextLine());
        System.out.print("Mô tả: "); String moTa = sc.nextLine();
        Phim p = new Phim(ma, ten, tl, moTa);
        ql.themPhim(p);
        System.out.println("Đã thêm phim.");
    }

    static void inPhim() {
        List<Phim> ds = ql.getDsPhim();
        System.out.println("=== DANH SÁCH PHIM ===");
        ds.forEach(System.out::println);
    }

    static void themSuat() {
        System.out.print("Mã suất: "); String ma = sc.nextLine();
        System.out.print("Mã phim (ví dụ P001): "); String maPhim = sc.nextLine();
        var op = ql.timPhimTheoMa(maPhim);
        if (!op.isPresent()) {
            System.out.println("Không tìm thấy phim.");
            return;
        }
        Phim p = op.get();
        System.out.print("Thời gian (yyyy-MM-dd HH:mm): ");
        String tg = sc.nextLine();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(tg, fmt);
        } catch (Exception e) {
            System.out.println("Sai định dạng thời gian.");
            return;
        }
        System.out.print("Tổng ghế: "); int tong = Integer.parseInt(sc.nextLine());
        System.out.print("Giá vé: "); double gia = Double.parseDouble(sc.nextLine());
        SuatChieu s = new SuatChieu(ma, p, time, tong, gia);
        ql.themSuat(s);
        System.out.println("Đã thêm suất chiếu.");
    }

    static void inSuat() {
        List<SuatChieu> ds = ql.getDsSuat();
        System.out.println("=== DANH SÁCH SUẤT CHIẾU ===");
        ds.forEach(System.out::println);
    }

    static void inGiaoDich() {
        System.out.println("=== LỊCH SỬ GIAO DỊCH ===");
        ql.getDsGiaoDich().forEach(System.out::println);
    }
    static void tinhDoanhThu() {
        double tong = ql.tinhTongDoanhThu();
        System.out.println("=== TỔNG DOANH THU HIỆN TẠI ===");
        System.out.println("👉 " + tong + " VND");
    }

    // ---------- USER ----------
    static void userMenu() {
        while (true) {
            System.out.println("\n--- KHÁCH HÀNG ---");
            System.out.println("1. Đăng ký khách hàng");
            System.out.println("2. Danh sách phim & suất");
            System.out.println("3. Đặt vé");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine();
            switch (c) {
                case "1": dangKy(); break;
                case "2": { inPhim(); inSuat(); break; }
                case "3": datVe(); break;
                case "0": return;
                default: System.out.println("Không hợp lệ."); break;
            }
        }
    }

    static void dangKy() {
        System.out.print("Mã KH (ví dụ KH002): "); String id = sc.nextLine();
        System.out.print("Họ tên: "); String ten = sc.nextLine();
        System.out.print("SĐT: "); String sdt = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        KhachHang kh = new KhachHang(id, ten, sdt, email);
        ql.themKhachHang(kh);
        System.out.println("Đăng ký thành công.");
    }

    static void datVe() {
        System.out.print("Mã KH: "); String id = sc.nextLine();
        var ok = ql.timKhachTheoId(id);
        if (!ok.isPresent()) {
            System.out.println("Không tìm thấy KH. Vui lòng đăng ký trước.");
            return;
        }
        System.out.print("Mã suất: "); String maSuat = sc.nextLine();
        var os = ql.timSuatTheoMa(maSuat);
        if (!os.isPresent()) {
            System.out.println("Không tìm thấy suất.");
            return;
        }
        SuatChieu s = os.get();
        System.out.println("Thông tin suất: " + s);
        System.out.print("Số vé muốn đặt: "); int sl = Integer.parseInt(sc.nextLine());
        if (sl <= 0) { System.out.println("Số vé không hợp lệ."); return; }
        if (s.getGheTrong() < sl) { System.out.println("Không đủ ghế trống."); return; }
        boolean kq = ql.datVe(id, maSuat, sl);
        if (kq) System.out.println("Đặt vé thành công. Tổng: " + (sl * s.getGiaVe()));
        else System.out.println("Đặt vé thất bại.");
    }
}
