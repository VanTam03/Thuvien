package DTO;

public class ChiTietPhieuMuon {
    private String maPhieumuon;
    private String maSach;
    private String ngayThuctra;
    private String tienPhat;
    private String tinhTrangSach;

    public ChiTietPhieuMuon() {

    }

    public ChiTietPhieuMuon(String maPhieumuon, String maSach, String ngayThuctra, String tienPhat,
            String tinhTrangSach) {
        this.maPhieumuon = maPhieumuon;
        this.maSach = maSach;
        this.ngayThuctra = ngayThuctra;
        this.tienPhat = tienPhat;
        this.tinhTrangSach = tinhTrangSach;
    }

    public String getMaPhieumuon() {
        return maPhieumuon;
    }

    public void setMaPhieumuon(String maPhieumuon) {
        this.maPhieumuon = maPhieumuon;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getNgayThuctra() {
        return ngayThuctra;
    }

    public void setNgayThuctra(String ngayThuctra) {
        this.ngayThuctra = ngayThuctra;
    }

    public String getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(String tienPhat) {
        this.tienPhat = tienPhat;
    }

    public String getTinhTrangSach() {
        return tinhTrangSach;
    }

    public void setTinhTrangSach(String tinhTrangSach) {
        this.tinhTrangSach = tinhTrangSach;
    }

}
