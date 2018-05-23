package ddalgi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class SingleFileWrite {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("Usage: SingleFileWrite <Filename> <contents>");
			System.exit(2);
		}
		
		try {
			//파일 시스템(HDFS) t
			Configuration conf = new Configuration();
			FileSystem hdfs = FileSystem.get(conf);	//파일시스템을 생성, get은 static method
			
			//경로체크
			Path path = new Path(args[0]);
			
			//입력받은 경로가 하둡에 존재한다면
			if(hdfs.exists(path)) {
				//지움. 이미 있는 경우 저장이 불가하기 때문
				hdfs.delete(path, true);
			}
			
			//파일 저장. path 경로로 하둡 파일 시스템에 생성
			//이미 경로를 체크했기 때문에 문제 없음
			FSDataOutputStream outStream = hdfs.create(path);
			//하둡에는 unicode로 써야함
			outStream.writeUTF(args[1]);
			outStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
