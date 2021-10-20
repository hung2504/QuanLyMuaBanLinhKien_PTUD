package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.CT_HoaDonBH_DAO;
import dao.HoaDonBanHang_DAO;
import entity.CT_HoaDonBanHang;
import entity.HoaDonBanHang;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class PhieuBaoHanh_Form extends JPanel {
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMaHD,lblLK;
    JComboBox<String> cbcMaHD,cbcTenLK;
	private JButton btnXuat;
    public PhieuBaoHanh_Form(){
        doShow();
    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JLabel lblTieuDe = new JLabel("BẢO HÀNH");
        pnNorth.add(lblTieuDe);
        lblTieuDe.setFont(new Font("arial", Font.BOLD, 18));
        
        
        //pnCenter
        pnCenter = new JPanel();
        Box b,b1,b2;
        b = Box.createVerticalBox();
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMaHD = new JLabel("Mã Hóa Đơn Bán: "));
        cbcMaHD = new JComboBox<>();
        HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
        for (HoaDonBanHang hd : hdDao.getLS()) {
			cbcMaHD.addItem(hd.getMaHDBH().toString());
		}
        b1.add(Box.createHorizontalStrut(30));
        b1.add(cbcMaHD);
        b.add(Box.createVerticalStrut(20));
        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblLK = new JLabel("Lựa chọn linh kiện cần xuất phiếu BH:"));
        cbcTenLK = new JComboBox<>();
        CT_HoaDonBH_DAO ctDao = new CT_HoaDonBH_DAO();
        for (CT_HoaDonBanHang ct : ctDao.getLS(cbcMaHD.getSelectedItem().toString().trim())) {
			cbcTenLK.addItem(ct.getLinhKien().getTenLK());
		}
        cbcTenLK.setPreferredSize(new Dimension(200,30));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(cbcTenLK);
        pnCenter.add(b);
        
        cbcMaHD.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				CT_HoaDonBH_DAO ctDao = new CT_HoaDonBH_DAO();
				cbcTenLK.removeAllItems();
		        for (CT_HoaDonBanHang ct : ctDao.getLS(cbcMaHD.getSelectedItem().toString())) {
					cbcTenLK.addItem(ct.getLinhKien().getTenLK());
				}
			}
		});
        
        
        //pnSouth
        
        pnSouth = new JPanel();
        pnSouth.add(btnXuat = new JButton("Xuất Phiếu Bảo Hành "));
        
        
        btnXuat.addActionListener(new ActionListener() {
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
					JasperDesign jdesign = JRXmlLoader.load("E:\\\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\\\src\\\\BaoCao_Jasper\\demo.jrxml");
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
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}
