package com.dimexer.validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {
	private static Pattern regex = Pattern.compile("[A-Za-z0-9._]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String email =arg2.toString();
		if(!regex.matcher(email).matches()){
			FacesMessage msg = new FacesMessage();
			msg.setDetail("Bad email entered");
			msg.setSummary("Bad email entered");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
	

}
