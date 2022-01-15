package com.seafta.service.domain.persistence.repository;

import com.seafta.service.domain.persistence.model.file.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileDB, UUID> {
    List<FileDB> findAllByReceiverId(UUID receiverId);
}
