package com.hs.eai.monitor.user.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field; 
import org.hibernate.search.annotations.Indexed; 




@Entity
@Indexed
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "from User u"),
	@NamedQuery(name = "User.findByUsername", query = "from User u where u.username = :username"),
	@NamedQuery(name = "User.findByEmail", query = "from User u where u.email = :email"),
	@NamedQuery(name = "User.findByRolename", query = "from User u where u.roles in (roles)")
	

})
public class User {

	@Id
	@GeneratedValue
	@DocumentId
	private Integer id;
 
	 //@Size(min = 3, message = "Name must be at least 3 characters!")
	//@Column(unique = true)
	//@UniqueUsername(message = "Such username already exists!")
	private String username;

	//@Size(min = 1, message = "Invalid email address!")
	//@Email(message = "Invalid email address!")
	private String email;

	//@Size(min = 5, message = "Name must be at least 5 characters!")
	private String password;
	
	@Column(name = "enabled", columnDefinition = "BIT", length = 1)
	private boolean enabled;
    
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
								inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;

	@Column ( name = "date_created")
	private Timestamp  dateCreated;

	public User(){
		this.enabled = true;
		Calendar cal = Calendar.getInstance(); 
		java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		this.dateCreated = timestamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	

}
