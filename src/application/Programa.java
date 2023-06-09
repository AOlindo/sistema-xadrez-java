package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		PartidaXadrez partidaXadrez = new PartidaXadrez();

		while (true) {
			try {
				UI.limparTela();
				UI.printTabuleiro(partidaXadrez.getPecas());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.leituraPosicaoXadrez(sc);
				
				boolean [] [] possiveisMovimentacoes = partidaXadrez.possiveisMovimentacoes(origem);
				UI.limparTela();
				UI.printTabuleiro(partidaXadrez.getPecas(), possiveisMovimentacoes);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.leituraPosicaoXadrez(sc);

				PecaXadrez capturarPeca = partidaXadrez.moverPecaXadrez(origem, destino);
			} 
			catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} 
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}

	}
}
