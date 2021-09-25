package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification req;
	Properties prop;
	FileInputStream fis;
	static PrintStream log ;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(log == null) {
			log = new PrintStream(new FileOutputStream("logging.txt"));
			
			req = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUri"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			
		} else {
			req = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUri"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			
		}
		
		return req;
	}

	public String getGlobalValue(String key) throws IOException{
		prop = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/global.properties");
		prop.load(fis);
		String keyValue = prop.getProperty(key);
		return keyValue;
		
	}
	
	public String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath jPath = new JsonPath(responseString);
		return jPath.getString(key);
		
		
	}
	
	
	
	
}
