/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.PhieuTra_DAL;
import DAO.ThanhLySach_DAO;
import DAO.ThanhLy_DALL;
import DTO.ChiTietPhieuMuon;
import DTO.PhieuMuon;
import DTO.Sach;
import DTO.ThanhLySach;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author tiena
 */
public class PhieuTra_BLL {
    PhieuTra_DAL phieuTra_DAL = new PhieuTra_DAL();
    ThanhLy_DALL thanhLy_DALL = new ThanhLy_DALL();
    public List<PhieuMuon> loaddata(){
        return phieuTra_DAL.loaddata();
    }
    public AbstractMap.SimpleEntry<List<Sach>, List<ChiTietPhieuMuon>> loaddataCT(String id) {
        List<Sach> books = thanhLy_DALL.selectidBook();
        List<ChiTietPhieuMuon> thanhLySachs = phieuTra_DAL.loaddataCT(id);
        return processLists(books, thanhLySachs);
    }

    public static AbstractMap.SimpleEntry<List<Sach>, List<ChiTietPhieuMuon>> processLists(List<Sach> bookList, List<ChiTietPhieuMuon> chitietlist) {
        List<Sach> processedBooks = new ArrayList<>();
        List<ChiTietPhieuMuon> processedExports = new ArrayList<>();

        // Duyệt qua danh sách thanh lý sách
        for (ChiTietPhieuMuon chiTietPhieuMuon : chitietlist) {
            // Duyệt qua danh sách sách
            for (Sach book : bookList) {
                // Nếu mã sách khớp, thêm vào danh sách kết quả
                if (chiTietPhieuMuon.getMaSach().equals(book.getMaSach())) {
                    processedBooks.add(book);
                    processedExports.add(chiTietPhieuMuon);
                    break;  // Dừng vòng lặp khi tìm thấy sự khớp
                }
            }
        }

        // Trả về cả hai danh sách sử dụng SimpleEntry
        return new AbstractMap.SimpleEntry<>(processedBooks, processedExports);
    }
    
    
    
    public void updateTraSach(String id, String idbook, String tinhtrang){
        phieuTra_DAL.updateTraSach(id, idbook, tinhtrang);
        List<ChiTietPhieuMuon> chiTietPhieuMuonss= phieuTra_DAL.loaddataCT(id);
            boolean allNgayThuctraNotNull = true;
            for(ChiTietPhieuMuon chiTietPhieuMuon : chiTietPhieuMuonss){
                if(chiTietPhieuMuon.getNgayThuctra() == null){
                    allNgayThuctraNotNull = false;
                    break;
                }
            }
            if(allNgayThuctraNotNull){
                phieuTra_DAL.updateTTPhieuMuon(id);
            }
    }
    
    public boolean updateChiTietPhieuMuon(String id, String idbook, String tinhtrang){
        System.out.println("-----------------------------------------------------------");
        System.out.println(tinhtrang);
        String tinhtrangsachs = phieuTra_DAL.getTinhTrangNow(id, idbook);
        int commaIndex = tinhtrangsachs.indexOf(',');
        String tinhtrangsach = tinhtrangsachs.substring(0, commaIndex);
        if(tinhtrangsach.equals(tinhtrang)){
            return false;
        }else{
            phieuTra_DAL.updateTinhTrang(id, idbook, tinhtrang);
            System.out.println(tinhtrangsach);
            int matDoHai = 0;
            if ("Mất".equals(tinhtrangsach)) {
                matDoHai = -1;
            } else if ("Hư hỏng mức ít".equals(tinhtrangsach)) {
                matDoHai = 1;
            } else if ("Hư hỏng mức vừa".equals(tinhtrangsach)) {
                matDoHai = 2;
            } else if ("Hư hỏng mức nhiều".equals(tinhtrangsach)) {
                matDoHai = 3;
            } else if ("Hư hỏng mức nghiêm trọng".equals(tinhtrangsach)) {
                matDoHai = 4;
            } else {
                matDoHai = 0;
            }
            System.out.println(matDoHai);
            if (matDoHai == 0){
                if(tinhtrang.equals("Mất")){
                    phieuTra_DAL.updateKho(-1, 0, idbook);
                }else{
                    phieuTra_DAL.updateKho(-1, 1, idbook);
                }
            }else if(matDoHai <0){
                if(tinhtrang.equals("Bình thường")){
                    phieuTra_DAL.updateKho(1, 0, idbook);
                }else{
                    phieuTra_DAL.updateKho(0, 1, idbook);
                }
            }else{
                if(tinhtrang.equals("Mất")){
                    phieuTra_DAL.updateKho(0, -1, idbook);
                }else if(tinhtrang.equals("Bình thường")){
                    phieuTra_DAL.updateKho(1, -1, idbook);
                }
            }
            
            return true;
        }
    }
    
    public boolean exportToExcel(AbstractMap.SimpleEntry<List<Sach>, List<ChiTietPhieuMuon>> danhSachThanhLy, String filePath, String id) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Phiếu xuất "+id);
            
             Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);

            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setFont(titleFont);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 4));
            
            
            
            // Tạo Font và CellStyle cho nội dung
            Font contentFont = workbook.createFont();
            contentFont.setFontHeightInPoints((short) 8);

            CellStyle contentStyle = workbook.createCellStyle();
            contentStyle.setFont(contentFont);

            // Tạo tiêu đề
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(2);
            titleCell.setCellValue("PHIẾU XUẤT");
            titleCell.setCellStyle(titleStyle);

            // Tạo các ô chứa thông tin
            for (int i = 1; i <= 4; i++) {
                Row row = sheet.createRow(i);
                Cell cell = row.createCell(0);
                cell.setCellStyle(contentStyle);
                switch (i) {
                    case 1:
                        
                        cell.setCellValue("TRUNG TÂM HỌC LIỆU TRƯỜNG ĐẠI HỌC SÀI GÒN");
                        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
                        break;
                    case 2:
                        
                        cell.setCellValue("Địa chỉ: 273 An Dương Vương, Phường 3, Quận 5,Thành phố Hồ Chí Minh");
                        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
            
                        break;
                    case 3:
                        
                        cell.setCellValue("Điện thoại: (028)38354004");
                        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
            
                        break;
                    case 4:
                        
                        cell.setCellValue("Email: tt_hoclieu@sgu.edu.vn");
                        sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
                        break;
                }
            }
            
            // Tạo hàng đầu tiên (tiêu đề)
            Row headerRow = sheet.createRow(6);
            headerRow.createCell(0).setCellValue("Mã Sách");
            headerRow.createCell(1).setCellValue("Tên Sách");
            headerRow.createCell(2).setCellValue("Ngày thực trả");
            headerRow.createCell(3).setCellValue("Tiền phạt");
            headerRow.createCell(4).setCellValue("Tình trạng sách");

            // Duyệt danh sách và thêm dữ liệu vào bảng Excel
            int rowNum = 7;
            List<Sach> sachList = danhSachThanhLy.getKey();
            List<ChiTietPhieuMuon> thanhLyList = danhSachThanhLy.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            double total = 0;

            for (ChiTietPhieuMuon chiTietPhieuMuon : thanhLyList) {
                total = total + chiTietPhieuMuon.getTienPhat();
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(chiTietPhieuMuon.getMaSach());

                // Find the corresponding Sach
                Sach correspondingSach = findCorrespondingSach(chiTietPhieuMuon.getMaSach(), sachList);
                if (correspondingSach != null) {
                    row.createCell(1).setCellValue(correspondingSach.getTenSach());
                    // Add more data for Sach if needed
                }
                if(chiTietPhieuMuon.getNgayThuctra() != null){
                    String ngayThanhLyString = chiTietPhieuMuon.getNgayThuctra().format(formatter);
                    row.createCell(2).setCellValue(ngayThanhLyString);
                }else{
                    String ngayThanhLyString = "Chưa trả";
                    row.createCell(2).setCellValue(ngayThanhLyString);
                }
                
                row.createCell(3).setCellValue(chiTietPhieuMuon.getTienPhat());
                row.createCell(4).setCellValue(chiTietPhieuMuon.getTinhTrangSach());
                
                // Add more data for ThanhLySach

                // Continue with other columns...
            }
            Row row = sheet.createRow(rowNum);
            row.createCell(3).setCellValue("Tổng tiền phạt");
            row.createCell(4).setCellValue(total);
            
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
        int numberOfColumns7 = sheet.getRow(6).getPhysicalNumberOfCells();
        for (int i = 0; i < numberOfColumns7; i++) {
            sheet.autoSizeColumn(i);
        }
        int numberOfColumns8 = sheet.getRow(7).getPhysicalNumberOfCells();
        for (int i = 0; i < numberOfColumns8; i++) {
            sheet.autoSizeColumn(i);
        }
    }
    public List<PhieuMuon> search (String id) {
        return phieuTra_DAL.search(id);
    }
    
    public List<PhieuMuon> loadPMintoTT(String select){
        return phieuTra_DAL.loadPMintoTT(select);
    }
    
}
