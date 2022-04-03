package rs.ac.uns.ftn.informatika.jpa.dto;

import java.util.ArrayList;

public class ImagesDTO {
	private ArrayList<String> imagePaths;

	public ImagesDTO(ArrayList<String> imagePaths) {
		super();
		this.imagePaths = imagePaths;
	}

	public ArrayList<String> getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(ArrayList<String> imagePaths) {
		this.imagePaths = imagePaths;
	}
	
}
