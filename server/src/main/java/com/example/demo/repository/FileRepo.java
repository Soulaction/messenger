package com.example.demo.repository;

import com.example.demo.entity.FileEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface FileRepo extends CrudRepository<FileEntity, Long> {
    FileEntity findByUser(UserEntity user);
}
