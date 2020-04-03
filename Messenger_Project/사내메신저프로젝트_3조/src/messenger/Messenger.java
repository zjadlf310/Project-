package messenger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Updateinfo.Updateinfo;
import chattingClient.ChattingClient;
import letter.Letter_List;
import letter.Letter_Send;
import login.Login;
import messengerDAO.MessengerDAO;
import messengerVO.MessengerVO;
import notice.Notice;

public class Messenger extends JFrame implements ActionListener {
	BufferedImage img = null;
	private JPanel contentPane;
	private JMenuBar menubar;
	private JMenu menu_setting;
	private JMenu menu_letter;
	private JMenu menu_notice;
	private JScrollPane scrollPane;
	private JTable tb_list;
	private DefaultTableModel dtm;
	private JLabel lb_main_name;
	private JLabel lb_main_dep;
	private JLabel lb_main_rank;
	private JComboBox cb_main_status;
	private JLabel lb_main_sign;
	private JMenuItem menu_revise;
	private JMenuItem menu_logout;
	private JMenuItem menu_letter_send;
	private JMenuItem letter_post;
	private JMenuItem menu_chat;
	private JMenuItem menu_notoce_check;
	private Socket socket;
	
	private MessengerDAO mdao;
	private ArrayList<MessengerVO> noVO ,arVO;
	private String [] column = {"이름","부서","직급"};
	private Object [][] list;
	private DefaultTableModel model;


	
	public Messenger(ArrayList<MessengerVO> info) throws UnknownHostException, IOException, ClassNotFoundException, SQLException {		
		//***** 서버 ip주소 , 정해진 포트번호 *****		
		arVO = info;
		
		setTitle("홈");
		
		setBounds(100, 100, 581, 712);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					mdao.outup(arVO.get(0).getEmployee_list());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		try {
			img = ImageIO.read(new File("e:/imgFile/메신저_배경.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 실패");
			System.exit(0);
		}
		
		myPanel3 panel = new myPanel3();
		panel.setSize(240, 240);
		panel.setBounds(0,15,300,300);
		contentPane.setLayout(null);
		
		menubar = new JMenuBar();
		menubar.setBounds(0, 0, 565, 32);
		contentPane.add(menubar);
		
		menu_setting = new JMenu("   \uC77C\uBC18   ");
		menu_setting.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		menu_setting.setBackground(new Color(204, 204, 204));
		menu_setting.setOpaque(true);
		menubar.add(menu_setting);
		
		//로그아웃
		menu_logout = new JMenuItem("\uB85C\uADF8\uC544\uC6C3");
		menu_logout.addActionListener(this);
		menu_setting.add(menu_logout);
		
		//정보수정
		menu_revise = new JMenuItem("\uC815\uBCF4 \uC218\uC815");
		menu_revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Updateinfo up=new Updateinfo(arVO.get(0),socket);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					dispose();			
			}
		});
		menu_setting.add(menu_revise);
		
		//채팅
		menu_chat = new JMenuItem("\uCC44\uD305");
		menu_chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ChattingClient(arVO);
				} catch (IOException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menu_setting.add(menu_chat);
		
		menu_letter = new JMenu("   \uCABD\uC9C0   ");
		menu_letter.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		menubar.add(menu_letter);
		
		menu_letter_send = new JMenuItem("\uBCF4\uB0B4\uAE30");
		menu_letter_send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Letter_Send(arVO.get(0).getEmployee_name(),arVO.get(0).getEmployee_dep());
			}
			
		});
		menu_letter.add(menu_letter_send);
		
		letter_post = new JMenuItem("\uBCF4\uAD00\uD568");
		letter_post.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new Letter_List(arVO.get(0).getEmployee_list(),arVO.get(0).getEmployee_name());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		menu_letter.add(letter_post);
		
		menu_notice = new JMenu("\uACF5\uC9C0 \uC0AC\uD56D");
		menu_notice.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		menu_notice.setBackground(new Color(204, 204, 204));
		menu_notice.setOpaque(true);
		menubar.add(menu_notice);
		
		menu_notoce_check = new JMenuItem("\uBAA9\uB85D");
		menu_notoce_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Notice notice=new Notice(arVO.get(0));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menu_notice.add(menu_notoce_check);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 304, 565, 369);
		contentPane.add(scrollPane);
		
		
		
		mdao =new MessengerDAO();
		noVO =mdao.LetterSendSearch();
		list = new Object[noVO.size()][column.length];
		for (int i = 0; i < noVO.size(); i++) {
			list[i] = new Object[] {
				noVO.get(i).getEmployee_name(),//이름
				noVO.get(i).getEmployee_rank(),//직급
				noVO.get(i).getEmployee_dep(),//여기에 로그인 로그아웃상태를 쏴줘야돼.
			};
		}
		model = new DefaultTableModel(list, column) {
			boolean[] columnEditables = new boolean[] {
					 false, false, false
	            };
			public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
		};
		
		tb_list = new JTable(model);
		tb_list.getTableHeader().setReorderingAllowed(false);
		tb_list.getTableHeader().setResizingAllowed(false);
		tb_list.setRowHeight(35);
		scrollPane.setViewportView(tb_list);
		
		lb_main_name = new JLabel();
		lb_main_name.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_main_name.setBounds(95, 107, 149, 32);
		lb_main_name.setText("이름 "+arVO.get(0).getEmployee_name());
		contentPane.add(lb_main_name);
		
		lb_main_dep = new JLabel();
		lb_main_dep.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_main_dep.setBounds(95, 149, 149, 32);
		lb_main_dep.setText("부서 "+arVO.get(0).getEmployee_dep());
		contentPane.add(lb_main_dep);
		
		lb_main_rank = new JLabel();
		lb_main_rank.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_main_rank.setBounds(95, 191, 149, 32);
		lb_main_rank.setText("직급 "+arVO.get(0).getEmployee_rank());
		contentPane.add(lb_main_rank);
		
		
		
		 String[] state = { "로그인", "로그아웃", "자리비움" };
	     ImageIcon[] images = { new ImageIcon("e:/imgFile/상태표시_로그인.PNG"),   new ImageIcon("e:/imgFile/상태표시_로그아웃.PNG"),
	       new ImageIcon("e:/imgFile/상태표시_자리비움.PNG") };

		
		
		JComboBox cb_main_status = new JComboBox(state);
		cb_main_status.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JComboBox cb = (JComboBox) e.getSource(); // 콤보박스 알아내기

                 int index = cb.getSelectedIndex();// 선택된 아이템의 인덱스

                 lb_main_sign.setIcon(images[index]); // 인덱스의 이미지를 이미지 레이블에 출력
			}
		});
		cb_main_status.setToolTipText("\uC628\uB77C\uC778\r\n\uC624\uD504\uB77C\uC778\r\n\uC790\uB9AC\uBE44\uC6C0\r\n");
		cb_main_status.setBounds(311, 139, 149, 32);
		contentPane.add(cb_main_status);
		
		contentPane.add(panel);
		
		//이미지는 이미지파일에 로그인, 로그아웃, 자리비움있으니 잘 써주세요~
		lb_main_sign = new JLabel("");
		lb_main_sign.setIcon(new ImageIcon(Messenger.class.getResource("/imgFile/\uC0C1\uD0DC\uD45C\uC2DC_\uB85C\uADF8\uC778.PNG")));
		lb_main_sign.setBounds(472, 139, 32, 32);
		contentPane.add(lb_main_sign);
		setVisible(true);
	}


	class myPanel3 extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==menu_logout) {
			try {
				mdao.outup(arVO.get(0).getEmployee_list());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new Login();
			
			dispose();
		}
		
		
	}
}
