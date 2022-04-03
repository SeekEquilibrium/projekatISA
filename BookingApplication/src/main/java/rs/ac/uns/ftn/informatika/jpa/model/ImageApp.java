package rs.ac.uns.ftn.informatika.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ImageApp {
	@Id
	@SequenceGenerator(name = "imageSeqGen", sequenceName = "imageSeqGen", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "imageSeqGen")
	private long id;
	@Column(unique = true)
	private String path;
	
	public ImageApp() {
		super();
	}

	public ImageApp(long id, String path) {
		super();
		this.id = id;
		this.path = path;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
