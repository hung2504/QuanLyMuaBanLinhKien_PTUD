package dao;

import connection.MyConnection;
import entity.LoaiLinhKien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiLinhKien_DAO {
    private Connection con;

    public LoaiLinhKien_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public ResultSet getResultSet(String StoreName)throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName +"}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }
    public List<LoaiLinhKien> getLS() {
        List<LoaiLinhKien> ds = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_LLK");
            while(rs.next()){
                LoaiLinhKien llk = new LoaiLinhKien(rs.getString(1),rs.getString(2));
                ds.add(llk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public LoaiLinhKien TimKiemTen(String ten){
        LoaiLinhKien llk = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LoaiLinhKien where TENLOAI = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                llk = new LoaiLinhKien(rs.getString(1), rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return llk;
    }
    public LoaiLinhKien TimKiemMa(String ma){
        LoaiLinhKien llk = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LoaiLinhKien where MALOAI = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                llk = new LoaiLinhKien(rs.getString(1), rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return llk;
    }
    public boolean addLoaiLinhKien(LoaiLinhKien llk) {
        try {
            PreparedStatement llkAdd = con.prepareStatement("INSERT INTO LoaiLinhKien ([TENLOAI]) VALUES(?)");
            llkAdd.setString(1,llk.getTenLoai());

            int n = llkAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteLLK(String maLLK) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from LoaiLinhKien where MALOAI = ?");
            stmt.setString(1, maLLK);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateLoaiLK(String maLOAI, LoaiLinhKien llk) {
        String sql = "update LoaiLinhKien set TENLOAI = ? where MALOAI = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,llk.getTenLoai());
            stmt.setString(2, maLOAI);

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
