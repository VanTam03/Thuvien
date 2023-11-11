package DTO;

import java.time.LocalDate;
import java.util.Date;

public class PhieuMuon {
    private String maPhieuMuon;
    private LocalDate ngayMuon;
    private int soNgayMuon;
    private LocalDate hanTraSach;
    private int soLuongSach;
    private String maTaikhoan;
    private String maQuanly;
    private String ghiChu;
    private String trangThai;

    public PhieuMuon() {

    }

    public PhieuMuon(String maPhieuMuon, LocalDate ngayMuon, int soNgayMuon, LocalDate hanTraSach, int soLuongSach, String maTaikhoan, String maQuanly, String ghiChu, String trangThai) {
        this.maPhieuMuon = maPhieuMuon;
        this.ngayMuon = ngayMuon;
        this.soNgayMuon = soNgayMuon;
        this.hanTraSach = hanTraSach;
        this.soLuongSach = soLuongSach;
        this.maTaikhoan = maTaikhoan;
        this.maQuanly = maQuanly;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public int getSoNgayMuon() {
        return soNgayMuon;
    }

    public void setSoNgayMuon(int soNgayMuon) {
        this.soNgayMuon = soNgayMuon;
    }

    public LocalDate getHanTraSach() {
        return hanTraSach;
    }

    public void setHanTraSach(LocalDate hanTraSach) {
        this.hanTraSach = hanTraSach;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
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

    @Override
    public String toString() {
        return "PhieuMuon{" +
                "maPhieuMuon='" + maPhieuMuon + '\'' +
                ", ngayMuon=" + ngayMuon +
                ", soNgayMuon=" + soNgayMuon +
                ", hanTraSach=" + hanTraSach +
                ", soLuongSach=" + soLuongSach +
                ", maTaikhoan='" + maTaikhoan + '\'' +
                ", maQuanly='" + maQuanly + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
