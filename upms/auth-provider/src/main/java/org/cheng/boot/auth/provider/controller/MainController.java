package org.cheng.boot.auth.provider.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/user/index")
	public String userIndex() {
		return "user/index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@GetMapping("/401")
	public String accesssDenied() {
		return "401";
	}

	/**
	 * 确认授权页
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/oauth/confirm_access")
	public String confirm_access(HttpServletRequest request, HttpSession session, Map model) {
		Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes")
				: request.getAttribute("scopes"));
		List<String> scopeList = new ArrayList<String>();
		for (String scope : scopes.keySet()) {
			scopeList.add(scope);
		}
		model.put("scopeList", scopeList);
		Object auth = session.getAttribute("authorizationRequest");
		if (auth != null) {
			try {
				AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
//				ClientDetails clientDetails = baseAppRemoteService.getAppClientInfo(authorizationRequest.getClientId()).getData();
//				model.put("app", clientDetails.getAdditionalInformation());
			} catch (Exception e) {

			}
		}
		return "confirm_access";
	}

	/**
	 * 自定义oauth2错误页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/oauth/error")
	@ResponseBody
	public Object handleError(HttpServletRequest request) {
		Object error = request.getAttribute("error");
		return error;
	}
}
