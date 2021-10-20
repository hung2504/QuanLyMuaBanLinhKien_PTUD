package entity;

public class LoaiLinhKien {
    private String maLoai;
    private String tenLoai;

    public LoaiLinhKien() {
        // TODO Auto-generated constructor stub
    }

    public LoaiLinhKien(String maLoai, String tenLoai) {
        super();
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Override
    public String toString() {
        return "LoaiLinhKien [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
    }

}
