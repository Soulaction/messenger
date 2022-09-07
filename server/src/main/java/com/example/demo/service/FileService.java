package com.example.demo.service;

import com.example.demo.entity.FileEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.config.StorageProperties;
import com.example.demo.exception.FileAlreadyExist;
import com.example.demo.exception.FileNotFoundException;
import com.example.demo.exception.StorageException;
import com.example.demo.repository.FileRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {

    private final Path rootLocation;
    @Autowired
    public FileService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FileRepo fileRepo;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    public FileEntity store(MultipartFile file, Long id) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        UserEntity user = userRepo.findById(id).get();
        FileEntity fileEntity = fileRepo.findByUser(user);

        if (fileEntity != null)
            throw new FileAlreadyExist("Файл уже загружен!");

        UUID nameKey = UUID.randomUUID();
        fileEntity = new FileEntity(filename, nameKey.toString(), file.getSize(), user);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

        return fileEntity;
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new FileNotFoundException(
                        "Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
