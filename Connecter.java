package hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class Connecter {
	
	static {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}
	
	
	public static void connect_hadoop() throws Exception{
		InputStream in = null;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("확인하고 싶은 데이터의 날짜를 선택해 주세요. (2018.01.01 ~ 2022.08.24)");
		System.out.println("입력형식: 20220801");
		System.out.print(">>");
		String date = sc.next();
		
		String str1 = "http://192.168.244.128:50070/webhdfs/v1/user/OutputDATA/sort"+date+"/part-r-00000?op=OPEN";
				
		try {
			in = new URL(str1).openStream();
			IOUtils.copyBytes(in, System.out, 4096, false);
		}
		finally {
			IOUtils.closeStream(in);
		}
	}
	
}