package lt.management.oms.crude;

import lombok.extern.slf4j.Slf4j;
import lt.management.oms.model.Image;
import lt.management.oms.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController()
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ImageRestControllerV1 {

    @Autowired
    private ImageService imageService;

    // Returns all the files
    @GetMapping("/files")
    public List<Image> getListOfFiles() {
        return imageService.getAll();
    }

    // Uploads multiple files
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam("files") MultipartFile[] files) {
        for (int i = 0; i < files.length; i++) {
            log.info(String.format("File name '%s' uploaded successfully.", files[i].getOriginalFilename()));
        }

        Arrays.asList(files).stream().forEach(file -> {
            try {
                imageService.saveFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/downloadFile/{fileId}")
    public Image downloadFile(@PathVariable Long fileId) {
        Image image = imageService.getFileById(fileId).get();
        return image;
    }

    @DeleteMapping("files/{fileId}")
    public void deleteImage(@PathVariable Long fileId) {
        imageService.delete(fileId);
        log.info(String.format("File by id '%s' deleted successfully.", fileId));
    }
}
