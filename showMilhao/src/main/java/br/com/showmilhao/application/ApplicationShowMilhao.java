package br.com.showmilhao.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static final String FILE_MUSIC = "src/main/resources/songs/som-abertura-2.mp3";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Show do Milhão");
			
			Pane telaInicial = FXMLLoader.load(getClass().getResource("/views/LayoutTelaInicial.fxml"));
			Scene scene = new Scene(telaInicial,800,600);
			telaInicial.getStylesheets().add(getClass().getResource("/css/buttonStyle.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//Aqui não estou usando a classe API jLayer, somente a repeticao na classe Continuos
			//e passando o caminho, a path aqui mesmo no main.
			
			ContinuosReprodution reprodution = new ContinuosReprodution(FILE_MUSIC, true);
		    reprodution.start(); // inicializa a reprodução do mp3.
			
//			JLayer layer = new JLayer();
//			File mp3 = new File("src/main/resources/songs/tire-a-carta-do-baralho-voice.mp3");
//			layer.tocar(mp3); // digo na classe JLayer qual o arquivo de dados do meu diretorio.
//			layer.start();// agora dou um start para reproduzir.
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
