package principal;

import dao.*;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elias
 */
public class TrabalhoJPA {

    private final ConnectionDB connection;

    public static void main(String[] args) {
        try {
            TrabalhoJPA main = new TrabalhoJPA();
            main.disconnect();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public TrabalhoJPA() {
        connection = new ConnectionDB();
        connection.connect();

        cadastrarUsuarios();
        cadastrarLivros();
        cadastrarAvaliacoes();
        
        LivroDAO livroDAO = new LivroDAO();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        EditoraDAO editoraDAO = new EditoraDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AutorDAO autorDAO = new AutorDAO();
        
        //1
        System.out.println("Liste todos os livros.");
        livroDAO.findAll().forEach(System.out::println);
        
        //2
        System.out.println("Dado um nome de pa´ıs X, a quantidade de usu´arios que avaliaram pelo menos 2 livros");
        System.out.println(avaliacaoDAO.findByLocal("nyc, new york, usa").size());
        
        //3
        System.out.println("Liste os t´ıtulos dos livros que brasileiros avaliaram e as suas notas.");
        avaliacaoDAO.findByLocal("rio de janeiro, rio de janeiro, brazil").forEach(System.out::println);
        
        //4
        System.out.println("Liste os t´ıtulos dos X livros livros mais bem avaliados no ano Y.");
        avaliacaoDAO.findByAvaliacao(2001).stream()
                .sorted(Comparator.comparing(Avaliacao::getNota))
                .limit(6).forEach((t) -> {
            System.out.println(t.getLivro());
        });
        
        //5
        System.out.println("Para cada autor, liste o n´umero de livros avaliados e sua m´edia de nota.");
        autorDAO.findAll().forEach((t) -> {
            System.out.println(t.getNome() + ": " + t.getLivros());
        });
        
        //6
        System.out.println("Para cada editora, o n´umero de livros cadastrados.");
        editoraDAO.findAll().forEach((t) -> {
            System.out.println(t.getNome() + ": " + t.getLivros().size());
        });
    }

    private void disconnect() throws SQLException {
        connection.disconnect();
    }

    private void cadastrarLivros() {
        String arquivoCSV = "BX-Books.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));
            int i = 0;
            while ((linha = br.readLine()) != null) {
                if (i <= 1000) {
                    String[] livro = linha.split(csvDivisor);

                    //Cadastrado Editora
                    String editora = livro[4].replace("\"", "");
                    EditoraDAO editoraDAO = new EditoraDAO();
                    Editora e = editoraDAO.salvar(new Editora(editora));

                    //Cadastrando Autor
                    String autor = livro[2].replace("\"", "");
                    AutorDAO autorDAO = new AutorDAO();
                    Autor a = autorDAO.salvar(new Autor(autor));

                    //Cadastrando Livro
                    String isbn = livro[0].replace("\"", "");
                    String titulo = livro[1].replace("\"", "");
                    String ano = livro[3].replace("\"", "");

                    Livro l = new Livro();
                    l.setIsbn(isbn);
                    l.setTitulo(titulo);
                    l.setAutor(a);
                    l.setEditora(e);
                    try {
                        l.setAno(Integer.parseInt(ano));
                    } catch (Exception ex) {
                    }

                    LivroDAO livroDAO = new LivroDAO();
                    livroDAO.salvar(l);
                    System.out.println("Salvo com sucesso!\t" + l);

                    i++;
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

    private void cadastrarUsuarios() {
        String arquivoCSV = "BX-Users.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));
            int i = 0;
            while ((linha = br.readLine()) != null) {
                if (i <= 1000) {
                    String[] lineUsuario = linha.split(csvDivisor);

                    //Cadastrando Livro
                    String id = lineUsuario[0].replace("\"", "");
                    String location = lineUsuario[1].replace("\"", "");
                    String idade = lineUsuario[2].replace("\"", "");

                    Usuario usuario = new Usuario();
                    usuario.setId(id);
                    usuario.setLocal(location);
                    try {
                        usuario.setIdade(Integer.parseInt(idade));
                    } catch (Exception e) {
                    }

                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuarioDAO.salvar(usuario);
                    System.out.println("Salvo com sucesso!\t" + usuario);

                    i++;
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

    private void cadastrarAvaliacoes() {
        String arquivoCSV = "BX-Book-Ratings.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));
            int i = 0;
            while ((linha = br.readLine()) != null) {
                if (i <= 1000) {
                    String[] avaliacao = linha.split(csvDivisor);

                    //Cadastrando Livro
                    String usuario = avaliacao[0].replace("\"", "");
                    String livro = avaliacao[1].replace("\"", "");
                    String nota = avaliacao[2].replace("\"", "");

                    LivroDAO livroDAO = new LivroDAO();
                    UsuarioDAO usuarioDAO = new UsuarioDAO();

                    Avaliacao a = new Avaliacao();
                    a.setLivro(livroDAO.findById(livro));
                    a.setUsuario(usuarioDAO.findById(usuario));
                    try {
                        a.setNota(Double.parseDouble(nota));
                        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
                        avaliacaoDAO.salvar(a);
                        System.out.println("Salvo com sucesso!\t" + a);
                    } catch (Exception e) {
                    }

                    i++;
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
