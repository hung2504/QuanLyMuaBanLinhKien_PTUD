package TableModel;

import entity.LinhKien;


import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LK_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7564752013607774627L;
	private List<LinhKien> ds;
    String[] headers = {"Mã LK", "Tên LK", "Bảo Hành","Ngày SX","Tình Trạng",
            "Số Lượng","Đơn Vị Tính","Đơn Giá","Loại LK","Nhà SX","Images"};

    public LK_TableModel(List<LinhKien> ds) {
        super();
        this.ds = ds;
    }

    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LinhKien lk = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lk.getMaLK();
            case 1:
                return lk.getTenLK();
            case 2:
                return lk.getBaoHanh();
            case 3:
                return lk.getNgaySX();
            case 4:
                return lk.getTinhTrang();
            case 5:
                return lk.getSoLuong();
            case 6:
                return lk.getDonViTinh();
            case 7:
                return lk.getDonGia();
            case 8:
                return lk.getLoaiLinhKien().getTenLoai();
            case 9:
                return lk.getNhaSanXuat().getTenNhaSX();
            case 10:
                return lk.getiMages();
            default:
                return lk;
        }
    }
}
