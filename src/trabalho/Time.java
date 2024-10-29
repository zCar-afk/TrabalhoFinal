package trabalho;

public class Time {
	private String nome;
	private int pontos;
	private int saldoGols;

	public Time(String nome) {
		this.nome = nome;
		this.pontos = 0;
		this.saldoGols = 0;
	}

	public String getNome() {
		return nome;
	}

	public int getPontos() {
		return pontos;
	}

	public void adicionarPontos(int pontos) {
		this.pontos += pontos;
	}

	public int getSaldoGols() {
		return saldoGols;
	}

	public void adicionarGols(int saldo) {
		this.saldoGols += saldo;
	}
}
