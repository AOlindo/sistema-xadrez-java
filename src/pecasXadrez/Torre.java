package pecasXadrez;

import jogoDeTabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public String toString() {
		return "T";
	}
	@Override
	public boolean[][] possiveisMovimento() {
		boolean [][] mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColuna()];
		return mat;
	}
}
