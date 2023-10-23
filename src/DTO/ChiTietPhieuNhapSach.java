package DTO;

public class ChiTietPhieuNhapSach {
    private String maPhieunhap;
    private String maSach;
    private String tenSach;
    private String maTacgia;
    private String maTheloai;
    private String NXB;
    private int namXuatban;
    private int soLuongnhap;
    private float giaNhap;

    public ChiTietPhieuNhapSach() {

    }

    public ChiTietPhieuNhapSach(String maPhieunhap, String maSach, String tenSach, String maTacgia, String maTheloai,
            String NXB, int namXuatban, int soLuongnhap, float giaNhap) {
        this.maPhieunhap = maPhieunhap;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTacgia = maTacgia;
        this.maTheloai = maTheloai;
        this.NXB = NXB;
        this.namXuatban = namXuatban;
        this.soLuongnhap = soLuongnhap;
        this.giaNhap = giaNhap;
    }

    public String getMaPhieunhap() {
        return maPhieunhap;
    }

    public void setMaPhieunhap(String maPhieunhap) {
        this.maPhieunhap = maPhieunhap;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaTacgia() {
        return maTacgia;
    }

    public void setMaTacgia(String maTacgia) {
        this.maTacgia = maTacgia;
    }

    public String getMaTheloai() {
        return maTheloai;
    }

    public void setMaTheloai(String maTheloai) {
        this.maTheloai = maTheloai;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String nXB) {
        NXB = nXB;
    }

    public int getNamXuatban() {
        return namXuatban;
    }

    public void setNamXuatban(int namXuatban) {
        this.namXuatban = namXuatban;
    }

    public int getSoLuongnhap() {
        return soLuongnhap;
    }

    public void setSoLuongnhap(int soLuongnhap) {
        this.soLuongnhap = soLuongnhap;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

}
