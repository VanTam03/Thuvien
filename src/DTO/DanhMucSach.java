package DTO;

public class DanhMucSach {
    private String maDanhmucSach, tenDanhmucSach;

    public DanhMucSach() {

    }

    public DanhMucSach(String maDanhmucSach, String tenDanhmucSach) {
        this.maDanhmucSach = maDanhmucSach;
        this.tenDanhmucSach = tenDanhmucSach;
    }

    public String getMaDanhmucSach() {
        return maDanhmucSach;
    }

    public void setMaDanhmucSach(String maDanhmucSach) {
        this.maDanhmucSach = maDanhmucSach;
    }

    public String getTenDanhmucSach() {
        return tenDanhmucSach;
    }

    public void setTenDanhmucSach(String tenDanhmucSach) {
        this.tenDanhmucSach = tenDanhmucSach;
    }

}
