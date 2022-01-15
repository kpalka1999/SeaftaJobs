package com.seafta.service.boundary.file;

import com.seafta.service.domain.dto.ResponseFile;
import com.seafta.service.domain.persistence.model.file.FileDB;
import com.seafta.service.domain.service.file.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FileController implements FileApi {

    @Autowired
    FileStorageService service;

    //todo log.trace for each method
    @Override
    public void store(UUID receiverId, MultipartFile file) throws IOException {
        service.store(receiverId, file);
    }

    @Override
    public ResponseEntity<byte[]> getFile(UUID fileId) {
        FileDB fileDB = service.getFile(fileId);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getAllFilesByCompanyId(UUID companyId) {
        List<ResponseFile> files = service.getAllFilesByCompanyId(companyId).stream().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/file/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
}
