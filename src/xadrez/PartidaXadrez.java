package xadrez;

import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import pecasXadrez.Rei;
import pecasXadrez.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		inicioPartida();
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColuna()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColuna(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}

		}
		return matriz;
	}

	private void inicioPartida() {
		tabuleiro.pecaMovimentacao(new Torre(tabuleiro, Color.WHITE), new Posicao(2, 1));
		tabuleiro.pecaMovimentacao(new Rei(tabuleiro, Color.BLACK), new Posicao(0, 4));
		tabuleiro.pecaMovimentacao(new Rei(tabuleiro, Color.WHITE), new Posicao(7, 4));
	}

}
