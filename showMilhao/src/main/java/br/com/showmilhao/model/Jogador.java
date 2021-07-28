package br.com.showmilhao.model;

public class Jogador {
		
		private Integer id; //com Integer, consigo usar NULL, com INT não.
		private String nome;
		private Integer pontuacao;//com Integer, consigo usar NULL, com INT não.
		
		private Integer linha;
		
		public Jogador() {//construtor para iniciar os valores
			nome = "";//deixamos vazio para nao ir como null
			linha = 0;
			pontuacao = 0;
		}
		
		public Jogador(String nome, Integer pontuacao) {
			this.nome = nome;
			this.pontuacao = pontuacao;
		}
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Integer getPontuacao() {
			return pontuacao;
		}

		public void setPontuacao(Integer pontuacao) {
			this.pontuacao = pontuacao;
		}

		public Integer getLinha() {
			return linha;
		}

		public void setLinha(Integer linha) {
			this.linha = linha;
		}

		@Override
		public String toString() {
			return "Jogador [id=" + id + ", nome=" + nome + ", pontuacao=" + pontuacao + ", linha=" + linha + "]";
		}	
		
		

}
