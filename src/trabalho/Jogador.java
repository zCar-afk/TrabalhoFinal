package trabalho;

public class Jogador {
	private String nome;
	private int fichas;
	private int pontuacao;

	public Jogador(String nome) {
		this.nome = nome;
		this.fichas = 50;
		this.pontuacao = 0;
	}

	public String getNome() {
		return nome;
	}

	public int getFichas() {
		return fichas;
	}

	public void apostarFichas(int fichasApostadas) {
		this.fichas -= fichasApostadas;
	}

	public void adicionarPontuacao(int pontos) {
		this.pontuacao += pontos;
	}

	public int getPontuacao() {
		return pontuacao;
	}
}
