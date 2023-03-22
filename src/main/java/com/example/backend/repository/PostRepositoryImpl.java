package com.example.backend.repository;

import com.example.backend.entities.post.Post;
import com.example.backend.entities.user.Status;
import com.example.backend.entities.user.User;
import com.example.backend.entities.user.UserType;

import javax.ws.rs.POST;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl extends MySqlAbstractRepository implements  PostRepository{


    @Override
    public Post addPost(Post post) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = newConnection();

//            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO posts (" +
                            "title, content, creationDAte, viewCount, author, categoryId) VALUES (?, ?, CURRENT_TIMESTAMP(), 0, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getAuthor());
            preparedStatement.setInt(4, post.getCategoryId());
            //preparedStatement.setString(5, category.getName()); TODO: proveriti date i visits
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                post.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return post;
    }

    @Override
    public Post editPost(Post post) {
        System.out.println(post.toString());

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE posts SET categoryId = ?, title = ?, content = ?, author = ?, viewCount = ? WHERE id = ?");
            preparedStatement.setInt(1, post.getCategoryId());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setString(4, post.getAuthor());
            preparedStatement.setInt(5, post.getViewCount());
            preparedStatement.setInt(6, post.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return post;
    }

    @Override
    public Post findPost(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Post post = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM posts WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                post =  new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("viewCount"),
                        resultSet.getString("author"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public Integer countPost(Integer catId, Integer tagId) {
        return null;
    }

    @Override
    public void deletePost(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM posts WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM comments WHERE postId = ?");//todo ako nema komentara da li puca
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM tag_posts WHERE postId = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }

    }

    @Override
    public List<Post> allPosts() {
        List<Post> posts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM posts ORDER BY creationDate DESC");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post article = new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("viewCount"),
                        resultSet.getString("author"));
                posts.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return posts;
    }

    @Override
    public List<Post> findPostsByPage(Integer page) {
        return null;
    }

    @Override
    public List<Post> findPostsByCategory(Integer categoryId) {
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM posts  WHERE categoryId = ? ORDER BY creationDate ASC");
            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("viewCount"),
                        resultSet.getString("author"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return posts;
    }

    @Override
    public List<Post> findPostsByTag(Integer tagId) {
        System.out.println("Tag ");


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Post> posts = new ArrayList<>();

        try {
            connection = newConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM posts post INNER JOIN tag_posts tag on post.id = tag.postId " +
                            "WHERE tag.id = ? ORDER BY post.creationDate DESC");// LIMIT 5 OFFSET ?
            preparedStatement.setInt(1, tagId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post toAdd = new Post();
                toAdd.setId(resultSet.getInt("id"));
                toAdd.setCategoryId(resultSet.getInt("categoryId"));
                toAdd.setAuthor(resultSet.getString("author"));
                toAdd.setViewCount(resultSet.getInt("viewCount"));
                toAdd.setCreationDate(resultSet.getDate("creationDate"));
                toAdd.setContent(resultSet.getString("content"));
                toAdd.setTitle(resultSet.getString("title"));

                posts.add(toAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return posts;
    }

    @Override
    public List<Post> searchPost(String search) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Post> posts = new ArrayList<>();

        String searchQuote = "%" + search + "%";


        try {
            connection = newConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM posts WHERE title LIKE  ? OR content LIKE ? ");
            preparedStatement.setString(1, searchQuote);
            preparedStatement.setString(2, searchQuote);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post toAdd = new Post();
                toAdd.setId(resultSet.getInt("id"));
                toAdd.setCategoryId(resultSet.getInt("categoryId"));
                toAdd.setAuthor(resultSet.getString("author"));
                toAdd.setViewCount(resultSet.getInt("viewCount"));
                toAdd.setCreationDate(resultSet.getDate("creationDate"));
                toAdd.setContent(resultSet.getString("content"));
                toAdd.setTitle(resultSet.getString("title"));

                posts.add(toAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return posts;
    }

    @Override
    public List<Post> postsOnPage(Integer pageNum) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Post> posts = new ArrayList<>();

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM posts ORDER BY creationDate DESC LIMIT 5 OFFSET ?");
            preparedStatement.setInt(1, (pageNum - 1) * 5);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post =new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("viewCount"),
                        resultSet.getString("author"));
                posts.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return posts;
    }

    @Override
    public void incrementViews(Integer postId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE posts SET viewCount = viewCount + 1 WHERE id = ?");
            preparedStatement.setInt(1, postId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

    }

    @Override
    public List<Post> findMostRecentPosts() {
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM posts ORDER BY creationDate DESC");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post =new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("viewCount"),
                        resultSet.getString("author"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return posts;
    }

    @Override
    public List<Post> findMostReadMonthlyPosts() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Post> posts = new ArrayList<>();

        try {
            connection = newConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM posts WHERE creationDate BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW()  ORDER BY viewCount DESC LIMIT 10"
            );
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post toAdd = new Post();
                toAdd.setId(resultSet.getInt("id"));
                toAdd.setCategoryId(resultSet.getInt("categoryId"));
                toAdd.setAuthor(resultSet.getString("author"));
                toAdd.setViewCount(resultSet.getInt("viewCount"));
                toAdd.setCreationDate(resultSet.getDate("creationDate"));
                toAdd.setContent(resultSet.getString("content"));
                toAdd.setTitle(resultSet.getString("title"));

                posts.add(toAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return posts;
    }

    @Override
    public List<Post> postByCatByPage(Integer categoryId, Integer pageNum) {
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM posts  WHERE categoryId = ? ORDER BY creationDate ASC LIMIT 5 OFFSET ?");
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, (pageNum - 1) * 5);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post =new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("creationDate"),
                        resultSet.getInt("viewCount"),
                        resultSet.getString("author"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return posts;
    }
}
