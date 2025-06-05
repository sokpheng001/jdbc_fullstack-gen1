package model.repository;

import model.entities.Post;
import utils.DatabaseConfigure;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class PostRepository implements Repository<Post, Integer> {
    @Override
    public Post save(Post post) {
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql =
                    """
                    INSERT INTO posts (uuid, title, description, user_id)
                    VALUES (?,?,?,?)
                    """;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, post.getUuid());
            pre.setString(2, post.getTitle());
            pre.setString(3, post.getDescription());
            pre.setInt(4, post.getUserId());
            int rowAffected = pre.executeUpdate();
            if(rowAffected>0){
                return post;
            }
        }catch (Exception exception){
            System.err.println("ERROR During creating post: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Post> findAll() {
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql = """
SELECT * FROM posts
JOIN users u on posts.user_id = u.id
LEFT JOIN post_images pim on posts.id = pim.post_id  ;
                    """;
            Statement statement =con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            List<Post> postList = new ArrayList<>();
            Set<String> imageUrls;
            Post post = null;
            AtomicInteger isMultiPost = new AtomicInteger();
            while (result.next()){
                post = new Post();
                imageUrls = new HashSet<>();
                post.setId(result.getInt("id"));
                post.setUuid(result.getString("uuid"));
                post.setTitle(result.getString("title"));
                post.setDescription(result.getString("description"));
                post.setUserId(result.getInt("user_id"));
                imageUrls.add(result.getString("image_url"));
                post.setImageUrls(imageUrls);
                Post finalPost = post;
                postList.forEach(e->{
                    if(e.getId().equals(finalPost.getId())){
                        isMultiPost.getAndIncrement();
                        try {
                            e.getImageUrls().add(result.getString("image_url"));
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                if(isMultiPost.get()>0){
                    isMultiPost.set(0);
                    continue;
                }
                postList.add(post);
            }
            return postList;
        }catch (Exception exception){
            System.err.println("Error during select all posts: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }
}
