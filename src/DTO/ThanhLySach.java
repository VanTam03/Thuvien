package DTO;

public class ThanhLySach {
    private String maThanhlysach, maSach, lyDothanhly, ngayThanhly, ghiChu;
    private int soLuongsachhong;
    private float tongTienthanhly;

    public ThanhLySach() {

    }

    public ThanhLySach(String maThanhlysach, String maSach, String lyDothanhly, String ngayThanhly, String ghiChu,
            int soLuongsachhong, float tongTienthanhly) {
        this.maThanhlysach = maThanhlysach;
        this.maSach = maSach;
        this.lyDothanhly = lyDothanhly;
        this.ngayThanhly = ngayThanhly;
        this.ghiChu = ghiChu;
        this.soLuongsachhong = soLuongsachhong;
        this.tongTienthanhly = tongTienthanhly;
    }

    public String getMaThanhlysach() {
        return maThanhlysach;
    }

    public void setMaThanhlysach(String maThanhlysach) {
        this.maThanhlysach = maThanhlysach;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getLyDothanhly() {
        return lyDothanhly;
    }

    public void setLyDothanhly(String lyDothanhly) {
        this.lyDothanhly = lyDothanhly;
    }

    public String getNgayThanhly() {
        return ngayThanhly;
    }

    public void setNgayThanhly(String ngayThanhly) {
        this.ngayThanhly = ngayThanhly;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getSoLuongsachhong() {
        return soLuongsachhong;
    }

    public void setSoLuongsachhong(int soLuongsachhong) {
        this.soLuongsachhong = soLuongsachhong;
    }

    public float getTongTienthanhly() {
        return tongTienthanhly;
    }

    public void setTongTienthanhly(float tongTienthanhly) {
        this.tongTienthanhly = tongTienthanhly;
    }

}
