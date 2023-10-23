package DTO;

public class PhieuNhapSach {
    private String maPhieuNhap, maNhacungcap, ngayNhap;

    public PhieuNhapSach() {

    }

    public PhieuNhapSach(String maPhieuNhap, String maNhacungcap, String ngayNhap) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNhacungcap = maNhacungcap;
        this.ngayNhap = ngayNhap;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaNhacungcap() {
        return maNhacungcap;
    }

    public void setMaNhacungcap(String maNhacungcap) {
        this.maNhacungcap = maNhacungcap;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

}
