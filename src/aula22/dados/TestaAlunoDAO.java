
package aula22.dados;

import aula22.entidades.Aluno;
import java.util.List;

public class TestaAlunoDAO {

    public static void main(String[] args) throws DadosException {
        AlunoDAO dao = new AlunoDAO();

        List<Aluno> lista = dao.listar();
        for (Aluno aluno : lista) {
            System.out.println(aluno);
        }

        Aluno novo = new Aluno();
        novo.setNome("Antonio");
        novo.setEmail("Antonio@iesb.br");
        novo.setDataNascimento("01/01/1999");

//    dao.inserir(novo);
        System.out.println("----------------------");

        lista = dao.listar();

        for (Aluno aluno : lista) {
            System.out.println(aluno);
        }
        Aluno consulta = dao.consultar(2);

        System.out.println("----------------------");

        System.out.println(consulta);
        
        dao.excluir(consulta);
        
         System.out.println("----------------------");
    }
}
