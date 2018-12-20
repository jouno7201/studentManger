package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class grape extends JPanel{
	int[] value;
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//고정
		g.drawLine(50, 100, 50, 500);
		g.drawLine(50, 500, 450, 500);
		//고정
		g.drawString("0~60", 70, 530);
		g.drawString("60~70", 140, 530);
		g.drawString("70~80", 210, 530);
		g.drawString("80~90", 280, 530);
		g.drawString("90~100", 350, 530);
		
		g.setColor(Color.BLUE);
		//변경
		System.out.println(value[0]*10);
		g.drawRect( 70, 500-value[0]*15, 30,value[0]*15);
		g.drawRect( 140,500-value[1]*15, 30,value[1]*15);
		g.drawRect( 210,500-value[2]*15, 30,value[2]*15);
		g.drawRect( 280,500-value[3]*15, 30,value[3]*15);
		g.drawRect( 350,500-value[4]*15, 30,value[4]*15);
		
		g.fillRect( 70, 500-value[0]*15, 30,value[0]*15);
		g.fillRect( 140,500-value[1]*15, 30,value[1]*15);
		g.fillRect( 210,500-value[2]*15, 30,value[2]*15);
		g.fillRect( 280,500-value[3]*15, 30,value[3]*15);
		g.fillRect( 350,500-value[4]*15, 30,value[4]*15);
		
		
	}
	public grape(int[] value) {
		this.value =value;
		
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.BLUE,5,true));
		//setSize(new Dimension(100,50));
	}
		
}
