package TableModel;

import entity.CT_HoaDonNhapHang;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CT_HoaDonNH_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3643059123220537124L;
	private List<CT_HoaDonNhapHang> ds;
    String[] headers = {"STT", "Tên Linh Kiện", "Loại Linh Kiện","Hãng SX","Số Lượng","ĐƠn Giá",
            "Bảo Hành","Thành Tiền"};

    public CT_HoaDonNH_TableModel(List<CT_HoaDonNhapHang> ds) {
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
        CT_HoaDonNhapHang cthdnh = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return cthdnh.getLinhKien().getTenLK();
            case 2:
                return cthdnh.getLinhKien().getLoaiLinhKien().getTenLoai();
            case 3:
                return cthdnh.getLinhKien().getNhaSanXuat().getTenNhaSX();
            case 4:
                return cthdnh.getSoLuong();
            case 5:
                return cthdnh.getLinhKien().getDonGia();
            case 6:
                return cthdnh.getLinhKien().getBaoHanh();
            case 7:
                return cthdnh.getThanhTien();
            default:
                return cthdnh;
        }
    }
}

