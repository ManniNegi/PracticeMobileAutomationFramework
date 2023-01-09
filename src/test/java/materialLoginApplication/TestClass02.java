package materialLoginApplication;

import org.testng.annotations.Test;

public class TestClass02 {

	@Test(groups = {"smoke", "coreRIT", "fullTest"})
	public void Test05() {
		System.out.println("Test case number 5");
	}
	
	@Test(groups = {"coreRIT", "fullTest"})
	public void Test06() {
		System.out.println("Test case number 6");
	}
	
    @Test(groups = {"coreRIT", "fullTest"})
	public void Test07() {
		
		System.out.println("Test case number 7");
	}
	
	@Test(groups = {"smoke"})
	public void Test08() {
		System.out.println("Test case number 8");
	}
	
}
