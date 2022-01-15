package com.seafta.service.domain.persistence.model.file;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "files")
public class FileDB {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    private String name;

    @NotNull
    private UUID receiverId;

    private String type;

    @Lob
    private byte[] data;

    public static FileDB buildFileDB(UUID receiverId, MultipartFile file) throws IOException {
        return FileDB.builder()
                .name(StringUtils.cleanPath(file.getOriginalFilename()))
                .receiverId(receiverId)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
    }
}
