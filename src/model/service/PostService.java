package model.service;

import model.dto.CreatePostDto;
import model.dto.PostResponseDto;

import java.util.List;

public interface PostService {
    PostResponseDto createPost(CreatePostDto createPostDto) throws Exception;
    List<PostResponseDto> getAllPosts();
    Integer deletePost(String postUuid) throws Exception;
}
