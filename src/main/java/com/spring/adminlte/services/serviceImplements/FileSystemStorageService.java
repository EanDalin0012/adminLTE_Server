package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.services.StorageService;
import com.spring.adminlte.utils.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {
    private static final Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);
    private final Path rootLocation;
    @Autowired
    public FileSystemStorageService() {
        this.rootLocation = Paths.get(SystemUtil.projectPath());
    }

    @Override
    @PostConstruct
    public void init() {

    }

    @Override
    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(filename);
        try {
            if (file.isEmpty()) {
                return "Failed to store empty file " + filename;
            }
            if (filename.contains("..")) {
                // This is a security check
                return  "Cannot store file with relative path outside current directory " + filename;
            }
            Path targetLocation = this.rootLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e) {
            log.error("\n file error \n", e.getMessage());
//            throw e;
        }
        return null;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
