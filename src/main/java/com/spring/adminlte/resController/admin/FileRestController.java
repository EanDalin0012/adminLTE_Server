package com.spring.adminlte.resController.admin;

import com.spring.adminlte.dao.ProductImageDao;
import com.spring.adminlte.dto.*;
import com.spring.adminlte.services.serviceImplements.FileSystemStorageService;
import com.spring.adminlte.templatesDto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/file")
public class FileRestController {
    @Autowired
    private FileSystemStorageService fileSystemStorageService;
    @Autowired
    private ProductImageDao productImageService;
    public FileResponseDto store() {
        return null;
    }

    /*
    * @functionName getImage
    * @description get image by path
    * */
    @GetMapping(value = "/logo/{id}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("id") int id) throws IOException {

        try{
            ProductImageDto productImageDto = productImageService.getValueById(id);
            String resource = productImageService.getPathImage(id);
//            String resource = productImageDto.getUri();
            ClassPathResource imgFile = new ClassPathResource(resource);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new InputStreamResource(imgFile.getInputStream()));
        }catch (Exception e) {
            throw e;
        }
    }
    @PostMapping(value = "/image")
    public ResponseEntity<ResponseData<ImageDto>> store(@RequestBody MultipartFile file) {
        ResponseData<ImageDto> response = new ResponseData<>();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/upload/product")
    public ResponseData<ImageURLDto> handleFileUpload(@RequestParam("file") MultipartFile multipartFile) {
        ResponseData<ImageURLDto> response = new ResponseData<>();
        InputStream is = null;
        HeaderDto header = new HeaderDto();
        ImageURLDto imageUrl = new ImageURLDto();
        try {
            UUID uuid = UUID.randomUUID();
            String resID = uuid.toString();

            String[] originalFilename = multipartFile.getOriginalFilename().split("\\.(?=[^\\.]+$)");

        }catch (Exception e) {

        }

        return response;
    }
}
