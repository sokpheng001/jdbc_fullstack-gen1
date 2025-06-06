package mapper;

import model.dto.CreatePostDto;
import model.dto.PostResponseDto;
import model.entities.Post;
import model.entities.User;
import model.repository.UserRepositoryImpl;

import java.util.UUID;

public class PostMapper {
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();
    public Post fromCreatePostDtoToPost(CreatePostDto createPostDto) throws Exception{
        User user = userRepository.findByUserUuid(createPostDto.userUuid());
        if(user==null){
            throw new Exception("This is user has not been found :(");
        }
        return new Post(null, UUID.randomUUID().toString(),
                createPostDto.title(),
                createPostDto.description(),
                createPostDto.imageUrls(),
                user.getId()
                );
    }
    public PostResponseDto fromPostToPostResponseDto(Post post){
        User user = userRepository.findByUserId(post.getUserId());
        return PostResponseDto.builder()
                .uuid(post.getUuid())
                .postTitle(post.getTitle())
                .postDescription(post.getDescription())
                .imageUrls(post.getImageUrls())
                .responseDto(UserMapper.fromUserToUserResponseDto(user))
                .build();
    }
}
