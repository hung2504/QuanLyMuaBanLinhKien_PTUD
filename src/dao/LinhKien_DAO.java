package dao;

import connection.MyConnection;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LinhKien_DAO {
    private Connection con;

    public LinhKien_DAO() {
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
    public List<LinhKien> getLS() {
        List<LinhKien> ds = new ArrayList<>();
        LoaiLinhKien_DAO loaiLinhKien_dao;
        NhaSanXuat_DAO nhaSanXuat_dao;
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_LK");
            while(rs.next()){
                LinhKien lk =new LinhKien(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                loaiLinhKien_dao = new LoaiLinhKien_DAO();
                nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiLinhKien llk = null; NhaSanXuat nsx = null;
                llk = loaiLinhKien_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                lk.setLoaiLinhKien(llk);lk.setNhaSanXuat(nsx);
                ds.add(lk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addLinhKien(LinhKien lk) {
        try {
            PreparedStatement lkAdd = con.prepareStatement("INSERT INTO LinhKien ([MALOAI],[MANHASX],[TENLK],[BAOHANH],[NGAYSANXUAT],[TINHTRANG],[DONVITINH],\n" +
                    "[DONGIA],[SOLUONG],[IMAGES]) VALUES(?,?,?,?,?,?,?,?,?,?)");
            lkAdd.setString(1,lk.getLoaiLinhKien().getMaLoai());
            lkAdd.setString(2,lk.getNhaSanXuat().getMaNhaSX());
            lkAdd.setString(3,lk.getTenLK());
            lkAdd.setString(4,lk.getBaoHanh());
            lkAdd.setDate(5,lk.getNgaySX());
            lkAdd.setString(6,lk.getTinhTrang());
            lkAdd.setString(7,lk.getDonViTinh());
            lkAdd.setDouble(8,lk.getDonGia());
            lkAdd.setInt(9,lk.getSoLuong());
            lkAdd.setString(10,lk.getiMages());

            int n = lkAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteLK(String maLK) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from LINHKIEN where MALK = ?");
            stmt.setString(1, maLK);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateLinhKien(String maLK, LinhKien lk) {
        String sql = "update LinhKien set MALOAI = ? ,MANHASX = ?, "
                + "TENLK = ?,BAOHANH = ? ,NGAYSANXUAT = ?,TINHTRANG = ? ,DONVITINH = ? ,DONGIA = ?,SOLUONG = ? ,IMAGES = ? where MALK = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,lk.getLoaiLinhKien().getMaLoai());
            stmt.setString(2,lk.getNhaSanXuat().getMaNhaSX());
            stmt.setString(3,lk.getTenLK());
            stmt.setString(4,lk.getBaoHanh());
            stmt.setDate(5,lk.getNgaySX());
            stmt.setString(6,lk.getTinhTrang());
            stmt.setString(7,lk.getDonViTinh());
            stmt.setDouble(8,lk.getDonGia());
            stmt.setInt(9,lk.getSoLuong());
            stmt.setString(10,lk.getiMages());
            stmt.setString(11, maLK);

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateSoLuong(String maLK,int soLuong) {
        String sql = "update LINHKIEN set SOLUONG = ? where MALK = ?";
        try {
            System.out.println("OK1");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,soLuong);
            stmt.setString(2,maLK);

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
    public LinhKien TimKiemMa(String ma){
        LinhKien lk = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LinhKien where MALK = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lk = new LinhKien(rs.getString(1),rs.getString(4), rs.getString(5), rs.getDate(6),rs.getString(7), rs.getString(8),
                rs.getDouble(9),rs.getInt(10), rs.getString(11));
                LoaiLinhKien_DAO loaiLinhKien_dao = new LoaiLinhKien_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiLinhKien llk = null; NhaSanXuat nsx = null;
                llk = loaiLinhKien_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                lk.setLoaiLinhKien(llk);lk.setNhaSanXuat(nsx);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lk;
    }
    public LinhKien TimKiemTen(String ten){
        LinhKien lk = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LinhKien where TENLK = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lk = new LinhKien(rs.getString(1),rs.getString(4), rs.getString(5), rs.getDate(6),rs.getString(7), rs.getString(8),
                        rs.getDouble(9),rs.getInt(10), rs.getString(11));
                LoaiLinhKien_DAO loaiLinhKien_dao = new LoaiLinhKien_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiLinhKien llk = null; NhaSanXuat nsx = null;
                llk = loaiLinhKien_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                lk.setLoaiLinhKien(llk);lk.setNhaSanXuat(nsx);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lk;
    }
    public List<LinhKien> TimKiemTen1(String ten){
        List<LinhKien> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LINHKIEN where CONTAINS(TENLK, ?)");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LinhKien lk =new LinhKien(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiLinhKien_DAO loaiLinhKien_dao = new LoaiLinhKien_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiLinhKien llk = null; NhaSanXuat nsx = null;
                llk = loaiLinhKien_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                lk.setLoaiLinhKien(llk);lk.setNhaSanXuat(nsx);
                ls.add(lk);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<LinhKien> TimKiemLoai(String maLoai){
        List<LinhKien> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LINHKIEN where MALOAI = ?");
            stmt.setString(1,maLoai);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LinhKien lk =new LinhKien(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiLinhKien_DAO loaiLinhKien_dao = new LoaiLinhKien_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiLinhKien llk = null; NhaSanXuat nsx = null;
                llk = loaiLinhKien_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                lk.setLoaiLinhKien(llk);lk.setNhaSanXuat(nsx);
                ls.add(lk);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<LinhKien> TimKiemNSX(String maNSX){
        List<LinhKien> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LINHKIEN where MANHASX = ?");
            stmt.setString(1,maNSX);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LinhKien lk =new LinhKien(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiLinhKien_DAO loaiLinhKien_dao = new LoaiLinhKien_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiLinhKien llk = null; NhaSanXuat nsx = null;
                llk = loaiLinhKien_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                lk.setLoaiLinhKien(llk);lk.setNhaSanXuat(nsx);
                ls.add(lk);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<LinhKien> TimKiemGia1(double gia1){
        List<LinhKien> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LINHKIEN where DONGIA <= ?");
            stmt.setDouble(1,gia1);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LinhKien lk =new LinhKien(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiLinhKien_DAO loaiLinhKien_dao = new LoaiLinhKien_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiLinhKien llk = null; NhaSanXuat nsx = null;
                llk = loaiLinhKien_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                lk.setLoaiLinhKien(llk);lk.setNhaSanXuat(nsx);
                ls.add(lk);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<LinhKien> TimKiemGia2(double gia1,double gia2){
        List<LinhKien> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LINHKIEN where DONGIA > ? AND DONGIA <= ?");
            stmt.setDouble(1,gia1);
            stmt.setDouble(2,gia2);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LinhKien lk =new LinhKien(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiLinhKien_DAO loaiLinhKien_dao = new LoaiLinhKien_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiLinhKien llk = null; NhaSanXuat nsx = null;
                llk = loaiLinhKien_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                lk.setLoaiLinhKien(llk);lk.setNhaSanXuat(nsx);
                ls.add(lk);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<LinhKien> TimKiemGia3(double gia3){
        List<LinhKien> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LINHKIEN where DONGIA > ?");
            stmt.setDouble(1,gia3);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LinhKien lk =new LinhKien(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiLinhKien_DAO loaiLinhKien_dao = new LoaiLinhKien_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiLinhKien llk = null; NhaSanXuat nsx = null;
                llk = loaiLinhKien_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                lk.setLoaiLinhKien(llk);lk.setNhaSanXuat(nsx);
                ls.add(lk);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
}
