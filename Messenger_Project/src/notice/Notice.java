package notice;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import messengerDAO.MessengerDAO;
import messengerVO.MessengerVO;


public class Notice extends JFrame {
	BufferedImage img = null;
	private JPanel contentPane;
	private JTable tb_notice_list;
	private JButton tb_notice_write;//작성하기
	private JScrollPane scrollPane;
	MessengerDAO mdao;
	ArrayList<MessengerVO> noVO;
	String [] column = {"번호","날짜","제목","이름"};
	Object [][] list;
	int titlesw=0;
	int writesw=0;
	DefaultTableModel model;


	
	public Notice(MessengerVO info) throws ClassNotFoundException, SQLException {

		mdao = new MessengerDAO();
		noVO = mdao.NoticeSendList();
		list = new Object[noVO.size()][column.length];
		for (int i = 0; i < noVO.size(); i++) {
			list[i] = new Object[] {
				noVO.get(i).getNum(),//공지번호
				noVO.get(i).getRegdate(),//날짜
				noVO.get(i).getTitle(),//제목
				noVO.get(i).getWriter()//글쓴이
				
			};
		}
		model = new DefaultTableModel(list, column) {
			boolean[] columnEditables = new boolean[] {
	                true, false, false, true
	            };
			public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
		};
		
		setBounds(100, 100, 581, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("공지사항");
		setContentPane(contentPane);
		
		
		try {
			img = ImageIO.read(new File("e:/imgFile/게시판_배경화면.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 실패");
			System.exit(0);
		}
		
		myPanel6 panel = new myPanel6();
		//panel.setSize(581, 712);
		panel.setBounds(0, 0, 581, 712);
		
	
		
		
		tb_notice_list = new JTable(model);
		tb_notice_list.getTableHeader().setReorderingAllowed(false);
		tb_notice_list.getTableHeader().setResizingAllowed(false);
		tb_notice_list.setRowHeight(38);
		tb_notice_list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2) {
					 int rowNum=tb_notice_list.getSelectedRow();
						try {
							new Notice_View(info,noVO.get(rowNum).getNum());
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
				 }
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 123, 363, 460);
		scrollPane.setViewportView(tb_notice_list);
		contentPane.add(scrollPane);
		

		
		tb_notice_write = new JButton("\uAE00\uC4F0\uAE30");
		tb_notice_write.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tb_notice_write.setForeground(new Color(255, 255, 255));
		tb_notice_write.setBackground(new Color(30, 144, 255));
		tb_notice_write.setBorderPainted(false);
		tb_notice_write.setBounds(416, 32, 114, 45);
		tb_notice_write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//글쓰는 창 띄우기?????????????????????????
				String pw = JOptionPane.showInputDialog("관리자 암호를 입력해주세요.");
				if(pw.equals("4321")) {
					dispose();
					new Notice_Write(info);
				}
				
			}
		});
		contentPane.add(tb_notice_write);
		
		contentPane.add(panel);
		contentPane.setLayout(null);
		setVisible(true);
	}
	
	
	
	class myPanel6 extends JPanel { //이미지 보내기
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}	
	}
	
}
