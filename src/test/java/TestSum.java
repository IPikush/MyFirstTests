import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSum {
    @Test
    public void testSumm(){
        MyFirstTest myFirstTest = new MyFirstTest();
        Assert.assertEquals(myFirstTest.summ(2,2), 4,"It's wrong");

    }
}
