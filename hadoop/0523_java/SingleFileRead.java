package ddalgi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class SingleFileRead {

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
			
			//입력받은 경로가 하둡에 존재하지 않는 경우
			if(!hdfs.exists(path)) {
				System.out.println("File does not exist!!");
				System.exit(2);
			}
			
			//파일 읽기
			FSDataInputStream inputStream = hdfs.open(path);
			String inputString = inputStream.readUTF();
			inputStream.close();
			
			
			System.out.println("\t## inputString: " + inputString);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
