package letter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import messengerDAO.MessengerDAO;
import messengerVO.MessengerVO;
import letter.Letter_Send;



public class letter_send_search extends JFrame implements ActionListener {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTextField tf_letter_name;
	private JTable tb_letterscerch_list;
	private JButton bt_letter_search, bt_letter_search_no, bt_letter_search_ok;
	private JScrollPane jsp = new JScrollPane();
	private String [] column = {"사원번호","이름","부서","직급","선택"};
	private Object [][] list;
	private MessengerDAO mdao;
	private ArrayList<MessengerVO> arVO;
	private File file;
	private String name, dep, sendname, title, text, filename;
	private int id, row ;
	
	public void DBTable(String name) throws SQLException, ClassNotFoundException {
		
		arVO = mdao.LetterSendSearch(name);
		list = new Object[arVO.size()][column.length];
		for (int i = 0; i < arVO.size(); i++) {
			list[i] = new Object[] {
				arVO.get(i).getEmployee_list(),
				arVO.get(i).getEmployee_name(),
				arVO.get(i).getEmployee_dep(),
				arVO.get(i).getEmployee_rank()
			};
		}
		model = new DefaultTableModel(list,column) {
			 boolean[] columnEditables = new boolean[] {
		                false, false, false, false, true
		            };
			public boolean isCellEditable (int row,int column) {
				
				return columnEditables[column];
				
			}
		};
		tb_letterscerch_list = new JTable(model);
		tb_letterscerch_list.setRowSelectionAllowed(true);
		tb_letterscerch_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb_letterscerch_list.getTableHeader().setReorderingAllowed(false);
		tb_letterscerch_list.getTableHeader().setResizingAllowed(false);
		tb_letterscerch_list.getColumnModel().getColumn(4).setCellRenderer(new TableCell());
		tb_letterscerch_list.getColumnModel().getColumn(4).setCellEditor(new TableCell());
		jsp.setViewportView(tb_letterscerch_list);
	}
	
	public letter_send_search(String sendname2, String title2, String letter2, String filename2, File file2, String name1, String dep1) throws ClassNotFoundException, SQLException {
		
		sendname = sendname2;
		title = title2;
		text = letter2;
		filename = filename2;
		file = file2;
		
		setBounds(100, 100, 581, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("쪽지수신자선택");
		contentPane.setLayout(null);
		
		tf_letter_name = new JTextField();
		tf_letter_name.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_letter_name.setText("\uAC80\uC0C9\uD560 \uC774\uB984\uC744 \uC785\uB825\uD558\uC138\uC694.");
		tf_letter_name.setBounds(120, 37, 394, 53);
		tf_letter_name.addActionListener(this);
		contentPane.add(tf_letter_name);
		tf_letter_name.setColumns(10);
		
		bt_letter_search = new JButton("");
		bt_letter_search.setBackground(new Color(255, 255, 255));
		bt_letter_search.setIcon(new ImageIcon(letter_send_search.class.getResource("/imgFile/\uC0AC\uC6D0\uAC80\uC0C9_\uC544\uC774\uCF58.png")));
		bt_letter_search.setBounds(66, 37, 53, 53);
		bt_letter_search.addActionListener(this);
		contentPane.add(bt_letter_search);
		
		jsp = new JScrollPane();
		jsp.setBounds(12, 129, 541, 457);
		contentPane.add(jsp);
		
		mdao = new MessengerDAO();
		arVO = mdao.LetterSendSearch(); 
		list = new Object[arVO.size()][column.length];
		for (int i = 0; i < arVO.size(); i++) {
			list[i] = new Object[] {
					arVO.get(i).getEmployee_list(),
					arVO.get(i).getEmployee_name(),
					arVO.get(i).getEmployee_dep(),
					arVO.get(i).getEmployee_rank()
			};
		}
		model = new DefaultTableModel(list,column) {
			 boolean[] columnEditables = new boolean[] {
		                false, false, false, false, true
		            };
			public boolean isCellEditable (int row,int column) {
				
				return columnEditables[column];
				
			}
		};
		tb_letterscerch_list = new JTable(model);
		tb_letterscerch_list.setRowSelectionAllowed(true);
		tb_letterscerch_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb_letterscerch_list.getTableHeader().setReorderingAllowed(false);
		tb_letterscerch_list.getTableHeader().setResizingAllowed(false);
		tb_letterscerch_list.getColumnModel().getColumn(4).setCellRenderer(new TableCell());
		tb_letterscerch_list.getColumnModel().getColumn(4).setCellEditor(new TableCell());
		tb_letterscerch_list.setRowHeight(35);
		jsp.setViewportView(tb_letterscerch_list);
		
		bt_letter_search_ok = new JButton("\uD655\uC778");
		bt_letter_search_ok.setBounds(103, 605, 126, 51);
		bt_letter_search_ok.setForeground(new Color(255, 255, 255));
		bt_letter_search_ok.setBackground(new Color(30, 144, 255));
		bt_letter_search_ok.setBorderPainted(false);
		bt_letter_search_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					mdao.LetterSend(id, name, dep, sendname, title, text, filename);
				} catch (SQLException | ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(file!=null) {
					try {
						LetterFile lf = new LetterFile();
						lf.FileSend(file,filename);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				dispose();
			}
			
		});
		contentPane.add(bt_letter_search_ok);
		
		bt_letter_search_no = new JButton("\uCDE8\uC18C");
		bt_letter_search_no.setForeground(new Color(255, 255, 255));
		bt_letter_search_no.setBackground(new Color(128, 128, 128));
		bt_letter_search_no.setBorderPainted(false);
		bt_letter_search_no.setBounds(325, 605, 126, 51);
		bt_letter_search_no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i =JOptionPane.showConfirmDialog(tb_letterscerch_list,"작성하신 내용이 모두 사라집니다."
												+ "\n 정말 닫으시겠습니까?",
											null, JOptionPane.YES_NO_OPTION);
				if(i==0) {
					new Letter_Send(name1,dep1);
					dispose();
				}
					
			}
			
		});
		
		contentPane.add(bt_letter_search_no);
		
		setVisible(true);
	}
	
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
		JCheckBox jcb;
		
		public TableCell() {
			jcb = new JCheckBox();
			jcb.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if(e.getStateChange()==1) {
						row = tb_letterscerch_list.getSelectedRow();
						id = (int) tb_letterscerch_list.getValueAt(row, 0);
						name = (String) tb_letterscerch_list.getValueAt(row, 1);
						dep = (String) tb_letterscerch_list.getValueAt(row, 2);

					}
				}
			});
		}
		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
			return jcb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) 
		{
			// TODO Auto-generated method stub
			return jcb;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt_letter_search) {
			if(tf_letter_name.getText() == null) {
				JOptionPane.showMessageDialog(tb_letterscerch_list, "이름을 입력해주세요.");
			}else {
				try {
					DBTable(tf_letter_name.getText());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}			
		}else if(e.getSource()==tf_letter_name) {
			try {
				DBTable(tf_letter_name.getText());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
