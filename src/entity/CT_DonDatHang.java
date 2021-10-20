package entity;

public class CT_DonDatHang {
    private DonDatHang donDatHang;
    private LinhKien linhKien;
    private int soLuong;
    private double thanhTien;

    public CT_DonDatHang() {
        // TODO Auto-generated constructor stub
    }

    public DonDatHang getDonDatHang() {
        return donDatHang;
    }

    public void setDonDatHang(DonDatHang donDatHang) {
        this.donDatHang = donDatHang;
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

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "CT_DonDatHang [donDatHang=" + donDatHang + ", linhKien=" + linhKien + ", soLuong=" + soLuong
                + ", thanhTien=" + thanhTien + "]";
    }
}
