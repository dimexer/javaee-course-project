package com.dimexer.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.dimexer.ejb.CategoryBean;
import com.dimexer.model.Category;

@ManagedBean
@RequestScoped
public class CategoryController {

	private Category currentCategory;

	@EJB
	private CategoryBean categoryBean;

	public Category getCurrentCategory() {
		return currentCategory;
	}

	@PostConstruct
	private void initialize() {
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String idString = req.getParameter("cid");

		Integer id = null;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception ex) {
			System.out.println("Bad id passed. Can't instantiate category");
		}
		if (id != null) {
			this.currentCategory = categoryBean.loadCategoryById(id);
		}
	}

	public void setCurrentCategory(Category currentCategory) {
		this.currentCategory = currentCategory;
	}

	public CategoryBean getCategoryBean() {
		return categoryBean;
	}

	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

}
