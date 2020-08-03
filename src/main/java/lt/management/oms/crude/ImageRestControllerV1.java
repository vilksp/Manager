package lt.management.oms.crude;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import lt.management.oms.model.Image;
import lt.management.oms.service.ImageService;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
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
	public ResponseEntity uploadFile(@RequestParam("files") MultipartFile[] files){
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
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
		Image image = imageService.getFileById(fileId).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + image.getImageName() + "\"")
				.body(new ByteArrayResource(image.getData()));
	}
}
