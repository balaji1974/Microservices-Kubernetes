package aero.alsalam.microservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

	// Option 1 (URI versioning)
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}
	
	// Option 1 (URI versioning)
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	// Option 2 (Parameter versioning)
	@GetMapping(value="/person", params="version=1")
	public PersonV1 paramPersonV1() {
		return new PersonV1("Bob Charlie");
	}
	
	// Option 2 (Parameter versioning)
	@GetMapping(value="/person", params="version=2")
	public PersonV2 paramPersonV2() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	
	// Option 3  (Header/Mime-type versioning)
	@GetMapping(value="/person", headers="X-API-VERSION=1")
	public PersonV1 headerPersonV1() {
		return new PersonV1("Bob Charlie");
	}
	
	// Option 3(Header/Mime-type versioning)
	@GetMapping(value="/person", headers="X-API-VERSION=2")
	public PersonV2 headerPersonV2() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	// Option 4 (Accept Header versioning)
	@GetMapping(value="/person", produces="application/vnd.company.app-v1+json")
	public PersonV1 producesPersonV1() {
		return new PersonV1("Bob Charlie");
	}
	
	// Option 4 (Accept Header versioning)
	@GetMapping(value="/person", produces="application/vnd.company.app-v2+json")
	public PersonV2 producesPersonV2() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
		
	
}
