/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import dao.AutorDAO;
import dao.AvaliacaoDAO;
import dao.ConnectionDAO;
import dao.EditoraDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import model.Autor;
import model.Avaliacao;
import model.Editora;
import model.Livro;
import model.Usuario;

/**
 *
 * @author elias
 */
public class TrabalhoJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        new TrabalhoJDBC();
    }

    public TrabalhoJDBC() throws SQLException {
        Connection db = new ConnectionDAO().getConnection();

        EditoraDAO editoraDAO = new EditoraDAO();
        editoraDAO.create(db);

        AutorDAO autorDAO = new AutorDAO();
        autorDAO.create(db);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.create(db);

        LivroDAO livroDAO = new LivroDAO();
        livroDAO.create(db);

        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.create(db);

        //cadastrarLivros(db);
        //cadastrarUsuarios(db);
        //cadastrarAvaliacoes(db);

        //1
       // livroDAO.buscarTodos(db).forEach(System.out::println);
        
        //2

        //3
        avaliacaoDAO.avaliacaoBrasileiros(db);

        //4
        avaliacaoDAO.melhoresAvaliacoes(2001, 10, db);

        //5
        avaliacaoDAO.avaliacaoPorAutor(db);

        //6
        editoraDAO.quantidadePorEditora(db);

        //7
        livroDAO.livrosPorAvaliacao(db);
        
        //8
        avaliacaoDAO.livrosComMediaMaiorQue(6, 10, db);
        
        //9
        avaliacaoDAO.mediaIdade(db);
        
        //10
        avaliacaoDAO.avaliacaoPorIdade(2001, db);
    }

    public void cadastrarLivros(Connection db) {
        String arquivoCSV = "BX-Books.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));
            boolean first = true;
            while ((linha = br.readLine()) != null) {
                if (!first) {
                    String[] livro = linha.split(csvDivisor);

                    //Cadastrado Editora
                    String editora = livro[4].replace("\"", "");
                    EditoraDAO editoraDAO = new EditoraDAO();
                    editoraDAO.salvar(new Editora(editora), db);

                    //Cadastrando Autor
                    String autor = livro[2].replace("\"", "");
                    AutorDAO autorDAO = new AutorDAO();
                    autorDAO.salvar(new Autor(autor), db);

                    //Cadastrando Livro
                    String isbn = livro[0].replace("\"", "");
                    String titulo = livro[1].replace("\"", "");
                    String ano = livro[3].replace("\"", "");

                    Livro l = new Livro();
                    l.setIsbn(isbn);
                    l.setTitulo(titulo);
                    l.setAutor(autor);
                    l.setEditora(editora);
                    try {
                        l.setAno(Integer.parseInt(ano));
                    } catch (Exception e) {
                    }

                    LivroDAO livroDAO = new LivroDAO();
                    livroDAO.salvar(l, db);
                    System.out.println("Salvo com sucesso!\t" + l);
                } else {
                    first = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void cadastrarUsuarios(Connection db) {
        String arquivoCSV = "BX-Users.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));
            boolean first = true;
            while ((linha = br.readLine()) != null) {
                if (!first) {
                    String[] usuario = linha.split(csvDivisor);

                    //Cadastrando Livro
                    String id = usuario[0].replace("\"", "");
                    String location = usuario[1].replace("\"", "");
                    String idade = usuario[2].replace("\"", "");

                    Usuario i = new Usuario();
                    i.setId(id);
                    i.setLocal(location);
                    try {
                        i.setIdade(Integer.parseInt(idade));
                    } catch (Exception e) {
                    }

                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuarioDAO.salvar(i, db);
                    System.out.println("Salvo com sucesso!\t" + i);
                } else {
                    first = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void cadastrarAvaliacoes(Connection db) {
        String arquivoCSV = "BX-Book-Ratings.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));
            boolean first = true;
            while ((linha = br.readLine()) != null) {
                if (!first) {
                    String[] avaliacao = linha.split(csvDivisor);

                    //Cadastrando Livro
                    String usuario = avaliacao[0].replace("\"", "");
                    String livro = avaliacao[1].replace("\"", "");
                    String nota = avaliacao[2].replace("\"", "");

                    Avaliacao a = new Avaliacao();
                    a.setLivro(livro);
                    a.setUsuario(usuario);
                    try {
                        a.setNota(Double.parseDouble(nota));
                        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
                        avaliacaoDAO.salvar(a, db);
                        System.out.println("Salvo com sucesso!\t" + a);
                    } catch (Exception e) {
                    }
                } else {
                    first = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
