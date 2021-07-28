package br.com.showmilhao.application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import br.com.showmilhao.util.LogUtil;
import javazoom.jl.player.Player;


//Classe responsavel pelo audio.
public class JLayer extends Thread{

	private File mp3;
	
	public void tocar(File mp3) {
		this.mp3 = mp3;
	}
	
	@Override
	public void run() {
		
		try {
			try(
				//Classe FileInputStream, variavel fileStream obtem bytes de entrada de um arq.
				//destina a leitura de fluxos de bytes,como dados de img. mp3. mp4.
				FileInputStream fileStream = new FileInputStream(mp3)){
				//Classe BufferdInputStream, armazena a entrada dos bytes gravados em fileStream.
				BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
				//A classe Player implementa um reprodutor simples para reprodução de um fluxo de áudio MPEG.
				Player player = new Player(bufferedStream); // API JLayer - uma lib. le o que esta na memoria.
				player.play(); 
				
				if(player.isComplete()) { //verdadeiro se todos os quadros de áudio MPEG disponíveis... 
					//tiverem sido decodificados ou falso caso contrário.
					player.close();
				}
			} 
		} catch (Exception e) {
			//Método getLogger da Classe LogUtil, verifica o erro de excessão e lança.
			LogUtil.getLogger(JLayer.class).error(e.getCause().toString());
			
		}
		
	}
	
}
