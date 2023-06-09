package jogoDeTabuleiro;

import xadrez.PecaXadrez;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Peca[][] pecas;

	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro criando tabuleiro: e necessario que haja pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColuna() {
		return colunas;
	}

	public Peca peca(int linha, int coluna) {
		if (!posicaoExiste(linha, coluna)) {
			throw new TabuleiroException("Posicao nao existe no tabuleiro");
		}
		return pecas[linha][coluna];
	}

	public Peca peca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posicao nao existe no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void pecaMovimentacao(Peca peca, Posicao posicao) {
		if (temUmaPeca(posicao)) {
			throw new TabuleiroException("Ja existe uma peca na posicao " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

	public Peca removerPeca(Posicao posicao) {
		// Se essa posicao não existe, lançar uma exception.
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posicao nao existe no tabuleiro");
		}
		// se a peca no tabuleiro for igual a null
		if (peca(posicao) == null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;

	}

	// Metodo para saber se uma posicao existe//
	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;

	}

	// Metodo para saber se uma posicao existe//
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());

	}

	// Verificar se tem uma peca na posicao//
	public boolean temUmaPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posicao nao existe no tabuleiro");
		}
		return peca(posicao) != null;
	}

}
