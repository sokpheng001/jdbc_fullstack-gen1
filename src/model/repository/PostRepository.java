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
    private void savePostImages(Integer postId, String imageUrl){
        try(Connection con = DatabaseConfigure.getDatabaseConnection()) {
            String sql = """
                    INSERT INTO post_images
                    VALUES (?,?)
                    """;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, postId);
            pre.setString(2, imageUrl);
            int rowAffected = pre.executeUpdate();
        }catch (Exception exception){
            System.err.println("ERROR during insert images: "  +exception.getMessage());
        }
    }
    @Override
    public Post save(Post post) {
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql =
                    """
                    INSERT INTO posts (uuid, title, description, user_id)
                    VALUES (?, ?, ?, ?)
                    RETURNING id;
                    """;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, post.getUuid());
            pre.setString(2, post.getTitle());
            pre.setString(3, post.getDescription());
            pre.setInt(4, post.getUserId());
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()){
                if(post.getImageUrls().size()>0){
                    for(String imgUrls: post.getImageUrls()){
                        savePostImages(resultSet.getInt("id"), imgUrls);
                    }
                }
            }
            System.out.println("Create post successfully");
            return post;

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
    private Integer deletePostImage(Integer postId){
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql = """
                    DELETE FROM post_images WHERE post_id =? ;
                    """;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, postId);
            return pre.executeUpdate();
        }catch (Exception exception){
            System.err.println("ERROR during deleting post_images by ID: " + exception.getMessage());
        }
        return 0;
    }
    @Override
    public Integer delete(Integer id) {
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql = """
                    DELETE FROM posts WHERE id =? ;
                    """;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            int rowAffected = pre.executeUpdate();
//            String sql2 = ""

            if(rowAffected>0){
                int isDeletedImageSuccess = deletePostImage(id);
                System.out.println("Deleted post successfully");

            }
        }catch (Exception exception){
            System.err.println("ERROR during deleting post by ID: " + exception.getMessage());
        }
        return 0;
    }
    public Post findPostByUuid(String uuid){
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql =
                    """
                            SELECT * FROM posts
                            WHERE uuid = ?
                            """;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1,uuid);
            ResultSet resultSet = pre.executeQuery();
            Post post= new Post();
            while (resultSet.next()){
                post.setId(resultSet.getInt("id"));
                post.setUuid(resultSet.getString("uuid"));
            }
            return post;
        }catch (Exception exception){
            System.err.println("ERROR during find post by uuid: " + exception.getMessage());
        }
        return null;
    }
}
