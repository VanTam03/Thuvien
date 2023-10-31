package DTO;

public class QuanLy {
    private String maQuanly, tenQuanly, matKhau, diaChi, SDT, Email, ngaySinh, gioiTinh;
    private int trangThai;

    public QuanLy() {

    }

    public QuanLy(String maQuanly,  String matKhau,String tenQuanly, String ngaySinh, String gioiTinh, String diaChi,
            String SDT, String Email , int trangThai) {
        this.maQuanly = maQuanly;
        this.tenQuanly = tenQuanly;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public String getMaQuanly() {
        return maQuanly;
    }

    public void setMaQuanly(String maQuanly) {
        this.maQuanly = maQuanly;
    }

    public String getTenQuanly() {
        return tenQuanly;
    }

    public void setTenQuanly(String tenQuanly) {
        this.tenQuanly = tenQuanly;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String sDT) {
        SDT = sDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
