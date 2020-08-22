package br.com.connection;

import java.sql.*;
import javax.swing.JOptionPane;

public class ModuloConexao {

    // método para estabelecer a conexão com o banco
    public static Connection conector() {
        java.sql.Connection conexao = null;
        // chamada do driver 
        String driver = "com.mysql.jdbc.Driver";
        // armazenamento de informações do banco
        String url = "jdbc:mysql://localhost:3306/hidrofire";
        // chamada do usuario do banco
        String login = "root";
        //chamada do password
        String pass = "";
        // chamada da conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, login, pass);
            return conexao;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
;

}
