package com.ontop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsocketController {

	@GetMapping("/")
	public String websocket() {
		return "websocket";
	}
}
