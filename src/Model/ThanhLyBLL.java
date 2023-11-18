/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.ThanhLySach_DAO;
import DAO.ThanhLy_DALL;
import DTO.Sach;
import DTO.ThanhLySach;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author tiena
 */
public class ThanhLyBLL {
    ThanhLy_DALL thanhLy_DALL = new ThanhLy_DALL();
    ThanhLySach_DAO thanhLySach_DAO = new ThanhLySach_DAO();
    public AbstractMap.SimpleEntry<List<Sach>, List<ThanhLySach>> loaddata() {
        List<Sach> books = thanhLy_DALL.selectidBook();
        List<ThanhLySach> thanhLySachs = thanhLySach_DAO.selectAll();
        return processLists(books, thanhLySachs);
    }

    public static AbstractMap.SimpleEntry<List<Sach>, List<ThanhLySach>> processLists(List<Sach> bookList, List<ThanhLySach> thanhLySachList) {
        List<Sach> processedBooks = new ArrayList<>();
        List<ThanhLySach> processedExports = new ArrayList<>();

        // Duyệt qua danh sách thanh lý sách
        for (ThanhLySach thanhLySach : thanhLySachList) {
            // Duyệt qua danh sách sách
            for (Sach book : bookList) {
                // Nếu mã sách khớp, thêm vào danh sách kết quả
                if (thanhLySach.getMaSach().equals(book.getMaSach())) {
                    processedBooks.add(book);
                    processedExports.add(thanhLySach);
                    break;  // Dừng vòng lặp khi tìm thấy sự khớp
                }
            }
        }

        // Trả về cả hai danh sách sử dụng SimpleEntry
        return new AbstractMap.SimpleEntry<>(processedBooks, processedExports);
    }
    
    public boolean Add(ThanhLySach thanhly){
        if(thanhly.getSoLuongSachHong() <= 0){
            return false;
        }else if(thanhLy_DALL.GetSoLuongSachHongByIdBook(thanhly)< thanhly.getSoLuongSachHong()){
            return false;
        }
        List<Sach> books = thanhLy_DALL.selectidBook();
        for(Sach sach : books){
            if(thanhly.getMaSach().equals(sach.getMaSach())){
                thanhLy_DALL.add(thanhly);
                thanhLy_DALL.UpdateKhoAfterAction(thanhly);
                return true;
            }
        }
        return false;
    }
    
    public boolean Update(ThanhLySach thanhly){
        int total = thanhLy_DALL.GetSoLuongSachHongByIdBook(thanhly)+ thanhLy_DALL.GetSoLuongSachHongByIdThanhLy(thanhly);
        System.out.println(total);
        if(thanhly.getSoLuongSachHong() <= 0){
            return false;
        }
        List<Sach> books = thanhLy_DALL.selectidBook();
        for(Sach sach : books){
            if(thanhly.getMaSach().equals(sach.getMaSach())){
                thanhLy_DALL.Update(thanhly);
                return true;
            }
        }
        return false;
    }
    
    public boolean delete (String id){
        if(thanhLy_DALL.delete(id)){
            return true;
        }
        return false;
    }
    
    public boolean exportToExcel(AbstractMap.SimpleEntry<List<Sach>, List<ThanhLySach>> danhSachThanhLy, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("DanhSachThanhLy");

            // Tạo hàng đầu tiên (tiêu đề)
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mã Thanh Lý Sách");
            headerRow.createCell(1).setCellValue("Mã Sách");
            headerRow.createCell(2).setCellValue("Tên Sách");
            headerRow.createCell(3).setCellValue("Số lượng");
            headerRow.createCell(4).setCellValue("Lý do");
            headerRow.createCell(5).setCellValue("Ngày thanh lý");
            headerRow.createCell(6).setCellValue("Ghi chú");
            headerRow.createCell(7).setCellValue("Tổng tiền");

            // Duyệt danh sách và thêm dữ liệu vào bảng Excel
            int rowNum = 1;
            List<Sach> sachList = danhSachThanhLy.getKey();
            List<ThanhLySach> thanhLyList = danhSachThanhLy.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (ThanhLySach thanhLy : thanhLyList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(thanhLy.getMaThanhLySach());
                row.createCell(1).setCellValue(thanhLy.getMaSach());

                // Find the corresponding Sach
                Sach correspondingSach = findCorrespondingSach(thanhLy.getMaSach(), sachList);
                if (correspondingSach != null) {
                    row.createCell(2).setCellValue(correspondingSach.getTenSach());
                    // Add more data for Sach if needed
                }

                row.createCell(3).setCellValue(thanhLy.getSoLuongSachHong());
                row.createCell(4).setCellValue(thanhLy.getLyDoThanhLy());
                String ngayThanhLyString = thanhLy.getNgayThanhLy().format(formatter);
                row.createCell(5).setCellValue(ngayThanhLyString);
                row.createCell(6).setCellValue(thanhLy.getGhiChu());
                row.createCell(7).setCellValue(thanhLy.getTongTienThanhLy());
                // Add more data for ThanhLySach

                // Continue with other columns...
            }
             // Tự động điều chỉnh chiều rộng của các cột
            autoSizeColumns(sheet);

            // Ghi workbook vào một file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Sach findCorrespondingSach(String maSach, List<Sach> sachList) {
        for (Sach sach : sachList) {
            if (maSach.equals(sach.getMaSach())) {
                return sach;
            }
        }
        return null; // If no corresponding Sach is found
    }
    
    private static void autoSizeColumns(Sheet sheet) {
        int numberOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 0; i < numberOfColumns; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
