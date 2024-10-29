package trabalho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	private static List<Time> times = new ArrayList<>();
	private static Jogador jogador1;
	private static Jogador jogador2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//aqui se cadastra os jogadores
		System.out.print("[Sistema] Informe o nome do Jogador 1: ");
		jogador1 = new Jogador(sc.nextLine());

		System.out.print("[Sistema] Informe o nome do Jogador 2: ");
		jogador2 = new Jogador(sc.nextLine());

		System.out.println("[Sistema] Seja bem vindo " + jogador1.getNome() + " e " + jogador2.getNome()
				+ ", vocês têm 50 fichas para palpitar nos seus times escolhidos.\n");

		System.out.print("[Sistema] Escolha o número de times para compor o campeonato (4 ou 8): ");
		int numeroTimes = sc.nextInt();
		sc.nextLine();
// Aqui se cadastra os times

		for (int i = 0; i < numeroTimes; i++) {
			System.out.print("[Sistema] Informe o nome do time " + (i + 1) + ": ");
			times.add(new Time(sc.nextLine()));
		}

		realizarPartidas(sc);

		exibirResultadosFinais();
	}

	// parte do sistema que faz tipo uma permutação para que ocorra todas as
	// partidas

	private static void realizarPartidas(Scanner sc) {
		List<String[]> partidas = gerarPartidas();

		for (String[] partida : partidas) {
			Time time1 = buscarTime(partida[0]);
			Time time2 = buscarTime(partida[1]);

			System.out.println("[Sistema] Partida: " + time1.getNome() + " X " + time2.getNome() + "\n");

			obterPalpite(sc, jogador1, time1, time2);

			obterPalpite(sc, jogador2, time1, time2);

			gerarResultado(time1, time2);
		}
	}

	private static List<String[]> gerarPartidas() {
		List<String[]> partidas = new ArrayList<>();
		for (int i = 0; i < times.size(); i++) {
			for (int j = i + 1; j < times.size(); j++) {
				partidas.add(new String[] { times.get(i).getNome(), times.get(j).getNome() });
			}
		}
		return partidas;
	}

	private static Time buscarTime(String nome) {
		for (Time time : times) {
			if (time.getNome().equalsIgnoreCase(nome)) {
				return time;
			}
		}
		return null;
	}

	// Aqui é a parte onde sera questionado os palpites

	private static void obterPalpite(Scanner sc, Jogador jogador, Time time1, Time time2) {
		System.out.println("[Sistema] Palpites para o jogador " + jogador.getNome() + ":");
		System.out.println("[Sistema] 1 - " + time1.getNome() + " X 2 - " + time2.getNome());
		System.out.print("[Sistema] Informe o palpite do time vencedor (1 ou 2): ");
		int palpite = sc.nextInt();
		System.out.print("[Sistema] Informe o número de fichas: ");
		int fichas = sc.nextInt();
		System.out.println();

		jogador.apostarFichas(fichas);
		if ((palpite == 1 && time1 != null) || (palpite == 2 && time2 != null)) {
			jogador.adicionarPontuacao(fichas);
		}
	}

	// resultados gerados aleatórios

	private static void gerarResultado(Time time1, Time time2) {
		int resultado = (int) (Math.random() * 2) + 1;
		int saldoGols = (int) (Math.random() * 3) + 1;

		if (resultado == 1) {

			time1.adicionarPontos(3);
			time1.adicionarGols(saldoGols);
			time2.adicionarGols(-saldoGols);
			System.out.println("[Sistema] " + time1.getNome() + " venceu com saldo de " + saldoGols + " gols.\n");
		} else {

			time2.adicionarPontos(3);
			time2.adicionarGols(saldoGols);
			time1.adicionarGols(-saldoGols);
			System.out.println("[Sistema] " + time2.getNome() + " venceu com saldo de " + saldoGols + " gols.\n");
		}
	}

	// resultados finais

	private static void exibirResultadosFinais() {
		System.out.println("\n--- Resultados Finais ---\n");
		System.out.println("Número de partidas: " + (times.size() * (times.size() - 1)) / 2);
		System.out.println("Jogadores: " + jogador1.getNome() + " e " + jogador2.getNome());
		System.out.println("Times participantes: ");
		for (Time time : times) {
			System.out.println(time.getNome());
		}

		System.out.println("\nPontuações dos times:");
		for (Time time : times) {
			System.out.println(
					time.getNome() + ": " + time.getPontos() + " pontos, Saldo de Gols: " + time.getSaldoGols());
		}

		System.out.println("\nPontuações dos jogadores:");
		System.out.println(jogador1.getNome() + ": " + jogador1.getPontuacao() + " pontos");
		System.out.println(jogador2.getNome() + ": " + jogador2.getPontuacao() + " pontos");

		if (jogador1.getPontuacao() > jogador2.getPontuacao()) {
			System.out.println("O vencedor é " + jogador1.getNome() + "!");
		} else if (jogador2.getPontuacao() > jogador1.getPontuacao()) {
			System.out.println("O vencedor é " + jogador2.getNome() + "!");
		} else {
			System.out.println("Empate entre os jogadores!");
		}
	}
}