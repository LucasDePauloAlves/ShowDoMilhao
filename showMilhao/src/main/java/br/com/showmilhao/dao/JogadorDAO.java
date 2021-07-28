package br.com.showmilhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.showmilhao.connection.ConnectionFactory;
import br.com.showmilhao.model.Jogador;
import br.com.showmilhao.util.LogUtil;

public class JogadorDAO {

	// camada de acesso a dados.

	private Connection connection;
	
	private final static String QUERY_ZERAR_RANKING = "DELETE FROM jogador";
	private final static String QUERY_UPDATE = "UPDATE jogador SET nome = ?, pontuacao = ? WHERE id = ?"; //Atualize o nome e a pontuação where(onde)?! No id.
	private final static String QUERY_INSERT = "INSERT INTO jogador (id, nome, pontuacao) VALUES ($next_id, ?, ?)";
	private final static String QUERY_CONSULTAR_TODOS = "SELECT * FROM jogador";
	private final static String QUERY_LISTAR_RANKING = "SELECT * FROM jogador ORDER BY pontuacao DESC LIMIT 10";
	
	public JogadorDAO() {

		connection = ConnectionFactory.getConnection();

	}

	public boolean adicionar(Jogador jogador) {
		//Interface PreparedStatement, pré compila o SQL e o armazena em um objeto.
		//cria um objeto para levar instruções SQL ao BD
		try (PreparedStatement stmt = connection.prepareStatement(QUERY_INSERT)) {
			stmt.setString(2, jogador.getNome());
			stmt.setInt(3, jogador.getPontuacao());
			stmt.executeUpdate();//Executa a instrução SQL(DML) no Obj stmt, como: INSERT, UPDATE ou DELETE. Ou DDL que não retorne nada.
			connection.commit();//Autoriza fazer a alteração no banco com os dados informados.
			return true;
		} catch (Exception e) {
			LogUtil.getLogger(JogadorDAO.class).error(e.getCause().toString());
			return false;
		}		
		
	}

	public void atualizar(Jogador jogador) {//void nao retorna nada.		
		//Interface PreparedStatement, pré compila o SQL e o armazena em um objeto.
		//cria um objeto para levar instruções SQL ao BD
		try (PreparedStatement stmt = connection.prepareStatement(QUERY_UPDATE)) {
			stmt.setString(1, jogador.getNome());
			stmt.setInt(2,  jogador.getPontuacao());
			stmt.setInt(3, jogador.getId());
			stmt.executeUpdate();//Executa a instrução SQL(DML) no Obj stmt, como: INSERT, UPDATE ou DELETE. Ou DDL que não retorne nada.
			connection.commit();//Autoriza fazer a alteração no banco com os dados informados. 
		} catch (Exception e) {
			LogUtil.getLogger(JogadorDAO.class).error(e.getCause().toString());
		}		
	}
	
	private List<Jogador> buscar(String sql){//ESTE MÉTODO RETORNA UMA LISTA DE JOGADORES
		
		List<Jogador> jogadores = new ArrayList<>();
		
		try(PreparedStatement stmt = connection.prepareStatement(sql)){
		//Interface PreparedStatement, pré compila o SQL e o armazena em um objeto.
		//cria um objeto para levar instruções SQL ao BD
			try(ResultSet rs = stmt.executeQuery()){
			//executa consulta SQL no Objeto PreparedStatement e retorna o Objeto ResultSet -->
			//que representa um conjunto de resultados de B.Dados que aponta para uma linha de dados,
			//usado para iterar por ex em um bloco while como a seguir.
				while (rs.next()) {
					Jogador jogador = new Jogador();
					jogador.setId(rs.getInt("id"));//passamos a coluna e não o atributo
					jogador.setLinha(rs.getRow());//retorna a linha
					jogador.setNome(rs.getString("nome"));
					jogador.setPontuacao(rs.getInt("pontuacao"));
					jogadores.add(jogador);
				}
			}
		} catch (Exception e) {
			LogUtil.getLogger(JogadorDAO.class).error(e.getCause().toString());
		}
		return jogadores;
	}
	
	public List<Jogador> listar() { //esse método vai buscar todos os jogadores
		return buscar(QUERY_CONSULTAR_TODOS);
	}
	
	public List<Jogador> listarRanking() { //esse método vai buscar todos os jogadores
		return buscar(QUERY_LISTAR_RANKING);
	}
	
	public void zerarRanking() {
		try(PreparedStatement stmt = connection.prepareStatement(QUERY_ZERAR_RANKING)){
			stmt.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			LogUtil.getLogger(JogadorDAO.class).error(e.getCause().toString());
		}
	}
}

























