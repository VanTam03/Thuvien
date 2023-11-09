package DAO;
import DTO.ChiTietPhieuNhapSach;
import DTO.KhoSach;
import DTO.Sach;
import DTO.TacGia;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChiTietPhieuNhap_DAO implements DAO_Interface<ChiTietPhieuNhapSach>{
    public static ChiTietPhieuNhap_DAO getInstance(){
        return new ChiTietPhieuNhap_DAO();
    }
    @Override
    public int add(ChiTietPhieuNhapSach chiTietPhieuNhapSach) {
        int rowsAffected=0;
        String sql = "INSERT INTO dbo.[ChiTietPhieuNhapSach] VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            String maPhieuNhap = chiTietPhieuNhapSach.getMaPhieuNhap();
            String maSach = chiTietPhieuNhapSach.getMaSach();
            String tenSach = chiTietPhieuNhapSach.getTenSach();
            String maTacGia = chiTietPhieuNhapSach.getMaTacGia();
            String maTheLoai = chiTietPhieuNhapSach.getMaTheLoai();
            String NXB = chiTietPhieuNhapSach.getNXB();
            int namXuatBan = chiTietPhieuNhapSach.getNamXuatBan();
            int soLuongNhap= chiTietPhieuNhapSach.getSoLuongNhap();
            Double giaNhap = chiTietPhieuNhapSach.getGiaNhap();

            pst.setString(1, maPhieuNhap);
            pst.setString(2, maSach);
            pst.setString(3, tenSach);
            pst.setString(4, maTacGia);
            pst.setString(5, maTheLoai);
            pst.setString(6,NXB);
            pst.setInt(7, namXuatBan);
            pst.setInt(8,soLuongNhap);
            pst.setDouble(9,giaNhap);
            rowsAffected = pst.executeUpdate();

            if (rowsAffected == 1){
                themSachVaoKho (maSach, soLuongNhap);
                //themVaoThongTinSach (maSach, tenSach, maTacGia, maTheLoai, NXB, namXuatBan);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public void themVaoTacGia(String maTacGia, String tenTacGia) {
        String sql = "SELECT * FROM dbo.[TacGia] WHERE maTacGia ='"+maTacGia+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery())  {
            if (!rs.next()) {
                TacGia tacGia = new TacGia (maTacGia, tenTacGia);
                TacGia_DAO.getInstance().add(tacGia);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void themVaoThongTinSach(String maSach, String tenSach, String maTacGia, String tenTacGia, String maTheLoai, String nxb, int namXuatBan) {
        String sql = "SELECT * FROM dbo.[ThongTinSach] WHERE maSach ='"+maSach+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery())  {
            if (!rs.next()) {
                Sach sach = new Sach(maSach, tenSach, maTacGia, tenTacGia, maTheLoai, nxb, namXuatBan);
                Sach_DAO.getInstance().add(sach);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void themSachVaoKho(String maSach, int soLuong) {
        String sql = "SELECT * FROM dbo.[KhoSach] WHERE maSach ='"+maSach+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery())  {
            if (rs.next()) {
                int soLuongCu = rs.getInt("tongSoLuong");
                int soLuongCon = rs.getInt("soLuongCon");
                int soLuongHong = rs.getInt("soLuongSachHong");
                KhoSach khoSach = new KhoSach (maSach, soLuongCu+soLuong, soLuongCon+soLuong, soLuongHong);
                KhoSach_DAO.getInstance().update(khoSach);
            } else {
                KhoSach khoSach = new KhoSach(maSach, soLuong, soLuong , 0);
                KhoSach_DAO.getInstance().add(khoSach);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int update(ChiTietPhieuNhapSach chiTietPhieuNhapSach) {
        int rowsAffected=0;
        String sql = "UPDATE dbo.[ChiTietPhieuNhapSach] SET tenSach = ?, " +
                "maTacGia = ? , maTheLoai = ?, NXB = ?, namXuatBan = ?, soLuongNhap = ?, giaNhap = ? WHERE maPhieuNhap = ? AND maSach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, chiTietPhieuNhapSach.getTenSach());
            pst.setString(2, chiTietPhieuNhapSach.getMaTacGia());
            pst.setString(3, chiTietPhieuNhapSach.getMaTheLoai());
            pst.setString(4, chiTietPhieuNhapSach.getNXB());
            pst.setInt(5, chiTietPhieuNhapSach.getNamXuatBan());
            pst.setInt(6, chiTietPhieuNhapSach.getSoLuongNhap());
            pst.setDouble(7, chiTietPhieuNhapSach.getGiaNhap());
            pst.setString(8, chiTietPhieuNhapSach.getMaPhieuNhap());
            pst.setString(9, chiTietPhieuNhapSach.getMaSach());
            rowsAffected = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }

    // Lưu ý: Việc xóa thông tin sách đồng nghĩa xóa nó trong kho sách.
    @Override
    public int delete(String maPhieuNhap) {
        int rowsAffected =0;
        String sql = "DELETE FROM dbo.[ChiTietPhieuNhapSach] WHERE maPhieuNhap ='"+maPhieuNhap+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            rowsAffected = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public int delete(String maPhieuNhap, String maSach) {
        int rowsAffected =0;
        String sql = "DELETE FROM dbo.[ChiTietPhieuNhapSach] WHERE maPhieuNhap ='"+maPhieuNhap+"' AND maSach='"+maSach+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            rowsAffected = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<ChiTietPhieuNhapSach> selectAll() {
        List<ChiTietPhieuNhapSach> rowSelected = new ArrayList<ChiTietPhieuNhapSach>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[ChiTietPhieuNhapSach]");
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                do {
                    String maPhieuNhap = rs.getString("maPhieuNhap");
                    String maSach = rs.getString("maSach");
                    String tenSach = rs.getString("tenSach");
                    String maTacGia = rs.getString("maTacGia");
                    String maTheLoai = rs.getString("maTheLoai");
                    String NXB = rs.getString("NXB");
                    int namXuatBan = rs.getInt("namXuatBan");
                    int soLuongNhap = rs.getInt("soLuongNhap");
                    double giaTienSach = rs.getDouble("giaNhap");
                    ChiTietPhieuNhapSach book = new ChiTietPhieuNhapSach(maPhieuNhap, maSach, tenSach, maTacGia, maTheLoai, NXB, namXuatBan, soLuongNhap, giaTienSach);
                    rowSelected.add(book);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }
    public List<ChiTietPhieuNhapSach> selectAllById(String maPhieuNhap) {
        List<ChiTietPhieuNhapSach> result = new ArrayList<ChiTietPhieuNhapSach>();
        String sql = "SELECT * FROM dbo.[ChiTietPhieuNhapSach] WHERE maPhieuNhap ='"+maPhieuNhap+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery())  {
            if (rs.next()) {
                do {
                    String maSach = rs.getString("maSach");
                    String tenSach = rs.getString("tenSach");
                    String maTacGia = rs.getString("maTacGia");
                    String maTheLoai = rs.getString("maTheLoai");
                    String NXB = rs.getString("NXB");
                    int namXuatBan = rs.getInt("namXuatBan");
                    int soLuongNhap = rs.getInt("soLuongNhap");
                    double giaTienSach = rs.getDouble("giaNhap");
                    ChiTietPhieuNhapSach book = new ChiTietPhieuNhapSach(maPhieuNhap, maSach, tenSach, maTacGia, maTheLoai, NXB, namXuatBan, soLuongNhap, giaTienSach);
                    result.add(book);
                } while (rs.next());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public ChiTietPhieuNhapSach selectById(String maPhieuNhap) {
        ChiTietPhieuNhapSach result = new ChiTietPhieuNhapSach();
        String sql = "SELECT * FROM dbo.[ChiTietPhieuNhapSach] WHERE maPhieuNhap ='"+maPhieuNhap+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery())  {
            if (rs.next()) {
                do {
                    result.setMaPhieuNhap(rs.getString("maPhieuNhap"));
                    result.setMaSach(rs.getString("maSach"));
                    result.setTenSach(rs.getString("tenSach"));
                    result.setMaTacGia(rs.getString("maTacGia"));
                    result.setMaTheLoai(rs.getString("maTheLoai"));
                    result.setNXB(rs.getString("NXB"));
                    result.setNamXuatBan(rs.getInt("namXuatBan"));
                    result.setSoLuongNhap(rs.getInt("soLuongNhap"));
                    result.setGiaNhap(rs.getDouble("giaNhap"));

                } while (rs.next());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int importExcel(String url){
        try {
            FileInputStream fis = new FileInputStream(url);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    String maPhieuNhap = row.getCell(0).getStringCellValue();
                    String maSach = row.getCell(1).getStringCellValue();
                    String tenSach = row.getCell(2).getStringCellValue();
                    String maTacGia = row.getCell(3).getStringCellValue();
                    String tenTacGia = row.getCell(4).getStringCellValue();
                    String maTheLoai = row.getCell(5).getStringCellValue();
                    String nxb = row.getCell(6).getStringCellValue();
                    int namXuatBan = (int) row.getCell(7).getNumericCellValue();
                    int soLuongNhap = (int) row.getCell(8).getNumericCellValue();
                    double giaNhap = row.getCell(9).getNumericCellValue();
                    themVaoTacGia(maTacGia, tenTacGia);
                    ChiTietPhieuNhapSach chiTietPhieuNhapSach = new ChiTietPhieuNhapSach(maPhieuNhap, maSach, tenSach, maTacGia, maTheLoai, nxb, namXuatBan, soLuongNhap, giaNhap);
                    add(chiTietPhieuNhapSach);
                    themVaoThongTinSach (maSach, tenSach, maTacGia, tenTacGia, maTheLoai, nxb, namXuatBan);
                }
            }

            fis.close();
            workbook.close();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

}
