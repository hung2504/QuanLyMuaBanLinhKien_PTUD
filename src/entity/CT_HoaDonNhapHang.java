package entity;

public class CT_HoaDonNhapHang {
    private HoaDonNhapHang donNhapHang;
    private LinhKien linhKien;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public CT_HoaDonNhapHang() {
        // TODO Auto-generated constructor stub
    }

    public CT_HoaDonNhapHang(int soLuong, double donGia) {
        super();
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    public HoaDonNhapHang getDonNhapHang() {
        return donNhapHang;
    }

    public void setDonNhapHang(HoaDonNhapHang donNhapHang) {
        this.donNhapHang = donNhapHang;
    }

    public LinhKien getLinhKien() {
        return linhKien;
    }

    public void setLinhKien(LinhKien linhKien) {
        this.linhKien = linhKien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "CT_HoaDonNhapHang [donNhapHang=" + donNhapHang + ", linhKien=" + linhKien + ", soLuong=" + soLuong
                + ", donGia=" + donGia + ", thanhTien=" + thanhTien + "]";
    }






}

