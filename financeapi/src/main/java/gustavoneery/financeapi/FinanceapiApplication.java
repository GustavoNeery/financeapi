package gustavoneery.financeapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceapiApplication {

	public static void main(String[] args) {
		if (!isTestEnvironment()) {
			Dotenv dotenv = Dotenv.configure().load();
			dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		}
		SpringApplication.run(FinanceapiApplication.class, args);
	}

	private static boolean isTestEnvironment() {
		return System.getProperty("TEST_ENV") != null;
	}

}
