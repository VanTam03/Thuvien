package DTO;

public class PhanLoaiSach {
    private String maTheloai;
    private String NXB;
    private String maTacgia;
    private String tenTacgia;
    private String tenTheloai;

    public PhanLoaiSach() {

    }

    public PhanLoaiSach(String maTheloai, String NXB, String maTacgia, String tenTacgia, String tenTheloai) {
        this.maTheloai = maTheloai;
        this.NXB = NXB;
        this.maTacgia = maTacgia;
        this.tenTacgia = tenTacgia;
        this.tenTheloai = tenTheloai;
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
