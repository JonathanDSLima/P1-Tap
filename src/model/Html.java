package model;

import java.io.Serializable;

public class Html implements Serializable {
	private static final long serialVersionUID = 6348954239070262693L;

	private String endereco;
	private String html;
	
	

	public Html() {
	}
	
	public Html(String endereco, String html ) {
		this.endereco = endereco; 
		this.html = html;
		
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return "Html [endereco=" + endereco + ", html=" + html + "]";
	}


}
