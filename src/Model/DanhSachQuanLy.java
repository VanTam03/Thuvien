package Model;

import java.util.ArrayList;

import DTO.QuanLy;

public class DanhSachQuanLy {

  private ArrayList<QuanLy> dsQuanLy;

  public DanhSachQuanLy() {
  }

  public DanhSachQuanLy(ArrayList<QuanLy> dsQuanLy) {
    this.dsQuanLy = dsQuanLy;
  }

  public ArrayList<QuanLy> getDsQuanLy() {
    return dsQuanLy;
  }

  public void setDsQuanLy(ArrayList<QuanLy> dsQuanLy) {
    this.dsQuanLy = dsQuanLy;
  }
  
}
