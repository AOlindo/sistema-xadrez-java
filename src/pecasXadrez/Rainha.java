package pecasXadrez;

import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public String toString() {
		return "A";
	}

	@Override
	public boolean[][] possiveisMovimentacoes() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColuna()];

		Posicao p = new Posicao(0, 0);

		// Acima
		// esta acessando a posicao da propria peca
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());
		// enquanto a posicao p existir e nao tiver uma peca la
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Esquerda
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Direita
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Para baixo
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Noroeste
		// esta acessando a posicao da propria peca
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
		// enquanto a posicao p existir e nao tiver uma peca la
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Nordeste
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Sudeste
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Sudoeste
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
