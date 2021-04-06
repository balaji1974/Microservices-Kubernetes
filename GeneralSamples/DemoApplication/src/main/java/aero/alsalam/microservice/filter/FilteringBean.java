package aero.alsalam.microservice.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(value={"field2","field1"}) // class level ignore, but better is field level ignore
@JsonFilter("FiltBean") // Needed for dynamic filtering and this name comes from what is defined as filter name in the controller
public class FilteringBean {
	private String field1;
	private String field2;
	
	@JsonIgnore // field level ignore
	private String field3;
	
	private String field4;
	
	
	
	public FilteringBean(String field1, String field2, String field3, String field4) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
		this.field4 = field4;
	}
	
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

}
