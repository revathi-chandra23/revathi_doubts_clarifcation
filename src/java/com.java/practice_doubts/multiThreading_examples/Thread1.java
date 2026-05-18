package multiThreading_examples;

public class Thread1 {
    public static void main(String[] args)
{
    MyThread1 t1=new MyThread1();
    t1.start();
}}

class MyThread1 extends Thread{
    public void run(){
        for (int i=0;i<5;i++){
            System.out.println("hello i am thread1");
        }
    }
}