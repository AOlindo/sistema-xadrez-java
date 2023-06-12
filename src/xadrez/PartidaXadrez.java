package xadrez;

import java.util.ArrayList;
import java.util.List;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import jogoDeTabuleiro.TabuleiroException;
import pecasXadrez.Rei;
import pecasXadrez.Torre;

public class PartidaXadrez {

	private int vez;
	private Color jogadorAtual;
	private Tabuleiro tabuleiro;

	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		vez = 1;
		jogadorAtual = Color.WHITE;
		inicioPartida();
	}

	public int getVez() {
		return vez;
	}

	public Color getJogadorAtual() {
		return jogadorAtual;
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
	public boolean[][] possiveisMovimentacoes(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentacoes();
	}
	public PecaXadrez moverPecaXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		ValidarPosicaoDestino(origem, destino);
		Peca capturarPeca = moverPeca(origem, destino);
		proximoTurno();
		return (PecaXadrez) capturarPeca;
	}
	private Peca moverPeca(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca capturarPeca = tabuleiro.removerPeca(destino);
		tabuleiro.pecaMovimentacao(p, destino);
		
		if (pecasCapturadas != null) {
		pecasNoTabuleiro.remove(pecasCapturadas);
		pecasCapturadas.add(capturarPeca);
		}
		return capturarPeca;
	}
	private void validarPosicaoOrigem(Posicao posicao) {
		// se não existir uma peça nesta posição
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Nao existe peca na posicao de origem");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getColor()) {
			throw new XadrezException("A peca escolhida nao e sua");
		}
		// se não tiver nenhum movimento possivel
		if (!tabuleiro.peca(posicao).temAlgumMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida");
		}
	}
	private void ValidarPosicaoDestino(Posicao origem, Posicao destino) {
		// se pra peça de origem, a posicao de destino não é um movimento possível,
		// significa que não pode mover para o destino.
		if (!tabuleiro.peca(origem).possivelMovimento(destino)) {
			throw new XadrezException("A peca escolhida nao pode se mover para posicao de destino");
		}
	}
	private void proximoTurno () {
		vez++;
		//Se jogador atual for igual ao color.white, então agora ele vai ser color.black, caso contrario vai ser o color.white
		//Condição ternaria. Mesma coisa do If/Else.
		jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	private void novaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.pecaMovimentacao(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);

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
