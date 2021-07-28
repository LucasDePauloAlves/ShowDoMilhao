package br.com.showmilhao.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.showmilhao.util.LogUtil;

public class ConnectionFactory {
		
		private static final String URL_CONNECTION = "jdbc:sqlite:src/main/resources/data/show_milhao.db";
		
		private static Connection connection;
		
		private ConnectionFactory() {}
		
		//static para privar a instancia dessa classe.
		static {
			conectar();
		}

		private static void conectar() {
			try {
				if (connection == null) {
					connection = DriverManager.getConnection(URL_CONNECTION);//Class carrega os drivers disposniveis e tenta realizar a conexão a partir da URL.
					connection.setAutoCommit(false);//diz para nao realizar alteração no banco, deixar por conta do "JogadorDAO".
				}
			} catch (Exception e) {
				LogUtil.getLogger(ConnectionFactory.class).error(e.getCause().toString());
			}
		}
		
		public static Connection getConnection() {
			return connection;
		}

}
