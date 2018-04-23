//开启线程的三种方式
//方式2.继承Runnable接口
public class MyThread2 implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("第"+i+"次");
		}
		System.out.println("执行完毕");
	}
	
	public static void main(String[] args) {
		MyThread2 my = new MyThread2();
		new Thread(my).start();
		new Thread(my).start();
	}
}
