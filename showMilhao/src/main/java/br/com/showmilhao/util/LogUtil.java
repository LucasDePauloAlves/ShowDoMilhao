package br.com.showmilhao.util;
import org.apache.log4j.Logger;

public class LogUtil {

	//construtor private para bloquear a instanciação dessa classe
	private LogUtil() { }
	
	
	public static Logger getLogger(Object object) { //método generico, pois não sei em qual classe vou usar, por isso Object.
		return Logger.getLogger(object.getClass()); // retorna a descrição da classe utilizada em tempo de execução.
				
	}
}
