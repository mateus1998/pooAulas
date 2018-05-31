package aula22.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    public static Connection getConexao() throws DadosException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/academico", "root", "iesb");
        } catch (ClassNotFoundException ex) {
            throw new DadosException("Erro ao carregar driver JDBC", ex);
        } catch (SQLException ex) {
            throw new DadosException("Erro ao conectar no Mysql", ex);
        }
    }
}
