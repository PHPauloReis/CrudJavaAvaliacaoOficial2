package Views;

import DAO.BookDAO;
import Entites.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BookCRUD extends JFrame {
    private JPanel mainPanel;
    private JLabel newBookLabel;
    private JButton newBookBtn;
    private JButton editButton;
    private JButton deleteButton;
    private JTable table1;
    private JScrollPane scrollpane;
    private JTextField titleTextField;
    private JTextField authorTextField;
    private JTextField summaryTextField;
    private JTextField publishingCompanyTextField;
    private JTextField categoryTextField;
    private JButton saveButton;
    private JButton resetButton;

    private Integer selectedBookId;

    public BookCRUD(String title) {
        super(title);

        resetButton.setVisible(false);
        loadTableData();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectionModel().isSelectionEmpty()) {
                    showMessageDialog("Selecione um registro clicando no mesmo, e depois clique em excluir!", "Ops!");
                    return;
                }

                int confirmationStatus = showConfirmationDialog("Você tem certeza que deseja remover este registro?", "Cuidado!");
                int selectedItem = (int) (table1.getValueAt(table1.getSelectedRow(), 0));

                if (confirmationStatus == 0) {
                    BookDAO bookDAO = new BookDAO();
                    try {
                        bookDAO.delete(selectedItem);
                        loadTableData();
                        showMessageDialog("Registro excluído com sucesso!", "Sucesso!");
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {

                    if (selectedBookId == null) {
                        if (createBook()) {
                            showMessageDialog("Registro gravado com sucesso!", "Sucesso!");
                        }
                    } else {
                        if (updateBook()) {
                            showMessageDialog("Registro atualizado com sucesso!", "Sucesso!");
                        }
                    }

                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectionModel().isSelectionEmpty()) {
                    showMessageDialog("Selecione um registro clicando no mesmo, e depois clique em editar!", "Ops!");
                    return;
                }

                selectedBookId = (int) (table1.getValueAt(table1.getSelectedRow(), 0));

                BookDAO bookDAO = new BookDAO();
                try {
                    Book book = bookDAO.findById(selectedBookId);

                    titleTextField.setText(book.getTitle());
                    authorTextField.setText(book.getAuthor());
                    summaryTextField.setText(book.getSummary());
                    publishingCompanyTextField.setText(book.getPublishingCompany());
                    categoryTextField.setText(book.getCategory());

                    resetButton.setVisible(true);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
                selectedBookId = null;
                resetButton.setVisible(false);
            }
        });
    }

    protected boolean createBook() {
        Book book = new Book();
        book.setTitle(titleTextField.getText());
        book.setSummary(summaryTextField.getText());
        book.setAuthor(authorTextField.getText());
        book.setPublishingCompany(publishingCompanyTextField.getText());
        book.setCategory(categoryTextField.getText());;

        BookDAO bookDAO = new BookDAO();

        try {
            bookDAO.insert(book);
            clearForm();
            loadTableData();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    protected boolean updateBook() {
        BookDAO bookDAO = new BookDAO();

        try {
            Book book = bookDAO.findById(selectedBookId);
            book.setTitle(titleTextField.getText());
            book.setSummary(summaryTextField.getText());
            book.setAuthor(authorTextField.getText());
            book.setPublishingCompany(publishingCompanyTextField.getText());
            book.setCategory(categoryTextField.getText());

            bookDAO.update(book, selectedBookId);
            loadTableData();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    protected void showMessageDialog(String message, String title) {
        JFrame frame = new JFrame(title);
        JOptionPane.showMessageDialog(frame, message);
    }

    protected int showConfirmationDialog(String message, String title) {
        JFrame frame = new JFrame(title);
        return JOptionPane.showConfirmDialog(frame, message);
    }

    public void loadTableData() {
        DefaultTableModel bookModel = new DefaultTableModel();
        bookModel.addColumn("id");
        bookModel.addColumn("Nome");
        bookModel.addColumn("Autor");
        bookModel.addColumn("Categoria");

        BookDAO bookDAO = new BookDAO();

        try {
            for (Book book : bookDAO.selectAll()) {
                bookModel.addRow(new Object[] {
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getCategory()
                });
            }

            table1.setModel(bookModel);
            table1.getColumnModel().getColumn(0).setMaxWidth(30);
            table1.getColumnModel().getColumn(2).setMaxWidth(500);
            table1.getColumnModel().getColumn(3).setMaxWidth(500);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    protected void clearForm() {
        titleTextField.setText("");
        summaryTextField.setText("");
        authorTextField.setText("");
        publishingCompanyTextField.setText("");
        categoryTextField.setText("");
    }

    protected boolean validateForm() {
        // Valida se o título do livro foi informado
        if (titleTextField.getText().isEmpty()) {
            showMessageDialog("Você esqueceu de informar o título do livro!", "Ops!");
            titleTextField.requestFocus();

            return false;
        }

        // Valida se o autor do livro foi informado
        if (authorTextField.getText().isEmpty()) {
            showMessageDialog("Você esqueceu de informar o autor do livro!", "Ops!");
            authorTextField.requestFocus();

            return false;
        }

        // Valida se o resumo do livro foi informado
        if (summaryTextField.getText().isEmpty()) {
            showMessageDialog("Você esqueceu de informar o resumo do livro!", "Ops!");
            summaryTextField.requestFocus();

            return false;
        }

        // Valida se a editora do livro foi informado
        if (publishingCompanyTextField.getText().isEmpty()) {
            showMessageDialog("Você esqueceu de informar a editora do livro!", "Ops!");
            publishingCompanyTextField.requestFocus();

            return false;
        }

        // Valida se a categoria do livro foi informado
        if (categoryTextField.getText().isEmpty()) {
            showMessageDialog("Você esqueceu de informar a categoria do livro!", "Ops!");
            categoryTextField.requestFocus();

            return false;
        }

        return true;
    }
}
