package letter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import messengerVO.MessengerVO;

public class Letter_View extends JFrame {
	BufferedImage img = null;
	private JPanel contentPane;
	private JTextField ta_letter_receive_title;
	private JTextArea ta_letter_read_file;
	private JButton bt_letter_view_get;
	private JTextArea textArea;

	public Letter_View(MessengerVO mvo) {
		
		setBounds(100, 100, 581, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			img = ImageIO.read(new File("e:/imgFile/ÂÊÁöÈ­¸é_Å×µÎ¸®.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ÀÌ¹ÌÁö ½ÇÆÐ");
			System.exit(0);
		}
		
		myPanel5 panel = new myPanel5();
		panel.setSize(240, 240);
		panel.setBounds(15,95,550,560);
		setTitle("ÂÊÁöº¸±â");
		contentPane.setLayout(null);
				
		ta_letter_receive_title = new JTextField();
		ta_letter_receive_title.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		ta_letter_receive_title.setText(mvo.getLetter_title());
		contentPane.add(ta_letter_receive_title);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 127, 480, 417);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		textArea.setText(mvo.getLetter_text());
		scrollPane_1.setViewportView(textArea);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(46, 565, 369, 58);
		contentPane.add(scrollPane_2);
		
		ta_letter_read_file = new JTextArea();
		ta_letter_read_file.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		ta_letter_read_file.setText(mvo.getLetter_filename());
		scrollPane_2.setViewportView(ta_letter_read_file);
		
		bt_letter_view_get = new JButton("\uBC1B\uAE30");
		bt_letter_view_get.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		bt_letter_view_get.setBorderPainted(false);
		bt_letter_view_get.setForeground(new Color(255, 255, 255));
		bt_letter_view_get.setBackground(new Color(30, 144, 255));
		bt_letter_view_get.setBounds(442, 565, 84, 58);
		bt_letter_view_get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					new LetterFile().FileDownload(mvo);
					JOptionPane.showMessageDialog(contentPane, "´Ù¿î¿Ï·á");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		contentPane.add(bt_letter_view_get);
		
		contentPane.add(panel);
		setVisible(true);
	}
	
	class myPanel5 extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}	
	}
}
