package entity;

public class CT_GioHang {
	private GioHang gioHang;
	private LinhKien linhKien;
	private int soLuong;
	private double thanhTien;

	public CT_GioHang() {
		// TODO Auto-generated constructor stub

	}

	public CT_GioHang(int soLuong) {
		super();
		this.soLuong = soLuong;
	}

	public GioHang getGioHang() {
		return gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
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
		return "CT_GioHang [gioHang=" + gioHang + ", linhKien=" + linhKien + ", soLuong=" + soLuong + ", thanhTien="
				+ thanhTien + "]";
	}



}
