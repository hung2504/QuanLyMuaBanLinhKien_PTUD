package gui;

import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.KhachHang;
import entity.NhaSanXuat;
import entity.NhanVien;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

//
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JRDesignQuery;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;


import connection.MyConnection;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class GD_Chinh extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    JMenu Account;
	public JLabel lb,lbCenter,lbCenter1,lbCenter2,lbCenter3,lbCenter4;
    private JPanel pn_Center,pn_west;
    static TrangChu_Form tc;
    Border bd_cen;
    ImageIcon s[],b[];
    JTree tree;
    KhachHang kh;
    NhanVien nv;

    public GD_Chinh(){
        doShow();
    }
    public void doShow(){
        setSize(1300,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Quản lý mua bán linh kiện máy tính");
        Container cp = getContentPane();
        tc= new TrangChu_Form();

        //pn_North
        JPanel pn_North = new JPanel();
        JPanel pn_Banner = new JPanel();
        pn_North.setLayout(new GridLayout(2,1));
        lb = new JLabel();
        Border bd = BorderFactory.createLineBorder(Color.red);
        b = new ImageIcon[2];
        b[0] = new ImageIcon(getClass().getResource("/images/banner.png"));
        b[1] = new ImageIcon(getClass().getResource("/images/banner1.png"));
        //pn1.setBorder(bd);
        lb.setIcon(new ImageIcon(getClass().getResource("/images/banner2.png")));
        pn_Banner.add(lb);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuTrangChu = new JMenu("TRANG CHỦ");
        menuTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/trangchu11.png")));
        menuTrangChu.setBackground(Color.PINK);
        menuTrangChu.setFont(new Font("Arial",Font.BOLD,16));


        JMenu menuHeThong = new JMenu("HỆ THỐNG");
        menuHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/hethong11.png")));

        menuHeThong.setForeground(Color.RED);
        Font fontMenu = new Font("Arial",Font.BOLD,16);
        menuHeThong.setFont(fontMenu);
        JMenuItem qlnd,t;
        menuHeThong.add(qlnd = new JMenuItem("Phân Quyền Người Dùng"));
        qlnd.setPreferredSize(new Dimension(200,40));
        qlnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/phanquyen.png")));
        menuHeThong.add(t = new JMenuItem("Thoát"));
        t.setPreferredSize(new Dimension(200,40));
        t.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/dangxuat.png")));

        JMenu menuDanhMuc = new JMenu("DANH MỤC");
        menuDanhMuc.setForeground(Color.RED);
        menuDanhMuc.setFont(fontMenu);
        menuDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/danhmuc11.png")));

        JMenu linhKien,nVien;
        JMenuItem kHang,nCungCap,mnLoaiLK,mnNhaSX,mnChucVu;
        menuDanhMuc.add(nVien = new JMenu("Nhân Viên"));
        nVien.add(mnChucVu = new JMenuItem("Chức Vụ"));
        mnChucVu.setPreferredSize(new Dimension(150,40));
        nVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhanvien11.png")));
        nVien.setPreferredSize(new Dimension(200,40));
        menuDanhMuc.add(kHang = new JMenuItem("Khách Hàng"));
        kHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/khachHang11.png")));
        kHang.setPreferredSize(new Dimension(200,40));
        menuDanhMuc.add(nCungCap = new JMenuItem("Nhà Cung Cấp"));
        nCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhaCC11.png")));
        nCungCap.setPreferredSize(new Dimension(200,40));
        menuDanhMuc.add(linhKien = new JMenu("Linh Kiện"));
        linhKien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/linhKien11.png")));
        linhKien.setPreferredSize(new Dimension(200,40));
        linhKien.add(mnLoaiLK = new JMenuItem("Loại Linh Kiện"));
        linhKien.add(mnNhaSX = new JMenuItem("Nhà Sản Xuất"));
        mnLoaiLK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/loailk.png")));
        mnNhaSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhasx.png")));
        mnLoaiLK.setPreferredSize(new Dimension(150,40));
        mnNhaSX.setPreferredSize(new Dimension(150,40));


        JMenu menuQuanLy = new JMenu("XỬ LÝ");
        menuQuanLy.setForeground(Color.RED);
        menuQuanLy.setFont(fontMenu);
        JMenuItem nhapHang,banHang,datHang;
        menuQuanLy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/xuly11.png")));
        menuQuanLy.add(nhapHang = new JMenuItem("Nhập Hàng"));
        nhapHang.setPreferredSize(new Dimension(180,40));
        nhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/import.png")));
        menuQuanLy.add(banHang = new JMenuItem("Bán Hàng"));
        banHang.setPreferredSize(new Dimension(180,40));
        banHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/export.png")));
        menuQuanLy.add(datHang = new JMenuItem("Đặt Hàng"));
        datHang.setPreferredSize(new Dimension(180,40));
        datHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/note.png")));

        JMenu menuBaoCao = new JMenu("BÁO CÁO");
        JMenu hdx,hdn;
        menuBaoCao.setForeground(Color.RED);
        menuBaoCao.setFont(fontMenu);
        JMenuItem htk,dt,xtn,xtt,xtq,xtnam,ntn,ntt,ntnam;
        menuBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/report11.png")));
        menuBaoCao.add(hdx = new JMenu("Hàng Đã Bán"));
        hdx.add(xtn = new JMenuItem("Trong Ngày"));
        hdx.add(xtt = new JMenuItem("Trong Tháng"));
        hdx.add(xtnam = new JMenuItem("Trong Năm"));
        xtn.setPreferredSize(new Dimension(150,40));
        xtt.setPreferredSize(new Dimension(150,40));
        xtnam.setPreferredSize(new Dimension(150,40));

        hdx.setPreferredSize(new Dimension(200,40));
        hdx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcHangBan.png")));
        menuBaoCao.add(hdn = new JMenu("Hàng Đã Nhập"));
//        hdn.add(ntn = new JMenuItem("Trong Ngày"));
        hdn.add(ntt = new JMenuItem("Trong Tháng"));
        hdn.add(ntnam = new JMenuItem("Trong Năm"));
//        ntn.setPreferredSize(new Dimension(150,40));
        ntt.setPreferredSize(new Dimension(150,40));
        ntnam.setPreferredSize(new Dimension(150,40));

        hdn.setPreferredSize(new Dimension(200,40));
        hdn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcHangNhap.png")));
        menuBaoCao.add(htk = new JMenuItem("Hàng Tồn Kho"));
        htk.setPreferredSize(new Dimension(200,40));
        htk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcHangTon.png")));
        menuBaoCao.add(dt = new JMenuItem("Doanh Thu"));
        dt.setPreferredSize(new Dimension(200,40));
        dt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcDoanhThu.png")));

        JMenuItem mnTimNV,mnTimKH,mnTimNCC,mnTimLK;
        JMenu menuTimKiem = new JMenu("TÌM KIẾM");
        menuTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/search11.png")));
        menuTimKiem.setForeground(Color.RED);
        menuTimKiem.setFont(fontMenu);
        menuTimKiem.add(mnTimNV = new JMenuItem("Nhân Viên"));
        mnTimNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhanvien11.png")));
        mnTimNV.setPreferredSize(new Dimension(200,40));
        menuTimKiem.add(mnTimKH = new JMenuItem("Khách Hàng"));
        mnTimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/khachHang11.png")));
        mnTimKH.setPreferredSize(new Dimension(200,40));
        menuTimKiem.add(mnTimNCC = new JMenuItem("Nhà Cung Cấp"));
        mnTimNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhaCC11.png")));
        mnTimNCC.setPreferredSize(new Dimension(200,40));
        menuTimKiem.add(mnTimLK = new JMenuItem("Linh Kiện"));
        mnTimLK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/linhKien11.png")));
        mnTimLK.setPreferredSize(new Dimension(200,40));

        //Bảo hành
        JMenu menuBaoHanh = new JMenu("BẢO HÀNH");
        menuBaoHanh.setForeground(Color.RED);
        menuBaoHanh.setFont(fontMenu);
        menuBaoHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/baohanh11.png")));

        JMenu menuTroGiup = new JMenu("TRỢ GIÚP");
        menuTroGiup.setForeground(Color.RED);
        menuTroGiup.setFont(fontMenu);
        menuTroGiup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/help11.png")));

        Account = new JMenu("Tài Khoản");
        Account.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/acount11.png")));
        Account.setFont(new Font("Arial",Font.BOLD,16));
        JMenuItem thongTin,donHang,lichSu,doiMatKhau,dangXuat,gioHang;
        Account.add(thongTin = new JMenuItem("Thông Tin Tài Khoản"));
        thongTin.setPreferredSize(new Dimension(170,40));
        thongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/thongtin.png")));
        Account.add(gioHang = new JMenuItem("Giỏ Hàng"));
        gioHang.setPreferredSize(new Dimension(170,40));
        gioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/giohang.png")));
        Account.add(donHang = new JMenuItem("Đơn Hàng"));
        donHang.setPreferredSize(new Dimension(170,40));
        donHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/donhang.png")));
        Account.add(lichSu = new JMenuItem("Lịch Sử Mua Hàng"));
        lichSu.setPreferredSize(new Dimension(170,40));
        lichSu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/lichsu.png")));
        Account.add(doiMatKhau = new JMenuItem("Đổi Mật Khẩu"));
        doiMatKhau.setPreferredSize(new Dimension(170,40));
        doiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/doimatkhau.png")));
        Account.add(dangXuat = new JMenuItem("Đăng Xuất"));
        dangXuat.setPreferredSize(new Dimension(170,40));
        dangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/log-out.png")));

        JMenu space = new JMenu("            ");
        space.setEnabled(false);
        menuTrangChu.setSelected(true);

        menuBar.add(menuTrangChu);
        menuBar.add(menuHeThong);
        menuBar.add(menuDanhMuc);
        menuBar.add(menuQuanLy);
        menuBar.add(menuBaoCao);
        menuBar.add(menuTimKiem);
        menuBar.add(menuBaoHanh);
        menuBar.add(menuTroGiup);
        menuBar.add(space);
        menuBar.add(Account);

        bd_cen = BorderFactory.createLineBorder(Color.red);

        menuTrangChu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	pn_west.setVisible(true);
                trangchu();
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

        //Sự kiện
        kHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TT_KhachHang_Form kh = new TT_KhachHang_Form();
                pn_Center.removeAll();
                pn_Center.add(kh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Khách hàng"));
                pn_Center.validate();

            }
        });
        nVien.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				TT_NhanVien_Form nv = new TT_NhanVien_Form();
                pn_Center.removeAll();
                pn_Center.add(nv);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Nhân Viên"));
                pn_Center.validate();
			}
		});
        linhKien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LinhKien_Form lk = new LinhKien_Form();
                pn_Center.removeAll();
                pn_Center.add(lk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Linh Kiện"));
                pn_Center.validate();
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
        menuBaoHanh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PhieuBaoHanh_Form lk = new PhieuBaoHanh_Form();
                pn_Center.removeAll();
                pn_Center.add(lk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Bảo Hành"));
                pn_Center.validate();
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
        nCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhaCungCap_Form ncc = new NhaCungCap_Form();
                pn_Center.removeAll();
                pn_Center.add(ncc);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Nhà Cung Cấp"));
                pn_Center.validate();
            }
        });
        KhachHang_DAO khDao = new KhachHang_DAO();
        System.out.println(this.Account.getText());
        kh = khDao.TimKiemTen(this.Account.getText());
        gioHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("KHách Hàng GD: "+kh);
                GioHang_Form gh = new GioHang_Form();
                gh.kh = kh;
                System.out.println(kh);
                gh.doShow();
                pn_Center.removeAll();
                pn_Center.add(gh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Giỏ hàng"));
                pn_Center.validate();
            }
        });
        datHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatHang_Form dh = new DatHang_Form();
                dh.kh = kh;
                System.out.println(kh);
                dh.doShow();
                pn_Center.removeAll();
                pn_Center.add(dh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Đặt Hàng"));
                pn_Center.validate();
            }
        });
        banHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nv != null){
                    BanHang1_Form bh = new BanHang1_Form();
                    bh.nv = nv;
                    bh.bh = bh;
                    bh.doShow();
                    pn_west.setVisible(false);
                    pn_Center.removeAll();
                    pn_Center.add(bh);
                    pn_Center.setBorder(new TitledBorder(bd_cen,"Hóa Đơn Bán Hàng"));
                    pn_Center.validate();
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền sử dụng chức năng này !");
                }

            }
        });
        donHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DonHang_Form dh = new DonHang_Form();
                dh.kh = kh;
                
                dh.doShow();
               
                pn_Center.removeAll();
                pn_Center.add(dh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Đơn Đặt Hàng"));
                pn_Center.validate();
            }
        });
        nhapHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhapHang1_Form nh = new NhapHang1_Form();
                nh.nv = nv;
                nh.nh = nh;
                nh.doShow();
                pn_west.setVisible(false);
                pn_Center.removeAll();
                pn_Center.add(nh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Hóa Đơn Nhập Hàng"));
                pn_Center.validate();
            }
        });
        thongTin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongTinTaiKhoan_Form tt = new ThongTinTaiKhoan_Form();
                tt.kh = kh;
                System.out.println(kh);
                tt.doshow();
                pn_Center.removeAll();
                pn_Center.add(tt);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Thông tin tài khoản"));
                pn_Center.validate();
            }
        });
        doiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoiMatKhau_Form dmk = new DoiMatKhau_Form();
                dmk.kh = kh;
                System.out.println(kh);
                dmk.ran = dmk.getSaltString();
                dmk.doshow();
                pn_Center.removeAll();
                pn_Center.add(dmk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Thay đổi mật khẩu"));
                pn_Center.validate();
            }
        });

        //Sự kiện thoát
        t.setMnemonic(KeyEvent.VK_T);
        t.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.ALT_MASK));
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn thoát chương trình không?","Xác nhận",JOptionPane.YES_NO_OPTION);
                if(lc == JOptionPane.YES_OPTION)
                    setVisible(false);
            }
        });

        mnNhaSX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhaSanXuat_Form nsx = new NhaSanXuat_Form();
                pn_Center.removeAll();
                pn_Center.add(nsx);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Nhà sản xuất"));
                pn_Center.validate();
            }
        });

        mnLoaiLK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoaiLinhKien_Form llk = new LoaiLinhKien_Form();
                pn_Center.removeAll();
                pn_Center.add(llk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Loại Linh Kiện"));
                pn_Center.validate();
            }
        });
        mnChucVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChucVu_Form llk = new ChucVu_Form();
                pn_Center.removeAll();
                pn_Center.add(llk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Chức Vụ"));
                pn_Center.validate();
            }
        });

        dt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TKDoanhThu_Form tk = new TKDoanhThu_Form();
                pn_Center.removeAll();
                pn_Center.add(tk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Thống Kê Doanh Thu"));
                pn_Center.validate();
            }
        });
        
        htk.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyCuaHangLinhKien;instance=SQLEXPRESS;user=sa;password=hung");
					String sql = "SELECT * FROM LINHKIEN";
					JasperDesign jdesign = JRXmlLoader.load("E:\\\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\\\src\\\\BaoCao_Jasper\\ThongKeHangKho.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);

					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
        xtn.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyCuaHangLinhKien;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT lk.MALK,lk.TENLK,llk.TENLOAI,nsx.TENNHASX,lk.BAOHANH,lk.DONVITINH,lk.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].[HoaDonBanHang] hd JOIN [dbo].[CT_HoaDonBanHang] ct ON hd.MaHDBH = ct.MaHDBH\r\n"
							+ "JOIN [dbo].[LinhKien] lk ON lk.MALK = ct.MALK\r\n"
							+ "JOIN [dbo].[LoaiLinhKien] llk ON llk.MALOAI = lk.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = lk.MANHASX\r\n"
							+ "WHERE DAY(hd.NGAYLAPHD) = DAY(GETDATE())";
					JasperDesign jdesign = JRXmlLoader.load("E:\\\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\\\src\\\\BaoCao_Jasper\\ThongKeHangBan.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);

					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
        xtt.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyCuaHangLinhKien;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT lk.MALK,lk.TENLK,llk.TENLOAI,nsx.TENNHASX,lk.BAOHANH,lk.DONVITINH,lk.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].[HoaDonBanHang] hd JOIN [dbo].[CT_HoaDonBanHang] ct ON hd.MaHDBH = ct.MaHDBH\r\n"
							+ "JOIN [dbo].[LinhKien] lk ON lk.MALK = ct.MALK\r\n"
							+ "JOIN [dbo].[LoaiLinhKien] llk ON llk.MALOAI = lk.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = lk.MANHASX\r\n"
							+ "WHERE MONTH(hd.NGAYLAPHD) = MONTH(GETDATE())";
					JasperDesign jdesign = JRXmlLoader.load("E:\\\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\\\src\\\\BaoCao_Jasper\\ThongKeHangBan1.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);

					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
        xtnam.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyCuaHangLinhKien;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT lk.MALK,lk.TENLK,llk.TENLOAI,nsx.TENNHASX,lk.BAOHANH,lk.DONVITINH,lk.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].[HoaDonBanHang] hd JOIN [dbo].[CT_HoaDonBanHang] ct ON hd.MaHDBH = ct.MaHDBH\r\n"
							+ "JOIN [dbo].[LinhKien] lk ON lk.MALK = ct.MALK\r\n"
							+ "JOIN [dbo].[LoaiLinhKien] llk ON llk.MALOAI = lk.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = lk.MANHASX\r\n"
							+ "WHERE YEAR(hd.NGAYLAPHD) = YEAR(GETDATE())";
					JasperDesign jdesign = JRXmlLoader.load("E:\\\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\\\src\\\\BaoCao_Jasper\\ThongKeHangBan2.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);

					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
        ntt.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyCuaHangLinhKien;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT lk.MALK,lk.TENLK,llk.TENLOAI,nsx.TENNHASX,lk.BAOHANH,lk.DONVITINH,lk.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].HoaDonNhapHang hd JOIN [dbo].CT_HoaDonNhapHang ct ON hd.MaHDNH = ct.MaHDNH\r\n"
							+ "JOIN [dbo].[LinhKien] lk ON lk.MALK = ct.MALK\r\n"
							+ "JOIN [dbo].[LoaiLinhKien] llk ON llk.MALOAI = lk.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = lk.MANHASX\r\n"
							+ "WHERE MONTH(hd.NGAYLAPHD) = MONTH(GETDATE())";
					JasperDesign jdesign = JRXmlLoader.load("E:\\\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\\\src\\\\BaoCao_Jasper\\ThongKeHangNhap1.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);

					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
        ntnam.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyCuaHangLinhKien;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT lk.MALK,lk.TENLK,llk.TENLOAI,nsx.TENNHASX,lk.BAOHANH,lk.DONVITINH,lk.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].HoaDonNhapHang hd JOIN [dbo].CT_HoaDonNhapHang ct ON hd.MaHDNH = ct.MaHDNH\r\n"
							+ "JOIN [dbo].[LinhKien] lk ON lk.MALK = ct.MALK\r\n"
							+ "JOIN [dbo].[LoaiLinhKien] llk ON llk.MALOAI = lk.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = lk.MANHASX\r\n"
							+ "WHERE YEAR(hd.NGAYLAPHD) = YEAR(GETDATE())";
					JasperDesign jdesign = JRXmlLoader.load("E:\\\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\\\src\\\\BaoCao_Jasper\\ThongKeHangNhap2.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);

					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});

        mnTimKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemKH_Form tkkh = new TimKiemKH_Form();
                pn_Center.removeAll();
                pn_Center.add(tkkh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Tìm kiếm khách hàng"));
                pn_Center.validate();
            }
        });

        mnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNV_Form tknv = new TimKiemNV_Form();
                pn_Center.removeAll();
                pn_Center.add(tknv);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Tìm kiếm nhân viên"));
                pn_Center.validate();
            }
        });

        mnTimNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNCC_Form tkncc = new TimKiemNCC_Form();
                pn_Center.removeAll();
                pn_Center.add(tkncc);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Tìm kiếm nhà cung cấp"));
                pn_Center.validate();
            }
        });

        mnTimLK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemLK_Form tklk = new TimKiemLK_Form();
                pn_Center.removeAll();
                pn_Center.add(tklk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Tìm kiếm linh kiện"));
                pn_Center.validate();
            }
        });


        dangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                ActionEvent.CTRL_MASK));
        dangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lc = JOptionPane.showConfirmDialog(null,"Bạn muốn đăng xuất phải không?","Xác nhận",JOptionPane.YES_NO_OPTION);
                if(lc == JOptionPane.YES_OPTION){
                    setVisible(false);
                    DangNhap_Form dn = new DangNhap_Form();
                    dn.setVisible(true);
                }

            }
        });
        
       
        JPanel pn_Menu = new JPanel();
        pn_Menu.add(menuBar);
        //pn2.setBorder(bd);
        pn_North.add(pn_Banner);
        pn_North.add(pn_Menu);
        cp.add(pn_North,BorderLayout.NORTH);

        //pn_west
        pn_west = new JPanel();
        pn_west.setLayout(new BorderLayout());
        JButton btnz = new JButton("Danh Mục Sản Phẩm");
        btnz.setIcon(new ImageIcon(getClass().getResource("/images/menu111.png")));
        btnz.setSelected(true);
        btnz.setForeground(Color.WHITE);
        btnz.setBackground(Color.GRAY);
        pn_west.add(btnz,BorderLayout.NORTH);
        Border bd_west = BorderFactory.createLineBorder(Color.red);
        TitledBorder title_west = new TitledBorder(bd_west,"DANH MỤC SẢN PHẨM");
        //pn_west.setBorder(bd_west);


        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Linh kiện");
        //create the child nodes
        DefaultMutableTreeNode manHinhNode = new DefaultMutableTreeNode("Màn hình");
        manHinhNode.add(new DefaultMutableTreeNode("HP"));
        manHinhNode.add(new DefaultMutableTreeNode("Samsung"));
        manHinhNode.add(new DefaultMutableTreeNode("Asus"));
        manHinhNode.add(new DefaultMutableTreeNode("Dell"));
        manHinhNode.add(new DefaultMutableTreeNode("MSI"));
        manHinhNode.add(new DefaultMutableTreeNode("AOC"));
        manHinhNode.add(new DefaultMutableTreeNode("Gigabyte"));
        manHinhNode.add(new DefaultMutableTreeNode("Lenovo"));
        DefaultMutableTreeNode banPhimNode = new DefaultMutableTreeNode("Bàn phím");
        banPhimNode.add(new DefaultMutableTreeNode("Razer"));
        banPhimNode.add(new DefaultMutableTreeNode("Logitech"));
        banPhimNode.add(new DefaultMutableTreeNode("Leopold"));
        banPhimNode.add(new DefaultMutableTreeNode("HyperX"));
        banPhimNode.add(new DefaultMutableTreeNode("Dare - U"));
        banPhimNode.add(new DefaultMutableTreeNode("Akko"));
        banPhimNode.add(new DefaultMutableTreeNode("Asus"));
        DefaultMutableTreeNode chuotNode = new DefaultMutableTreeNode("Chuột + Lót chuột");
        chuotNode.add(new DefaultMutableTreeNode("Logitech"));
        chuotNode.add(new DefaultMutableTreeNode("Razer"));
        chuotNode.add(new DefaultMutableTreeNode("HyperX"));
        chuotNode.add(new DefaultMutableTreeNode("Dare - U"));
        chuotNode.add(new DefaultMutableTreeNode("Corsair"));
        chuotNode.add(new DefaultMutableTreeNode("Microsoft"));
        DefaultMutableTreeNode taiNgheNode = new DefaultMutableTreeNode("Tai nghe Gaming");
        taiNgheNode.add(new DefaultMutableTreeNode("HyperX"));
        taiNgheNode.add(new DefaultMutableTreeNode("Razer"));
        taiNgheNode.add(new DefaultMutableTreeNode("MSI"));
        taiNgheNode.add(new DefaultMutableTreeNode("ASus"));
        taiNgheNode.add(new DefaultMutableTreeNode("Astro"));
        DefaultMutableTreeNode mainNode = new DefaultMutableTreeNode("Mainboard - Bo mạch chủ");
        mainNode.add(new DefaultMutableTreeNode("Mainboard intel"));
        mainNode.add(new DefaultMutableTreeNode("Mainboard AMD Ryzen"));
        DefaultMutableTreeNode cpuNode = new DefaultMutableTreeNode("CPU - Bộ vi xử lý");
        cpuNode.add(new DefaultMutableTreeNode("CPU intel"));
        cpuNode.add(new DefaultMutableTreeNode("CPU AMD Ryzen"));
        DefaultMutableTreeNode ramNode = new DefaultMutableTreeNode("Ram - Bộ nhớ trong");
        ramNode.add(new DefaultMutableTreeNode("Ram kingston"));
        ramNode.add(new DefaultMutableTreeNode("Ram LED RGB"));
        ramNode.add(new DefaultMutableTreeNode("Ram ECC"));
        ramNode.add(new DefaultMutableTreeNode("Ram Corsair"));
        DefaultMutableTreeNode cardMHnode = new DefaultMutableTreeNode("VGA - Card màn hình");
        cardMHnode.add(new DefaultMutableTreeNode("VGA RTX"));
        cardMHnode.add(new DefaultMutableTreeNode("VGA GTX"));
        cardMHnode.add(new DefaultMutableTreeNode("VGA AMD"));
        cardMHnode.add(new DefaultMutableTreeNode("VGA Nvidia Quadpro"));
        DefaultMutableTreeNode ssdNode = new DefaultMutableTreeNode("SSD - Ổ cứng");
        ssdNode.add(new DefaultMutableTreeNode("SSD - SATA 3"));
        ssdNode.add(new DefaultMutableTreeNode("SSD - M.2 SATA 3"));
        ssdNode.add(new DefaultMutableTreeNode("SSD - PCI Express Card"));
        ssdNode.add(new DefaultMutableTreeNode("SSD - M.2 PCle GEN 3x4"));
        DefaultMutableTreeNode psuNode = new DefaultMutableTreeNode("PSU - Nguồn máy tính");
        psuNode.add(new DefaultMutableTreeNode("Nguồn Asus"));
        psuNode.add(new DefaultMutableTreeNode("Nguồn Corsair"));
        psuNode.add(new DefaultMutableTreeNode("Nguồn Cooler Master"));
        DefaultMutableTreeNode caseNode = new DefaultMutableTreeNode("CASE - Vỏ máy tính");
        caseNode.add(new DefaultMutableTreeNode("Vỏ Case Custom"));
        caseNode.add(new DefaultMutableTreeNode("Case MSI"));
        caseNode.add(new DefaultMutableTreeNode("Case Corsair"));
        DefaultMutableTreeNode tanNhietNode = new DefaultMutableTreeNode("Tản nhiệt - Fan RGB");
        tanNhietNode.add(new DefaultMutableTreeNode("Fan RGB"));
        tanNhietNode.add(new DefaultMutableTreeNode("Tản nhiệt khí"));
        tanNhietNode.add(new DefaultMutableTreeNode("tản nhiệt nước"));
        tanNhietNode.add(new DefaultMutableTreeNode("Keo tản nhiệt + Dựng VGA"));
        tanNhietNode.add(new DefaultMutableTreeNode("Dựng Cable Sleeve PC"));
        //add the child nodes to the root node
        root.add(manHinhNode);root.add(banPhimNode);
        root.add(chuotNode);root.add(taiNgheNode);

        root.add(mainNode);root.add(cpuNode);
        root.add(ramNode);root.add(cardMHnode);

        root.add(ssdNode);root.add(psuNode);
        root.add(caseNode);root.add(tanNhietNode);

        //create the tree by passing in the root node
        tree = new JTree(root);
        tree.getSelectionModel().addTreeSelectionListener(new Selector());
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane sp_tree = new JScrollPane(tree,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp_tree.setPreferredSize(new Dimension(200,50));
        pn_west.add(sp_tree,BorderLayout.CENTER);
        cp.add(pn_west,BorderLayout.WEST);

        //pn_Center
        pn_Center = new JPanel();
        JPanel pn_C1 = new JPanel();
        JPanel pn_C2 = new JPanel();
        TitledBorder title_center = new TitledBorder(bd_west,"Trang chủ");
        pn_Center.setBorder(title_center);
        lbCenter = new JLabel();lbCenter1 = new JLabel();lbCenter2 = new JLabel();
        lbCenter3 = new JLabel();lbCenter4 = new JLabel();

        s = new ImageIcon[7];
        s[0] = new ImageIcon(getClass().getResource("/images/pn111.png"));
        s[1] = new ImageIcon(getClass().getResource("/images/pn1112.png"));
        s[2] = new ImageIcon(getClass().getResource("/images/pn1113.png"));
        s[3] = new ImageIcon(getClass().getResource("/images/pn1114.png"));
        s[4] = new ImageIcon(getClass().getResource("/images/pn1115.png"));
        s[5] = new ImageIcon(getClass().getResource("/images/pn1116.png"));
        s[6] = new ImageIcon(getClass().getResource("/images/pn1118.png"));

        lbCenter.setIcon(new ImageIcon(getClass().getResource("/images/pn1116.png")));


        lbCenter1.setIcon(new ImageIcon(getClass().getResource("/images/pn1121.png")));
        lbCenter2.setIcon(new ImageIcon(getClass().getResource("/images/pn1131.png")));
        lbCenter3.setIcon(new ImageIcon(getClass().getResource("/images/pn1132.png")));
        lbCenter4.setIcon(new ImageIcon(getClass().getResource("/images/pn1151.png")));
        pn_C1.add(lbCenter);
        pn_C1.add(lbCenter1);
        pn_C2.add(lbCenter2);
        pn_C2.add(lbCenter3);
        pn_C2.add(lbCenter4);

        pn_Center.add(pn_C1,BorderLayout.WEST);
        pn_Center.add(pn_C2,BorderLayout.WEST);
        cp.add(pn_Center,BorderLayout.CENTER);

        //pn_South
        JPanel pn_south = new JPanel();
        pn_south.setLayout(new GridLayout(2,1));
        JPanel pnt = new JPanel();
        JPanel pnd = new JPanel();
        JPanel pnd1 = new JPanel();
        Font ft_south = new Font("Arial",Font.BOLD,13);
        JLabel lb_south = new JLabel("Nguyễn Văn Hùng - Nguyễn Văn Anh - Lê Khắc Trung");
        JLabel lb_nhom = new JLabel("Nhóm 01: Chương trình quản lý cửa hàng mua bán linh kiện máy tính");
        lb_nhom.setFont(ft_south);
        lb_south.setFont(ft_south);
        pnt.add(lb_south);
        pnd.add(lb_nhom);
        pnd1.add(new JLabel("    "));
        pn_south.add(pnt);
        pn_south.add(pnd);
        pn_south.add(pnd1);
        pn_south.setPreferredSize(new Dimension(0,50));
        cp.add(pn_south,BorderLayout.SOUTH);
        pn_south.setBorder(bd);
        
        if(kh != null) {
        	menuBaoCao.setEnabled(false);
        	menuQuanLy.setEnabled(false);
        	menuBaoHanh.setEnabled(false);
        	menuHeThong.setEnabled(false);
        }
    }

    public void trangchu() {
        pn_Center.removeAll();
        tc= new TrangChu_Form();
        pn_Center.add(tc);
        pn_Center.setBorder(new TitledBorder(bd_cen,"Trang chủ"));
        pn_Center.validate();
        System.out.println("ok");
    }

    public void iterator() {
            do {
                for (int i = 0; i <= 6; i += 1) {
                    lbCenter.setIcon(s[i]);
                    tc.lbCenter.setIcon(s[i]);
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } while (true);
    }
    public void iterator1() {
        do {
            for (int i = 0; i <= 6; i += 1) {
                lbCenter.setIcon(s[i]);
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }
        } while (true);
    }
    private class Selector implements TreeSelectionListener {
        public void valueChanged(TreeSelectionEvent event) {
            Object obj = event.getNewLeadSelectionPath().getLastPathComponent();
            System.out.println("" + obj.toString());
            if(obj.toString().equalsIgnoreCase("Linh kiện")){
                TT_LinhKien_Form lk = new TT_LinhKien_Form();
                lk.kh = kh;
                lk.doShow();
                pn_Center.removeAll();
                pn_Center.add(lk);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Sản Phẩm"));
                pn_Center.validate();
            }else if(obj.toString().equalsIgnoreCase("Màn hình")){
                ManHinh_Form mh = new ManHinh_Form();
                mh.doShow();
                mh.kh = kh;
                pn_Center.removeAll();
                pn_Center.add(mh);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Màn hình"));
                pn_Center.validate();
            }else if(obj.toString().equalsIgnoreCase("Mainboard - Bo mạch chủ")){
                MainBoard_Form mh = new MainBoard_Form();
                mh.doShow();
                mh.kh = kh;
                pn_Center.removeAll();
                pn_Center.add(mh);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Mainboard - Bo mạch chủ"));
                pn_Center.validate();
            }else if(obj.toString().equalsIgnoreCase("Ram - Bộ nhớ trong")){
                Ram_Form mh = new Ram_Form();
                mh.doShow();
                mh.kh = kh;
                pn_Center.removeAll();
                pn_Center.add(mh);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Ram - Bộ nhớ trong"));
                pn_Center.validate();
            }
            else {
                System.out.println("" + obj.toString().length());
            }

        }
    }
    public static void main(String[] args) {
        GD_Chinh gd = new GD_Chinh();
        gd.setVisible(true);
        System.out.println("heloo121");
        gd.iterator();
        System.out.println("heloo11");

    }
}
