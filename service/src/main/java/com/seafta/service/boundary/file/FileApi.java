package com.seafta.service.boundary.file;

import com.seafta.service.domain.dto.ResponseFile;
import com.seafta.service.domain.persistence.model.account.Account;
import com.seafta.service.domain.persistence.model.file.FileDB;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public interface FileApi {

    @Operation(summary = "CreateFile", description = "FILE_CREATE_POST")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(CREATED)
    void store(@RequestParam("receiverId") UUID receiverId, @RequestParam(value = "file")MultipartFile file) throws IOException;

    @Operation(summary = "GetFile", description = "FILE_GET")
    @GetMapping(value = "/{fileId}")
    @ResponseStatus(OK)
    ResponseEntity<byte []> getFile(@PathVariable("fileId")UUID fileId);

    @Operation(summary = "GetFiles", description = "FILE_GET")
    @GetMapping(value ="/all/{companyId}")
    @ResponseStatus(OK)
    ResponseEntity<List<ResponseFile>> getAllFilesByCompanyId(@PathVariable("companyId") UUID companyId);


}
