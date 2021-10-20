package gui;

import com.toedter.calendar.JDateChooser;

import TableModel.CT_GioHang_TableModel;
import TableModel.CT_HoaDonBH_tableModel;
import TableModel.LK_TableModel;
import dao.CT_DonDatHang_DAO;
import dao.CT_GioHang_DAO;
import dao.CT_HoaDonBH_DAO;
import dao.DonDatHang_DAO;
import dao.GioHang_DAO;
import dao.HoaDonBanHang_DAO;
import dao.KhachHang_DAO;
import dao.LinhKien_DAO;
import entity.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BanHang1_Form extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5430744024866983777L;
	JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblTienThuoc,lblTienNhan,lblTienThue,lblTienThoi,lblTongTien,lblTim,
            lblKhachHang,lblMaHD,lblST,lblNgayLap,lblNhanVien,lblDiaChi,lblGioiTinh;
    JTextField txttienThuoc,txtTienNhan,txtThue,txtTienThoi,txtTong,txtTim,
            txtKhachHang,txtSDT,txtMaHD,txtNhanVien,txtDiaChi;
    JButton btnHoaDonMoi,btnIn,btnThoat,btnTim,btnKhachHang;
    JComboBox<String> cbcTim,cbcGT;
    JDateChooser NgayLap;
    String mahd = "";
    NhanVien nv;
    KhachHang kh;
    BanHang1_Form bh;
    double tongTien = 0;
    JTable table1;
	private JButton btnCapNhatSL;
	private JButton btnXoa;
    public BanHang1_Form(){
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_C = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("LẬP HÓA ĐƠN BÁN HÀNG");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,18));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe,BorderLayout.NORTH);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setBorder(new TitledBorder("Thông Tin Hóa Đơn"));
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(550,210));
        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMaHD = new JLabel("Mã HD:"));
        b1.add(txtMaHD = new JTextField());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblNgayLap = new JLabel("Ngày Lập HD:"));
        NgayLap = new JDateChooser();
        NgayLap.setDateFormatString("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayLap.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1.add(NgayLap);
        b.add(Box.createVerticalStrut(10));

        Box b11;
        b.add(b11 = Box.createHorizontalBox());
        b11.add(lblNhanVien = new JLabel("Nhân Viên Lập HD: "));
        b11.add(txtNhanVien = new JTextField());
        b.add(Box.createVerticalStrut(10));
        b.add(b2 = Box.createHorizontalBox());
        b2.add(btnKhachHang = new JButton("Khách Hàng"));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblKhachHang = new JLabel("Khách Hàng: "));
        b3.add(txtKhachHang = new JTextField());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblGioiTinh = new JLabel("Giới Tính: "));
        b3.add(cbcGT = new JComboBox<>());
        cbcGT.addItem("Nam");
        cbcGT.addItem("Nữ");
        cbcGT.setPreferredSize(new Dimension(180,20));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblST = new JLabel("Điện Thoại: "));
        b4.add(txtSDT = new JTextField());
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblDiaChi = new JLabel("Địa Chỉ: "));
        b4.add(txtDiaChi = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMaHD.setPreferredSize(lblNhanVien.getPreferredSize());
        lblNgayLap.setPreferredSize(lblNhanVien.getPreferredSize());
        lblKhachHang.setPreferredSize(lblNhanVien.getPreferredSize());
        lblST.setPreferredSize(lblNhanVien.getPreferredSize());

        JPanel pnThuoc = new JPanel();
        pnThuoc.setBorder(new TitledBorder("Danh Sách Linh Kiện"));
        pnThuoc.setLayout(new BorderLayout());
        JPanel pnThuoc_N = new JPanel();
        JPanel pnthuoc_C = new JPanel();
        JPanel pnthuoc_S = new JPanel();
        JButton btnChonHang = new JButton("Chọn Mua");
        btnChonHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/datmua.png")));
        btnChonHang.setEnabled(false);
        pnthuoc_S.add(btnChonHang);
        Box t = Box.createHorizontalBox();
        t.setPreferredSize(new Dimension(500,25));
        t.add(lblTim = new JLabel("Tìm Linh Kiện Theo: "));
        t.add(cbcTim = new JComboBox<>());
        cbcTim.setPreferredSize(new Dimension(130,20));
        t.add(Box.createHorizontalStrut(20));
        t.add(txtTim = new JTextField());
        t.add(Box.createHorizontalStrut(20));
        t.add(btnTim = new JButton("Tìm Kiếm"));
        btnTim.setIcon(new ImageIcon(getClass().getResource("/images_menu/datmua.png")));
        List<LinhKien> ls = new ArrayList<>();
        LinhKien_DAO lkDao = new LinhKien_DAO();
        LK_TableModel model = new LK_TableModel(lkDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = (String) formatter.format(NgayLap.getDate());
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    btnChonHang.setEnabled(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(650,150));
        pnThuoc_N.add(t);
        pnthuoc_C.add(sc);

        pnThuoc.add(pnThuoc_N,BorderLayout.NORTH);
        pnThuoc.add(pnthuoc_C,BorderLayout.CENTER);
        pnThuoc.add(pnthuoc_S,BorderLayout.SOUTH);
        pnThongTin.add(b);

        pnNorth.add(pnThuoc,BorderLayout.EAST);
        pnNorth.add(pnThongTin,BorderLayout.CENTER);
        txtMaHD.setEditable(false);
        if(nv != null){
            txtNhanVien.setEditable(false);
            txtNhanVien.setText(nv.getTenNV());
        }

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        JPanel pnCen1  =new JPanel();
        JPanel pnCen2 = new JPanel();
        pnCen1.setBorder(new TitledBorder("Danh Sách Linh Kiện Đã Chọn"));
        pnCen2.setBorder(new TitledBorder("Tác Vụ"));
        List<CT_HoaDonBanHang> ls1 = new ArrayList<>();
        CT_HoaDonBH_tableModel model1 = new CT_HoaDonBH_tableModel(ls1);
        table1 = new JTable();
        table1.setModel(model1);
        //Sự kiện Table
        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table1.getSelectedRow();
                if(r != -1){
                    btnCapNhatSL.setEnabled(true);
                    btnXoa.setEnabled(true);

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane sc1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc1.setPreferredSize(new Dimension(850,100));

        pnCen1.add(sc1);
        
        Box e = Box.createVerticalBox();
        e.setPreferredSize(new Dimension(200,100));
        btnCapNhatSL = new JButton("Cập Nhật Số Lượng");
        btnXoa = new JButton("     Xóa Sản Phẩm     ");
        btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        btnCapNhatSL.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));
        e.add(btnCapNhatSL);
        e.add(Box.createVerticalStrut(20));
		e.add(btnXoa);
		pnCen2.add(e);
        pnCenter.add(pnCen1,BorderLayout.CENTER);
        pnCenter.add(pnCen2,BorderLayout.EAST);

        //pnSouth
        pnSouth = new JPanel();
        pnSouth.setLayout(new BorderLayout());
        Box s,s1,s2,s3;
        s = Box.createVerticalBox();
        s.setPreferredSize(new Dimension(1000,100));
        s.add(s1 = Box.createHorizontalBox());
        s1.add(lblTienThuoc = new JLabel("Tổng Tiền Linh Kiện: "));
        s1.add(txttienThuoc = new JTextField(40));
        txttienThuoc.setEditable(false);
        s1.add(Box.createHorizontalStrut(50));
        s1.add(lblTienNhan = new JLabel("Tiền Nhận: "));
        s1.add(txtTienNhan = new JTextField(40));
        s.add(Box.createVerticalStrut(7));

        s.add(s2 = Box.createHorizontalBox());
        s2.add(lblTienThue = new JLabel("Thuế GTGT: "));
        s2.add(txtThue = new JTextField());
        txtThue.setEditable(false);
        s2.add(Box.createHorizontalStrut(55));
        s2.add(lblTienThoi = new JLabel("Tiền Trả Lại: "));
        s2.add(txtTienThoi = new JTextField(40));
        txtTienThoi.setEditable(false);
        s.add(Box.createVerticalStrut(10));

        s.add(s3 = Box.createHorizontalBox());
        s3.add(lblTongTien = new JLabel("Tổng Tiền HD: "));
        s3.add(txtTong = new JTextField());
        txtTong.setEditable(false);
        s3.add(Box.createHorizontalStrut(50));
        s3.add(btnHoaDonMoi = new JButton("Tạo Hóa Đơn Mới"));
        s.add(Box.createVerticalStrut(10));

        Box d;
        d = Box.createVerticalBox();
        d.setPreferredSize(new Dimension(150,30));
        d.add(btnIn = new JButton("In Hóa Đơn"));
        d.add(Box.createVerticalStrut(15));
        d.add(btnThoat = new JButton("Quay Lại     "));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/quaylai.png")));
        btnIn.setIcon(new ImageIcon(getClass().getResource("/images_menu/printer.png")));

        pnSouth.add(s,BorderLayout.WEST);
        pnSouth.add(d,BorderLayout.EAST);
        pnSouth.setBorder(new TitledBorder("Chi tiết hóa đơn"));

        lblTienNhan.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTienThoi.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTienThue.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTongTien.setPreferredSize(lblTienThuoc.getPreferredSize());


        List<CT_HoaDonBanHang> list = new ArrayList<>();
        table.addMouseListener(new MouseListener() {
            double tongTien = 0;
            @Override
            public void mouseClicked(MouseEvent e) {

                }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        btnKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DS_KhachHang_Form ds = new DS_KhachHang_Form();
                ds.setVisible(true);
                ds.banhang = bh;
            }
        });
        DecimalFormat df = new DecimalFormat("#,###.00 VND");
        txtTienNhan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            	
//	                if(Double.parseDouble(txtTienNhan.getText().trim())) < tongTien)){
//	                    JOptionPane.showMessageDialog(null,"Số tiền nhận phải lớn hơn số tiền hóa đơn");
//	                }else{
//	                    txtTienThoi.setText(String.valueOf(df.format(Double.parseDouble(txtTienNhan.getText().trim()) - Double.parseDouble(txtTong.getText().trim()))));
//	                }
            }
            
        });
       
        btnHoaDonMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txttienThuoc.setText("");
                txtTong.setText("");
                txtKhachHang.setText("");
                txtDiaChi.setText("");
                txtSDT.setText("");
                txtTienNhan.setText("");
                txtTienThoi.setText("");
                List<CT_HoaDonBanHang> ls = new ArrayList<>();
                table1.setModel(new CT_HoaDonBH_tableModel(ls));
            }
        });
        btnIn.addActionListener(new ActionListener() {
        	private JasperPrint jprint;
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CT_GioHang_DAO ct = new CT_GioHang_DAO();
				GioHang_DAO ghDao = new GioHang_DAO();
				KhachHang_DAO khDao = new KhachHang_DAO();
				HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
				CT_HoaDonBH_DAO ctbhDao = new CT_HoaDonBH_DAO();
				DonDatHang_DAO ddhDao = new DonDatHang_DAO();
				CT_DonDatHang_DAO ctddh = new CT_DonDatHang_DAO();
				
				try {
					mahd = hdDao.getMa();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(ddhDao.TimTheoMaKH(kh.getMaKH()) != null) {
					HoaDonBanHang hd = hdDao.TimKiemMa(mahd);
					List<CT_DonDatHang> lst = ctddh.getLS(ddhDao.TimTheoMaKH(kh.getMaKH()).getMaDDH());
					for (CT_DonDatHang ctgh : lst) {
						CT_HoaDonBanHang ctbh = new CT_HoaDonBanHang(ctgh.getSoLuong());
						ctbh.setHoaDonBanHang(hd);
						ctbh.setLinhKien(ctgh.getLinhKien());
						ctbh.setThanhTien(ctgh.getThanhTien());
						if(ctbhDao.addCTHoaDonBH(ctbh)) {
							System.out.println(ctbh);
						}
						System.out.println(ctgh);
					}
				}else {
					HoaDonBanHang hd = hdDao.TimKiemMa(mahd);
					List<CT_GioHang> list = ct.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH());
					for (CT_GioHang ctgh : list) {
						CT_HoaDonBanHang ctbh = new CT_HoaDonBanHang(ctgh.getSoLuong());
						ctbh.setHoaDonBanHang(hd);
						ctbh.setLinhKien(ctgh.getLinhKien());
						ctbh.setThanhTien(ctgh.getThanhTien());
						if(ctbhDao.addCTHoaDonBH(ctbh)) {
							System.out.println(ctbh);
						}
						System.out.println(ctgh);
					}
				}
				
				try {					
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyCuaHangLinhKien;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT hd.MAHDBH,hd.NGAYLAPHD,nv.TENNV,kh.TENKH,kh.DIENTHOAI,kh.DIACHI ,lk.TenLK,llk.TENLOAI,nsx.TENNHASX,\r\n" + 
							"cthd.SOLUONG,lk.DonGia,lk.BAOHANH,cthd.SOLUONG * lk.DONGIA AS ThanhTien\r\n" + 
							"FROM [dbo].[CT_HoaDonBanHang] cthd JOIN [dbo].[HoaDonBanHang] hd ON cthd.MAHDBH = hd.MAHDBH\r\n" + 
							"JOIN [dbo].[NhanVien] NV ON HD.MANV = NV.MANV\r\n" + 
							"JOIN [dbo].[KhachHang] kh ON hd.MAKH =kh.MAKH\r\n" + 
							"JOIN [dbo].[LinhKien] lk ON cthd.MaLK = lk.MALK\r\n" + 
							"JOIN [dbo].[LoaiLinhKien] llk ON llk.MALOAI = lk.MALOAI\r\n" + 
							"JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = lk.MANHASX\r\n" + 
							"WHERE hd.MAHDBH = "+"'"+mahd+"'";
					JasperDesign jdesign = JRXmlLoader.load("E:\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\src\\BaoCao_Jasper\\BaoCaoBanHang.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);
					
					ct.deleteCTGH();
					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                TrangChu_Form tc = new TrangChu_Form();
                add(tc);
                validate();
            }
        });
        CT_GioHang_DAO ctghDao = new CT_GioHang_DAO();
        //Sự kiện chọn mua
        btnChonHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(kh != null){
                    if(r != -1){
                        int sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm muốn mua: ",
                                "Nhập số lượng", JOptionPane.INFORMATION_MESSAGE));
                        System.out.println(kh);
                        btnChonHang.setEnabled(false);
                        table.clearSelection();
                        GioHang_DAO ghDao = new GioHang_DAO();
                        CT_GioHang_DAO ctGioHangDao = new CT_GioHang_DAO();
                        LinhKien lk; GioHang gh;
                        System.out.println("KTGH: "+ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()).size());
                        if(sl > lkDao.TimKiemTen(table.getValueAt(r,1).toString()).getSoLuong()){
                            JOptionPane.showMessageDialog(null,"Không đủ số lượng linh kiện");
                        }
                        else{
                            if(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()).size() != 0){
                                for (CT_GioHang ct: ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())) {
                                    System.out.println(ct);
                                    if(!ct.getLinhKien().getMaLK().equalsIgnoreCase(lkDao.TimKiemTen(table.getValueAt(r,1).toString()).getMaLK())){
                                        gh = ghDao.TimKiemMaKH(kh.getMaKH());
                                        System.out.println("GioHang-----:"+gh);
                                        System.out.println(gh);
                                        lk = lkDao.TimKiemTen(table.getValueAt(r,1).toString());
                                        System.out.println(lk);
                                        CT_GioHang ctgh = new CT_GioHang(sl);
                                        ctgh.setLinhKien(lk);ctgh.setGioHang(gh);ctgh.setThanhTien(lk.getDonGia()* ctgh.getSoLuong());
                                        if(ctGioHangDao.addCTGioHang(ctgh)){
                                            try {
                                                table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                                                table.setModel(new LK_TableModel(lkDao.getLS()));
                                                tongTien+= ctgh.getThanhTien();
                                                txttienThuoc.setText(String.valueOf(df.format(tongTien)));
                                                txtThue.setText("4%");
                                                txtTong.setText(String.valueOf(df.format(tongTien + tongTien*0.04)));
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    }
                                    else {
                                        ctghDao.updateCTGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH(),lkDao.TimKiemTen(table.getValueAt(r,1).toString()).getMaLK(),sl+ct.getSoLuong());
                                        table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                                        int sl1 = lkDao.TimKiemTen(table.getValueAt(r,1).toString()).getSoLuong();
                                        lkDao.updateSoLuong(lkDao.TimKiemTen(table.getValueAt(r,1).toString()).getMaLK(),sl1 - sl);
                                        table.setModel(new LK_TableModel(lkDao.getLS()));
                                        tongTien+= ct.getThanhTien();
                                        txttienThuoc.setText(String.valueOf(df.format(tongTien)));
                                        txtThue.setText("4%");
                                        txtTong.setText(String.valueOf(df.format(tongTien + tongTien*0.04)));
                                    }
                                }
                            }else{
                                System.out.println("null");
                                gh = ghDao.TimKiemMaKH(kh.getMaKH());
                                System.out.println("GioHang-----:"+gh);
                                System.out.println(gh);
                                lk = lkDao.TimKiemTen(table.getValueAt(r,1).toString());
                                System.out.println(lk);
                                CT_GioHang ctgh = new CT_GioHang(sl);

                                ctgh.setLinhKien(lk);ctgh.setGioHang(gh);ctgh.setThanhTien(lk.getDonGia()* ctgh.getSoLuong());

                                if(ctGioHangDao.addCTGioHang(ctgh)){
                                    try {
                                        table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                                        table.setModel(new LK_TableModel(lkDao.getLS()));
                                        tongTien+= ctgh.getThanhTien();
                                        txttienThuoc.setText(String.valueOf(df.format(tongTien)));
                                        txtThue.setText("4%");
                                        txtTong.setText(String.valueOf(df.format(tongTien + tongTien*0.04)));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Chưa nhập thông tin khách hàng!");
                    txtKhachHang.requestFocus();
                }
            }
        });
        
        //Sự kiện cập nhật
        btnCapNhatSL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table1.getSelectedRow();
                btnCapNhatSL.setEnabled(false);
                btnXoa.setEnabled(false);
                if(r != -1) {
                    int sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng cập nhật: ",
                            "Nhập số lượng", JOptionPane.INFORMATION_MESSAGE));
                    CT_GioHang_DAO ctGioHangDao = new CT_GioHang_DAO();
                    GioHang_DAO ghDao = new GioHang_DAO();
                    LinhKien lk = lkDao.TimKiemTen(table1.getValueAt(r,1).toString());
                    CT_GioHang ct = ctGioHangDao.TimTheoMaGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH());
                    double thanhtientruoc = Double.parseDouble(table1.getValueAt(r, 7).toString());
                    if (ctGioHangDao.updateCTGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH(),lkDao.TimKiemTen(table1.getValueAt(r,1).toString()).getMaLK(),sl)){
                        try {
//                            System.out.println("ok");
                        	
                            int sl1 = lk.getSoLuong()+ct.getSoLuong() - sl;
                            System.out.println(sl1);
                            lkDao.updateSoLuong(lk.getMaLK(),sl1);
                            table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                            double thanhtiensau = Double.parseDouble(table1.getValueAt(r, 7).toString());
                            tongTien = tongTien - thanhtientruoc + thanhtiensau;
                            txttienThuoc.setText(String.valueOf(df.format(tongTien)));
                            txtThue.setText("4%");
                            txtTong.setText(String.valueOf(df.format(tongTien + tongTien*0.04)));
                            table.setModel(new LK_TableModel(lkDao.getLS()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                table1.clearSelection();
            }
        });
        //Sự kiện xóa sản phẩm
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table1.getSelectedRow();
                btnXoa.setEnabled(false);
                btnCapNhatSL.setEnabled(false);
                if(r != -1) {
                    CT_GioHang_DAO ctGioHangDao = new CT_GioHang_DAO();
                    GioHang_DAO ghDao = new GioHang_DAO();
                    LinhKien_DAO linhKien_dao = new LinhKien_DAO();
                    int soluong = ctGioHangDao.TimTheoMaGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()).getSoLuong();
                    String malk = linhKien_dao.TimKiemTen(table1.getValueAt(r,1).toString()).getMaLK();
                    if (ctGioHangDao.deleteCTGH_TheoMa(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH(),linhKien_dao.TimKiemTen(table1.getValueAt(r,1).toString()).getMaLK())){
                        try {
                        	tongTien = tongTien - Double.parseDouble(table1.getValueAt(r, 7).toString());
                        	txttienThuoc.setText(String.valueOf(df.format(tongTien)));
                            txtThue.setText("4%");
                            txtTong.setText(String.valueOf(df.format(tongTien + tongTien*0.04)));
                            table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                            if(lkDao.updateSoLuong(malk,soluong+lkDao.TimKiemMa(malk).getSoLuong())){
                                table.setModel(new LK_TableModel(lkDao.getLS()));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }

}
