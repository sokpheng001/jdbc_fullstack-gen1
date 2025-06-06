package model.service;

import mapper.PostMapper;
import model.dto.CreatePostDto;
import model.dto.PostResponseDto;
import model.entities.Post;
import model.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements PostService{
    private final PostRepository postRepository = new PostRepository();
    private final PostMapper postMapper = new PostMapper();
    @Override
    public PostResponseDto createPost(CreatePostDto createPostDto) throws Exception{
        try{
            Post post = postMapper.fromCreatePostDtoToPost(createPostDto);

            return postMapper.fromPostToPostResponseDto(
                    postRepository.save(post)
            );
        }catch (Exception exception){
            throw new Exception(exception.getMessage());
        }

    }
    @Override
    public List<PostResponseDto> getAllPosts() {
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        postRepository.findAll()
                .forEach(p->{
                    postResponseDtos
                            .add(postMapper.fromPostToPostResponseDto(p));
                });
        return postResponseDtos;
    }
    @Override
    public Integer deletePost(String postUuid) throws Exception{
        Post post = postRepository.findPostByUuid(postUuid);
        if(post!=null){
            return postRepository.delete(post.getId());
        }
        throw new Exception("Post is not found by uuid: " + postUuid);
    }
}
