package com.seafta.service.boundary.file;

import com.seafta.service.domain.dto.ResponseFile;
import com.seafta.service.domain.persistence.model.file.FileDB;
import com.seafta.service.domain.service.file.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.security.logging.SecurityMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController implements FileApi {

    @Autowired
    FileStorageService service;

    @Override
    public ResponseEntity<String> store(UUID receiverId, MultipartFile file) throws IOException {
        log.trace(SecurityMarkers.CONFIDENTIAL, "File Controller: Uploading file {receiver: {}, file name: {}", receiverId, file.getName());
        service.store(receiverId, file);
        log.debug(SecurityMarkers.CONFIDENTIAL, "File Controller: Uploaded file {receiver: {}. file name: {}", receiverId, file.getName());
        return ResponseEntity.status(HttpStatus.OK).body(new String("Uploaded"));
    }

    @Override
    public ResponseEntity<byte[]> getFile(UUID fileId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "File Controller: Getting file by id {file ID: {}}", fileId);
        FileDB fileDB = service.getFile(fileId);
        log.debug(SecurityMarkers.CONFIDENTIAL, "File Controller: Got file by id {file ID: {}, file name: {}}", fileId, fileDB.getName());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getAllFilesByCompanyId(UUID companyId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "File Controller: Getting files by company id {company ID: {}}", companyId);
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
        log.debug(SecurityMarkers.CONFIDENTIAL, "File Controller: Got files by id {company ID: {}, file name: {}}", companyId, files.stream().map(ResponseFile::getName));
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
