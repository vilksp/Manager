package lt.management.oms.service;

import lt.management.oms.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    void saveFile(MultipartFile file) throws Exception;

    Optional<Image> getFileById(Long imageId);

    List<Image> getAll();

    void delete(Long imageId);

}