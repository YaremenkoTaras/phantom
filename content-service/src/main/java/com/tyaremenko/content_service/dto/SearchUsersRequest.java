package com.tyaremenko.content_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Value
@Builder
@Jacksonized
@With
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchUsersRequest {
    Set<Long> userIds;
}

