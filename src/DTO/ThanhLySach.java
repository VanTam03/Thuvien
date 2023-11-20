package DTO;

import java.time.LocalDate;

public class ThanhLySach {
    private String maThanhLySach;
    private String maQuanLy;
    private String maSach;
    private int soLuongSachHong;
    private String lyDoThanhLy;
    private LocalDate ngayThanhLy;
    private String ghiChu;
    private double tongTienThanhLy;

    public ThanhLySach() {
    }

    public ThanhLySach(String maThanhLySach, String maQuanLy, String maSach, int soLuongSachHong, String lyDoThanhLy, LocalDate ngayThanhLy, String ghiChu, double tongTienThanhLy) {
        this.maThanhLySach = maThanhLySach;
        this.maQuanLy = maQuanLy;
        this.maSach = maSach;
        this.soLuongSachHong = soLuongSachHong;
        this.lyDoThanhLy = lyDoThanhLy;
        this.ngayThanhLy = ngayThanhLy;
        this.ghiChu = ghiChu;
        this.tongTienThanhLy = tongTienThanhLy;
    }

    public String getMaQuanLy() {
        return maQuanLy;
    }

    public void setMaQuanLy(String maQuanLy) {
        this.maQuanLy = maQuanLy;
    }

    public String getMaThanhLySach() {
        return maThanhLySach;
    }

    public void setMaThanhLySach(String maThanhLySach) {
        this.maThanhLySach = maThanhLySach;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuongSachHong() {
        return soLuongSachHong;
    }

    public void setSoLuongSachHong(int soLuongSachHong) {
        this.soLuongSachHong = soLuongSachHong;
    }

    public String getLyDoThanhLy() {
        return lyDoThanhLy;
    }

    public void setLyDoThanhLy(String lyDoThanhLy) {
        this.lyDoThanhLy = lyDoThanhLy;
    }

    public LocalDate getNgayThanhLy() {
        return ngayThanhLy;
    }

    public void setNgayThanhLy(LocalDate ngayThanhLy) {
        this.ngayThanhLy = ngayThanhLy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public double getTongTienThanhLy() {
        return tongTienThanhLy;
    }

    public void setTongTienThanhLy(double tongTienThanhLy) {
        this.tongTienThanhLy = tongTienThanhLy;
    }

    @Override
    public String toString() {
        return "ThanhLySach{" +
                "maThanhLySach='" + maThanhLySach + '\'' +
                ", maSach='" + maSach + '\'' +
                ", soLuongSachHong=" + soLuongSachHong +
                ", lyDoThanhLy='" + lyDoThanhLy + '\'' +
                ", ngayThanhLy=" + ngayThanhLy +
                ", ghiChu='" + ghiChu + '\'' +
                ", tongTienThanhLy=" + tongTienThanhLy +
                '}';
    }


}
