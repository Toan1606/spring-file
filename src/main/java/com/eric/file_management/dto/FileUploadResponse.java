package com.eric.file_management.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
public class FileUploadResponse {
    private String fileName;

    private String downloadUri;

    private Long size;

    private String fileCode;
}
