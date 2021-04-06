package aero.alsalam.microservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	MessageSource messageSource;
	
	
	@GetMapping(path="/helloworld")
	public String getHelloWorld() {
		return "Hello World";
	}
	
	
	@GetMapping(path="/helloworld-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}
	
	@GetMapping(path="/helloworld/{message}")
	public HelloWorldBean getHelloWorldDynamic(@PathVariable String message) {
		return new HelloWorldBean(String.format("Hello World %s", message));
	}
	
	@GetMapping(path="/helloworld-inter")
	public String getHelloWorldInternationalizedBean() {
		return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
	}
	
}
