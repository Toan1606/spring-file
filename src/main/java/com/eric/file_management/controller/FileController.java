package com.eric.file_management.controller;

import com.eric.file_management.dto.FileUploadResponse;
import com.eric.file_management.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        FileUploadResponse response = fileService.saveFile(fileName, multipartFile);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileCode") String fileCode) throws IOException {
        Resource resource = fileService.getFileAsResource(fileCode);

        String contentType = "application/octet-stream";
        String header = new StringBuffer().append("attachment; filename=\"")
                .append(resource.getFile())
                .append("\"")
                .toString();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }
}
