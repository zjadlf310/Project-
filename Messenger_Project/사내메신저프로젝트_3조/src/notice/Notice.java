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
	private JButton tb_notice_write;//�ۼ��ϱ�
	private JScrollPane scrollPane;
	MessengerDAO mdao;
	ArrayList<MessengerVO> noVO;
	String [] column = {"��ȣ","��¥","����","�̸�"};
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
				noVO.get(i).getNum(),//������ȣ
				noVO.get(i).getRegdate(),//��¥
				noVO.get(i).getTitle(),//����
				noVO.get(i).getWriter()//�۾���
				
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
		setTitle("��������");
		setContentPane(contentPane);
		
		
		try {
			img = ImageIO.read(new File("e:/imgFile/�Խ���_���ȭ��.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�̹��� ����");
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
		tb_notice_write.setFont(new Font("���� ���", Font.BOLD, 15));
		tb_notice_write.setForeground(new Color(255, 255, 255));
		tb_notice_write.setBackground(new Color(30, 144, 255));
		tb_notice_write.setBorderPainted(false);
		tb_notice_write.setBounds(416, 32, 114, 45);
		tb_notice_write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�۾��� â ����?????????????????????????
				String pw = JOptionPane.showInputDialog("������ ��ȣ�� �Է����ּ���.");
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
	
	
	
	class myPanel6 extends JPanel { //�̹��� ������
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}	
	}
	
}
