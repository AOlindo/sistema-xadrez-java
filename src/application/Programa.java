package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturada = new ArrayList<>();

		while (!partidaXadrez.getCheckMate()) {
			try {
				UI.limparTela();
				UI.printMatch(partidaXadrez, capturada);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.leituraPosicaoXadrez(sc);

				boolean[][] possiveisMovimentacoes = partidaXadrez.possiveisMovimentacoes(origem);
				UI.limparTela();
				UI.printTabuleiro(partidaXadrez.getPecas(), possiveisMovimentacoes);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.leituraPosicaoXadrez(sc);

//Sempre que movimentar uma peca e a mesma for capturada (entra no if)
				PecaXadrez pecaCapturada = partidaXadrez.moverPecaXadrez(origem, destino);

				if (pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}

				if (partidaXadrez.getPromovido() != null) {
					System.out.println("Peca para entrar na promocao (Bispo/Cavalo/Torre/A(Rainha)): ");
					String tipo = sc.nextLine().toUpperCase();
					while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("R") && !tipo.equals("A")) {
						System.out.println(
								"Letra invalida! Digite novamente a peca para entrar na promocao (Bispo/Cavalo/Torre/A(Rainha)): ");
						tipo = sc.nextLine().toUpperCase();
					}
					partidaXadrez.subsPecaPromovida(tipo);
				}
			} catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.limparTela();
		UI.printMatch(partidaXadrez, capturada);
	}
}
