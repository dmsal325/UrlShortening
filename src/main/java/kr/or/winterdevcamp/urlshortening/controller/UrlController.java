package kr.or.winterdevcamp.urlshortening.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.winterdevcamp.urlshortening.dto.UrlInfo;
import kr.or.winterdevcamp.urlshortening.service.UrlShortingService;
import kr.or.winterdevcamp.urlshortening.util.Base62;

@Controller
public class UrlController {

	@Autowired
	UrlShortingService urlShortingService;

	// 초기화면보여주기
	@GetMapping(path="/")
	public String showForm() {
		return "search";
	}

	// 해당하는 원래주소를 찾아 redirect 해주는 기능
	@GetMapping(path="{url}")
	public String redirect(@PathVariable String url) {
		String shortingUrl = urlShortingService.getUrl(url);
		return "redirect:" + shortingUrl;
	}

	// url short주소를 만들어주는 기능
	@PostMapping(path="/make")
	public String insert(@RequestParam(name = "address", required = true) String address, ModelMap modelMap) {

		int id = 1;

		// url인지아닌지 검사
		if (!ResourceUtils.isUrl(address)) {
			return "Not a valid url: " + address;
		}

		try {
			// 서비스에 구현한 메소드를 이용해서 테이블에서 가장 큰 id 받아오기
			// DB에 값이 없을 경우를 잡아줌
			id = urlShortingService.getMaxId();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// base62로 인코드해서 짧은 url만들어주기
		String shortAddress = Base62.encode(id); //디비에 id를 높이기

		// DB에 넣을 값을 bean객체에 넣어주기
		UrlInfo urlInfo = new UrlInfo();
		urlInfo.setAddress(address);
		urlInfo.setShortaddress(shortAddress);

		// 서비스에 구현한 메소드를 이용해서 DB에 저장
		urlShortingService.addUrlInfo(urlInfo);

		// 사용자에게 보여줄 짧은 url 알려주기
		String infoAddress = "localhost:8080/urlshortening/" + shortAddress;
		modelMap.addAttribute("address", address);
		modelMap.addAttribute("shortAddress", infoAddress);

		// 결과페이지반환
		return "result";
	}

}
