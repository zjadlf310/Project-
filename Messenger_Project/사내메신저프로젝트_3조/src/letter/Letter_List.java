package letter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import messengerDAO.MessengerDAO;
import messengerVO.MessengerVO;

public class Letter_List extends JFrame {

	private JPanel contentPane;
	private JTable tb_letter_list;
	private String [] column = {"쪽지번호","제목","보낸이","작성일","선택"};
	private Object [][] list;
	private JButton bt_letter_delete;
	private MessengerDAO mdao;
	private ArrayList<MessengerVO> arVO;
	private DefaultTableModel model;
	private int id, row ;
	private String title, sendname, name;
	private JScrollPane jsp;
	
	public void Letter_Table_Refrash() throws SQLException, ClassNotFoundException {
		arVO = mdao.LetterList(id,name);
		list = new Object[arVO.size()][column.length];
		for (int i = 0; i < arVO.size(); i++) {
			list[i] = new Object[] {
				(i+1),
				arVO.get(i).getLetter_title(),
				arVO.get(i).getLetter_send_name(),
				arVO.get(i).getLetter_date()
			};
			System.out.println(arVO.get(i).getLetter_title());
		}
		
		model = new DefaultTableModel(list,column) {
			 boolean[] columnEditables = new boolean[] {
		                false, false, false, false, true
		            };
			public boolean isCellEditable (int row,int column) {
				
				return columnEditables[column];
				
			}
		};
		
		
		
		tb_letter_list = new JTable(model);
		tb_letter_list.setRowHeight(35);
		tb_letter_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb_letter_list.getTableHeader().setReorderingAllowed(false);
		tb_letter_list.getTableHeader().setResizingAllowed(false);
		tb_letter_list.getColumnModel().getColumn(4).setCellRenderer(new TableCell());
		tb_letter_list.getColumnModel().getColumn(4).setCellEditor(new TableCell());
		tb_letter_list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2) {
					 int rowNum = tb_letter_list.getSelectedRow();
		             MessengerVO mvo = new MessengerVO();
		             mvo = arVO.get(rowNum);
		             
		             new Letter_View(mvo);
				 }
					 
			}
		});
		jsp.setViewportView(tb_letter_list);
	}


	public Letter_List(int id, String name) throws ClassNotFoundException, SQLException {
		this.id = id;
		this.name = name;
		
		setBounds(100, 100, 581, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("쪽지보관함");
		contentPane.setLayout(null);
		
		
		jsp = new JScrollPane();
		jsp.setBounds(12, 10, 541, 451);
		contentPane.add(jsp);
		mdao = new MessengerDAO();
				
		arVO = mdao.LetterList(id,name);
		list = new Object[arVO.size()][column.length];
		for (int i = 0; i < arVO.size(); i++) {
			list[i] = new Object[] {
				(i+1),
				arVO.get(i).getLetter_title(),
				arVO.get(i).getLetter_send_name(),
				arVO.get(i).getLetter_date()
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
		
		tb_letter_list = new JTable(model);
		tb_letter_list.setRowHeight(35);
		tb_letter_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb_letter_list.getTableHeader().setReorderingAllowed(false);
		tb_letter_list.getTableHeader().setResizingAllowed(false);
		tb_letter_list.getColumnModel().getColumn(4).setCellRenderer(new TableCell());
		tb_letter_list.getColumnModel().getColumn(4).setCellEditor(new TableCell());
		tb_letter_list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2) {
					 int rowNum = tb_letter_list.getSelectedRow();
		             MessengerVO mvo = new MessengerVO();
		             mvo = arVO.get(rowNum);
		             
		             new Letter_View(mvo);
				 }
					 
			}
		});
		jsp.setViewportView(tb_letter_list);
		
		bt_letter_delete = new JButton("\uC0AD\uC81C");
		bt_letter_delete.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		bt_letter_delete.setBorderPainted(false);
		bt_letter_delete.setForeground(new Color(255, 255, 255));
		bt_letter_delete.setBackground(new Color(30, 144, 255));
		bt_letter_delete.setBounds(27, 485, 100, 50);
		bt_letter_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(mdao.LetterDelete(id, name, sendname, title)) {
						JOptionPane.showMessageDialog(contentPane, "삭제되었습니다.");
						Letter_Table_Refrash();
					}
				} catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		contentPane.add(bt_letter_delete);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Letter_List.class.getResource("/imgFile/\uCABD\uC9C0_\uBCF4\uAD00\uD568_\uBAA9\uB85D3.jpg")));
		lblNewLabel.setBounds(12, 460, 541, 203);
		contentPane.add(lblNewLabel);
		
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
						row = tb_letter_list.getSelectedRow();
						title = (String) tb_letter_list.getValueAt(row, 1);
						sendname = (String) tb_letter_list.getValueAt(row, 2);
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
	
}
