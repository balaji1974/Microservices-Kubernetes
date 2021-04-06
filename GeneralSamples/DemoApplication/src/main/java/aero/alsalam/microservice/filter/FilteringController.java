package aero.alsalam.microservice.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retreiveFilteringBean() {
		FilteringBean filteringBean = new FilteringBean("value1","value2","value3", "value4");
		
		// To dynamically filter out certain fields that are not needed to be sent 
		SimpleBeanPropertyFilter sbpf=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		return myDynamicFilter(filteringBean, sbpf);
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue retreiveFilteringBeanList() {
		List<FilteringBean> list = Arrays.asList(new FilteringBean("value1","value2","value3", "value4"),
				new FilteringBean("value5","value6","value7" , "value8"),
				new FilteringBean("value9","value10","value11", "value12"));
		
		// To dynamically filter out certain fields that are not needed to be sent 
		SimpleBeanPropertyFilter sbpf=SimpleBeanPropertyFilter.filterOutAllExcept("field2","field4");
		return myDynamicFilter(list, sbpf);
		
		
	}
	
	private MappingJacksonValue myDynamicFilter(Object obj, SimpleBeanPropertyFilter sbpf) {
		FilterProvider filterProvider=new SimpleFilterProvider().addFilter("FiltBean", sbpf);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(obj);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
}
