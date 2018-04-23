//开启线程的三种方式
//方式1.继承Thread
public class MyThread1 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("第"+i+"次");
		}
	}
	
	public static void main(String[] args) {
		new MyThread1().start();
		new MyThread1().start();
	}
}
