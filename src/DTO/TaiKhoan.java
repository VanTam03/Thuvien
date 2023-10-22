package DTO;

public class TaiKhoan {
    private String maTaikhoan, matKhau, tenNguoidung, ngaySinh, Email, SDT;
    private int gioiTinh, trangThai, soLuongmuon;

    public TaiKhoan() {
    }

    public TaiKhoan(String maTaikhoan, String matKhau, int gioiTinh, int trangThai, int soLuongmuon,
            String tenNguoidung, String ngaySinh, String Email, String SDT) {
        this.maTaikhoan = maTaikhoan;
        this.matKhau = matKhau;
        this.tenNguoidung = tenNguoidung;
        this.ngaySinh = ngaySinh;
        this.Email = Email;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.soLuongmuon = soLuongmuon;
    }

    public String getMaTaikhoan() {
        return maTaikhoan;
    }

    public void setMaTaikhoan(String maTaikhoan) {
        this.maTaikhoan = maTaikhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenNguoidung() {
        return tenNguoidung;
    }

    public void setTenNguoidung(String tenNguoidung) {
        this.tenNguoidung = tenNguoidung;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String sDT) {
        SDT = sDT;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoLuongmuon() {
        return soLuongmuon;
    }

    public void setSoLuongmuon(int soLuongmuon) {
        this.soLuongmuon = soLuongmuon;
    }
}