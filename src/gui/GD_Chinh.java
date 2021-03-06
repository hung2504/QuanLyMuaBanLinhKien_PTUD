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
        setTitle("Qu???n l?? mua b??n linh ki???n m??y t??nh");
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

        JMenu menuTrangChu = new JMenu("TRANG CH???");
        menuTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/trangchu11.png")));
        menuTrangChu.setBackground(Color.PINK);
        menuTrangChu.setFont(new Font("Arial",Font.BOLD,16));


        JMenu menuHeThong = new JMenu("H??? TH???NG");
        menuHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/hethong11.png")));

        menuHeThong.setForeground(Color.RED);
        Font fontMenu = new Font("Arial",Font.BOLD,16);
        menuHeThong.setFont(fontMenu);
        JMenuItem qlnd,t;
        menuHeThong.add(qlnd = new JMenuItem("Ph??n Quy???n Ng?????i D??ng"));
        qlnd.setPreferredSize(new Dimension(200,40));
        qlnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/phanquyen.png")));
        menuHeThong.add(t = new JMenuItem("Tho??t"));
        t.setPreferredSize(new Dimension(200,40));
        t.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/dangxuat.png")));

        JMenu menuDanhMuc = new JMenu("DANH M???C");
        menuDanhMuc.setForeground(Color.RED);
        menuDanhMuc.setFont(fontMenu);
        menuDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/danhmuc11.png")));

        JMenu linhKien,nVien;
        JMenuItem kHang,nCungCap,mnLoaiLK,mnNhaSX,mnChucVu;
        menuDanhMuc.add(nVien = new JMenu("Nh??n Vi??n"));
        nVien.add(mnChucVu = new JMenuItem("Ch???c V???"));
        mnChucVu.setPreferredSize(new Dimension(150,40));
        nVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhanvien11.png")));
        nVien.setPreferredSize(new Dimension(200,40));
        menuDanhMuc.add(kHang = new JMenuItem("Kh??ch H??ng"));
        kHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/khachHang11.png")));
        kHang.setPreferredSize(new Dimension(200,40));
        menuDanhMuc.add(nCungCap = new JMenuItem("Nh?? Cung C???p"));
        nCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhaCC11.png")));
        nCungCap.setPreferredSize(new Dimension(200,40));
        menuDanhMuc.add(linhKien = new JMenu("Linh Ki???n"));
        linhKien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/linhKien11.png")));
        linhKien.setPreferredSize(new Dimension(200,40));
        linhKien.add(mnLoaiLK = new JMenuItem("Lo???i Linh Ki???n"));
        linhKien.add(mnNhaSX = new JMenuItem("Nh?? S???n Xu???t"));
        mnLoaiLK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/loailk.png")));
        mnNhaSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhasx.png")));
        mnLoaiLK.setPreferredSize(new Dimension(150,40));
        mnNhaSX.setPreferredSize(new Dimension(150,40));


        JMenu menuQuanLy = new JMenu("X??? L??");
        menuQuanLy.setForeground(Color.RED);
        menuQuanLy.setFont(fontMenu);
        JMenuItem nhapHang,banHang,datHang;
        menuQuanLy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/xuly11.png")));
        menuQuanLy.add(nhapHang = new JMenuItem("Nh???p H??ng"));
        nhapHang.setPreferredSize(new Dimension(180,40));
        nhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/import.png")));
        menuQuanLy.add(banHang = new JMenuItem("B??n H??ng"));
        banHang.setPreferredSize(new Dimension(180,40));
        banHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/export.png")));
        menuQuanLy.add(datHang = new JMenuItem("?????t H??ng"));
        datHang.setPreferredSize(new Dimension(180,40));
        datHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/note.png")));

        JMenu menuBaoCao = new JMenu("B??O C??O");
        JMenu hdx,hdn;
        menuBaoCao.setForeground(Color.RED);
        menuBaoCao.setFont(fontMenu);
        JMenuItem htk,dt,xtn,xtt,xtq,xtnam,ntn,ntt,ntnam;
        menuBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/report11.png")));
        menuBaoCao.add(hdx = new JMenu("H??ng ???? B??n"));
        hdx.add(xtn = new JMenuItem("Trong Ng??y"));
        hdx.add(xtt = new JMenuItem("Trong Th??ng"));
        hdx.add(xtnam = new JMenuItem("Trong N??m"));
        xtn.setPreferredSize(new Dimension(150,40));
        xtt.setPreferredSize(new Dimension(150,40));
        xtnam.setPreferredSize(new Dimension(150,40));

        hdx.setPreferredSize(new Dimension(200,40));
        hdx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcHangBan.png")));
        menuBaoCao.add(hdn = new JMenu("H??ng ???? Nh???p"));
//        hdn.add(ntn = new JMenuItem("Trong Ng??y"));
        hdn.add(ntt = new JMenuItem("Trong Th??ng"));
        hdn.add(ntnam = new JMenuItem("Trong N??m"));
//        ntn.setPreferredSize(new Dimension(150,40));
        ntt.setPreferredSize(new Dimension(150,40));
        ntnam.setPreferredSize(new Dimension(150,40));

        hdn.setPreferredSize(new Dimension(200,40));
        hdn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcHangNhap.png")));
        menuBaoCao.add(htk = new JMenuItem("H??ng T???n Kho"));
        htk.setPreferredSize(new Dimension(200,40));
        htk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcHangTon.png")));
        menuBaoCao.add(dt = new JMenuItem("Doanh Thu"));
        dt.setPreferredSize(new Dimension(200,40));
        dt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcDoanhThu.png")));

        JMenuItem mnTimNV,mnTimKH,mnTimNCC,mnTimLK;
        JMenu menuTimKiem = new JMenu("T??M KI???M");
        menuTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/search11.png")));
        menuTimKiem.setForeground(Color.RED);
        menuTimKiem.setFont(fontMenu);
        menuTimKiem.add(mnTimNV = new JMenuItem("Nh??n Vi??n"));
        mnTimNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhanvien11.png")));
        mnTimNV.setPreferredSize(new Dimension(200,40));
        menuTimKiem.add(mnTimKH = new JMenuItem("Kh??ch H??ng"));
        mnTimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/khachHang11.png")));
        mnTimKH.setPreferredSize(new Dimension(200,40));
        menuTimKiem.add(mnTimNCC = new JMenuItem("Nh?? Cung C???p"));
        mnTimNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhaCC11.png")));
        mnTimNCC.setPreferredSize(new Dimension(200,40));
        menuTimKiem.add(mnTimLK = new JMenuItem("Linh Ki???n"));
        mnTimLK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/linhKien11.png")));
        mnTimLK.setPreferredSize(new Dimension(200,40));

        //B???o h??nh
        JMenu menuBaoHanh = new JMenu("B???O H??NH");
        menuBaoHanh.setForeground(Color.RED);
        menuBaoHanh.setFont(fontMenu);
        menuBaoHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/baohanh11.png")));

        JMenu menuTroGiup = new JMenu("TR??? GI??P");
        menuTroGiup.setForeground(Color.RED);
        menuTroGiup.setFont(fontMenu);
        menuTroGiup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/help11.png")));

        Account = new JMenu("T??i Kho???n");
        Account.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/acount11.png")));
        Account.setFont(new Font("Arial",Font.BOLD,16));
        JMenuItem thongTin,donHang,lichSu,doiMatKhau,dangXuat,gioHang;
        Account.add(thongTin = new JMenuItem("Th??ng Tin T??i Kho???n"));
        thongTin.setPreferredSize(new Dimension(170,40));
        thongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/thongtin.png")));
        Account.add(gioHang = new JMenuItem("Gi??? H??ng"));
        gioHang.setPreferredSize(new Dimension(170,40));
        gioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/giohang.png")));
        Account.add(donHang = new JMenuItem("????n H??ng"));
        donHang.setPreferredSize(new Dimension(170,40));
        donHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/donhang.png")));
        Account.add(lichSu = new JMenuItem("L???ch S??? Mua H??ng"));
        lichSu.setPreferredSize(new Dimension(170,40));
        lichSu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/lichsu.png")));
        Account.add(doiMatKhau = new JMenuItem("?????i M???t Kh???u"));
        doiMatKhau.setPreferredSize(new Dimension(170,40));
        doiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/doimatkhau.png")));
        Account.add(dangXuat = new JMenuItem("????ng Xu???t"));
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

        //S??? ki???n
        kHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TT_KhachHang_Form kh = new TT_KhachHang_Form();
                pn_Center.removeAll();
                pn_Center.add(kh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Kh??ch h??ng"));
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"Nh??n Vi??n"));
                pn_Center.validate();
			}
		});
        linhKien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LinhKien_Form lk = new LinhKien_Form();
                pn_Center.removeAll();
                pn_Center.add(lk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Linh Ki???n"));
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"B???o H??nh"));
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"Nh?? Cung C???p"));
                pn_Center.validate();
            }
        });
        KhachHang_DAO khDao = new KhachHang_DAO();
        System.out.println(this.Account.getText());
        kh = khDao.TimKiemTen(this.Account.getText());
        gioHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("KH??ch H??ng GD: "+kh);
                GioHang_Form gh = new GioHang_Form();
                gh.kh = kh;
                System.out.println(kh);
                gh.doShow();
                pn_Center.removeAll();
                pn_Center.add(gh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Gi??? h??ng"));
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"?????t H??ng"));
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
                    pn_Center.setBorder(new TitledBorder(bd_cen,"H??a ????n B??n H??ng"));
                    pn_Center.validate();
                }else{
                    JOptionPane.showMessageDialog(null,"B???n kh??ng c?? quy???n s??? d???ng ch???c n??ng n??y !");
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"????n ?????t H??ng"));
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"H??a ????n Nh???p H??ng"));
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"Th??ng tin t??i kho???n"));
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"Thay ?????i m???t kh???u"));
                pn_Center.validate();
            }
        });

        //S??? ki???n tho??t
        t.setMnemonic(KeyEvent.VK_T);
        t.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.ALT_MASK));
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lc = JOptionPane.showConfirmDialog(null,"B???n c?? ch???c ch???n mu???n tho??t ch????ng tr??nh kh??ng?","X??c nh???n",JOptionPane.YES_NO_OPTION);
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"Nh?? s???n xu???t"));
                pn_Center.validate();
            }
        });

        mnLoaiLK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoaiLinhKien_Form llk = new LoaiLinhKien_Form();
                pn_Center.removeAll();
                pn_Center.add(llk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Lo???i Linh Ki???n"));
                pn_Center.validate();
            }
        });
        mnChucVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChucVu_Form llk = new ChucVu_Form();
                pn_Center.removeAll();
                pn_Center.add(llk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Ch???c V???"));
                pn_Center.validate();
            }
        });

        dt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TKDoanhThu_Form tk = new TKDoanhThu_Form();
                pn_Center.removeAll();
                pn_Center.add(tk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Th???ng K?? Doanh Thu"));
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
                pn_Center.setBorder(new TitledBorder(bd_cen,"T??m ki???m kh??ch h??ng"));
                pn_Center.validate();
            }
        });

        mnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNV_Form tknv = new TimKiemNV_Form();
                pn_Center.removeAll();
                pn_Center.add(tknv);
                pn_Center.setBorder(new TitledBorder(bd_cen,"T??m ki???m nh??n vi??n"));
                pn_Center.validate();
            }
        });

        mnTimNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNCC_Form tkncc = new TimKiemNCC_Form();
                pn_Center.removeAll();
                pn_Center.add(tkncc);
                pn_Center.setBorder(new TitledBorder(bd_cen,"T??m ki???m nh?? cung c???p"));
                pn_Center.validate();
            }
        });

        mnTimLK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemLK_Form tklk = new TimKiemLK_Form();
                pn_Center.removeAll();
                pn_Center.add(tklk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"T??m ki???m linh ki???n"));
                pn_Center.validate();
            }
        });


        dangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                ActionEvent.CTRL_MASK));
        dangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lc = JOptionPane.showConfirmDialog(null,"B???n mu???n ????ng xu???t ph???i kh??ng?","X??c nh???n",JOptionPane.YES_NO_OPTION);
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
        JButton btnz = new JButton("Danh M???c S???n Ph???m");
        btnz.setIcon(new ImageIcon(getClass().getResource("/images/menu111.png")));
        btnz.setSelected(true);
        btnz.setForeground(Color.WHITE);
        btnz.setBackground(Color.GRAY);
        pn_west.add(btnz,BorderLayout.NORTH);
        Border bd_west = BorderFactory.createLineBorder(Color.red);
        TitledBorder title_west = new TitledBorder(bd_west,"DANH M???C S???N PH???M");
        //pn_west.setBorder(bd_west);


        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Linh ki???n");
        //create the child nodes
        DefaultMutableTreeNode manHinhNode = new DefaultMutableTreeNode("M??n h??nh");
        manHinhNode.add(new DefaultMutableTreeNode("HP"));
        manHinhNode.add(new DefaultMutableTreeNode("Samsung"));
        manHinhNode.add(new DefaultMutableTreeNode("Asus"));
        manHinhNode.add(new DefaultMutableTreeNode("Dell"));
        manHinhNode.add(new DefaultMutableTreeNode("MSI"));
        manHinhNode.add(new DefaultMutableTreeNode("AOC"));
        manHinhNode.add(new DefaultMutableTreeNode("Gigabyte"));
        manHinhNode.add(new DefaultMutableTreeNode("Lenovo"));
        DefaultMutableTreeNode banPhimNode = new DefaultMutableTreeNode("B??n ph??m");
        banPhimNode.add(new DefaultMutableTreeNode("Razer"));
        banPhimNode.add(new DefaultMutableTreeNode("Logitech"));
        banPhimNode.add(new DefaultMutableTreeNode("Leopold"));
        banPhimNode.add(new DefaultMutableTreeNode("HyperX"));
        banPhimNode.add(new DefaultMutableTreeNode("Dare - U"));
        banPhimNode.add(new DefaultMutableTreeNode("Akko"));
        banPhimNode.add(new DefaultMutableTreeNode("Asus"));
        DefaultMutableTreeNode chuotNode = new DefaultMutableTreeNode("Chu???t + L??t chu???t");
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
        DefaultMutableTreeNode mainNode = new DefaultMutableTreeNode("Mainboard - Bo m???ch ch???");
        mainNode.add(new DefaultMutableTreeNode("Mainboard intel"));
        mainNode.add(new DefaultMutableTreeNode("Mainboard AMD Ryzen"));
        DefaultMutableTreeNode cpuNode = new DefaultMutableTreeNode("CPU - B??? vi x??? l??");
        cpuNode.add(new DefaultMutableTreeNode("CPU intel"));
        cpuNode.add(new DefaultMutableTreeNode("CPU AMD Ryzen"));
        DefaultMutableTreeNode ramNode = new DefaultMutableTreeNode("Ram - B??? nh??? trong");
        ramNode.add(new DefaultMutableTreeNode("Ram kingston"));
        ramNode.add(new DefaultMutableTreeNode("Ram LED RGB"));
        ramNode.add(new DefaultMutableTreeNode("Ram ECC"));
        ramNode.add(new DefaultMutableTreeNode("Ram Corsair"));
        DefaultMutableTreeNode cardMHnode = new DefaultMutableTreeNode("VGA - Card m??n h??nh");
        cardMHnode.add(new DefaultMutableTreeNode("VGA RTX"));
        cardMHnode.add(new DefaultMutableTreeNode("VGA GTX"));
        cardMHnode.add(new DefaultMutableTreeNode("VGA AMD"));
        cardMHnode.add(new DefaultMutableTreeNode("VGA Nvidia Quadpro"));
        DefaultMutableTreeNode ssdNode = new DefaultMutableTreeNode("SSD - ??? c???ng");
        ssdNode.add(new DefaultMutableTreeNode("SSD - SATA 3"));
        ssdNode.add(new DefaultMutableTreeNode("SSD - M.2 SATA 3"));
        ssdNode.add(new DefaultMutableTreeNode("SSD - PCI Express Card"));
        ssdNode.add(new DefaultMutableTreeNode("SSD - M.2 PCle GEN 3x4"));
        DefaultMutableTreeNode psuNode = new DefaultMutableTreeNode("PSU - Ngu???n m??y t??nh");
        psuNode.add(new DefaultMutableTreeNode("Ngu???n Asus"));
        psuNode.add(new DefaultMutableTreeNode("Ngu???n Corsair"));
        psuNode.add(new DefaultMutableTreeNode("Ngu???n Cooler Master"));
        DefaultMutableTreeNode caseNode = new DefaultMutableTreeNode("CASE - V??? m??y t??nh");
        caseNode.add(new DefaultMutableTreeNode("V??? Case Custom"));
        caseNode.add(new DefaultMutableTreeNode("Case MSI"));
        caseNode.add(new DefaultMutableTreeNode("Case Corsair"));
        DefaultMutableTreeNode tanNhietNode = new DefaultMutableTreeNode("T???n nhi???t - Fan RGB");
        tanNhietNode.add(new DefaultMutableTreeNode("Fan RGB"));
        tanNhietNode.add(new DefaultMutableTreeNode("T???n nhi???t kh??"));
        tanNhietNode.add(new DefaultMutableTreeNode("t???n nhi???t n?????c"));
        tanNhietNode.add(new DefaultMutableTreeNode("Keo t???n nhi???t + D???ng VGA"));
        tanNhietNode.add(new DefaultMutableTreeNode("D???ng Cable Sleeve PC"));
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
        TitledBorder title_center = new TitledBorder(bd_west,"Trang ch???");
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
        JLabel lb_south = new JLabel("Nguy???n V??n H??ng - Nguy???n V??n Anh - L?? Kh???c Trung");
        JLabel lb_nhom = new JLabel("Nh??m 01: Ch????ng tr??nh qu???n l?? c???a h??ng mua b??n linh ki???n m??y t??nh");
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
        pn_Center.setBorder(new TitledBorder(bd_cen,"Trang ch???"));
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
            if(obj.toString().equalsIgnoreCase("Linh ki???n")){
                TT_LinhKien_Form lk = new TT_LinhKien_Form();
                lk.kh = kh;
                lk.doShow();
                pn_Center.removeAll();
                pn_Center.add(lk);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"S???n Ph???m"));
                pn_Center.validate();
            }else if(obj.toString().equalsIgnoreCase("M??n h??nh")){
                ManHinh_Form mh = new ManHinh_Form();
                mh.doShow();
                mh.kh = kh;
                pn_Center.removeAll();
                pn_Center.add(mh);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"M??n h??nh"));
                pn_Center.validate();
            }else if(obj.toString().equalsIgnoreCase("Mainboard - Bo m???ch ch???")){
                MainBoard_Form mh = new MainBoard_Form();
                mh.doShow();
                mh.kh = kh;
                pn_Center.removeAll();
                pn_Center.add(mh);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Mainboard - Bo m???ch ch???"));
                pn_Center.validate();
            }else if(obj.toString().equalsIgnoreCase("Ram - B??? nh??? trong")){
                Ram_Form mh = new Ram_Form();
                mh.doShow();
                mh.kh = kh;
                pn_Center.removeAll();
                pn_Center.add(mh);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Ram - B??? nh??? trong"));
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
