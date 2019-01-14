package kr.or.winterdevcamp.urlshortening.dto;

public class UrlInfo {
	private long id;
	private String address;
	private String shortaddress;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShortaddress() {
		return shortaddress;
	}

	public void setShortaddress(String shortaddress) {
		this.shortaddress = shortaddress;
	}

	@Override
	public String toString() {
		return "UrlInfo [id=" + id + ", address=" + address + ", shortaddress=" + shortaddress + "]";
	}

}
