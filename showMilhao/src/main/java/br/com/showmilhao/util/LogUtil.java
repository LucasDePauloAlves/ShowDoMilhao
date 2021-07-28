package br.com.showmilhao.util;
import org.apache.log4j.Logger;

public class LogUtil {

	//construtor private para bloquear a instancia��o dessa classe
	private LogUtil() { }
	
	
	public static Logger getLogger(Object object) { //m�todo generico, pois n�o sei em qual classe vou usar, por isso Object.
		return Logger.getLogger(object.getClass()); // retorna a descri��o da classe utilizada em tempo de execu��o.
				
	}
}
