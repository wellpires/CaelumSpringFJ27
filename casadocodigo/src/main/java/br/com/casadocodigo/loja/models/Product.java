package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Product {

	@Id
	private Long id = 0L;
	private String title = null;
	
	@Lob
	private String description = null;
	private Integer numberOfPages = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	@Override
	public String toString() {
		return "Product [title=" + title + ", description=" + description + ", numberOfPages=" + numberOfPages + "]";
	}

}
