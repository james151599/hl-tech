package instanceThread;

public class TestThread2 {
	public static void main(String[] args) {
		Basket2 b = new Basket2();
		Producer2 p = new Producer2(b);
		Consumer2 c = new Consumer2(b);
		new Thread(p).start();
		new Thread(c).start();
		// view wait(),notify() api
	}
}

class Producer2 implements Runnable {
	Basket2 b;

	public Producer2(Basket2 b) {
		this.b = b;
	}

	@Override
	public void run() {
		try {
			Commodity2 c = null;
			for (int i = 0; i < 20; i++) {
				if (i > 10) {
					Thread.sleep(700);
				}
				c = new Commodity2(i);
				b.putOne(c);
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}

class Consumer2 implements Runnable {
	Basket2 b;

	public Consumer2(Basket2 b) {
		this.b = b;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				if (i < 10) {
					Thread.sleep(700);
				}
				b.getOne();
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}

class Commodity2 {
	private int id;

	public Commodity2(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "commodity" + this.id;
	}
}

class Basket2 {
	private int index = 0;
	private Commodity2[] food = new Commodity2[3];

	public synchronized void putOne(Commodity2 one) {
		while (index == food.length) {
			try {
				// notify(); //生产满了才通知消耗
				wait(); // 使访问当前对象的线程等待
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		food[index] = one;
		System.out.println("produce " + one + " put at index: " + index);
		index++;
		notify();// 调用wait后仍然可以执行
	}

	public synchronized Commodity2 getOne() {
		Commodity2 tmp = null;
		while (index == 0) {
			try {
				// notify(); //消耗完了才通知生产
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		index--;
		tmp = food[index];
		food[index] = null;
		System.out.println("consume " + tmp + " get at index: " + index);
		notify();
		return tmp;
	}
}