package TEST;

import DAO.ChiTietPhieuNhap_DAO;
import DAO.PhieuNhapSach_DAO;

public class test {
    public static void main(String[] args) {
        PhieuNhapSach_DAO.getInstance().XuatExcelPhieuNhap("PN002", "D:\\OneDrive\\Máy tính");
       // System.out.println(ChiTietPhieuNhap_DAO.getInstance().selectAllById("PN002"));
    }
}
