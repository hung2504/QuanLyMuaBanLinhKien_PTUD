package dao;

import connection.MyConnection;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CT_DonDatHang_DAO {
    private Connection con;

    public CT_DonDatHang_DAO() {

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
    public List<CT_DonDatHang> getLS() {
        DonDatHang_DAO ddhDao;
        LinhKien_DAO lkDao;
        GioHang_DAO ghDao = new GioHang_DAO();
        CT_GioHang_DAO ctghDao = new CT_GioHang_DAO();
        List<CT_DonDatHang> ls = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_CTDDH");
            while(rs.next()){
                CT_DonDatHang ctddh = new CT_DonDatHang();
                ddhDao = new DonDatHang_DAO();
                lkDao = new LinhKien_DAO();
                LinhKien lk = null; DonDatHang ddh = null;
                ddh = ddhDao.TimKiemMa(rs.getString(1));
                System.out.println(ddh);
                lk = lkDao.TimKiemMa(rs.getString(2));
                System.out.println(lk);
                ctddh.setLinhKien(lk);ctddh.setDonDatHang(ddh);
                ctddh.setThanhTien(ctghDao.TimTheoMaGH(ghDao.TimKiemMaKH(ddh.getKhachHang().getMaKH()).getMaGH()).getThanhTien());
                ctddh.setSoLuong(ctghDao.TimTheoMaGH(ghDao.TimKiemMaKH(ddh.getKhachHang().getMaKH()).getMaGH()).getSoLuong());
                ls.add(ctddh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public boolean addCTDonDatHang(CT_DonDatHang ctddh) {
        try {
            PreparedStatement ghAdd = con.prepareStatement("INSERT INTO [dbo].[CT_DonDatHang]([MADDH],[MALK],[SOLUONG]) VALUES(?,?,?)");
            ghAdd.setString(1,ctddh.getDonDatHang().getMaDDH());
            ghAdd.setString(2,ctddh.getLinhKien().getMaLK());
            ghAdd.setInt(3,ctddh.getSoLuong());

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
            PreparedStatement stmt = con.prepareStatement("DELETE FROM GT_GIOHANG");
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteCTDH_TheoMa(String maDHH,String maLK) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from CT_DONDATHANG where MADDH = ? AND MALK = ?");
            stmt.setString(1, maDHH);
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
//    public List<CT_GioHang> getLS(String maGH){
//        List<CT_GioHang> ls = new ArrayList<>();
//        GioHang_DAO ghDao;
//        try{
//            PreparedStatement stmt = con.prepareStatement("select * from CT_GIOHANG where MAGH = ?");
//            stmt.setString(1,maGH);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()) {
//                CT_GioHang ct = new CT_GioHang(rs.getInt(3));
//                ghDao = new GioHang_DAO();
//                LinhKien_DAO lkDao = new LinhKien_DAO();
//                LinhKien lk = null; GioHang gh = null;
//                gh = ghDao.TimKiemMa(rs.getString(1));
//                System.out.println(gh);
//                lk = lkDao.TimKiemMa(rs.getString(2));
//                System.out.println(lk);
//                ct.setLinhKien(lk);ct.setGioHang(gh);ct.setThanhTien(lk.getDonGia()* ct.getSoLuong());
//                ls.add(ct);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return ls;
//    }
    public CT_DonDatHang TimTheoMaLK(String maLK){
        DonDatHang_DAO ddhDao;
        LinhKien_DAO lkDao;
        CT_DonDatHang ctdh = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_DONDATHANG where MALK = ?");
            stmt.setString(1,maLK);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ctdh = new CT_DonDatHang();
                ddhDao = new DonDatHang_DAO();
                lkDao = new LinhKien_DAO();
                LinhKien lk = null; DonDatHang ddh = null;
                ddh = ddhDao.TimKiemMa(rs.getString(1));
                System.out.println(ddh);
                lk = lkDao.TimKiemMa(rs.getString(2));
                System.out.println(lk);
                ctdh.setLinhKien(lk);ctdh.setDonDatHang(ddh);
                ctdh.setThanhTien(lk.getDonGia()* rs.getInt(3));
                ctdh.setSoLuong(rs.getInt(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ctdh;
    }
    public List<CT_DonDatHang> getLS(String maDDH){
        DonDatHang_DAO ddhDao;
        LinhKien_DAO lkDao;
        List<CT_DonDatHang> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_DONDATHANG where MADDH = ?");
            stmt.setString(1,maDDH);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CT_DonDatHang ctddh = new CT_DonDatHang();
                ddhDao = new DonDatHang_DAO();
                lkDao = new LinhKien_DAO();
                LinhKien lk = null; DonDatHang ddh = null;
                ddh = ddhDao.TimKiemMa(rs.getString(1));
                System.out.println(ddh);
                lk = lkDao.TimKiemMa(rs.getString(2));
                System.out.println(lk);
                ctddh.setLinhKien(lk);ctddh.setDonDatHang(ddh);
                ctddh.setThanhTien(lk.getDonGia()* rs.getInt(3));
                ctddh.setSoLuong(rs.getInt(3));
                ls.add(ctddh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
}
