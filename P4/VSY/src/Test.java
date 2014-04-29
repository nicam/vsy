import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HttpServerFactory.create( "http://localhost:8080/").start();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
