package DTO;

public class KhoSach {
    private String maSach;
    private int tongSoluong, soLuongcon, soLuongsachhong;

    public KhoSach() {

    }

    public KhoSach(String maSach, int tongSoluong, int soLuongcon, int soLuongsachhong) {
        this.maSach = maSach;
        this.tongSoluong = tongSoluong;
        this.soLuongcon = soLuongcon;
        this.soLuongsachhong = soLuongsachhong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getTongSoluong() {
        return tongSoluong;
    }

    public void setTongSoluong(int tongSoluong) {
        this.tongSoluong = tongSoluong;
    }

    public int getSoLuongcon() {
        return soLuongcon;
    }

    public void setSoLuongcon(int soLuongcon) {
        this.soLuongcon = soLuongcon;
    }

    public int getSoLuongsachhong() {
        return soLuongsachhong;
    }

    public void setSoLuongsachhong(int soLuongsachhong) {
        this.soLuongsachhong = soLuongsachhong;
    }

}
