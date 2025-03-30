package com.tyaremenko.content_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.With;
import lombok.extern.jackson.Jacksonized;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "contents")
@Entity
public class Content {
    @Id
    @GeneratedValue
    Long id;
    String content;
    long likes;
    long dislikes;
    long authorId;
}
