package lt.management.oms.service.impl;

import lombok.extern.slf4j.Slf4j;
import lt.management.oms.model.Image;
import lt.management.oms.repository.ImageRepository;
import lt.management.oms.service.ImageService;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final ImageRepository imageRepository;

    @Autowired
    private HttpServletRequest request;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void saveFile(MultipartFile file) throws Exception {
        isEmpty(file);
        isImageType(file);
        try {


            String uploadsDir = "/uploads/";
            String realPathToUploads = request.getServletContext().getRealPath(uploadsDir);

            if (!new File(realPathToUploads).exists()) {
                new File(realPathToUploads).mkdir();
            }

            log.info("image folder = {}", realPathToUploads);

            byte[] imageData = file.getBytes();
            String imageName = file.getOriginalFilename();
            Path path = Paths.get(realPathToUploads + imageName);
            log.info("image path = {}", path);
            Files.write(path, imageData);

            Image image = new Image(imageName, file.getContentType(), path.toString());
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
