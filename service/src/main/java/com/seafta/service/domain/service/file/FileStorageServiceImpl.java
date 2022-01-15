package com.seafta.service.domain.service.file;

import com.seafta.service.domain.persistence.model.file.FileDB;
import com.seafta.service.domain.persistence.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public FileDB store(UUID receiverId, MultipartFile file) throws IOException {
        FileDB fileDB = FileDB.buildFileDB(receiverId, file);

        return fileRepository.save(fileDB);
    }

    @Override
    public FileDB getFile(UUID id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public List<FileDB> getAllFilesByCompanyId(UUID companyId) {
        return new ArrayList<>(fileRepository.findAllByReceiverId(companyId));
    }
}
