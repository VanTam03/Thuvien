package Model;

public class PhanLoaiThe {
  private String maLoaiThe;
  private String tenLoaiThe;
  private int soLuongSachMuon;
  private int thoiGianMuonToiDa;
  private String giaTienGiaHan;
  
  public PhanLoaiThe() {
  }

  public PhanLoaiThe(String maLoaiThe, String tenLoaiThe, int soLuongSachMuon,
      int thoiGianMuonToiDa, String giaTienGiaHan) {
    this.maLoaiThe = maLoaiThe;
    this.tenLoaiThe = tenLoaiThe;
    this.soLuongSachMuon = soLuongSachMuon;
    this.thoiGianMuonToiDa = thoiGianMuonToiDa;
    this.giaTienGiaHan = giaTienGiaHan;
  }

  public String getMaLoaiThe() {
    return maLoaiThe;
  }

  public void setMaLoaiThe(String maLoaiThe) {
    this.maLoaiThe = maLoaiThe;
  }

  public String getTenLoaiThe() {
    return tenLoaiThe;
  }

  public void setTenLoaiThe(String tenLoaiThe) {
    this.tenLoaiThe = tenLoaiThe;
  }

  public int getSoLuongSachMuon() {
    return soLuongSachMuon;
  }

  public void setSoLuongSachMuon(int soLuongSachMuon) {
    this.soLuongSachMuon = soLuongSachMuon;
  }

  public int getThoiGianMuonToiDa() {
    return thoiGianMuonToiDa;
  }

  public void setThoiGianMuonToiDa(int thoiGianMuonToiDa) {
    this.thoiGianMuonToiDa = thoiGianMuonToiDa;
  }

  public String getGiaTienGiaHan() {
    return giaTienGiaHan;
  }

  public void setGiaTienGiaHan(String giaTienGiaHan) {
    this.giaTienGiaHan = giaTienGiaHan;
  }
}
