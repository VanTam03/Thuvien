package TEST;

import DAO.ChiTietPhieuNhap_DAO;
import DAO.PhieuNhapSach_DAO;
import DTO.ChiTietPhieuNhapSach;
import DTO.PhieuNhapSach;

public class test {
    public static void main(String[] args) {
      //  PhieuNhapSach_DAO.getInstance().XuatExcelPhieuNhap("PN002", "D:\\OneDrive\\Máy tính");
       // System.out.println(ChiTietPhieuNhap_DAO.getInstance().selectAllById("PN002"));
        ChiTietPhieuNhapSach chiTietPhieuNhap = new ChiTietPhieuNhapSach("PN003", "S0025", "Kết cấu bê tông cốt thép - Phần cấu kiện cơ bản", "TG017", "TL099", "NXB Tre", 2023, 50, 20022200);
        ChiTietPhieuNhap_DAO.getInstance().add(chiTietPhieuNhap);
    }
}
