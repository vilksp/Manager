package lt.management.oms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lt.management.oms.model.Image;
import lt.management.oms.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public Image saveFile(MultipartFile file) {
		String imageName = file.getOriginalFilename();
		try {
			Image image = new Image(imageName, file.getContentType(), file.getBytes());
			return imageRepository.save(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Optional<Image> getFileById(Long imageId) {
		return imageRepository.findById(imageId);
	}
	
	@Override
	public List<Image> getAll() {
		return imageRepository.findAll();
	}
	
}
