package DTO;

public class PhieuMuon {
    private String maPhieuMuon, ngayMuon, maTaikhoan, maQuanly, ghiChu, trangThai;
    private int soNgaymuon;

    public PhieuMuon() {

    }

    public PhieuMuon(String maPhieumuon, String ngayMuon, String maTaikhoan, String maQuanly, String ghiChu,
            String trangThai, int soNgaymuon) {
        this.maPhieuMuon = maPhieumuon;
        this.ngayMuon = ngayMuon;
        this.maTaikhoan = maTaikhoan;
        this.maQuanly = maQuanly;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.soNgaymuon = soNgaymuon;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getMaTaikhoan() {
        return maTaikhoan;
    }

    public void setMaTaikhoan(String maTaikhoan) {
        this.maTaikhoan = maTaikhoan;
    }

    public String getMaQuanly() {
        return maQuanly;
    }

    public void setMaQuanly(String maQuanly) {
        this.maQuanly = maQuanly;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoNgaymuon() {
        return soNgaymuon;
    }

    public void setSoNgaymuon(int soNgaymuon) {
        this.soNgaymuon = soNgaymuon;
    }

}
