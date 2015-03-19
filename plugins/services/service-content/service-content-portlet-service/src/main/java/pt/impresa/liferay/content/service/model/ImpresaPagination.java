package pt.impresa.liferay.content.service.model;

import java.io.Serializable;

public class ImpresaPagination implements Serializable {
	
	private static final long serialVersionUID = 1337L;
    
	private int currentPage;
	private int totalPages;

	public ImpresaPagination(){ }
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
