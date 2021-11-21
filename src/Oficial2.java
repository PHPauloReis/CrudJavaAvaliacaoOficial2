/*
* Unime - Lauro de Freitas
* Bacharelado em Sistemas de Informação
* Programação Orientada a Obejtos II
* Pablo Ricardo Roxo Silva
* Ítalo Nascimento e Paulo Reis
*/

import Views.BookCRUD;

import javax.swing.*;
import java.awt.*;

public class Oficial2 {
    public static void main(String[] args) {
        JFrame bookListing = new BookCRUD("Cadastro de Livros");
        bookListing.setPreferredSize(new Dimension(800, 600));
        bookListing.pack();
        bookListing.setResizable(false);
        bookListing.setVisible(true);
    }
}
