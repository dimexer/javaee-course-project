package com.dimexer.listener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.dimexer.controller.UserController;

public class LoggedInCheck implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Set<String> loginRequiredPages = new HashSet<String>(
			Arrays.asList(new String[] { "myAccount", "checkout" }));

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	@Override
	public void afterPhase(PhaseEvent arg0) {
		FacesContext fc = arg0.getFacesContext();
		String viewId = fc.getViewRoot().getViewId();
		for (String s : loginRequiredPages) {
			if (viewId.lastIndexOf(s) > -1 && !loggedIn(fc)) {
				fc.addMessage(null, new FacesMessage("Please, login first!"));
				NavigationHandler nh = fc.getApplication()
						.getNavigationHandler();
				nh.handleNavigation(fc, null, "pretty:login");
			}
		}

	}

	private boolean loggedIn(FacesContext fc) {
		UserController uc = (UserController) fc.getApplication()
				.getELResolver()
				.getValue(fc.getELContext(), null, "userController");
		return uc.getCustomer()!=null;
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
