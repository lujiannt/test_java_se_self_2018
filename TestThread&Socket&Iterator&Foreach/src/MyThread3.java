import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//开启线程的三种方式
//方式3.继承callable接口，与runable的区别：1：call方法有返回值，2：call方法可以抛出异常
//futureTask
public class MyThread3 implements Callable<Integer>{

	public static void main(String[] args) {
		MyThread3 my = new MyThread3();
		FutureTask<Integer> ft = new FutureTask<Integer>(my);
		FutureTask<Integer> ft1 = new FutureTask<Integer>(my);
		new Thread(ft, "方式3").start();
		new Thread(ft1, "方式3").start();
	}

	@Override
	public Integer call() throws Exception {
		for(int i=0;i<10;i++) {
			System.out.println("第"+i+"次");
		}
		System.out.println("执行完毕");
		return null;
	}
}
