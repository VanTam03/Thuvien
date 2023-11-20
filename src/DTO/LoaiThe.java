package DTO;

public class LoaiThe {
    private String maLoaiThe;
    private String tenLoaiThe;
    private int SoSachDuocMuon;
    private int thoiGianDuocMuonToiDa;
    private double giaTienGiaHan;

    public LoaiThe() {
    }

    public LoaiThe(String maLoaiThe, String tenLoaiThe, int soSachDuocMuon, int thoiGianDuocMuonToiDa, double giaTienGiaHan) {
        this.maLoaiThe = maLoaiThe;
        this.tenLoaiThe = tenLoaiThe;
        SoSachDuocMuon = soSachDuocMuon;
        this.thoiGianDuocMuonToiDa = thoiGianDuocMuonToiDa;
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

    public int getSoSachDuocMuon() {
        return SoSachDuocMuon;
    }

    public void setSoSachDuocMuon(int soSachDuocMuon) {
        SoSachDuocMuon = soSachDuocMuon;
    }

    public int getThoiGianDuocMuonToiDa() {
        return thoiGianDuocMuonToiDa;
    }

    public void setThoiGianDuocMuonToiDa(int thoiGianDuocMuonToiDa) {
        this.thoiGianDuocMuonToiDa = thoiGianDuocMuonToiDa;
    }

    public double getGiaTienGiaHan() {
        return giaTienGiaHan;
    }

    public void setGiaTienGiaHan(double giaTienGiaHan) {
        this.giaTienGiaHan = giaTienGiaHan;
    }
}
