package main.java;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class ClassTaskManager {
	
	private static List<Task> tasks = new ArrayList<>();
	private static int nextId = 1;

	public static void main(String[] args) throws IOException {
		
		tasks.add(new Task(1, "Complete and submit this assignment", false));
		tasks.add(new Task(2, "Do introduction work", false));
		tasks.add(new Task(3, "Study for the test", false));
		tasks.add(new Task(4, "review the concept of continous integration", false));
		
		nextId = 4;
		
		HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
		
		server.createContext("/", ClassTaskManager::handleRequest);
		
		server.setExecutor(null);
		
		server.start();
		
		System.out.println("Your task manager is running at http://localhost:8081");
		
	}
	private static void handleRequest(HttpExchange exchange) throws IOException {
		
		String path = exchange.getRequestURI().getPath();
		
		if (path.equals("/complete")) {
			String query = exchange.getRequestURI().getQuery();
			
			if (query != null && query.startsWith("id=")) {
				int id = Integer.parseInt(query.substring(3));
				completeTask(id);
			}
		}
		
		else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
			addTask(exchange);
		}
		String response = createPage();
		
		byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
		
		exchange.sendResponseHeaders(200, responseBytes.length);
		
		OutputStream output = exchange.getResponseBody();
		output.write(responseBytes);
		output.close();
	}
	private static void completeTask(int id) {
		for (Task task : tasks) {
			if (task.getId() == id) {
				task.setCompleted(true);
				return;
			}
		}
		
	}
	private static void addTask(HttpExchange exchange) throws IOException {
		String formData = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
		
		String description = URLDecoder.decode(formData, StandardCharsets.UTF_8);
		
		if (description.startsWith("description=")) {
			description = description.substring("description=".length());
		}
		if (!description.isBlank()) {
			tasks.add(new Task(nextId++, description, false));
		}
	}
	private static String createPage() {
		StringBuilder html = new StringBuilder();
		
		html.append("<!DOCTYPE html>");
		html.append("<html>");
		html.append("<head>");
		html.append("<title>My Task Manager</title>");
		html.append("<head>");
		html.append("<body>");
		html.append("<h1>Adam's Task Manager</h1>");
		html.append("<h2>My Tasks for the week</h2>");
		html.append("<ul>");
		
		for (Task task : tasks) {
			html.append("<li>");
			html.append(task.getId());
			html.append(": ");
			html.append(task.getDescription());
			if (task.isCompleted()) {
				html.append(" - completed!!");
			}else {
				html.append(" <a href='/complete?id=" + task.getId() + 
						"'>" + "<input type='checkbox'>" + "</a>");
			}
			html.append("</li>");
				
			
			
		}
		html.append("</ul>");
		html.append("<h2>Add a Task</h2>");
		html.append("<form method='POST'>");
		html.append("<input type='text' " + "name='description' " + "placeholder='Enter new task'>");
		html.append("<button type='submit'" + "Add Task" + "</button>");
		html.append("</form>");
		html.append("</body>");
		html.append("</html>");
		
		return html.toString();	
	}
}
