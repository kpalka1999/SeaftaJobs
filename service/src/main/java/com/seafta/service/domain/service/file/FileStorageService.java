package com.seafta.service.domain.service.file;

import com.seafta.service.domain.persistence.model.file.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FileStorageService {
    FileDB store(UUID receiverId, MultipartFile file) throws IOException;
    FileDB getFile(UUID id);
    List<FileDB> getAllFilesByCompanyId(UUID companyId);
}
