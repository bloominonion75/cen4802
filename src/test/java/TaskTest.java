package test.java;
//import static org.junit.jupiter.api.Assertions;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.java.Task;
import java.util.ArrayList;
import java.util.List;
import main.java.ClassTaskManager;

public class TaskTest {
	
	@Test
	public void testTaskCreation() {
		Task task = new Task(1, "Complete and submit this assignment", false);
		
		Assertions.assertEquals(1, task.getId());
		Assertions.assertEquals("Complete and submit this assignment", task.getDescription());
		Assertions.assertFalse(task.isCompleted());
	}

//	private void assertFalse(boolean completed) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private void assertEquals(String string, String description) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private void assertEquals(int i, int id) {
//		// TODO Auto-generated method stub
//		
//	}

	@Test
	public void testTaskCompletion() {
		Task task = new Task(2, "Do introduction work", true);
		
		Assertions.assertEquals(2, task.getId());
		Assertions.assertEquals("Do introduction work", task.getDescription());
		Assertions.assertTrue(task.isCompleted());
	}
	
	@Test
	public void testDifferentTaskDescription() {
		Task task = new Task(3, "Study for the test", false);

		Assertions.assertEquals("Study for the test", task.getDescription());
		
	}
	
	@Test
	public void testDifferentTaskId() {
		Task task = new Task(4, "Attend class", false);

		Assertions.assertEquals(4, task.getId());
	}
	
//	@Test
//	public void testCompletedTaskCreation() {
//		Task task = new Task(5, "Already completed the task", true);
//
//		assertTrue(5, task.isCompleted());
//	
//}
	
	@Test
	public void testTaskCounter() {
		List<Task> tasks = new ArrayList<>();
		
		tasks.add(new Task(1, "First Task", false));
		tasks.add(new Task(2, "Second Task", false));
		tasks.add(new Task(3, "Third Task", false));
		
		Assertions.assertEquals(4, ClassTaskManager.countTasks(tasks));
	}

	private void assertTrue(int i, boolean completed) {
		// TODO Auto-generated method stub
		
	}}