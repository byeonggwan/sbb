package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Using this class controller
@Controller
public class HelloController {
	// mapping to 'http://localhost:port/hello'
	// (method name ( String hello() ) not need to be same with url)
	@GetMapping("/hello")
	// The type of res is literally string.
	@ResponseBody
	public String hello() {
		return "Hello World5";
	}
}
