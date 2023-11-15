/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.ThanhLySach_DAO;
import DAO.ThanhLy_DALL;
import DTO.Sach;
import DTO.ThanhLySach;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        }else if(thanhLy_DALL.GetSoLuongSachHongByIdBook(thanhly.getMaSach())< thanhly.getSoLuongSachHong()){
            return false;
        }
        List<Sach> books = thanhLy_DALL.selectidBook();
        for(Sach sach : books){
            if(thanhly.getMaSach().equals(sach.getMaSach())){
                thanhLy_DALL.add(thanhly);
                return true;
            }
        }
        return false;
    }
    
    public boolean Update(ThanhLySach thanhly){
        int total = thanhLy_DALL.GetSoLuongSachHongByIdBook(thanhly.getMaSach())+ thanhLy_DALL.GetSoLuongSachHongByIdThanhLy(thanhly.getMaThanhLySach());
        if(thanhly.getSoLuongSachHong() <= 0){
            return false;
        }
        else if(total < thanhly.getSoLuongSachHong()){
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
    
    public void UpdateKhoAfterAction (ThanhLySach thanhly){
        thanhLy_DALL.UpdateKhoAfterAction(thanhly);
    }
}
