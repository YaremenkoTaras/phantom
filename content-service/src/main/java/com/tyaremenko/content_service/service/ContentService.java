package com.tyaremenko.content_service.service;

import com.tyaremenko.content_service.clients.CommentServiceClient;
import com.tyaremenko.content_service.clients.UserServiceClient;
import com.tyaremenko.content_service.domain.Content;
import com.tyaremenko.content_service.dto.CommentDto;
import com.tyaremenko.content_service.dto.CommentResponse;
import com.tyaremenko.content_service.dto.ContentResponse;
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
                                                                 .id("0")
                                                                 .nickname("Unknown")
                                                                 .firstName("Unknown")
                                                                 .lastName("Unknown")
                                                                 .build();
    private ContentRepository contentRepository;
    private UserServiceClient userServiceClient;
    private CommentServiceClient commentServiceClient;

    private static CommentResponse getCommentResponse(CommentDto dto, Map<String, UserResponse> users) {
        return CommentResponse.builder()
                              .id(dto.getId())
                              .content(dto.getContent())
                              .likes(dto.getLikes())
                              .dislikes(dto.getDislikes())
                              .user(users.getOrDefault(dto.getUserId(), UNKNOWN_USER))
                              .build();
    }

    public Content createContent(Content user) {
        return contentRepository.save(user);
    }

    public Content updateContent(Content user) {
        return contentRepository.save(user);
    }

    public void deleteContent(String id) {
        contentRepository.deleteById(id);
    }

    public Optional<ContentResponse> getContent(String id) {
        Optional<Content> optionalContent = contentRepository.findById(id);
        if (optionalContent.isEmpty()) {
            return Optional.empty();
        }
        Content content = optionalContent.get();

        Set<CommentDto> commentDtos = commentServiceClient.getCommentsForContent(content.getId());
        Set<String> userIds = commentDtos.stream()
                                         .map(CommentDto::getUserId)
                                         .collect(Collectors.toCollection(HashSet::new));
        userIds.add(content.getAuthorId());
        Map<String, UserResponse> users = userServiceClient.searchUsers(userIds)
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
}
