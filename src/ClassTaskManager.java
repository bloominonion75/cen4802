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
		
		tasks.add(new Task(nextId++, "Complete and submit this assignment"));
		tasks.add(new Task(nextId++, "Do introduction work"));
		tasks.add(new Task(nextId++, "Study for the test"));
		
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		
		server.createContext("/", ClassTaskManager::handleRequest);
		
		server.setExecutor(null);
		
		server.start();
		
		System.out.println("Your task manager is running at http://localhost:8080");
		
	}
	private static void handleRequest(HttpExchange exchange) throws IOException {
		
		if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
			addTask(exchange);
		}
		String response = createPage();
		
		byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
		
		exchange.sendResponseHeaders(200, responseBytes.length);
		
		OutputStream output = exchange.getResponseBody();
		output.write(responseBytes);
		output.close();
	}
	private static void addTask(HttpExchange exchange) throws IOException {
		String formData = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
		
		String description = URLDecoder.decode(formData, StandardCharsets.UTF_8);
		
		if (description.startsWith("description=")) {
			description = description.substring("description=".length());
		}
		if (!description.isBlank()) {
			tasks.add(new Task(nextId++, description));
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
		html.append("<h1>My Task Manager</h1>");
		html.append("<h2>My Tasks for the week</h2>");
		html.append("<ul>");
		
		for (Task task : tasks) {
			html.append("<li>");
			html.append(task.getId());
			html.append(": ");
			html.append(task.getDescription());
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
