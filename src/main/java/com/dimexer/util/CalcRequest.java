package com.dimexer.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CalcRequest {

	private List<RequestEntry> entries;

	public CalcRequest() {
		this.entries = new ArrayList<RequestEntry>();
	}

	public void add(RequestEntry oe) {
		this.entries.add(oe);
	}

	public List<RequestEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<RequestEntry> entries) {
		this.entries = entries;
	}

}
