package com.eric.file_management.service;

import com.eric.file_management.dto.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
@Slf4j
public class FileService {

    private Path foundFile;

    @Transactional
    public FileUploadResponse saveFile(String fileName, MultipartFile multipartFile)  {
        FileUploadResponse fileUploadResponse = FileUploadResponse.builder()
                .fileName(fileName)
                .downloadUri("/downloadFile")
                .size(multipartFile.getSize())
                .build();
        // B1. Lấy đường dẫn của file upload
        Path uploadDirectory = Paths.get("Files-Upload");

        // B2. Convert file to stream of bytes
        try(InputStream inputStream = multipartFile.getInputStream();) {
            // B3. get file path
            Path filePath = uploadDirectory.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileUploadResponse;
    }

    public boolean foundFile(String path) {
        return true;
    }

    public Resource getFileAsResource(String code) throws IOException {
        Path uploadDirectory = Paths.get("Files-Upload");
        Files.list(uploadDirectory).forEach(file -> {
            if (file.getFileName().toString().startsWith(code)) {
                foundFile = file;
            }
        });
        return new UrlResource(foundFile.toUri());
    }
}
