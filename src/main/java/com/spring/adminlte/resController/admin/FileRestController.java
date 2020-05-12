package com.spring.adminlte.resController.admin;

import com.spring.adminlte.dao.ProductImageDao;
import com.spring.adminlte.dto.FileResponseDto;
import com.spring.adminlte.dto.ProductImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/file")
public class FileRestController {
    @Autowired
    private ProductImageDao productImageService;
    public FileResponseDto store() {
        return null;
    }
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
}
