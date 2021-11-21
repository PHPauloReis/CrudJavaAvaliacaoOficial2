package DAO;

import Entites.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {
    private Connection connection;

    public BookDAO() {
        String url = "jdbc:mysql://localhost/oficial2POO";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<Book> selectAll() throws SQLException {
        String sql = "SELECT * FROM books";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Book> booksList = new ArrayList<>();

        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setSummary(resultSet.getString("summary"));
            book.setAuthor(resultSet.getString("author"));
            book.setPublishingCompany(resultSet.getString("publishing_company"));
            book.setCategory(resultSet.getString("category"));

            booksList.add(book);
        }

        return booksList;
    }

    public Book findById(int id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        Book book = new Book();

        if (resultSet.next()) {
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setSummary(resultSet.getString("summary"));
            book.setAuthor(resultSet.getString("author"));
            book.setPublishingCompany(resultSet.getString("publishing_company"));
            book.setCategory(resultSet.getString("category"));
        }

        return book;
    }

    public void insert(Book book) throws SQLException {
        String sql = "INSERT INTO books (title, summary, author, publishing_company, category) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getSummary());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setString(4, book.getPublishingCompany());
        preparedStatement.setString(5, book.getCategory());

        preparedStatement.executeUpdate();
    }

    public void update(Book book, int id) throws SQLException {
        String sql = "UPDATE books SET title = ?, summary = ?, author = ?, publishing_company = ?, category = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getSummary());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setString(4, book.getPublishingCompany());
        preparedStatement.setString(5, book.getCategory());
        preparedStatement.setInt(6, book.getId());

        preparedStatement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }
}
