package kr.or.winterdevcamp.urlshortening.service;

import kr.or.winterdevcamp.urlshortening.dto.UrlInfo;

public interface UrlShortingService {
	
	public UrlInfo addUrlInfo(UrlInfo urlInfo);
	public int getMaxId();
	public String getUrl(String shortaddress);

}
