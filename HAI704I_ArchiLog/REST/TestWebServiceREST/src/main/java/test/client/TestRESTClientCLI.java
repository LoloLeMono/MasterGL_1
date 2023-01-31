package test.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
// import test.models.test;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TestRESTClientCLI extends AbstractMain implements CommandLineRunner
{
	/* ATTRIBUTES */
	@Autowired
	private RestTemplate proxy;
	private IntegerInputProcessor inputProcessor;
	private static String URI_COVERAGE; // <service_url>/coverage
	private static String URI_NB_TRAM; // <service_url>/nb de tram (prend un int)
	
	/* METHODS */
	@Override
	public void run(String... args) throws Exception
	{
		BufferedReader inputReader;
		String userInput = "";
		
		try
		{
			inputReader = new BufferedReader(new InputStreamReader(System.in));
			setTestServiceUrl(inputReader);
			URI_COVERAGE = SERVICE_URL + "/coverage";
			URI_NB_TRAM = settingTramURI(2);
			do
			{
				menu();
				userInput = inputReader.readLine();
				//processUserInput(inputReader, userInput, proxy);
				Thread.sleep(3000);
			} while(!userInput.equals(QUIT));
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public String settingTramURI(int nbTram)
	{
		return URI_COVERAGE + "/sandbox/stop_areas/stop_area%3ARAT%3ASA%3ABASTI/lines/line%3ARAT%3AM5/arrivals?count=" + nbTram + "&depth=2&";
	}
	
	@Override
	protected boolean validServiceUrl()
	{
		return SERVICE_URL.equals("https://api.navitia.io/v1/");
	}
	
	@Override
	protected void menu()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(QUIT+". Quit.");
		builder.append("\n1. Get the firsts arivals metro");
		System.out.println(builder);
	}
	
	/*
	
	private void processUserInput(BufferedReader reader,
	String userInput, RestTemplate proxy)
	{
		Map<String, String> params = new HashMap<>();
		
		inputProcessor = new IntegerInputProcessor(reader);
		try
		{
			switch(userInput)
			{
				case "1":
					String uri = URI_EMPLOYEES + "/count";
					String countStr = proxy.getForObject(uri, String.class);
					ObjectMapper mapper = new ObjectMapper();
					long count = (int) mapper.readValue(countStr, Map.class).get("count");
					System.out.println(String.format("There are %d employees", count));
					System.out.println();
					break;
					
				case "2":
					uri = URI_EMPLOYEES;
					Employee[] employees = proxy.getForObject(uri, Employee[].class);
					System.out.println("Employees:");
					Arrays.asList(employees).forEach(System.out::println);
					System.out.println();
					break;
					
				case "3":
					uri = URI_EMPLOYEES_ID;
					System.out.println("Employee ID:");
					int id = inputProcessor.process();
					params.put("id", String.valueOf(id));
					Employee employee = proxy.getForObject(uri, Employee.class, params);
					System.out.println(String.format("Employee with ID %s: %s", id, employee
					System.out.println();
					break;
					
				case "4":
					uri = URI_EMPLOYEES;
					System.out.println("Employee Name:");
					String name = reader.readLine();
					System.out.println();
					System.out.println("Employee Role:");
					String role = reader.readLine();
					System.out.println();
					Employee createdEmployee = new Employee(name, role);
					Employee returnedEmployee = proxy.postForObject(uri, createdEmployee, Em
					System.out.println(String.format("Successfully added employee: %s", retu
					System.out.println();
					break;
					
				case "5":
					uri = URI_EMPLOYEES_ID;
					System.out.println("Employee ID:");
					id = inputProcessor.process();
					params.put("id", String.valueOf(id));
					proxy.delete(uri, params);
					System.out.println(String.format("Successfully removed employee with ID
					System.out.println();
					break;
					
				case "6":
					uri = URI_EMPLOYEES_ID;
					System.out.println("Employee ID:");
					id = inputProcessor.process();
					System.out.println("New Employee Name:");
					name = reader.readLine();
					46
					System.out.println();
					System.out.println("New Employee Role:");
					role = reader.readLine();
					System.out.println();
					params.put("id", String.valueOf(id));
					Employee newEmployee = new Employee(name, role);
					proxy.put(uri, newEmployee, params);
					System.out.println(String.format("Successfully updated/created employee
					System.out.println();
					break;
					
				case QUIT:
					System.out.println("Bye...");
					System.exit(0);
					default:
					System.err.println("Sorry, wrong input. Please try again.");
					return;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (HttpClientErrorException e)
		{
			System.err.println(e.getMessage());
			System.err.println("Please try again with a different ID.");
		}
	}
	
	*/
}
