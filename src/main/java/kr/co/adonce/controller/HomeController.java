package kr.co.adonce.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);
	private static final String VO_AUTHENTICATION = "authentication";

	private boolean handleUrl(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {

		boolean intended = true;

		// start - 등록되지 않은 사용자 처리
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return intended;
	}

	/**
	 * Home
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled()) {
			logger.debug("\n\n-------------------------------------------------------");
			logger.debug(request.getRequestURI() + " - Welcome home! The client locale is " + locale);
		}

		ModelAndView view = new ModelAndView("sbp");
		handleUrl(view, request, response);

		return view;
	}
}
