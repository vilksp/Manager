package lt.management.oms.service.impl;

import lt.management.oms.model.Image;
import lt.management.oms.repository.ImageRepository;
import lt.management.oms.service.ImageService;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {


    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void saveFile(MultipartFile file) throws Exception {
        isEmpty(file);
        isImageType(file);
        String imageName = file.getOriginalFilename();
        try {
            Image image = new Image(imageName, file.getContentType(), file.getBytes());
            imageRepository.save(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<Image> getFileById(Long imageId) {
        return imageRepository.findById(imageId);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    private void isEmpty(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalAccessException("Cannot pass empty file!");
        }
    }

    private void isImageType(MultipartFile file) {
        if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image type [" + file.getContentType() + "]");
        }
    }

	@Override
	public void delete(Long imageId) {
		imageRepository.deleteById(imageId);
	}
}
