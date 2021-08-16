import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSum {
    private User anton;
    private MyFirstTest myFirstTest;
    @BeforeClass //available everywhere

    public void setUpUser(){
        myFirstTest = new MyFirstTest();
        anton=new User("Anton",30,true);
    }
    @Test
    public void testSumm(){
        Assert.assertEquals(myFirstTest.summ(2,2), 4,errorMessage());

    }
    @Test
    public void testUserName(){
        Assert.assertEquals(anton.getName(),"Anton",errorMessage());
    }
    @Test
    public void testUserAge(){
        Assert.assertEquals(anton.getAge(), 30,errorMessage());
    }
    @Test
    public  void testUserRegistered(){
        Assert.assertTrue(anton.getRegistered(),errorMessage());
    }
    @AfterTest
    public void  cleanUp(){
        System.out.println("Cleared data");
        anton=null;
    }
    private String errorMessage(){
        return "something wrong with test data";
    }
}
