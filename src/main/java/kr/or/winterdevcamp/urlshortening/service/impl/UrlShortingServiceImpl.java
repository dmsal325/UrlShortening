package kr.or.winterdevcamp.urlshortening.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.winterdevcamp.urlshortening.dao.UrlShortingDao;
import kr.or.winterdevcamp.urlshortening.dto.UrlInfo;
import kr.or.winterdevcamp.urlshortening.service.UrlShortingService;

@Service
public class UrlShortingServiceImpl implements UrlShortingService{

	@Autowired //빈자동등록 //생성해서 주입시킴
	UrlShortingDao urlShortingDao;

	@Override
	@Transactional(readOnly=false)
	public UrlInfo addUrlInfo(UrlInfo urlInfo) {
		Long id = urlShortingDao.insert(urlInfo);
		urlInfo.setId(id);
		return urlInfo;
	}

	@Override
	@Transactional
	public int getMaxId() {
		return urlShortingDao.selectMaxId();
	}

	@Override
	@Transactional
	public String getUrl(String shortaddress) {
		return urlShortingDao.selectUrl(shortaddress);
	}
	
	
}
