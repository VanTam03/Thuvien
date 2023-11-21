package DAO;

import DTO.KhoSach;

import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    public int SoLuongSachCon(){
        int dem=0;
        List<KhoSach> rowSelected = KhoSach_DAO.getInstance().selectAll();
        for (KhoSach khoSach : rowSelected){
            dem+=khoSach.getSoLuongCon();
        }
        return dem;
    }
    public int SoLuongSachHong(){
        int dem=0;
        List<KhoSach> rowSelected = KhoSach_DAO.getInstance().selectAll();
        for (KhoSach khoSach : rowSelected){
            dem+=khoSach.getSoLuongSachHong();
        }
        return dem;
    }
    public int SoLuongTong(){
        int dem=0;
        List<KhoSach> rowSelected = KhoSach_DAO.getInstance().selectAll();
        for (KhoSach khoSach : rowSelected){
            dem+=khoSach.getTongSoLuong();
        }
        return dem;
    }
    public List<KhoSach> SachHong(){
        List<KhoSach> rowSelected = KhoSach_DAO.getInstance().selectAll();
        for (KhoSach khoSach : rowSelected){
            if (khoSach.getSoLuongSachHong()==0){
                rowSelected.remove(khoSach);
            }
        }
        return rowSelected;
    }
    public List<KhoSach> SachCon(){
        List<KhoSach> rowSelected = KhoSach_DAO.getInstance().selectAll();
        for (KhoSach khoSach : rowSelected){
            if (khoSach.getSoLuongCon()==0){
                rowSelected.remove(khoSach);
            }
        }
        return rowSelected;
    }
    public List<KhoSach> ToanBoSach(){
        List<KhoSach> rowSelected = KhoSach_DAO.getInstance().selectAll();
        return rowSelected;
    }

}
