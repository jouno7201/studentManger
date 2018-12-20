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
	
	//menu 생성
	JMenu Fnotice = new JMenu("F알림");
	JMenu file = new JMenu("파일");
	JMenu sorting = new JMenu("정렬");
	JMenu rate_setting = new JMenu("비율설정");
	JMenu same = new JMenu("동점자");
	
	//menuitem 생성
	JMenuItem FnoticeAlert = new JMenuItem("F대상자");
	
	JMenuItem fileWrite = new JMenuItem("내보내기");
	JMenuItem fileRead = new JMenuItem("불러오기");
	
	JMenuItem namesort = new JMenuItem("이름 순");
	JMenuItem totalsort = new JMenuItem("총점 순");
	JMenuItem idsort = new JMenuItem("학번 순");
	
	
	JMenuItem rate1 = new JMenuItem("성적 반영 비율");
	JMenuItem rate2 = new JMenuItem("학점 비율");
	
	JMenuItem sameBool = new JMenuItem("동점자 여부");
	
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
