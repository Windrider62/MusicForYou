import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {

        for (int i=0; i<8; i++)
        {
            MultithreadingDemo object = new MultithreadingDemo();
            object.start();

            MultithreadingDemo2 object2 = new MultithreadingDemo2();
            object2.start();
        }

    }
}
class MultithreadingDemo2 extends Thread
{
    public void run()
    {
        try
        {
            // Displaying the thread that is running
            testFunction2();
            Thread.sleep(20000);


        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }


    public void testFunction2(){
        System.out.println("hello new test 22");
    }
}
class MultithreadingDemo extends Thread
{
    public void run()
    {
        try
        {
            // Displaying the thread that is running
          testFunction();


        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }


    public void testFunction(){
        System.out.println("hello new test");
    }
}
