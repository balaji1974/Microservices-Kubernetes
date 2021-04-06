package aero.alsalam.microservice.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="This class gives full infomation about the user that is using the system") // This is for swagger
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ApiModelProperty(notes="Name must be atleast5 characters") // This is for swagger
	@Size(min=5, message="name must be atleast 5 characters")
	private String name;
	
	@ApiModelProperty(notes="Birthdate must be in the past")  // This is for swagger
	@Past(message="Dates cannot be from the past")
	private Date dateOfBirth;
	
	@OneToMany(mappedBy="user")
	List<Post> posts;

	public User(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
}
