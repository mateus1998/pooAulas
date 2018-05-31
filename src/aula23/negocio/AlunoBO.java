
package aula23.negocio;

import aula22.dados.AlunoDAO;
import aula22.dados.DadosException;
import aula22.entidades.Aluno;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoBO implements BO<Aluno>{

    @Override
    public void validar(Aluno entidade) throws NegocioException {
        if(entidade.getNome().isEmpty()){
            throw new NegocioException("O campo nome é obrigátoria");
        }
        
        if(entidade.getEmail().isEmpty()){
            throw new NegocioException("O campo email é obrigatório");
        }
    }

    @Override
    public void inserir(Aluno entidade) throws NegocioException {
        validar(entidade);
        AlunoDAO dao = new AlunoDAO();
        try {
            dao.inserir(entidade);
        } catch (DadosException ex) {
            throw new NegocioException("Falha na operação", ex);
        }
        
    }

    @Override
    public void alterar(Aluno entidade) throws NegocioException {
        consultar(entidade.getId());
        validar(entidade);
        AlunoDAO dao = new AlunoDAO();
        try {
            dao.alterar(entidade);
        } catch (DadosException ex) {
            throw new NegocioException("Falha na operação", ex);
        }
    }

    @Override
    public void excluir(Aluno entidade) throws NegocioException {
        consultar(entidade.getId());
        AlunoDAO dao = new AlunoDAO();
        try {
            dao.excluir(entidade);
        } catch (DadosException ex) {
            throw new NegocioException("Falha na operação", ex);
        }
    }

    @Override
    public Aluno consultar(int id) throws NegocioException {
        AlunoDAO dao = new AlunoDAO();
        try {
            Aluno aluno = dao.consultar(id);
            if(aluno.getId() == 0){
                throw new NegocioException("Aluno não encontrado");
            }
            return aluno;
        } catch (DadosException ex) {
            throw new NegocioException("Falha na operação", ex);
        }
    }

    @Override
    public List<Aluno> listar() throws NegocioException {
        AlunoDAO dao = new AlunoDAO();
        try {
            List<Aluno> lista = dao.listar();
            if(lista.isEmpty()){
                throw new NegocioException("Nenhum aluno cadastrado");
            }
            return lista;
        } catch (DadosException ex) {
            throw new NegocioException("Falha na operação", ex);
        }
    }
    
}
