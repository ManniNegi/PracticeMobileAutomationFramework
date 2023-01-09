package materialLoginApplication;

import org.testng.annotations.Test;

public class TestCase01 {

	@Test(groups = {"smoke", "fullTest"})
	public void Test01() {
		
		System.out.println("Test case number 1");
	}
	
	@Test(groups = {"smoke", "fullTest"})
	public void Test02() {
		System.out.println("Test case number 2");
	}
	
    @Test(groups = {"smoke", "coreRIT", "fullTest"})
	public void Test03() {
		
		System.out.println("Test case number 3");
	}
	
	@Test(groups = {"smoke", "coreRIT"})
	public void Test04() {
		System.out.println("Test case number 4");
	}
}
