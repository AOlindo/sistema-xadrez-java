package xadrez;

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

	private void novaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.pecaMovimentacao(peca, new PosicaoXadrez(coluna, linha).toPosicao());

	}

	private void inicioPartida() {

		novaPeca('c', 1, new Torre(tabuleiro, Color.WHITE));
		novaPeca('c', 2, new Torre(tabuleiro, Color.WHITE));
		novaPeca('d', 2, new Torre(tabuleiro, Color.WHITE));
		novaPeca('e', 2, new Torre(tabuleiro, Color.WHITE));
		novaPeca('e', 1, new Torre(tabuleiro, Color.WHITE));
		novaPeca('d', 1, new Rei(tabuleiro, Color.WHITE));

		novaPeca('c', 7, new Torre(tabuleiro, Color.BLACK));
		novaPeca('c', 8, new Torre(tabuleiro, Color.BLACK));
		novaPeca('d', 7, new Torre(tabuleiro, Color.BLACK));
		novaPeca('e', 7, new Torre(tabuleiro, Color.BLACK));
		novaPeca('e', 8, new Torre(tabuleiro, Color.BLACK));
		novaPeca('d', 8, new Rei(tabuleiro, Color.BLACK));

	}

}
