package kr.co.adonce.sbp.controller;

import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.adonce.sbp.dao.model.UserGrade;
import kr.co.adonce.sbp.security.GrantedAuthorityDetail;
import kr.co.adonce.sbp.util.SecurityUtil;

@Controller
public class HomeController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);
	private static final String VO_AUTHENTICATION = "authentication";

	private boolean handleUrl(ModelAndView view, HttpServletRequest request, HttpServletResponse response) {

		boolean intended = true;

		// start - 등록되지 않은 사용자 처리
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || !UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
			if (logger.isDebugEnabled()) {
				logger.debug("(Invalid Security Access) There is no Security.");
			}
			view.setViewName("login");
			view.addObject("message", "환영합니다.");
			return false;
		}
		// end - 등록되지 않은 사용자 처리
		try {

			Iterator<? extends GrantedAuthority> itrAuthority = auth.getAuthorities().iterator();
			GrantedAuthorityDetail authDetail = null;

			while (itrAuthority.hasNext()) {
				authDetail = (GrantedAuthorityDetail) itrAuthority.next();
				UserGrade userGrade = authDetail.getGrade();

				switch (userGrade.getId()) {
				case UserGrade.ADMIN:
				case UserGrade.USER:
					view.addObject(VO_AUTHENTICATION, authDetail.getUser().toJSONString());
					break;

				case UserGrade.UNKNOWN_USER:
					view.setViewName("login");
					view.addObject("message", "입력하신 ID, Password에 해당하는 사용자가 존재하지 않습니다.");
					SecurityUtil.clearAuthentication();

					return false;

				case UserGrade.INVALID_UNKNOWN_AND_ERROR:
				case UserGrade.NOT_ENTERED_ID_OR_PASSWORD:
				default:
					view.setViewName("login");
					view.addObject("message", "로그인 정보(ID, Password)를 정확히 입력해 주시기 바랍니다.");
					SecurityUtil.clearAuthentication();
					break;
				}

			}
		} catch (Exception e) {
			logger.warn("(Invalid Authentication)", e);
			view.setViewName("login");
			view.addObject("message", "로그인 도중 오류가 발생하였습니다.\n관리자에게 문의하시기 바랍니다.");
			intended = false;
		}

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
