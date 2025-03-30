package com.tyaremenko.content_service.service;

import com.tyaremenko.content_service.clients.CommentServiceClient;
import com.tyaremenko.content_service.clients.UserServiceClient;
import com.tyaremenko.content_service.domain.Content;
import com.tyaremenko.content_service.dto.CommentDto;
import com.tyaremenko.content_service.dto.CommentResponse;
import com.tyaremenko.content_service.dto.ContentResponse;
import com.tyaremenko.content_service.dto.SearchUsersRequest;
import com.tyaremenko.content_service.dto.UserDto;
import com.tyaremenko.content_service.dto.UserResponse;
import com.tyaremenko.content_service.repository.ContentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ContentService {
    private static final UserResponse UNKNOWN_USER = UserResponse.builder()
                                                                 .id(0L)
                                                                 .nickname("Unknown")
                                                                 .firstName("Unknown")
                                                                 .lastName("Unknown")
                                                                 .build();
    private ContentRepository contentRepository;
    private UserServiceClient userServiceClient;
    private CommentServiceClient commentServiceClient;

    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    public Content updateContent(Content content) {
        return contentRepository.save(content);
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public Optional<ContentResponse> getContent(Long id) {
        Optional<Content> optionalContent = contentRepository.findById(id);
        if (optionalContent.isEmpty()) {
            return Optional.empty();
        }
        Content content = optionalContent.get();

        Set<CommentDto> commentDtos = commentServiceClient.getCommentsForContent(content.getId());
        Set<Long> userIds = commentDtos.stream()
                                       .map(CommentDto::getUserId)
                                       .collect(Collectors.toCollection(HashSet::new));
        userIds.add(content.getAuthorId());
        Map<Long, UserResponse> users = userServiceClient.searchUsers(SearchUsersRequest.builder()
                                                                                        .userIds(userIds)
                                                                                        .build())
                                                         .stream()
                                                         .collect(Collectors.toMap(UserDto::getId, this::toUserResponse));
        Set<CommentResponse> comments = commentDtos.stream()
                                                   .map(dto -> getCommentResponse(dto, users))
                                                   .collect(Collectors.toSet());
        ContentResponse contentResponse = ContentResponse.builder()
                                                         .id(content.getId())
                                                         .content(content.getContent())
                                                         .likes(content.getLikes())
                                                         .dislikes(content.getDislikes())
                                                         .comments(comments)
                                                         .author(users.getOrDefault(content.getAuthorId(), UNKNOWN_USER))
                                                         .build();
        return Optional.of(contentResponse);
    }

    private UserResponse toUserResponse(UserDto userDto) {
        return UserResponse.builder()
                           .id(userDto.getId())
                           .nickname(userDto.getNickname())
                           .firstName(userDto.getFirstName())
                           .lastName(userDto.getLastName())
                           .build();
    }

    private CommentResponse getCommentResponse(CommentDto dto, Map<Long, UserResponse> users) {
        return CommentResponse.builder()
                              .id(dto.getId())
                              .content(dto.getContent())
                              .likes(dto.getLikes())
                              .dislikes(dto.getDislikes())
                              .user(users.getOrDefault(dto.getUserId(), UNKNOWN_USER))
                              .build();
    }
}
