import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;

public class Test {
	
	public static void main(String[] args) {
		try {
			HttpServerFactory.create( "http://localhost:8080/").start();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
