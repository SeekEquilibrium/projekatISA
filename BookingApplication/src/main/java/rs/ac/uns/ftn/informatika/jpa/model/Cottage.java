package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Cottage {
	@Id
	@SequenceGenerator(name = "cottageSeqGen", sequenceName = "cottageSeqGen", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cottageSeqGen")
	private long id;
	@Column(unique = true)
	private String name;
	@Column
	private String address;
	@Column
	private String description;
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@ManyToOne(fetch = FetchType.EAGER)
//	private UserApp cottageOwner;
	
	public Cottage() {}

	public Cottage(long id, String name, String address, String description/*, UserApp cottageOwner*/) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		//this.cottageOwner = cottageOwner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public UserApp getCottageOwner() {
//		return cottageOwner;
//	}
//
//	public void setCottageOwner(UserApp cottageOwner) {
//		this.cottageOwner = cottageOwner;
//	}

	
}