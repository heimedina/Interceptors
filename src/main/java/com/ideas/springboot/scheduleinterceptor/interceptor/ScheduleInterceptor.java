package com.ideas.springboot.scheduleinterceptor.interceptor;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("schedule")
public class ScheduleInterceptor implements HandlerInterceptor{
	
	@Value("${config.schedule.open}")
	private Integer apertura;
	
	@Value("${config.schedule.close}")
	private Integer cierre;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		if(hora>=apertura && hora<cierre) {
			StringBuilder mensaje = new StringBuilder("Welcome to the schedule of clients");
			mensaje.append(", we attend between ");
			mensaje.append(apertura);
			mensaje.append(" hours to ");
			mensaje.append(cierre);
			mensaje.append(". Thanks for visiting us!");
			request.setAttribute("mensaje", mensaje.toString());
			return true;
		}
		response.sendRedirect(request.getContextPath().concat("/cerrado"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String mensaje = (String) request.getAttribute("mensaje");
		modelAndView.addObject("horario",mensaje);
	}

}
