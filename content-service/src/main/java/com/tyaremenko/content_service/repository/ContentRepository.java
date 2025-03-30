package com.tyaremenko.content_service.repository;

import com.tyaremenko.content_service.domain.Content;
import org.springframework.data.repository.CrudRepository;

public interface ContentRepository extends CrudRepository<Content, String> {
}
