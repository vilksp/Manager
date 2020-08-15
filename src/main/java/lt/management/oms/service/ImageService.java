package lt.management.oms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import lt.management.oms.model.Image;

public interface ImageService {

    void saveFile(MultipartFile file) throws Exception;

    Optional<Image> getFileById(Long imageId);

    List<Image> getAll();
    
    void delete(Long imageId);

}