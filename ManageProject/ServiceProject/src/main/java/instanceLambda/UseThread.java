package instanceLambda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseThread {

  private List<Ticket> tickets = new ArrayList<>();

  private Lock lock = new ReentrantLock();

  public void addTicket(String name) {
    Ticket t = null;
    while (tickets.size() < 10) {
      synchronized (this) {
        if (tickets.size() < 10) {
          t = new Ticket();
          t.setNumber(tickets.size() + 1);
          t.setName(name);
          tickets.add(t);
          System.out
              .println(Thread.currentThread().getName() + "   " + t.toString() + "   by   " + name);
        }
      }

      try {
        TimeUnit.MILLISECONDS.sleep(50L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void sellTicket(String name) {
    Ticket t = null;
    while (tickets.size() > 0) {
      try {
        lock.lock();
        if (tickets.size() > 0) {
          t = tickets.remove(0);
          System.out
              .println(Thread.currentThread().getName() + "   " + t.toString() + "   by   " + name);
        }
      } finally {
        lock.unlock();
      }

      try {
        TimeUnit.MILLISECONDS.sleep(50L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}


class Ticket {

  private int number;

  private String name;

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.number + "   " + this.name;
  }
}
