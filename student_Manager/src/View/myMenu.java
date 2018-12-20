package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controller.Control;
import DB.DB_conneting;
import Model.StudentManager;

public class myMenu extends JMenuBar{
	
	//menu ����
	JMenu Fnotice = new JMenu("F�˸�");
	JMenu file = new JMenu("����");
	JMenu sorting = new JMenu("����");
	JMenu rate_setting = new JMenu("��������");
	JMenu same = new JMenu("������");
	
	//menuitem ����
	JMenuItem FnoticeAlert = new JMenuItem("F�����");
	
	JMenuItem fileWrite = new JMenuItem("��������");
	JMenuItem fileRead = new JMenuItem("�ҷ�����");
	
	JMenuItem namesort = new JMenuItem("�̸� ��");
	JMenuItem totalsort = new JMenuItem("���� ��");
	JMenuItem idsort = new JMenuItem("�й� ��");
	
	
	JMenuItem rate1 = new JMenuItem("���� �ݿ� ����");
	JMenuItem rate2 = new JMenuItem("���� ����");
	
	JMenuItem sameBool = new JMenuItem("������ ����");
	
	public myMenu(StudentManager SM, DB_conneting DB) {
		
		FnoticeAlert.addActionListener(new Control().new FA_ActionListenner(SM));
		
		fileRead.addActionListener(new Control().new fr_ActionListenner(SM,DB));
		fileWrite.addActionListener(new Control().new fw_ActionListenner(SM));
		
		namesort.addActionListener(new Control().new namesort_ActionListenner(SM));
		totalsort.addActionListener(new Control().new totalsort_ActionListenner(SM));
		idsort.addActionListener(new Control().new idsort_ActionListenner(SM));
		
		rate1.addActionListener(new Control().new rate_ActionListenner(SM));
		rate2.addActionListener(new Control().new rateG_ActionListenner(SM));
		
		sameBool.addActionListener(new Control().new same_ActionListenner(SM));
		
		Fnotice.add(FnoticeAlert);
		
		file.add(fileRead);
		file.add(fileWrite);
		
		sorting.add(namesort);
		sorting.add(totalsort);
		sorting.add(idsort);
		
		
		rate_setting.add(rate1);
		rate_setting.add(rate2);
		
		same.add(sameBool);
		
		add(Fnotice);
		add(file);
		add(sorting);
		add(rate_setting);
		add(same);
	}
}
