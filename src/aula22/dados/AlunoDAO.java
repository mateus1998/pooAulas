
package aula22.dados;

import aula22.entidades.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO implements DAO<Aluno> {

    @Override
    public void inserir(Aluno entidade) throws DadosException {
        Connection conexao = ConexaoDB.getConexao();
        String sql = "INSERT INTO ALUNOS(NOME, EMAIL, DATA_NASC) VALUES(?, ?, ?)";
        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, entidade.getNome());
            comando.setString(2, entidade.getEmail());
            comando.setString(3, entidade.getDataNascimento());
            comando.execute();
            conexao.close();
        } catch (SQLException ex) {
            throw new DadosException("Erro ao inserir", ex);
        }
    }

    @Override
    public void alterar(Aluno entidade) throws DadosException {
        Connection conexao = ConexaoDB.getConexao();
        String sql = "UPDATE ALUNOS SET NOME=?, EMAIL=?, DATA_NASC=?) WHERE ID=?";
        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, entidade.getNome());
            comando.setString(2, entidade.getEmail());
            comando.setString(3, entidade.getDataNascimento());
            comando.setInt(4, entidade.getId());
            comando.execute();
            conexao.close();
        } catch (SQLException ex) {
            throw new DadosException("Erro ao alterar", ex);
        }
    }

    @Override
    public void excluir(Aluno entidade) throws DadosException {
        Connection conexao = ConexaoDB.getConexao();
        String sql = "DELETE FROM ALUNOS WHERE ID=?";
        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, entidade.getId());
            comando.execute();
            conexao.close();
        } catch (SQLException ex) {
            throw new DadosException("Erro ao excluir", ex);
        }
    }

    @Override
    public Aluno consultar(int id) throws DadosException {
        Connection conexao = ConexaoDB.getConexao();
        String sql = "SELECT * FROM ALUNOS WHERE ID=?";
        Aluno aluno = new Aluno();
        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            //toda que vez q passar parametros usa PreparedStatement
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                aluno.setId(resultado.getInt(1));
                aluno.setNome(resultado.getString(2));
                aluno.setEmail(resultado.getString(3));
                aluno.setDataNascimento(resultado.getString(4));
            }

            conexao.close();
            return aluno;
        } catch (SQLException ex) {
            throw new DadosException("Erro ao consultar", ex);
        }
    }

    @Override
    public List<Aluno> listar() throws DadosException {
        List<Aluno> lista = new ArrayList<Aluno>();
        Connection conexao = ConexaoDB.getConexao();
        String sql = "SELECT * FROM ALUNOS";
        try {
            Statement comando = conexao.createStatement();
            ResultSet resultado = comando.executeQuery(sql);

            while (resultado.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(resultado.getInt(1));
                aluno.setNome(resultado.getString(2));
                aluno.setEmail(resultado.getString(3));
                aluno.setDataNascimento(resultado.getString(4));
                lista.add(aluno);
            }

            conexao.close();
            return lista;
        } catch (SQLException ex) {
            throw new DadosException("Erro ao listar", ex);
        }
    }

}
