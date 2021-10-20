package dao;

import connection.MyConnection;
import entity.CT_GioHang;
import entity.GioHang;
import entity.LinhKien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CT_GioHang_DAO {
    private Connection con;

    public CT_GioHang_DAO() {
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
    public List<CT_GioHang> getLS() {
        GioHang_DAO ghDao;
        LinhKien_DAO lkDao;
        List<CT_GioHang> ls = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_CTGH");
            while(rs.next()){
                CT_GioHang ctgh = new CT_GioHang(Integer.parseInt(rs.getString(3)));
                ghDao = new GioHang_DAO();
                lkDao = new LinhKien_DAO();
                LinhKien lk = null; GioHang gh = null;
                gh = ghDao.TimKiemMa(rs.getString(1));
                System.out.println(gh);
                lk = lkDao.TimKiemMa(rs.getString(2));
                System.out.println(lk);
                ctgh.setLinhKien(lk);ctgh.setGioHang(gh);ctgh.setThanhTien(lk.getDonGia()* ctgh.getSoLuong());
                ls.add(ctgh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public boolean addCTGioHang(CT_GioHang ctgh) {
        try {
            PreparedStatement ghAdd = con.prepareStatement("INSERT INTO [dbo].[CT_GioHang]([MAGH],[MALK],[SOLUONG]) VALUES(?,?,?)");
            ghAdd.setString(1,ctgh.getGioHang().getMaGH());
            ghAdd.setString(2,ctgh.getLinhKien().getMaLK());
            ghAdd.setInt(3,ctgh.getSoLuong());

            int n = ghAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteCTGH() {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM CT_GIOHANG");
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteCTGH_TheoMa(String maGH,String maLK) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from CT_GIOHANG where MAGH = ? AND MALK = ?");
            stmt.setString(1, maGH);
            stmt.setString(2, maLK);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateCTGH(String maGH,String maLK,int soLuong) {
        String sql = "update CT_GIOHANG set SOLUONG = ? where MAGH = ? AND MALK = ?";
        try {
            System.out.println("OK1");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,soLuong);
            stmt.setString(2,maGH);
            stmt.setString(3,maLK);

            int n = stmt.executeUpdate();
            System.out.println("OK2");
            if(n > 0){
                System.out.println("OK3");
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<CT_GioHang> getLS(String maGH){
        List<CT_GioHang> ls = new ArrayList<>();
        GioHang_DAO ghDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_GIOHANG where MAGH = ?");
            stmt.setString(1,maGH);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CT_GioHang ct = new CT_GioHang(rs.getInt(3));
                ghDao = new GioHang_DAO();
                LinhKien_DAO lkDao = new LinhKien_DAO();
                LinhKien lk = null; GioHang gh = null;
                gh = ghDao.TimKiemMa(rs.getString(1));
                System.out.println(gh);
                lk = lkDao.TimKiemMa(rs.getString(2));
                System.out.println(lk);
                ct.setLinhKien(lk);ct.setGioHang(gh);ct.setThanhTien(lk.getDonGia()* ct.getSoLuong());
                ls.add(ct);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public CT_GioHang TimTheoMaGH(String maGH){
        GioHang_DAO ghDao;
        CT_GioHang ct =null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_GIOHANG where MAGH = ?");
            stmt.setString(1,maGH);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ct = new CT_GioHang(rs.getInt(3));
                ghDao = new GioHang_DAO();
                LinhKien_DAO lkDao = new LinhKien_DAO();
                LinhKien lk = null; GioHang gh = null;
                gh = ghDao.TimKiemMa(rs.getString(1));
                System.out.println(gh);
                lk = lkDao.TimKiemMa(rs.getString(2));
                System.out.println(lk);
                ct.setLinhKien(lk);ct.setGioHang(gh);ct.setThanhTien(lk.getDonGia()* ct.getSoLuong());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ct;
    }
}
