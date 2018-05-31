
package aula21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaDriverManager {
    public static void main(String[] args) {
        //carrega o drive, conecta, cria o comando para conexao, executa o camando,
        //fechar conexao//Receita de bolo
        try {
            Class.forName("com.mysql.jdbc.Driver");//Primeria coisa pra fazer
            //add a biblioteca Driver JDBC 
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/academico", "root", "");
            String sql = "INSERT INTO ALUNOS(NOME, EMAIL, DATA_NASC) "
                    + "VALUES('JOSE', 'JOSE@IESB.BR', '01/01/2000')";
            Statement comando = conexao.createStatement();
//            comando.executeUpdate(sql);
            sql = "INSERT INTO ALUNOS(NOME, EMAIL, DATA_NASC) VALUES (?,?,?)";
            PreparedStatement insert = conexao.prepareStatement(sql);//melhor usar ele pra não da merda depois
            insert.setString(1, "MARIA");
            insert.setString(2, "MARIA@IESB.BR");
            insert.setString(3, "10/11/1998");
//            insert.execute();

            sql = "SELECT * FROM ALUNOS";
            ResultSet resultado = comando.executeQuery(sql);
            while(resultado.next()){
                System.out.println("Id: " + resultado.getInt(1));
                System.out.println("Nome: " + resultado.getString(2));
                System.out.println("Email: " + resultado.getString(3));
                System.out.println("DataNasc: " + resultado.getString(4) + "\n");
            }
            conexao.close();//fechar a conexao 
        } catch (ClassNotFoundException ex) {
            System.err.println("Não foi possível carregar o drive JDBC. Motivo:" + ex.getMessage());//err no System significa mensagem de erro
        } catch (SQLException ex) {
            System.err.println("Erro ao chamar MySQL. Motivo:" + ex.getMessage());//usar o getMessage() para detalhar melhor o erro
        }
    }
}
