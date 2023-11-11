package DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DTO.ChiTietPhieuMuon;
import DTO.Sach;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import DTO.PhieuMuon;

import static org.apache.poi.hslf.model.textproperties.TextPropCollection.TextPropType.paragraph;
import static org.apache.poi.ss.usermodel.TableStyleType.headerRow;

public class PhieuMuon_DAO implements DAO_Interface<PhieuMuon> {
    public static PhieuMuon_DAO getInstance() {
        return new PhieuMuon_DAO();
    }

    @Override
    public int add(PhieuMuon phieuMuon) {
        if (phieuMuon.getHanTraSach()==null){
            phieuMuon.setHanTraSach(phieuMuon.getNgayMuon().plusDays(phieuMuon.getSoNgayMuon()));
        }
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[PhieuMuon] (maPhieuMuon, ngayMuon, soNgayMuon, hanTraSach, soLuongSach, maTaikhoan, maQuanly, ghiChu, trangThai) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, phieuMuon.getMaPhieuMuon());
            pst.setDate(2, java.sql.Date.valueOf(phieuMuon.getNgayMuon()));
            pst.setInt(3, phieuMuon.getSoNgayMuon());
            pst.setDate(4, java.sql.Date.valueOf(phieuMuon.getHanTraSach()));
            pst.setInt(5, phieuMuon.getSoLuongSach());
            pst.setString(6, phieuMuon.getMaTaikhoan());
            pst.setString(7, phieuMuon.getMaQuanly());
            pst.setString(8, phieuMuon.getGhiChu());
            pst.setString(9, phieuMuon.getTrangThai());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(PhieuMuon phieuMuon) {
        int rowsAffected = 0;
        if (phieuMuon.getHanTraSach()==null){
            phieuMuon.setHanTraSach(phieuMuon.getNgayMuon().plusDays(phieuMuon.getSoNgayMuon()));
        }
        String sql = "UPDATE dbo.[PhieuMuon] SET ngayMuon = ?, soNgayMuon = ?, hanTraSach = ?, " +
                "soLuongSach = ?, maTaikhoan = ?, maQuanly = ?, ghiChu = ?, trangThai = ? " +
                "WHERE maPhieuMuon = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setDate(1, java.sql.Date.valueOf(phieuMuon.getNgayMuon()));
            pst.setInt(2, phieuMuon.getSoNgayMuon());
            pst.setDate(3, java.sql.Date.valueOf(phieuMuon.getHanTraSach()));
            pst.setInt(4, phieuMuon.getSoLuongSach());
            pst.setString(5, phieuMuon.getMaTaikhoan());
            pst.setString(6, phieuMuon.getMaQuanly());
            pst.setString(7, phieuMuon.getGhiChu());
            pst.setString(8, phieuMuon.getTrangThai());
            pst.setString(9, phieuMuon.getMaPhieuMuon());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int delete(String maPhieuMuon) {
        ChiTietPhieuMuon_DAO.getInstance().delete(maPhieuMuon);
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[PhieuMuon] WHERE maPhieuMuon = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maPhieuMuon);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<PhieuMuon> selectAll() {
        List<PhieuMuon> rowSelected = new ArrayList<>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[PhieuMuon]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String maPhieuMuon = rs.getString("maPhieuMuon");
                LocalDate ngayMuon = rs.getDate("ngayMuon").toLocalDate();
                int soNgayMuon = rs.getInt("soNgayMuon");
                LocalDate hanTraSach = rs.getDate("hanTraSach").toLocalDate();
                int soLuongSach = rs.getInt("soLuongSach");
                String maTaikhoan = rs.getString("maTaikhoan");
                String maQuanly = rs.getString("maQuanly");
                String ghiChu = rs.getString("ghiChu");
                String trangThai = rs.getString("trangThai");
                PhieuMuon phieuMuon = new PhieuMuon(maPhieuMuon, ngayMuon, soNgayMuon, hanTraSach, soLuongSach,
                        maTaikhoan, maQuanly, ghiChu, trangThai);
                rowSelected.add(phieuMuon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public PhieuMuon selectById(String maPhieuMuon) {
        PhieuMuon result = new PhieuMuon();
        String sql = "SELECT * FROM dbo.[PhieuMuon] WHERE maPhieuMuon = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maPhieuMuon);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setMaPhieuMuon(rs.getString("maPhieuMuon"));
                result.setNgayMuon(rs.getDate("ngayMuon").toLocalDate());
                result.setSoNgayMuon(rs.getInt("soNgayMuon"));
                result.setHanTraSach(rs.getDate("hanTraSach").toLocalDate());
                result.setSoLuongSach(rs.getInt("soLuongSach"));
                result.setMaTaikhoan(rs.getString("maTaikhoan"));
                result.setMaQuanly(rs.getString("maQuanly"));
                result.setGhiChu(rs.getString("ghiChu"));
                result.setTrangThai(rs.getString("trangThai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<String> selectAllMaCanBo() {
        List<String > rowSelected = new ArrayList<>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT DISTINCT maQuanLy FROM dbo.[PhieuMuon]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                rowSelected.add(rs.getString("maQuanly"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    public int XuatExcelPhieuMuon(String maPhieuMuon, String url) {
        PhieuMuon phieuMuon = PhieuMuon_DAO.getInstance().selectById(maPhieuMuon);
        List<ChiTietPhieuMuon> listChiTietPhieuMuon = ChiTietPhieuMuon_DAO.getInstance().selectAllById(maPhieuMuon);
        XWPFDocument document = new XWPFDocument();

        XWPFParagraph logo = document.createParagraph();
        XWPFRun logoRun = logo.createRun();
        logoRun.setFontSize(8);
        logoRun.setText("TRUNG TÂM HỌC LIỆU TRƯỜNG ĐẠI HỌC SÀI GÒN");
        logoRun.addBreak();
        logoRun.setText("Địa chỉ: 273 An Dương Vương, Phường 3, Quận 5,Thành phố Hồ Chí Minh");
        logoRun.addBreak();
        logoRun.setText("Điện thoại: (028)38354004");
        logoRun.addBreak();
        logoRun.setText("Email: tt_hoclieu@sgu.edu.vn");
        logoRun.addBreak();

        //title
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setBold(true);
        titleRun.setFontSize(16);
        titleRun.setText("PHIẾU MƯỢN SÁCH");

        //Thong tin nhanh
        XWPFParagraph info = document.createParagraph();
        XWPFRun infoRun = info.createRun();
        infoRun.addBreak();
        infoRun.setText("Mã phiếu mượn: " + phieuMuon.getMaPhieuMuon());
        infoRun.addBreak();
        infoRun.setText("Mã người mượn: " + phieuMuon.getMaTaikhoan());
        infoRun.addBreak();
        infoRun.setText("Số ngày mượn: " + phieuMuon.getSoNgayMuon());
        infoRun.addBreak();
        LocalDate ngayMuon = phieuMuon.getNgayMuon();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngayMuonFormatted = ngayMuon.format(formatter);
        infoRun.setText("Ngày mượn sách: " + ngayMuonFormatted);
        infoRun.addBreak();
        LocalDate ngayTra = phieuMuon.getHanTraSach();
        String ngayTraFormatted = ngayTra.format(formatter);
        infoRun.setText("Ngày trả sách: " + ngayTraFormatted);
        infoRun.addBreak();

        // Bang thong tin chi tiet
        XWPFTable table = document.createTable();
        table.setWidth("100%");
        XWPFTableRow tableRow = table.getRow(0);
        tableRow.getCell(0).setText("STT");
        tableRow.createCell().setText("Mã Sách");
        tableRow.createCell().setText("Tên Sách");
        tableRow.createCell().setText("Tình Trạng Sách");
        tableRow.createCell().setText("Ghi Chú");

        int dem = 1;

        for (ChiTietPhieuMuon chiTietPhieuMuon : listChiTietPhieuMuon) {
            XWPFTableRow dataRow = table.createRow();
            dataRow.getCell(0).setText(String.valueOf(dem));
            dataRow.getCell(1).setText(chiTietPhieuMuon.getMaSach());
            Sach sach = Sach_DAO.getInstance().selectById(chiTietPhieuMuon.getMaSach());
            dataRow.getCell(2).setText(sach.getTenSach());
            dataRow.getCell(3).setText(chiTietPhieuMuon.getTinhTrangSach());
            dem++;
        }
        for (XWPFTableRow row : table.getRows()) {
            row.setHeight((int) (1.5 * 144));
        }

        XWPFParagraph footer = document.createParagraph();
        XWPFRun footerRun = footer.createRun();
        footerRun.setFontSize(11);
        footerRun.addBreak();
        footerRun.addBreak();
        footerRun.addBreak();
        footerRun.setText("Thủ Thư: " + phieuMuon.getMaQuanly());
        footerRun.addTab();
        footerRun.addTab();
        footerRun.setText("Độc giả: " + phieuMuon.getMaTaikhoan());
        footerRun.addBreak();
        footerRun.setText("(ký tên)\t\t\t\t(ký tên) \t ");
        footerRun.addBreak();
        footerRun.addBreak();
        footerRun.addBreak();
        footer.setAlignment(ParagraphAlignment.RIGHT);

        // Thêm ngày in cho footer
        XWPFParagraph dateStamp = document.createParagraph();
        //  dateStamp.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun dateStampRun = dateStamp.createRun();
        dateStampRun.setText("Ngày in: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));


        // Ghi xuống tệp
        String fileName = "PhieuMuonSach" + phieuMuon.getMaPhieuMuon() + ".doc"; // Tên tệp Word
        File destFile = new File(url, fileName);
        try (FileOutputStream fos = new FileOutputStream(destFile)) {
            document.write(fos);
        } catch (Exception e) {
            // Xử lý nếu có lỗi khi ghi xuống tệp
            return 0;
        }
        return 1;
    }

}
