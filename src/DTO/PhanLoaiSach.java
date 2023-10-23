package DTO;

public class PhanLoaiSach {
    private String maTheloai, tenTheloai, NXB, maTacgia, tenTacgia;

    public PhanLoaiSach() {

    }

    public PhanLoaiSach(String maTheloai, String tenTheloai, String NXB, String maTacgia, String tenTacgia) {
        this.maTacgia = maTheloai;
        this.tenTheloai = tenTheloai;
        this.NXB = NXB;
        this.maTacgia = maTacgia;
        this.tenTacgia = tenTacgia;
    }

    public String getMaTheloai() {
        return maTheloai;
    }

    public void setMaTheloai(String maTheloai) {
        this.maTheloai = maTheloai;
    }

    public String getTenTheloai() {
        return tenTheloai;
    }

    public void setTenTheloai(String tenTheloai) {
        this.tenTheloai = tenTheloai;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String nXB) {
        NXB = nXB;
    }

    public String getMaTacgia() {
        return maTacgia;
    }

    public void setMaTacgia(String maTacgia) {
        this.maTacgia = maTacgia;
    }

    public String getTenTacgia() {
        return tenTacgia;
    }

    public void setTenTacgia(String tenTacgia) {
        this.tenTacgia = tenTacgia;
    }

}
