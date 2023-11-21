package Model;

public class TaiKhoan {
  private String tenTaiKhoan;
  private String matKhau;
  private String loaiTK;
  private String tenNguoiDung;
  private String ngaySinh;
  private String gioiTinh;
  private String email;
  private String sdt;
  private int trangThai;
  private int soLuongMuon;
  private String ngayMoThe;
  private String hanSuDung;
  private String diaChi;

  public TaiKhoan() {
  }

  public TaiKhoan(String tenTaiKhoan, String matKhau, String tenNguoiDung, String ngaySinh, String gioiTinh,
      String email, String sdt, int trangThai, int soLuongMuon, String loaiTK, String diaChi, String ngayMoThe, String hanSuDung) {
    this.tenTaiKhoan = tenTaiKhoan;
    this.matKhau = matKhau;
    this.tenNguoiDung = tenNguoiDung;
    this.ngaySinh = ngaySinh;
    this.gioiTinh = gioiTinh;
    this.email = email;
    this.sdt = sdt;
    this.trangThai = trangThai;
    this.soLuongMuon = soLuongMuon;
    this.loaiTK = loaiTK;
    this.diaChi = diaChi; 
    this.ngayMoThe = ngayMoThe;
    this.hanSuDung = hanSuDung;
  }
  
  public String getNgayMoThe() {
    return ngayMoThe;
  }

  public void setNgayMoThe(String ngayMoThe) {
    this.ngayMoThe = ngayMoThe;
  }

  public String getHanSuDung() {
    return hanSuDung;
  }

  public void setHanSuDung(String hanSuDung) {
    this.hanSuDung = hanSuDung;
  }

  public String getDiaChi() {
    return diaChi;
  }

  public void setDiaChi(String diaChi) {
    this.diaChi = diaChi;
  }

  public String getTenTaiKhoan() {
    return tenTaiKhoan;
  }

  public void setTenTaiKhoan(String tenTaiKhoan) {
    this.tenTaiKhoan = tenTaiKhoan;
  }

  public String getMatKhau() {
    return matKhau;
  }

  public void setMatKhau(String matKhau) {
    this.matKhau = matKhau;
  }

  public String getTenNguoiDung() {
    return tenNguoiDung;
  }

  public void setTenNguoiDung(String tenNguoiDung) {
    this.tenNguoiDung = tenNguoiDung;
  }

  public String getNgaySinh() {
    return ngaySinh;
  }

  public void setNgaySinh(String ngaySinh) {
    this.ngaySinh = ngaySinh;
  }

  public String getGioiTinh() {
    return gioiTinh;
  }

  public void setGioiTinh(String gioiTinh) {
    this.gioiTinh = gioiTinh;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSdt() {
    return sdt;
  }

  public void setSdt(String sdt) {
    this.sdt = sdt;
  }

  public int getTrangThai() {
    return trangThai;
  }

  public void setTrangThai(int trangThai) {
    this.trangThai = trangThai;
  }

  public int getSoLuongMuon() {
    return soLuongMuon;
  }

  public void setSoLuongMuon(int soLuongMuon) {
    this.soLuongMuon = soLuongMuon;
  }

  public String getLoaiTK() {
    return loaiTK;
  }

  public void setLoaiTK(String loaiTK) {
    this.loaiTK = loaiTK;
  }
  
}
