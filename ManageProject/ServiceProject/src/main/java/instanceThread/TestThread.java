package instanceThread;

public class TestThread {
	public static void main(String[] args) {
		Basket b = new Basket();
		Producer p = new Producer(b);
		Consumer c = new Consumer(b);
		new Thread(p).start();
		new Thread(c).start();
	}
}

class Producer implements Runnable {
	Basket b;

	public Producer(Basket b) {
		this.b = b;
	}

	@Override
	public void run() {
		try {
			Commodity c = null;
			for (int i = 0; i < 20; i++) {
				c = new Commodity(i);
				b.putOne(c);
				if (i > 10) {
					Thread.sleep(700);
				}
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	Basket b;

	public Consumer(Basket b) {
		this.b = b;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				b.getOne();
				if (i < 10) {
					Thread.sleep(700);
				}
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}

class Commodity {
	private int id;

	public Commodity(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "commodity" + this.id;
	}
}

class Basket {
	private int index = 0;
	private Commodity[] food = new Commodity[3];

	public synchronized void putOne(Commodity one) {
		if (index < food.length) {
			food[index] = one;
			System.out.println("produce " + one + " put at index: " + index);
			index++;
		}
	}

	public synchronized Commodity getOne() {
		Commodity tmp = null;
		if (index > 0) {
			index--;
			tmp = food[index];
			food[index] = null;
			System.out.println("consume " + tmp + " get at index: " + index);
		}
		return tmp;
	}
}