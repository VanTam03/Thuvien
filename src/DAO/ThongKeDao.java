package DAO;

import DTO.KhoSach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThongKeDao {
    public static ThongKeDao getInstance() {
        return new ThongKeDao();
    }
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
        // Use iterator to safely remove elements
        Iterator<KhoSach> iterator = rowSelected.iterator();
        while (iterator.hasNext()) {
            KhoSach khoSach = iterator.next();
            if (khoSach.getSoLuongSachHong() == 0) {
                iterator.remove();
            }
        }
        return rowSelected;
    }
    public List<KhoSach> SachCon(){
        List<KhoSach> rowSelected = KhoSach_DAO.getInstance().selectAll();
        // Use iterator to safely remove elements
        Iterator<KhoSach> iterator = rowSelected.iterator();
        while (iterator.hasNext()) {
            KhoSach khoSach = iterator.next();
            if (khoSach.getSoLuongCon() == 0) {
                iterator.remove();
            }
        }
        return rowSelected;
    }
    public List<KhoSach> ToanBoSach(){
        List<KhoSach> rowSelected = KhoSach_DAO.getInstance().selectAll();
        return rowSelected;
    }

}
