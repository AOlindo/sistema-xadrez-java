package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	private Color cor;
	private int contagemMovimento;

	public PecaXadrez(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.cor = color;
	}

	public Color getCor() {
		return cor;
	}
	
	public int getContagemMovimento() {
		return contagemMovimento;
	}

	public void aumentarContagem() {
		contagemMovimento++;
	}

	public void diminuirContagem() {
		contagemMovimento--;
	}

	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.daPosicao(posicao);
	}

	protected boolean temUmaPecaAdversaria(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}

}
