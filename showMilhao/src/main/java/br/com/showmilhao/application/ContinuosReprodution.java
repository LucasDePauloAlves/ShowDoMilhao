package br.com.showmilhao.application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import br.com.showmilhao.util.LogUtil;
import javazoom.jl.player.Player;

public class ContinuosReprodution extends Thread {
		
		private String fileMusic; //recebera o fully qualified name
		private boolean loop; //variavel lógica de repetição
		
		public ContinuosReprodution(String fileMusic, boolean loop) {
			this.fileMusic = fileMusic; //recebera o fully qualified name
			this.loop = loop; // inicializa como false.
			
		}
		
		@Override
		public void run() {
			
			try { // usamos pq os métodos do JLayer pede pra tratar as excessoes.
			
				do {
					//Vai dar play no arquivo mp3 lido por FileInputStream e armazenado em BufferdInputStream.  
					 new Player(new BufferedInputStream(new FileInputStream(fileMusic))).play();
				 
				} while (loop); // deste modo quer dizer enquanto for verdadeiro.
				
			} catch (Exception e) {
				
				//caso de errado, mostrara a descrição de erro da classe expecificada, no caso: ContinuosReprodution
				//Classe.Método de Captura da (Classe.ContinuosReprodution). ERROR converte o objeto passado como parametro em String. getCause(). retorna a Causa e toString a Descrição.
				LogUtil.getLogger(ContinuosReprodution.class).error(e.getCause().toString());
				
			}
			 
		}

}
