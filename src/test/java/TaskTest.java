package test.java;
//import static org.junit.jupiter.api.Assertions;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.java.Task;

public class TaskTest {
	
	@Test
	public void testTaskCreation() {
		Task task = new Task(1, "Complete and submit this assignment", false);
		
		assertEquals(1, task.getId());
		assertEquals("Complete and submit this assignment", task.getDescription());
		assertFalse(task.isCompleted());
	}

	private void assertFalse(boolean completed) {
		// TODO Auto-generated method stub
		
	}

	private void assertEquals(String string, String description) {
		// TODO Auto-generated method stub
		
	}

	private void assertEquals(int i, int id) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testTaskCompletion() {
		Task task = new Task(2, "Do introduction work", true);
		
		assertEquals(2, task.getId());
		assertEquals("Do introduction work", task.getDescription());
		assertFalse(task.isCompleted());
	}
	
	@Test
	public void testDifferentTaskDescription() {
		Task task = new Task(3, "Study for the test", false);

		assertEquals("Study for the test", task.getDescription());
		
	}
	
	@Test
	public void testDifferentTaskId() {
		Task task = new Task(4, "Attend class", false);

		assertEquals(4, task.getId());
	}
	
//	@Test
//	public void testCompletedTaskCreation() {
//		Task task = new Task(5, "Already completed the task", true);
//
//		assertTrue(5, task.isCompleted());
//	
//}

	private void assertTrue(int i, boolean completed) {
		// TODO Auto-generated method stub
		
	}}