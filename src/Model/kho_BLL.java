/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.Sach_DAO;
import DAO.khosach_DAL;
import DTO.KhoSach;
import DTO.LoadKhoDTO;
import DTO.Sach;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author tiena
 */
public class kho_BLL {
    Sach_DAO sachdao = new Sach_DAO();
    khosach_DAL khodal = new khosach_DAL();
    public List<LoadKhoDTO> loadKho() {
        List<KhoSach> khoSachList = khodal.getAllKhoSach();
        List<Sach> thongTinSachList = sachdao.selectAll();
        List<LoadKhoDTO> sachList = new ArrayList<>();

    // Tạo một Map để lưu thông tin sách dựa trên mã sách
        Map<String, String> maSachToTenSachMap = new HashMap<>();
        for (Sach sach : thongTinSachList) {
            maSachToTenSachMap.put(sach.getMaSach(), sach.getTenSach());
        }

        for (KhoSach kho : khoSachList) {
            String maSach = kho.getMaSach();
            String tenSach = maSachToTenSachMap.get(maSach);

            LoadKhoDTO khoload = new LoadKhoDTO(maSach, tenSach, kho.getTongSoLuong(), kho.getSoLuongCon(), kho.getSoLuongSachHong());
            sachList.add(khoload);
        }

        return sachList;
    }
    
    public boolean update (KhoSach khoSach){
        if(khoSach.getSoLuongCon() < 0 || khoSach.getSoLuongCon() > khoSach.getTongSoLuong() || khoSach.getSoLuongSachHong()< 0 || khoSach.getSoLuongSachHong() > khoSach.getTongSoLuong()){
            return false;
        }else{
            khodal.update(khoSach);
            return true;
        }
    }
    
    public List<LoadKhoDTO> search (String search){
        return khodal.getKhobysearch(search);
    }
    
    public List<LoadKhoDTO> loadKho_into (String condition, String sortOrder){
        if ("Mã sách".equals(condition)){
            return khodal.loadKho_into("masach", sortOrder);
        }else if("Tổng số lượng".equals(condition)){
            return khodal.loadKho_into("tongSoLuong", sortOrder);
        }else if("Số lượng sách còn lại".equals(condition)){
            return khodal.loadKho_into("soLuongCon", sortOrder);
        }else{
            return khodal.loadKho_into("soLuongSachHong", sortOrder);
        }
        
    }
}
