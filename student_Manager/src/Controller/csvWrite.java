package Controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import Model.StudentManager;

//csv파일을 만들어 내보내주는 클래스
public class csvWrite {
		private String filename;
		
		public csvWrite(String path,String name,StudentManager SM) {
			filename = path+"\\" + name + ".csv";
			
			try {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),"MS949"));
				for(int i=0;i<SM.stu_L.size();i++) {
					writer.write(SM.stu_L.get(i).name+","+SM.stu_L.get(i).stunum+",");
					for(int j=0;j<8;j++) {
						if(SM.stu_L.get(i).scores[j] != null) {
							writer.write(SM.stu_L.get(i).scores[j].score+",");
						}else {
							writer.write(0+",");
						}
					}
					writer.write(SM.stu_L.get(i).total+","+SM.stu_L.get(i).grade+"\n");
				}
				System.out.println("studnet 파일 생성");
				writer.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
}
