package com.example.backend.repository;

import com.example.backend.entities.tag.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagRepositoryImpl extends MySqlAbstractRepository implements TagRepository{

    @Override
    public Tag addTag(Tag tag1) {

        List<Tag> tags = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String[] generatedColumns = {"id"};
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tag WHERE keyword = ?");
            preparedStatement.setString(1, tag1.getKeyword());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tag1.setId(resultSet.getInt("id"));
                return tag1;
            }

            preparedStatement = connection.prepareStatement("INSERT INTO tag (keyword) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, tag1.getKeyword());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) tag1.setId(resultSet.getInt(1));

//         String date = "01/05/2022"

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag1;
    }

    @Override
    public List<Tag> allTags() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Tag> tags = new ArrayList<>();

        try {
            connection = newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM tag");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tags.add(new Tag(
                        resultSet.getInt("id"),
                        resultSet.getString("keyword")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return tags;
    }

    @Override
    public List<Tag> tagsFromArticle(Integer postId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Integer> tagIds = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tag_posts WHERE postId = ?");
            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tagIds.add(resultSet.getInt("id"));
            }

            for (Integer tagId: tagIds) {
                preparedStatement = connection.prepareStatement("SELECT * FROM tag WHERE id = ?");
                preparedStatement.setInt(1, tagId);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    tags.add(new Tag(
                            resultSet.getInt("id"),
                            resultSet.getString("keyword")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return tags;
    }

    @Override
    public void addTagsArticles(Integer tagId, Integer postId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("SELECT * FROM tag_posts WHERE id = ? AND postId = ?");
            preparedStatement.setInt(1, tagId);
            preparedStatement.setInt(2, postId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return;
            }

            preparedStatement = connection.prepareStatement("INSERT INTO tag_posts (postId, tagId) VALUES(?,?)", generatedColumns);
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, tagId);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }
}
