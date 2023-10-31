package DTO;

public class TaiKhoan {
    private String maTaikhoan, matKhau, loaitaikhoan, tenNguoidung, ngaySinh, Email, SDT, gioiTinh;
    private int  trangThai, soLuongmuon;

    public TaiKhoan() {
    }

    public TaiKhoan(String maTaikhoan, String matKhau, String loaitaikhoan, String tenNguoidung, String ngaySinh, String gioitinh, String email, String sdt, int trangThai, int soLuongmuon) {
        this.maTaikhoan = maTaikhoan;
        this.matKhau = matKhau;
        this.loaitaikhoan = loaitaikhoan;
        this.tenNguoidung = tenNguoidung;
        this.ngaySinh = ngaySinh;
        this.Email = Email;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.soLuongmuon = soLuongmuon;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
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

    public int getSoLuongmuon() {
        return soLuongmuon;
    }

    public void setSoLuongmuon(int soLuongmuon) {
        this.soLuongmuon = soLuongmuon;
    }
}