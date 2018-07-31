import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient implements Runnable{
	
	public static void main(String[] args) {
		new MyClient().run();
	}
	
	@Override
	public void run() {
		try {
			//1.创建socket对象，绑定服务端地址和端口
			Socket socket = new Socket("localhost", 10086);
			
			//2.获取输出流对客户端发送信息
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("我是客户端----发出请求");
			pw.flush();
			socket.shutdownOutput();
			
			//3.获取输入流获取服务端的响应
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			if((info = br.readLine())!=null) {
				System.out.println(info);
			}
			
			//4.关闭资源
			pw.close();
			os.close();
			br.close();
			is.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
