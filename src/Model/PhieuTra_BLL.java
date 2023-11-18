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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
        String tinhtrangsach = phieuTra_DAL.getTinhTrangNow(id, idbook);
        if(tinhtrangsach.equals(tinhtrang)){
            return false;
        }else{
            phieuTra_DAL.updateTinhTrang(id, idbook, tinhtrang);
            System.out.println(tinhtrangsach);
            if (tinhtrangsach.equals("Không hỏng")){
                if(tinhtrang.equals("Mất sách")){
                    phieuTra_DAL.updateKho(-1, 0, idbook);
                }else{
                    phieuTra_DAL.updateKho(-1, 1, idbook);
                }
            }else if(tinhtrangsach.equals("Bị hỏng")){
                if(tinhtrang.equals("Không hỏng")){
                    phieuTra_DAL.updateKho(1, -1, idbook);
                }else{
                    phieuTra_DAL.updateKho(0, -1, idbook);
                }
            }else{
                if(tinhtrang.equals("Không hỏng")){
                    phieuTra_DAL.updateKho(1, 0, idbook);
                }else{
                    phieuTra_DAL.updateKho(0, 1, idbook);
                }
            }
            
            return true;
        }
    }
    
    public boolean exportToExcel(AbstractMap.SimpleEntry<List<Sach>, List<ChiTietPhieuMuon>> danhSachThanhLy, String filePath, String id) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Phiếu mượn "+id);

            // Tạo hàng đầu tiên (tiêu đề)
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mã Sách");
            headerRow.createCell(1).setCellValue("Tên Sách");
            headerRow.createCell(2).setCellValue("Ngày thực trả");
            headerRow.createCell(3).setCellValue("Tiền phạt");
            headerRow.createCell(4).setCellValue("Tình trạng sách");

            // Duyệt danh sách và thêm dữ liệu vào bảng Excel
            int rowNum = 1;
            List<Sach> sachList = danhSachThanhLy.getKey();
            List<ChiTietPhieuMuon> thanhLyList = danhSachThanhLy.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (ChiTietPhieuMuon chiTietPhieuMuon : thanhLyList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(chiTietPhieuMuon.getMaSach());

                // Find the corresponding Sach
                Sach correspondingSach = findCorrespondingSach(chiTietPhieuMuon.getMaSach(), sachList);
                if (correspondingSach != null) {
                    row.createCell(1).setCellValue(correspondingSach.getTenSach());
                    // Add more data for Sach if needed
                }
                String ngayThanhLyString = chiTietPhieuMuon.getNgayThuctra().format(formatter);
                row.createCell(2).setCellValue(ngayThanhLyString);
                row.createCell(3).setCellValue(chiTietPhieuMuon.getTienPhat());
                row.createCell(4).setCellValue(chiTietPhieuMuon.getTinhTrangSach());
                
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
    public List<PhieuMuon> search (String id) {
        return phieuTra_DAL.search(id);
    }
    
    public List<PhieuMuon> loadPMintoTT(String select){
        return phieuTra_DAL.loadPMintoTT(select);
    }
    
}
