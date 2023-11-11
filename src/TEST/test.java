package TEST;

import DAO.ChiTietPhieuNhap_DAO;
import DAO.PhieuMuon_DAO;
import DAO.PhieuNhapSach_DAO;
import DTO.ChiTietPhieuNhapSach;
import DTO.PhieuMuon;
import DTO.PhieuNhapSach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) {
      //  PhieuNhapSach_DAO.getInstance().XuatExcelPhieuNhap("PN002", "D:\\OneDrive\\Máy tính");
       PhieuMuon_DAO.getInstance().XuatExcelPhieuMuon("PM005", "D:\\OneDrive\\Máy tính");
       // System.out.println(ChiTietPhieuNhap_DAO.getInstance().selectAllById("PN002"));
    //    ChiTietPhieuNhapSach chiTietPhieuNhap = new ChiTietPhieuNhapSach("PN003", "S0025", "Kết cấu bê tông cốt thép - Phần cấu kiện cơ bản", "TG017", "TL099", "NXB Tre", 2023, 50, 20022200);
      //  ChiTietPhieuNhap_DAO.getInstance().add(chiTietPhieuNhap);
//        String dateString = "2023-08-12";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(dateString, formatter);
//        PhieuNhapSach phieuNhapSach = new PhieuNhapSach("",localDate, "NCC008");
//        PhieuNhapSach_DAO.getInstance().add(phieuNhapSach);
        
    }
}
