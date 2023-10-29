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
         String sql = "INSERT INTO dbo.[PhieuNhapSach] (maPhieuNhap, ngayNhap, maNhaCungCap) VALUES (?, ?, ?)";
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
         String sql = "UPDATE dbo.[PhieuNhapSach] SET ngayNhap = ?, maNhaCungCap = ? WHERE maPhieuNhap = ?";
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
                 String maNhaCungCap = rs.getString("maNhaCungCap");
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
                 result.setMaNhaCungCap(rs.getString("maNhaCungCap"));
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return result;
     }
     public void XuatExcelPhieuNhap(String maPhieuNhap){
         PhieuNhapSach phieuNhapSach = PhieuNhapSach_DAO.getInstance().selectById(maPhieuNhap);
         ChiTietPhieuNhapSach chiTietPhieuNhapSach = ChiTietPhieuNhap_DAO.getInstance().selectById(maPhieuNhap);
         XWPFDocument document = new XWPFDocument();

         // Thêm logo cho header
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

         LocalDate localDate = phieuNhapSach.getNgayNhap();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         String ngayNhap = localDate.format(formatter);
         infoRun.setText("Ngày nhập sách: " + ngayNhap);
         infoRun.addBreak();
         infoRun.addBreak();

         infoRun.setText("- Mã nhà cung cấp: " + phieuNhapSach.getMaNhaCungCap());
         infoRun.addBreak();
         infoRun.setText("- Mã Sách: " + chiTietPhieuNhapSach.getMaSach());
         infoRun.addBreak();
         infoRun.setText("- Tên Sách: " + chiTietPhieuNhapSach.getTenSach());
         infoRun.addBreak();
         infoRun.setText("- Mã tác giả: " + chiTietPhieuNhapSach.getMaTacGia());
         infoRun.addBreak();
         infoRun.setText("- Mã thể loại: " + chiTietPhieuNhapSach.getMaTheLoai());
         infoRun.addBreak();
         infoRun.setText("- Nhà xuất bản: " + chiTietPhieuNhapSach.getNXB());
         infoRun.addBreak();
         infoRun.setText("- Năm xuất bản: " + chiTietPhieuNhapSach.getNamXuatBan());
         infoRun.addBreak();

         XWPFParagraph info2 = document.createParagraph();
         XWPFRun infoRun2 = info2.createRun();
         infoRun2.setText("Số Lượng nhập: " + chiTietPhieuNhapSach.getSoLuongNhap() + " (sách)");
         infoRun2.addBreak();
         infoRun2.setText("Giá nhập: " + chiTietPhieuNhapSach.getGiaNhap() + " VND");
         infoRun2.addBreak();
         document.getParagraphs().forEach(p -> {
             p.setBorderTop(Borders.SINGLE);
             p.setBorderBottom(Borders.SINGLE);
             p.setBorderLeft(Borders.SINGLE);
             p.setBorderRight(Borders.SINGLE);
         });

         XWPFParagraph footer = document.createParagraph();
         XWPFRun footerRun = footer.createRun();
         footerRun.setFontSize(11);
         footerRun.setText("Nhân viên giao hàng            Nhân viên Thủ Kho");
         footerRun.addBreak();
         footerRun.setText("(ký tên)                        (ký tên)           .");
         footerRun.addBreak();
         footerRun.addBreak();
         footerRun.addBreak();
         footer.setAlignment(ParagraphAlignment.RIGHT);

         // Thêm ngày in cho footer
         XWPFParagraph dateStamp = document.createParagraph();
       //  dateStamp.setAlignment(ParagraphAlignment.RIGHT);
         XWPFRun dateStampRun = dateStamp.createRun();
         dateStampRun.setText("Ngày in: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

         // Lưu tệp Word
         File f = new File("D://PhieuNhapSach.doc");
         try (FileOutputStream fos = new FileOutputStream(f)) {
             document.write(fos);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

 }


 //Bang thong tin chi tiet
 //        XWPFTable table = document.createTable();
 //        XWPFTableRow tableRow = table.getRow(0);
 //        tableRow.getCell(0).setText("Mã Sách");
 //        tableRow.createCell().setText("Tên Sách");
 //        tableRow.createCell().setText("Mã Tác Giả");
 //        tableRow.createCell().setText("Mã Thể Loại");
 //        tableRow.createCell().setText("Nhà Xuất Bản");
 //        tableRow.createCell().setText("Năm Xuất Bản");
 //       // tableRow.createCell().setText("Số Lượng Nhập");
 //        //tableRow.createCell().setText("Giá Nhập");
 //
 //        XWPFTableRow dataRow = table.createRow();
 //
 //        dataRow.getCell(0).setText(maPhieuNhap);
 //        dataRow.getCell(1).setText(chiTietPhieuNhapSach.getTenSach());
 //        dataRow.getCell(2).setText(chiTietPhieuNhapSach.getMaTacGia());
 //        dataRow.getCell(3).setText(chiTietPhieuNhapSach.getMaTheLoai());
 //        dataRow.getCell(4).setText(chiTietPhieuNhapSach.getNXB());
 //        dataRow.getCell(5).setText(String.valueOf(chiTietPhieuNhapSach.getNamXuatBan()));
