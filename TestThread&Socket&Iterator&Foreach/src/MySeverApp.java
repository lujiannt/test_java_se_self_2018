import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MySeverApp implements Runnable{
	
	public static void main(String[] args) {
		new MySeverApp().run();
	}
	
	@Override
	public void run() {
		try {
			//1.创建socket对象绑定端口
			ServerSocket socket = new ServerSocket(10086);
			//2.调用accept方法对客户端进行监听
			Socket sk = socket.accept();
			//3.获取输入流读取客户端请求信息
			InputStream is = sk.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
			String line = null;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
			//4.获取输出流对客户端进行响应
			OutputStream os = sk.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("响应");
			
			//关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
