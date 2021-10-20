package entity;
import java.sql.Date;

public class LinhKien {
    private String maLK;
    private String tenLK;
    private String baoHanh;
    private Date ngaySX;
    private String tinhTrang;
    private String donViTinh;
    private double donGia;
    private int soLuong;
    private String iMages;

    private LoaiLinhKien loaiLinhKien;
    private NhaSanXuat nhaSanXuat;

    public LinhKien() {
        // TODO Auto-generated constructor stub
    }

    public LinhKien(String maLK, String tenLK, String baoHanh, Date ngaySX, String tinhTrang, String donViTinh,
                    double donGia, int soLuong, String iMages) {
        super();
        this.maLK = maLK;
        this.tenLK = tenLK;
        this.baoHanh = baoHanh;
        this.ngaySX = ngaySX;
        this.tinhTrang = tinhTrang;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.iMages = iMages;
    }

    public String getMaLK() {
        return maLK;
    }

    public void setMaLK(String maLK) {
        this.maLK = maLK;
    }

    public String getTenLK() {
        return tenLK;
    }

    public void setTenLK(String tenLK) {
        this.tenLK = tenLK;
    }

    public String getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(String baoHanh) {
        this.baoHanh = baoHanh;
    }

    public Date getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(Date ngaySX) {
        this.ngaySX = ngaySX;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getiMages() {
        return iMages;
    }

    public void setiMages(String iMages) {
        this.iMages = iMages;
    }

    public LoaiLinhKien getLoaiLinhKien() {
        return loaiLinhKien;
    }

    public void setLoaiLinhKien(LoaiLinhKien loaiLinhKien) {
        this.loaiLinhKien = loaiLinhKien;
    }

    public NhaSanXuat getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    @Override
    public String toString() {
        return "LinhKien [maLK=" + maLK + ", tenLK=" + tenLK + ", baoHanh=" + baoHanh + ", ngaySX=" + ngaySX
                + ", tinhTrang=" + tinhTrang + ", donViTinh=" + donViTinh + ", donGia=" + donGia + ", soLuong="
                + soLuong + ", iMages=" + iMages + ", loaiLinhKien=" + loaiLinhKien + ", nhaSanXuat=" + nhaSanXuat
                + "]";
    }
}
