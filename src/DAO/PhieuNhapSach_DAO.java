 package DAO;
 import DTO.ChiTietPhieuNhapSach;
 import DTO.PhieuNhapSach;

 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;

 import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
 import org.apache.poi.ss.usermodel.*;
 import org.apache.poi.util.Units;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 import org.apache.poi.xwpf.usermodel.*;
 import org.apache.poi.xwpf.usermodel.XWPFPicture;
 import org.apache.poi.xwpf.usermodel.XWPFRun;

 import javax.swing.*;

 import static javax.print.attribute.standard.MediaSizeName.D;

 public class PhieuNhapSach_DAO implements DAO_Interface<PhieuNhapSach> {
     public static PhieuNhapSach_DAO getInstance() {
         return new PhieuNhapSach_DAO();
     }

     @Override
     public int add(PhieuNhapSach phieuNhapSach) {
         int rowsAffected = 0;
         String sql = "INSERT INTO dbo.[PhieuNhapSach] (maPhieuNhap, ngayNhap, NhaCungCap) VALUES (?, ?, ?)";
         try (Connection conn = KetNoiSQL.getConnection();
              PreparedStatement pst = conn.prepareStatement(sql)) {
             pst.setString(1, phieuNhapSach.getMaPhieuNhap());
             pst.setDate(2, java.sql.Date.valueOf(phieuNhapSach.getNgayNhap()));
             pst.setString(3, phieuNhapSach.getMaNhaCungCap());
             rowsAffected = pst.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return rowsAffected;
     }

     @Override
     public int update(PhieuNhapSach phieuNhapSach) {
         int rowsAffected = 0;
         String sql = "UPDATE dbo.[PhieuNhapSach] SET ngayNhap = ?, NhaCungCap = ? WHERE maPhieuNhap = ?";
         try (Connection conn = KetNoiSQL.getConnection();
              PreparedStatement pst = conn.prepareStatement(sql)) {
             pst.setDate(1, java.sql.Date.valueOf(phieuNhapSach.getNgayNhap()));
             pst.setString(2, phieuNhapSach.getMaNhaCungCap());
             pst.setString(3, phieuNhapSach.getMaPhieuNhap());
             rowsAffected = pst.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return rowsAffected;
     }

     @Override
     public int delete(String maPhieuNhap) {
         ChiTietPhieuNhap_DAO.getInstance().delete(maPhieuNhap);
         int rowsAffected = 0;
         String sql = "DELETE FROM dbo.[PhieuNhapSach] WHERE maPhieuNhap = ?";
         try (Connection conn = KetNoiSQL.getConnection();
              PreparedStatement pst = conn.prepareStatement(sql)) {
             pst.setString(1, maPhieuNhap);
             rowsAffected = pst.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return rowsAffected;
     }

     @Override
     public List<PhieuNhapSach> selectAll() {
         List<PhieuNhapSach> rowSelected = new ArrayList<PhieuNhapSach>();
         try (Connection conn = KetNoiSQL.getConnection();
              PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[PhieuNhapSach]");
              ResultSet rs = pst.executeQuery()) {
             while (rs.next()) {
                 String maPhieuNhap = rs.getString("maPhieuNhap");
                 LocalDate ngayNhap = rs.getDate("ngayNhap").toLocalDate();
                 String maNhaCungCap = rs.getString("NhaCungCap");
                 PhieuNhapSach phieuNhapSach = new PhieuNhapSach(maPhieuNhap, ngayNhap, maNhaCungCap);
                 rowSelected.add(phieuNhapSach);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return rowSelected;
     }

     @Override
     public PhieuNhapSach selectById(String maPhieuNhap) {
         PhieuNhapSach result = new PhieuNhapSach();
         String sql = "SELECT * FROM dbo.[PhieuNhapSach] WHERE maPhieuNhap = ?";
         try (Connection conn = KetNoiSQL.getConnection();
              PreparedStatement pst = conn.prepareStatement(sql)) {
             pst.setString(1, maPhieuNhap);
             ResultSet rs = pst.executeQuery();
             if (rs.next()) {
                 result.setMaPhieuNhap(rs.getString("maPhieuNhap"));
                 result.setNgayNhap(rs.getDate("ngayNhap").toLocalDate());
                 result.setMaNhaCungCap(rs.getString("NhaCungCap"));
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return result;
     }
     public int XuatExcelPhieuNhap(String maPhieuNhap, String url){
         PhieuNhapSach phieuNhapSach = PhieuNhapSach_DAO.getInstance().selectById(maPhieuNhap);
         List<ChiTietPhieuNhapSach> ListChiTietPhieuNhapSach = ChiTietPhieuNhap_DAO.getInstance().selectAllById(maPhieuNhap);
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

         //title
         XWPFParagraph title = document.createParagraph();
         title.setAlignment(ParagraphAlignment.CENTER);
         XWPFRun titleRun = title.createRun();
         titleRun.setBold(true);
         titleRun.setFontSize(16);
         titleRun.setText("HÓA ĐƠN PHIẾU NHẬP");

         //Thong tin nhanh
         XWPFParagraph info = document.createParagraph();
         XWPFRun infoRun = info.createRun();
         infoRun.addBreak();
         infoRun.setText("Mã phiếu nhập: " + phieuNhapSach.getMaPhieuNhap());
         infoRun.addBreak();
         infoRun.setText("Mã nhà cung cấp: " + phieuNhapSach.getMaNhaCungCap());
         infoRun.addBreak();
         LocalDate localDate = phieuNhapSach.getNgayNhap();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         String ngayNhap = localDate.format(formatter);
         infoRun.setText("Ngày nhập sách: " + ngayNhap);
         infoRun.addBreak();

        // Bang thong tin chi tiet
         XWPFTable table = document.createTable();
         XWPFTableRow tableRow = table.getRow(0);
         tableRow.getCell(0).setText("STT");
         tableRow.createCell().setText("Mã Sách");
         tableRow.createCell().setText("Tên Sách");
         tableRow.createCell().setText("Mã Tác Giả");
         tableRow.createCell().setText("Mã Thể Loại");
         tableRow.createCell().setText("Nhà Xuất Bản");
         tableRow.createCell().setText("Năm Xuất Bản");
         tableRow.createCell().setText("Số Lượng Nhập");
         tableRow.createCell().setText("Giá Nhập");
         XWPFTableRow dataRow0 = table.createRow();
         dataRow0.getCell(0).setText("A");
         dataRow0.getCell(1).setText("B");
         dataRow0.getCell(2).setText("C");
         dataRow0.getCell(3).setText("D");
         dataRow0.getCell(4).setText("E");
         dataRow0.getCell(5).setText("F");
         dataRow0.getCell(6).setText("G");
         dataRow0.getCell(7).setText("1");
         dataRow0.getCell(8).setText("2");
         int dem=1;
         int tongSL=0;
         double tongTien=0;
         for (ChiTietPhieuNhapSach chiTietPhieuNhapSach:ListChiTietPhieuNhapSach){
             XWPFTableRow dataRow = table.createRow();
             dataRow.getCell(0).setText(String.valueOf(dem));
             dataRow.getCell(1).setText(chiTietPhieuNhapSach.getMaSach());
             dataRow.getCell(2).setText(chiTietPhieuNhapSach.getTenSach());
             dataRow.getCell(3).setText(chiTietPhieuNhapSach.getMaTacGia());
             dataRow.getCell(4).setText(chiTietPhieuNhapSach.getMaTheLoai());
             dataRow.getCell(5).setText(chiTietPhieuNhapSach.getNXB());
             dataRow.getCell(6).setText(String.valueOf(chiTietPhieuNhapSach.getNamXuatBan()));
             dataRow.getCell(7).setText(String.valueOf(chiTietPhieuNhapSach.getSoLuongNhap()));
             dataRow.getCell(8).setText(String.valueOf(chiTietPhieuNhapSach.getGiaNhap()));
             tongSL+=chiTietPhieuNhapSach.getSoLuongNhap();
             tongTien+=chiTietPhieuNhapSach.getGiaNhap();
             dem++;
         }
         XWPFTableRow dataRow = table.createRow();
         dataRow.getCell(1).setText("TỔNG: ");
         dataRow.getCell(7).setText(String.valueOf(tongSL));
         dataRow.getCell(8).setText(String.valueOf(tongTien));

         XWPFParagraph footer = document.createParagraph();
         XWPFRun footerRun = footer.createRun();
         footerRun.setFontSize(11);
         footerRun.addBreak();
         footerRun.setText("Nhân viên Thủ Kho");
         footerRun.addBreak();
         footerRun.setText("    (ký tên)");
         footerRun.addBreak();
         footerRun.addBreak();
         footerRun.addBreak();
         footer.setAlignment(ParagraphAlignment.RIGHT);

         // Thêm ngày in cho footer
         XWPFParagraph dateStamp = document.createParagraph();
       //  dateStamp.setAlignment(ParagraphAlignment.RIGHT);
         XWPFRun dateStampRun = dateStamp.createRun();
         dateStampRun.setText("Ngày in: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

         String fileName = "PhieuNhapSach.doc"; // Tên tệp Word
         File destFile = new File(url, fileName);
         try (FileOutputStream fos = new FileOutputStream(destFile)) {
             document.write(fos);
         } catch (Exception e) {
             //e.printStackTrace();
             return 0;
         }
         return 1;
     }

 }



