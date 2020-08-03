package lt.management.oms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import lt.management.oms.model.Image;

public interface ImageService {

	public Image saveFile(MultipartFile file);
	
	public Optional<Image> getFileById(Long imageId);
	
	public List<Image> getAll();
	
}