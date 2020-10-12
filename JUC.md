# JUC

juc 是java.util.concurrent在并发编程中使用的工具类

**什么是线程/线程：**

**进程**是一个具有一定独立功能的程序关于某个数据集合的一次运行活动。它是操作系统动态执行的基本单元，在传统的操作系统中，进程既是基本的分配单元，也是基本的执行单元。

**线程**通常在一个进程中可以包含若干个线程，当然一个进程中至少有一个线程。线程可以利用进程所拥有的资源，在引入线程的操作系统中，通常都是把进程作为分配资源的基本单位，而线程作为独立运行和独立调度的基本单位，由于线程比进程更小，基本上不拥有系统资源，故对他的调度所付出的开销就会小的多，能更效的提高系统多个程序间并发执行的程度。

## 什么是并发/并行

**并行**在操作系统中，是指一个时间段中有几个程序都处于已启动运行到运行完毕之间，且这几个程序都是在同一个处理机上运行

**并发**当系统有一个以上CPU时，当一个CPU执行一个进程时，另一个CPU可以执行另一个进程，两个进程互不抢占CPU资源，可以同时进行，这种方式我们称之为并行(Parallel)。

多线程编程：高内聚低耦合，线程   操作   资源类

```java
class Ticket{//资源类
    private int number = 30;
    //private Lock lock = new ReentrantLock();
    public synchronized void saleTicket(){
        
        if (number > 0){
            System.out.println(Thread.currentThread().getName()+ "\t卖出第："+(number--)+"\t"+"还剩下"+number);
        }
    }
}
```

**synchronized**：（直接全部锁死了）代表这个方法加锁,相当于不管哪一个线程（例如线程A），运行到这个方法时,都要检查有没有其它线程B（或者C、 D等）正在用这个方法(或者该类的其他同步方法)，有的话要等正在使用synchronized方法的线程B（或者C 、D）运行完这个方法后再运行此线程A,没有的话,锁定调用者,然后直接运行。

synchronized关键字是用来控制线程同步的，就是在多线程的环境下，控制synchronized代码段不被多个线程同时执行

```java
class Ticket{
    private int number = 30;
    private Lock lock = new ReentrantLock();
    public void saleTicket(){
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+ "\t卖出第："+(number--)+"\t"+"还剩下"+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
```

（1）synchronized是独占锁，加锁和解锁的过程自动进行，易于操作，但不够灵活。ReentrantLock也是独占锁，加锁和解锁的过程需要手动进行，不易操作，但非常灵活。

（2）synchronized可重入，因为加锁和解锁自动进行，不必担心最后是否释放锁；ReentrantLock也可重入，但加锁和解锁需要手动进行，且次数需一样，否则其他线程无法获得锁。

（3）synchronized不可响应中断，一个线程获取不到锁就一直等着；ReentrantLock可以相应中断。

ReentrantLock好像比synchronized关键字没好太多，我们再去看看synchronized所没有的，一个最主要的就是ReentrantLock还可以实现公平锁机制。什么叫公平锁呢？也就是在锁上等待时间最长的线程将获得锁的使用权。通俗的理解就是谁排队时间最长谁先执行获取锁。

（自己选择代码块进行锁死）

```java
new Thread(new Runnable() {  //传统匿名类
    @Override
    public void run() {
        for (int i = 1; i <= 40; i++){
            ticket.saleTicket();  //资源类
        }
    }
}, "A").start();
```

```java
public static void main(String[] args) throws Exception {//拉不大表达式

    Ticket ticket = new Ticket();

    new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.saleTicket();},"A").start();
    new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.saleTicket();},"B").start();
    new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.saleTicket();},"C").start();
}
```

mysql：3306

线程几种状态：

NEW（新建）， RUNNABLE（可运行），BLOCKED（阻塞），WAITING(一直等待)，TIMED_WAITING（等待一定时间），

**wait/sleep**功能都是当前线程暂停，区别？

wait：放开手去睡，放开手里的锁  sleep：紧握手去睡，醒了手里还有锁

![img](https://img-blog.csdnimg.cn/2019050816141738.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzIwMDA5MDE1,size_16,color_FFFFFF,t_70)

## LambdExpress

```java

public class LambdaExpressDemo {
    public static void main(String[] args) {
        /*Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello");
            }
        };
        foo.sayHello();*/
        Foo foo = () -> {
            System.out.println("hello");
        };
        foo.sayHello();
    }
}

interface Foo{
    public void sayHello();
}
```

```java
public class LambdaExpressDemo {
    public static void main(String[] args) {
        Foo foo = (x, y) -> {
            System.out.println("come baby");
            return x + y;
        };
        System.out.println(foo.add(3, 5));
    }
}

@FunctionalInterface  //函数式接口
interface Foo{
    public int add(int x, int y);

    default int div(int x, int y) {
        System.out.println("hello");
        return x/y;
    }
    public static int mv(int x, int y){
        return x * y;
    }
}
```

**@FunctionalInterface** 

1、该注解只能标记在"有且仅有一个抽象方法"的接口上。

2、JDK8接口中的[静态方法和默认方法](http://blog.csdn.net/aitangyong/article/details/54134385)，都不算是抽象方法。

3、接口默认继承java.lang.Object，所以如果接口显示声明覆盖了Object中方法，那么也不算抽象方法。

4、该注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错。

java TimeUnit新工具类

Lock [Condition](https://blog.csdn.net/qq_29951485/article/details/90294502?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param)

lock中类似wait和notify的方法：
await和signal/signalAll

原理：lock中使用Condition类做为锁监视器，调用Condition的await()和signal()实现线程的等待和唤醒。



## 8锁问题

```java
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(200);

        new Thread(() -> {
            try {
                //phone.sendSMS();
                //phone.hello();
                phone1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

class Phone {
    public static synchronized void sendEmail() throws Exception{
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("sendEmail");
    }
    public synchronized void sendSMS() throws Exception{
        System.out.println("sendSMS");
    }
    public void hello(){
        System.out.println("hello");
    }
}
```

```java
/* 题目：多线程8锁
* 1.标准访问，请问先打印邮件还是短信 sendEmail
* 2.邮件方法暂停4秒钟，请问先打印邮件还是短信 sendEmail
* 3.新增一个普通方法hello， 先打印邮件还是hello hello
* 4.两部手机，先打印邮件还是短信 sendSMS
* 5.两个静态同步方法，同一部手机，请问先打印邮件还是短信 sendEmail
* 6.两个静态同步方法，两部手机，请问先打印邮件还是短信 sendEmail
* 7.1个普通同步方法，1一个静态同步方法，1个手机，先打印短信还是邮件 sendSMS
* 8.1个普通同步方法，1一个静态同步方法，2个手机，先打印短信还是邮件 sendSMS
*/
```

1.synchronized锁的是一个资源类的所有方法，即一个只有一个线程先执行

一个对象里面如果有多个synchronized方法，某一时刻内，只有一个线程调用其中一个synchronized方法，其他线程都只能等待，换句话说，某一时刻内，只能有唯一一个线程访问synchronized这些方法，锁的是当前对象this，被锁后，其他线程都不能进入到当前对象的其他的synchronized

2.和第一问一样，synchronized锁的是一个类，只有一个线程能访问当前类的一个方法，结束了才能下一个线程访问

3.加普通方法后发现和同步锁无关，

4.换成两个对象后，不是同一把锁，情况立刻变化

5.6 都换成静态同步方法后，情况又变化，new this 具体的一部部， 静态class，唯一的一个模板

所有的非静态同步方法用的都是同一把锁——实例化对象本身

synchronized实现同步的基础：java中的每一个对象都可以作为锁。

具体表现为一下三种形式：1.对于普通同步方法，锁是当前实例对象。2.对于静态同步方法，锁的当前类的Class对象。3.对于同步方法块，锁是synchronized括号里的对象

当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。

也就是说如果一个实例对的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不用的锁，所以必须等待该实例对象以获取锁的非静态同步方法释放锁就可以获取他们自己的锁。

所有的静态同步方法用的是同一把锁——类对象本身（calss），这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的，但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，而不管同一个实例对象的静态同步方法之间，还是不同的实例对对象的静态同步方法之间，只要他们同一个类的实例对象。

## list的线程不安全

```java
public class NotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
```

报错，当线程增多时

报错：java.util.ConcurrentModificationException

![image-20201002100147177](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201002100147177.png)

```java
ArrayList，LinkedList，HashMap，HashSet//不安全
Vector //线程安全，但是不使用，太慢了
```

解决方案如下：

3.1 Vector 线程安全（不使用）

3.2 Collections.synchronizedList(new ArrayList<>());（不使用）

3.3 new CopyOnWriteArrayList<>();（使用）

源码如下：

```java
public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}
```

**写时复制**：CopyOnWrite容器即写时复制的容器，往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行copy，复制出一个新的容器Objecct[] newElements，然后新的容器Objecct[] newElements里添加元素，添加完元素之后，再将原容器的引用指向新的容器setArray(newElements);这样做的好处是可以对CopyOnWrite容器进行并发读，而不需要加锁，因为当前容器不会添加任何元素，所以CopyOnWrite容器也是一种读写分离的思想，读写不同的容器。

## Set的线程不安全

Collections.synchronizedSet();（不使用）

```java
new HashSet<>();
//hashset底层时hashmap
public HashSet() {
    map = new HashMap<>();
}
//set存入的值就是map的key，map的value就是object固定写死的常量。set的add调用的是map的put
public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }
```

**Set<String> list = new CopyOnWriteArraySet<>();**

```java
private static void setNotSafe() {
    //Set<String> list = new HashSet<>();
    new HashSet<>();
    //Set<String> set = Collections.synchronizedSet(new HashSet<>());
    Set<String> list = new CopyOnWriteArraySet<>();
    for (int i = 0; i < 30; i++) {
        new Thread(() -> {
            list.add(UUID.randomUUID().toString().substring(0,8));
            System.out.println(list);
        }, String.valueOf(i)).start();
    }
}
```

## Map的线程不安全

```java
//底层数组+链表+红黑树
Map<String, String> map = new HashMap<>();

```

Map<String, String> map1 = Collections.synchronizedMap(new HashMap<>());（不适用）

 **Map<String, String> map = new ConcurrentHashMap<>();**

```java
private static void mapNotSafe() {
    //Map<String, String> map = new HashMap<>();
    Map<String, String> map = new ConcurrentHashMap<>();
    //Map<String, String> map1 = Collections.synchronizedMap(new HashMap<>());
    Map<String, String> map2 = new HashMap<>();
    for (int i = 0; i < 30; i++) {
        new Thread(() -> {
            map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
            System.out.println(map);
        }, String.valueOf(i)).start();
    }
}
```

ConcurrentHashMap 在1.7中 实现线程安全是通过锁住Segment对象的。而在1.8 中则是针对首个节点（table[hash(key)]取得的链接或红黑树的首个节点）进行加锁操作。

每一个Segment都拥有一个锁，当进行写操作时，只需要锁定一个Segment，而其它Segment中的数据是可以访问的。

ConcurrentHashMap在jdk1.8中取消segments字段，直接采用transient volatile Node<K,V>[] table保存数据，采用table数组元素（链表的首个元素或者红黑色的根节点）作为锁，从而实现了对每一行数据进行加锁，减少并发冲突的概率。

HashMap：构造一个空的 `HashMap` ，默认初始容量（16）和默认负载系数（0.75）。

ArrayList 扩容为原来的一半，HashMap为原来的一倍。当长度大于8把变成红黑树。

## Callable接口

**java多线程的几种实现方式：**

1.继承Thread类，重写run方法
2.实现Runnable接口，重写run方法，实现Runnable接口的实现类的实例对象作为Thread构造函数的target
3.通过Callable和FutureTask创建线程
4.通过线程池创建线程

```java
class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
```

callable 接口与runnable接口的区别

答：1.是否有返回值 2.是否抛异常 3.落地方法不一样，一个run，一个call

**3.通过Callable和FutureTask创建线程**

![image-20201004145316270](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004145316270.png)

```java
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask futureTask = new FutureTask(new MyThread2());
        new Thread(futureTask, "A").start();
        System.out.println(futureTask.get());
    }
}
```

```java
class MyThread2 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("**************come in here");
        return 1024;
    }
}
```

**Callable的细节**

1.1.get方法一般请放在最后一行

```java
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask futureTask = new FutureTask(new MyThread2());
        new Thread(futureTask, "A").start();
        //new Thread(futureTask, "B").start();
        System.out.println(Thread.currentThread().getName() + "*****计算完成");
        System.out.println(futureTask.get());
    }
}
```

如果不放在最后一行会导致程序阻塞，即先拿到get的结果在执行。

2.2.FutureTask内部自带缓存，多个线程调用一个类，只会返回一个结果

```java
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask futureTask = new FutureTask(new MyThread2());
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();
        System.out.println(Thread.currentThread().getName() + "*****计算完成");
        System.out.println(futureTask.get());
    }
}
```

![image-20201004151457653](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004151457653.png)

## CountDownLatch JUC辅助类

允许一个或多个线程等待直到在其他线程中执行的一组操作完成的同步辅助。

如下代码：

```java
private static void closeDoor() {
    for (int i = 0; i < 6; i++) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 离开教室");
        }, String.valueOf(i + 1)).start();
    }
    System.out.println(Thread.currentThread().getName() + "\t 班长走人关门");
}
```

![image-20201004153716542](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004153716542.png)

```java
CountDownLatch countDownLatch = new CountDownLatch(6);
for (int i = 0; i < 6; i++) {
    new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "\t 离开教室");
        countDownLatch.countDown();
    }, String.valueOf(i + 1)).start();
}
countDownLatch.await();
System.out.println(Thread.currentThread().getName() + "\t 班长走人关门");
```

![image-20201004153814808](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004153814808.png)

CountDownLatch主要有两个方法，当一个或者多个线程调用await方法时，这些线程会阻塞。其他线程调用**CountDown方法会将计数器减一（调用**CountDown的线程不会阻塞），当计数器的值变为0，因await方法阻塞的线程会被唤醒，继续执行

## CyclicBarrier  JUC辅助类

和**CountDownLatch JUC辅助类**一样的作用只是一个前者做减法，这个做加法

```java
public static void main(String[] args) {
    //CyclicBarrier(int parties, Runnable barrierAction)
    CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() ->{
        System.out.println("召唤神龙");});

    for (int i = 0; i < 7; i++) {
        final int flag = i;
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +"\t收集到" + flag + "颗龙珠");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, String.valueOf(i + 1)).start();
    }
}
```

![image-20201004155138836](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004155138836.png)

## Semaphore JUC辅助类

在信号量上我们定义两种操作：

acquire（获取）当前一个线程调用acquire操作时，他要么通过成功获取信号量（信号量减一），要么一直等下去，直到有线程释放信号量，或超时

release（释放）实际上会将信号量的值加1，然后唤醒等待的线程

信号量主要用于两个目的，一是用于多个共享资源的互斥使用，另一个用于并发线程数的控制

```java
public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(3);//模拟资源类，有三个空车位
    for (int i = 0; i <6 ; i++) {
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() +"\t抢到车位");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t离开车位");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }, String.valueOf(i + 1)).start();
    }
}
```

![image-20201004161028994](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004161028994.png)

## ReentrantReadWriteLock读写锁

/*
* 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源因该同时进行

* 但是如果有一个线程想去写共享资源，就不因该再有其他线程可以对该资源进行读或者写

* 读——读共存

* 读——写共存

* 写——写不能共存

* */

  ```java
  public class ReadWriteLock {
      public static void main(String[] args) {
          MyCache myCache = new MyCache();
          for (int i = 0; i < 5; i++) {
              final int temp = i;
              new Thread(() -> {
                  myCache.put(temp+"", temp+"");
              }, String.valueOf(i)).start();
          }
          for (int i = 0; i < 5; i++) {
              final int temp = i;
              new Thread(() -> {
                  myCache.get(temp+"");
              }, String.valueOf(i)).start();
          }
      }
  }
  
  class MyCache{
      private volatile Map<String, Object> map = new HashMap<>();
      private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock ();
  
      public void put(String key, Object value){
          readWriteLock.writeLock().lock();
          try {
              System.out.println(Thread.currentThread().getName() + "\t 写入数据" + key);
              try {
                  TimeUnit.MILLISECONDS.sleep(300);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              map.put(key, value);
              System.out.println(Thread.currentThread().getName()+ "\t 写入完成" + key);
          } catch (Exception e){
              e.printStackTrace();
          } finally {
              readWriteLock.writeLock().unlock();
          }
      }
      public void get(String key){
          readWriteLock.readLock().lock();
          try {
              System.out.println(Thread.currentThread().getName()+"\t 读取数据");
              try {
                  TimeUnit.MILLISECONDS.sleep(300);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              Object result = map.get(key);
              System.out.println(Thread.currentThread().getName()+"\t 读取完成"+ result);
          } catch (Exception e){
              e.printStackTrace();
          } finally {
              readWriteLock.readLock().unlock();
          }
  
      }
  }
  ```

## 阻塞队列

![image-20201004171026684](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004171026684.png)

当队列是空的，从队列获取元素的操作将会被阻塞

当队列是满的，从队列添加元素的操作将会被阻塞

在多线程领域，所谓阻塞，在某些情况下会挂起线程（阻塞），一旦条件满足，被挂起的线程又会自动被唤起。

为什么需要BlockingQueue

好处我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给一手包办了

![image-20201004171800774](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004171800774.png)

![image-20201004172405106](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004172405106.png)

![image-20201004172500476](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004172500476.png)

![image-20201004172526033](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201004172526033.png)

![image-20201005102019806](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201005102019806.png)

## 线程池的优势

线程池做的工作只要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了最大数量，超出数量的线程排队等待，等其他线程执行完毕，在从队列中取出任务来执行

只要特点：**线程复用，控制最大并发数，管理线程**

减低资源消耗。通过重复利用已经创建的线程减低线程和销毁造成的消耗，提高响应速度，当任务到达时，任务可以不需要等待线程创建就能立即执行

提高线程的可管理性，线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控

![image-20201005112348760](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201005112348760.png)

线程池的底层原理

![image-20201005114119712](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201005114119712.png)

线程池源码

![image-20201005114537301](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201005114537301.png)

![image-20201005114615934](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201005114615934.png)

![image-20201006092428338](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201006092428338.png)

1.在创建线程池后，开始等待请求

2.当调用execute（）方法添加一个请求任务时，线程池会做出如下判断：

​	2.1如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务：

​	2.2如果正在运行的线程数量大于或者等于corePoolSize，那么将这个任务放入队列；

​	2.3如果这个时候队列满了切正在运行的线程数量小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务；

​	2.4如果这个队列满了且正在运行的数量大于或等于maximumPoolSize，那么线程池会启动饱和拒绝策略来执行。

​	3.当一个线程完成任务时，他会从队列中取下一个任务执行。

4当一个线程无事可做超过一定时间（keepAliveTime）时，线程会判断

**线程池不允许使用Executes去创建，而是通过ThreadPoolExecutor的方式**

![image-20201006095951971](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201006095951971.png)

**AbortPolicy()**（默认）：直接抛出RejectedExecutionException异常阻止系统正常运行

**CallerRunsPolicy()**：调用者运行，一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量

**DiscardOldestPolicy()**：抛弃队列等待最久的任务然后把当前任务加入队列中，尝试再次提交当前任务

**DiscardPolicy()**：该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。如果允许任务丢失，这是最好的一种策略

## Java8之流式计算复习

![image-20201006110223562](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201006110223562.png)

```java
Function<String, Integer> function = new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return null;
        }
    };
    System.out.println(function.apply("abc"));

    Function<String, Integer> function1 = s -> {return s.length();};
    System.out.println(function.apply("abc"));

    Predicate<String> predicate = new Predicate<String>() {
        @Override
        public boolean test(String s) {
            return false;
        }
    };

    Predicate<String> predicate1 = s -> {return s.isEmpty();};
    System.out.println(predicate1.test("dsds"));

    Consumer<String> consumer = new Consumer<String>() {
        @Override
        public void accept(String s) {

        }
    };

    Consumer<String> consumer1 = s -> {
        System.out.println(s);
    };
    consumer1.accept("sdsdsd");

    Supplier<String> stringSupplier = new Supplier<String>() {
        @Override
        public String get() {
            return null;
        }
    };

    Supplier<String> stringSupplier1 = () -> {return "java";};
    stringSupplier1.get();
}
```

流到底是什么？

是数据渠道，用于操作数据源所生成的元素序列，集合讲的是数据，流讲的是计算

Stream自己不会存储元素，不会改变源对象。相反，他们会返回一个持有结果的新Stream。操作时延迟执行的，这意味着他们会等到需要结果的时候才执行。

```java
User user1 = new User(11,"a",23);
User user2 = new User(12,"b",24);
User user3 = new User(13,"c",22);
User user4 = new User(14,"d",28);
User user5 = new User(16,"e",26);

List<User> list = Arrays.asList(user1,user2,user3,user4,user5);
list.stream().filter(user -> {
    return user.getId() % 2 == 0;
}).filter(user -> {
    return user.getAge() > 24;
}).map(m -> {return m.getUserName().toUpperCase();
}).sorted((o1, o2) -> {return o2.compareTo(o1);
}).limit(1).forEach(System.out::println);
```

**分支合并框架**

```java
public class ForkJoinTaskDemo {
    public static void main(String[] args) throws Exception {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool threadPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
        System.out.println(forkJoinTask.get());
        threadPool.shutdown();
    }

}

class MyTask extends RecursiveTask<Integer> {

    private static final Integer ADJUST_VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if ((end - begin) <= ADJUST_VALUE) {
            for (int i = begin; i <= end ; i++) {
                result = result + i;
            }
        } else {
            int middle = (end + begin) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle+1, end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
}
```

## 异步回调

```java
public static void main(String[] args) throws Exception {
    CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
        System.out.println(Thread.currentThread().getName() + "没有返回");
    });
    completableFuture.get();

    CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
        System.out.println(Thread.currentThread().getName() + "有返回");
        int i = 10/0;
        return 1024;
    });

    System.out.println(completableFuture1.whenComplete((t, u) -> {
        System.out.println("+++++t:" + t);
        System.out.println("+++++u:" + u);
    }).exceptionally(f -> {
        System.out.println("+++++execption" + f.getMessage());
        return 444;
    }).get());
}
```

# JVM

JVM是运行在操作系统之上的，它与硬件没有直接的交互

![image-20201007082015193](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201007082015193.png)

## 类加载器Class Loader

​	负责加载class文件，**class文件在文件开头有特定的文件标示**，将class文件字节码加载到内存中，并且这些内容转化成方法区中的运行时数据结构并且ClassLoader只负责class文件的加载，至于它是否可以运行，则由Execution Engine决定

![image-20201007083120109](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201007083120109.png)

虚拟机类加载器种类：

1.虚拟机自带的加载器  1.1启动类加载器 （Bootstrap） C++  1.2 扩展类加载器（Extension）Java  1.3应用程序类加载器（App Class Loader）java也叫系统类加载器，加载当前应用的classpath的所有类

2.用户自定义加载器 2.1 Java.lang.ClassLoader的子类，用户可以定制类的加载方式

![image-20201007091215800](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201007091215800.png)

**双亲委派机制：**当一个类收到类加载请求，他首先不会尝试自己去加载这个类，而是把这个请求委派给父类去完成，每一个层次类加载器都是如此，因此所有的加载请求都因该传送到启动类加载其中，只有当父类加载器反馈自己无法完成这个请求的时候（再在它的加载路径下没有找到所需加载的class），子类加载器才会尝试自己去加载。

具体流程如下：

1.当AppClassLoader加载一个class时，它不会尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。

2.当父类ExtClassLoader加载到这个.class时，他也不会尝试加载这个类，而是把类加载请求委派给父类加载器BootstrapClassLoader。

3.当BootstrapClassLoader加载到这个.class时，它会查找该类是否存在，如果不存在，就往下传递，交给ExtClassLoader，如果ExtClassLoader在自己相应的包中也没找到对应的类，就交给AppClassLoader来加载，如果AppClassLoader也没有，就报ClassNotFoundException();

采用双亲委派的一个好处是比如加载位于rt.jar包中的类java.lang.Object,不管是哪一个加载器加载这个类，最终都是委托给顶层的启动类加载器进行加载，这样就保证了使用不同类加载器最终得到的都是同样一个Object对象（沙箱安全机制）

**沙箱安全：防止恶意代码污染java源代码**

比如我定义了一个类名为String所在包为java.lang，因为这个类本来是属于jdk的，如果没有沙箱安全机制的话，这个类将会污染到我所有的String,但是由于沙箱安全机制，所以就委托顶层的bootstrap加载器查找这个类，如果没有的话就委托extsion,extsion没有就到aapclassloader，但是由于String就是jdk的源代码，所以在bootstrap那里就加载到了，先找到先使用，所以就使用bootstrap里面的String,后面的一概不能使用，这就保证了不被恶意代码污染

![img](https://img2018.cnblogs.com/i-beta/1524840/201912/1524840-20191228111734221-1305929184.png)

执行引擎负责解释命令，提交操作系统执行。

## 本地方法栈

Native Interface 本地接口

本地接口的作用是融合不同的编程语言为Java所用，它的初衷是融合C/C++程序，java诞生的时候是C/C++横行的时候，要想立足，必须有调用C/C++程序，于是就在内存中专门开辟一块区域处理标记为native的代码，它的具体做法是native method stack中登记native方法，在Execution Engine执行时加载native libraries。

native 是一个关键字，生命有，实现无

## PC寄存器（程序计算器）

每一个线程都有一个程序计数器，是线程私有的，就是一个指针，指向方法区的方法字节码**（用来存储指向下一条指令地址，也即将要执行的指令代码）**，由执行执行引擎读取下一条指令，是一个非常小的内存空间，几乎可以忽略不记

这块内存区域很小，**它是当前线程所执行的字节码的行号指示器**，字节码解释器通过改变这个计数器的值来选取下一条需要执行的字节码指令。

如果执行的是一个Native方法，那这个计数器是空的。

用以完成分支，循环，跳转，异常处理，线程恢复等基础功能。不会发生内存溢出（OutOfMemory==OOM）错误

## 方法区

供各个线程共享的运行时内存区域。**他存储每一个类的结构信息**，例如运行时常量池，字段和方法数据，构造函数和普通方法的字节码内容。上面讲的时规范，在不同虚拟机里头实现是不一样的，最典型的就是永久代和元空间

但是实例变量存在堆内存中和方法区不一样

## JAVA栈

**栈管运行，堆管存储**

栈也叫内存，主管java程序的运行，是线程创建时创建，它的生命周期是跟随线程的生命期，线程结束栈内存也就释放，**对于栈来说不存在垃圾回收问题**，只要线程结束他就结束，是线程私有的，8种**基本类型的变量+对象的引用变量+实例方法都是在函数的栈内存中分配**。

栈存储什么？

栈帧主要保存三类数据：1.**本地变量**：输入参数和输出参数以及方法内的变量 2.**栈操作**：记录出栈，入栈的操作 3.**栈帧数据**：包括类文件，方法等等

![image-20201008103605303](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008103605303.png)

**栈溢出：**

![image-20201008104345667](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008104345667.png)

![image-20201008104450004](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008104450004.png)

## 栈+堆+方法区交互关系

![image-20201008104633816](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008104633816.png)

```java
Person p1（引用类型） = new Person();
```

## 堆

一个JVM实例只存在一个堆内存，堆内存的大小是可以调节的，类加载器读取类文件后，需要把类，方法，常变量放到堆内存中，保存所有引用类型的真实信息，以方便执行器执行，堆内存分为三部分：

新生区，养老区，永久代

![image-20201008194005237](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008194005237.png)

永久区（1.7）永久存储区是一个常驻内存区域，用于存放JDK自身所携带的Class，Interface的元数据，也就是说它存储的是运行环境必须的类信息，被装载进此区域的数据是不会被垃圾回收器收到的，关闭JVM才会释放此区域所占用的内存。

![image-20201008195159462](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008195159462.png)

![image-20201008195326267](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008195326267.png)

![image-20201008200754976](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008200754976.png)

MinorGC过程（复制，清空，互换）

1.eden，survivorFrom复制SurvivorTo，年龄+1

![image-20201008200901447](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008200901447.png)

2.清空eden，survivorFrom

![image-20201008200931904](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008200931904.png)

3.survivorFrom和SurvivorTo互换

![image-20201008203106604](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201008203106604.png)

![image-20201009110222911](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201009110222911.png)

方法区和堆一样，是各个线程共享的内存区域，它用于存储虚拟机加载的：类信息+普通常量+静态常量+编译器编译后的代码等等，虽然JVM规范将方法去描述为堆的一个逻辑部分，但它却还有一个叫非堆，目的是和堆分开

![image-20201009110850287](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201009110850287.png)

heap（堆 1.7）

一个JVM实例只存在一个堆内存，堆内存的大小是可以调节的。类加载读取了类文件后，需要把类，方法，常量放到堆内存中，保存所有引用类型的真实信息，以方便执行器执行

**堆参数调优**

-Xms10m -Xmx10m -XX:+PrintGCDetails

```java
public class T2 {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory(); //返回java虚拟机试图使用的最大内存
        long totalMemory = Runtime.getRuntime().totalMemory(); //返回java虚拟机中的内存总量

        int nums = Runtime.getRuntime().availableProcessors(); //返回核数

        System.out.println("-Xmx:maxMemory = " + maxMemory+" 字节 " + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("-Xms:totalMemory = " + totalMemory+" 字节 " + (totalMemory / (double)1024 / 1024) + "MB");

        System.out.println("电脑核数" + nums);

       /* String str = "WWW.atguigu.com";
        while (true){
            str += str + new Random().nextInt(88888888) + new Random().nextInt(99999999);
        }*/
    }
}
```

![image-20201012085158536](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012085158536.png)

![image-20201012085234749](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012085234749.png)

内存溢出

## 垃圾回收算法

![image-20201012085341793](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012085341793.png)

**GC算法总体概述**

![image-20201012085412368](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012085412368.png)

JVM在进行GC时，并非每次都对上面三个内存区域一起回收的，大部分时候回收的都是新生代。因此GC按照回收的区域又分成两种类型，一种普通GC，一种全局GC

**Minor GC和Full GC区别**

普通GC：只针对新生代区域的GC，指发生在新生代的垃圾收集动作，因为大多数Java对象存活率不高，所以MinorGC非常频繁，一般回收速度也较快

全局GC：指发生在老年代的垃圾收集动作，出现Major GC，经常会伴随至少一次的Minor GC（不是绝对）。Major GC的速度一般都要比MInorGC慢上10倍以上。

**GC的四大算法**

1.引用计数法  2.复制算法  3.标记清楚  4.标记压缩

### 1.引用计数法

![image-20201012090303556](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012090303556.png)

### 2.复制算法

**年轻代**中使用的是Minor GC，这种GC算法采用的是复制算法

![image-20201012091518150](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012091518150.png)

HotSpot JVM把年轻代分为三部分，1个Eden区和2个Survivor区（分别叫From和To）。默认比例为8：1：1，一般情况下，新创建的对象都会被分配到Eden区（一些大对象特殊处理），这些对象经过第一次Minor GC后，如果任然存活，将会被移动到Survior区，对象在Survior区每熬过一次MinorGC 年龄就会增加一岁，当它年龄增加到一定程度时，就会被移动到老年代中，因为年轻代的对象基本都是朝生夕死，所以在年轻代的垃圾回收算法使用的是复制算法，复制算法的基本思想就是内存分为两块，每次只用其中的一块，当这一块内存用完了，就将还活着的对象复制到另外一块上面。复制算法不会产生内存碎片。

![image-20201012092351374](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012092351374.png)

![image-20201012093008281](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012093008281.png)

![image-20201012093144590](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012093144590.png)

因为Eden区对象一般存活率较低，一般的，使用两块10%的内存作为空闲和活动区间，而另外80%的内存，则是用来给新建对象分配内存的，一旦发生GC，将10%的from活动区间与另外80%中存活的eden对象转移到10%的to空闲区间，接下来，将之前90%的内存全部释放。

**复制算法的缺点**

1.他浪费以一半的内存

2.如果对象的存活率很高，我们可以极端一点，假设100%存活，那么我们需要将所有对象都复制一遍，并将所有引用地址重置一遍。复制这一工作所花费的时间，在对象存活率达到一定程度时，将会变的不可忽略，，最起码对象存活率要非常低，而且最重要的是，必须克服50%内存浪费。

### 3.标记清除

垃圾收集算法——标记清除

算法分成标记和清除两个阶段，先标记出要回收的对象，然后统一回收这些对象

![image-20201012094819363](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012094819363.png)

![image-20201012095136377](https://github.com/422314646/leetcode/blob/master/typora-user-images/image-20201012095136377.png)

标记清除，当程序运行期间，使用内存被耗尽的时候，GC线程就会被触发并将程序暂停，随后将回收的对象标记一遍，最终统一回收这些对象，完成标记清理工作接下来便让应用程序恢复运行。

缺点

1.它的效率比较低（递归全堆对象遍历），而且在进行GC的时候，需要停止应用程序，这会导致用户体验非常差。

2.主要缺点清理出来的空闲内存不是连续的，我们死亡对象都是随即的出现在内存各个角落的，现在把它们清除之后，内存的布局自然会乱七八糟。而为了应付这一点，JVM就不得不维持一个内存的空闲列表，这是又是一种开销，而且分配数组对象的时候，寻找连续内存空间会不好找。