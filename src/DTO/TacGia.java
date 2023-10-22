package DTO;

public class TacGia {
    private String maTacgia, tenTacgia;
    private int soSach;

    public TacGia() {

    }

    public TacGia(String maTacgia, String tenTacgia, int soSach) {
        this.maTacgia = maTacgia;
        this.tenTacgia = tenTacgia;
        this.soSach = soSach;
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

    public int getSoSach() {
        return soSach;
    }

    public void setSoSach(int soSach) {
        this.soSach = soSach;
    }

}
