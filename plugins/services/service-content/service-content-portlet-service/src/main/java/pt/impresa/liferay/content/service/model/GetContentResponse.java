package pt.impresa.liferay.content.service.model;

import java.io.Serializable;
import java.util.List;

public class GetContentResponse implements Serializable {
	
	private static final long serialVersionUID = 1337L;
    
	private List<ImpresaContent> content;
	private ImpresaPagination pagination;

	public GetContentResponse() {
	}
	
	public List<ImpresaContent> getContent() {
		return content;
	}

	public void setContent(List<ImpresaContent> content) {
		this.content = content;
	}

	public ImpresaPagination getPagination() {
		return pagination;
	}

	public void setPagination(ImpresaPagination pagination) {
		this.pagination = pagination;
	}
	
}
