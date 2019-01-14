package kr.or.winterdevcamp.urlshortening.dao;

public class UrlShortingDaoSqls {
	public static final String SELECT_MAX = "SELECT max(id) FROM winterdevcamp.shortingdb";
	public static final String SELECT_URL = "SELECT address FROM winterdevcamp.shortingdb WHERE shortaddress= :shortaddress";
}
