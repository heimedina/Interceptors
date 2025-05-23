package com.ideas.springboot.scheduleinterceptor.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
	
	@Value("${config.schedule.open}")
	private Integer apertura;
	
	@Value("${config.schedule.close}")
	private Integer cierre;
	
	@GetMapping({"/","index"})
	public String index(Model model) {
		model.addAttribute("titulo","Welcome to the schedule client");
		return "index";
	}
	
	@GetMapping("/cerrado")
	public String Closed(Model model) {
		
		StringBuilder mensaje = new StringBuilder("It's closed now. Please come back in our schedule: ");
		mensaje.append(apertura);
		mensaje.append(" hours to ");
		mensaje.append(cierre);
		mensaje.append(" hours.");
		model.addAttribute("titulo","Out of our schedule");
		model.addAttribute("mensaje", mensaje);
		return "closed";
	}
	

}
