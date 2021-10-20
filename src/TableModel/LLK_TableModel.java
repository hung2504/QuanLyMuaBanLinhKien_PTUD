package TableModel;

import entity.LoaiLinhKien;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LLK_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7438128988031924793L;
	private List<LoaiLinhKien> ds;
    String [] headers = {"STT","Mã Loại Linh Kiện", "Tên Loại Linh Kiện"};

    public LLK_TableModel(List<LoaiLinhKien> ds){
        super();
        this.ds = ds;
    }
    public String getColumnName(int column){
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
        LoaiLinhKien llk = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return llk.getMaLoai();
            case 2:
                return llk.getTenLoai();
            default:
                return llk;
        }


    }
}

