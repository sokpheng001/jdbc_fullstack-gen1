package controller;

import model.dto.CreatePostDto;
import model.dto.PostResponseDto;
import model.service.PostServiceImpl;

import java.util.List;

public class PostController {
    private final PostServiceImpl postService = new PostServiceImpl();
    public List<PostResponseDto> getAllPosts(){
        return postService.getAllPosts();
    }
    public PostResponseDto createPost(CreatePostDto createPostDto) throws Exception{
        return postService.createPost(createPostDto);
    }
    public Integer deletePostByUuid(String uuid) throws Exception{
        return postService.deletePost(uuid);
    }
}
