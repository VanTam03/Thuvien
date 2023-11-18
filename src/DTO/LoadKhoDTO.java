/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author tiena
 */
public class LoadKhoDTO {
    private String maSach;
    private String tensach;
    private int tongSoLuong;
    private int soLuongCon;
    private int soLuongSachHong;

    public LoadKhoDTO(String maSach, String tensach, int tongSoLuong, int soLuongCon, int soLuongSachHong) {
        this.maSach = maSach;
        this.tensach = tensach;
        this.tongSoLuong = tongSoLuong;
        this.soLuongCon = soLuongCon;
        this.soLuongSachHong = soLuongSachHong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public int getSoLuongCon() {
        return soLuongCon;
    }

    public void setSoLuongCon(int soLuongCon) {
        this.soLuongCon = soLuongCon;
    }

    public int getSoLuongSachHong() {
        return soLuongSachHong;
    }

    public void setSoLuongSachHong(int soLuongSachHong) {
        this.soLuongSachHong = soLuongSachHong;
    }
    
    
}
