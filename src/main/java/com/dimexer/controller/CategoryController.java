package com.dimexer.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.NavigationHandler;
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

	@PostConstruct
	private void initialize() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
		String idString = req.getParameter("cid");
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		Integer id = null;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception ex) {
			nh.handleNavigation(fc, null, "pretty:notfound");
		}
		if (id != null) {
			this.currentCategory = categoryBean.loadCategoryById(id);
			if(this.currentCategory == null){
				nh.handleNavigation(fc, null, "pretty:notfound");
			}
		}
	}

	public Category getCurrentCategory() {
		return currentCategory;
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
