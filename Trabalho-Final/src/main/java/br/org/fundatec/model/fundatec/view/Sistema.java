package br.org.fundatec.model.fundatec.view;


import br.org.fundatec.model.fundatec.controller.EmpresaController;
import br.org.fundatec.util.TecladoUtil;

public class Sistema {

    private static EmpresaController controller = new EmpresaController();
    private static boolean sair = false;



    public static void main(String[] args) {
        while (!sair) {
            menu();
            int opcao = TecladoUtil.lerInteiro("Informa uma Opcao:");
            executaAcao(opcao);
        }
    }


    private static void executaAcao(int opcao) {
        try {
            switch (opcao) {
                case 1:
                    inserirAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    listarMaterias();
                    break;
                case 4:
                    cadastrarNota();
                    break;
                case 5:
                    listarNotas();
                    break;
                case 6:
                    sair = true;
                    break;
                default:
                    System.out.println("Opcao invalida!!");
                    break;
            }
        }catch (AplicacaoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inserirAluno() throws  AplicacaoException{
        String nome = TecladoUtil.lerString("Informe um nome:");
        String materia = TecladoUtil.lerString("Informe uma materia:");
        Integer nota = TecladoUtil.lerInteiro("Informe a nota:");

        controller.inserirAluno(nome, materia, nota);

        System.out.println("Aluno inserido!");
    }

    private static  void  listarAlunos() {
        System.out.println(controller.listarAlunos());
    }

    private static  void  listarMaterias() {
        System.out.println(controller.listarMaterias());
    }


    private static  void cadastrarNota() throws  AplicacaoException {
        Integer idAluno = TecladoUtil.lerInteiro("Informa e o codigo do Aluno:");
        String materia = TecladoUtil.lerString("Informa o nome da matéria: ");
        Integer nota = TecladoUtil.lerInteiro("Informa a nota");

        controller.inserirNota(idAluno, materia, nota);
        System.out.println("Nota cadastrada!");
    }

    private static  void  listarNotas() throws  AplicacaoException{
        Integer idAluno = TecladoUtil.lerInteiro("Informa e o codigo do Aluno:");
        System.out.println(controller.listarNotas(idAluno));
    }

    private static void menu() {
        System.out.println("________________________");
        System.out.println("(1) Cadastrar Aluno");
        System.out.println("(2) Listar Alunos");
        System.out.println("(3) Listar Materias");
        System.out.println("(4) Cadastrar nota");
        System.out.println("(5) Listar Notas por Aluno");
        System.out.println("(6) Sair");
        System.out.println("________________________");
    }
}
