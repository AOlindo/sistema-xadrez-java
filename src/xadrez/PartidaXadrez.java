package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import pecasXadrez.Bispo;
import pecasXadrez.Cavalo;
import pecasXadrez.Peao;
import pecasXadrez.Rei;
import pecasXadrez.Torre;

public class PartidaXadrez {

	private int vez;
	private Color jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;// Por padrão o boolean começa com o valor false;
	private boolean checkMate;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		vez = 1;
		jogadorAtual = Color.BRANCO;
		inicioPartida();
	}

	public int getVez() {
		return vez;
	}

	public Color getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
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

		if (testeCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturarPeca);
			throw new XadrezException("Voce nao pode se colocar em check!");
		}

		check = (testeCheck(adversario(jogadorAtual))) ? true : false;

		if (testeCheckMate(adversario(jogadorAtual))) {
			checkMate = true;
		} else {
			proximoTurno();
		}

		return (PecaXadrez) capturarPeca;
	}

	private Peca moverPeca(Posicao origem, Posicao destino) {
		PecaXadrez peca = (PecaXadrez) tabuleiro.removerPeca(origem);
		peca.aumentarContagem();
		Peca capturarPeca = tabuleiro.removerPeca(destino);
		tabuleiro.pecaMovimentacao(peca, destino);

		if (pecasCapturadas != null) {
			pecasNoTabuleiro.remove(capturarPeca);
			pecasCapturadas.add(capturarPeca);
		}
		return capturarPeca;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez peca = (PecaXadrez) tabuleiro.removerPeca(destino);
		peca.diminuirContagem();
		tabuleiro.pecaMovimentacao(peca, origem);

		if (pecaCapturada != null) {
			tabuleiro.pecaMovimentacao(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}

	private void validarPosicaoOrigem(Posicao posicao) {
		// se não existir uma peça nesta posição
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Nao existe peca na posicao de origem");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
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

	private void proximoTurno() {
		vez++;
		// Se jogador atual for igual ao color.white, então agora ele vai ser
		// color.black, caso contrario vai ser o color.white
		// Condição ternaria. Mesma coisa do If/Else.
		jogadorAtual = (jogadorAtual == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
	}

	private Color adversario(Color cor) {
		if (cor == Color.BRANCO) {
			return Color.PRETO;
		} else {
			return Color.BRANCO;
		}
	}

	private PecaXadrez rei(Color cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca peca : list) {
			if (peca instanceof Rei) {
				return (PecaXadrez) peca;
			}
		}
		throw new IllegalStateException("Não existe o Rei da cor " + cor + "no tabuleiro");
	}

	private boolean testeCheck(Color cor) {
		Posicao reiPosicao = rei(cor).getPosicaoXadrez().toPosicao();
		// Lista para verificar se cada peca percorrida existe algum movimento possivel
		// para chegar na posicao do Rei
		List<Peca> pecaAdversaria = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == adversario(cor))
				.collect(Collectors.toList());
		for (Peca peca : pecaAdversaria) {
			boolean[][] mat = peca.possiveisMovimentacoes();
			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testeCheckMate(Color cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca peca : list) {
			boolean[][] mat = peca.possiveisMovimentacoes();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColuna(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaXadrez) peca).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = moverPeca(origem, destino);
						boolean testeCheck = testeCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeCheck) {
							return false;
						}
					}
				}

			}
		}
		return true;
	}

	private void novaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.pecaMovimentacao(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);

	}

	private void inicioPartida() {

		novaPeca('a', 1, new Torre(tabuleiro, Color.BRANCO));
		novaPeca('b', 1, new Cavalo(tabuleiro, Color.BRANCO));
		novaPeca('c', 1, new Bispo(tabuleiro, Color.BRANCO));
		novaPeca('e', 1, new Rei(tabuleiro, Color.BRANCO));
		novaPeca('f', 1, new Bispo(tabuleiro, Color.BRANCO));
		novaPeca('g', 1, new Cavalo(tabuleiro, Color.BRANCO));
		novaPeca('h', 1, new Torre(tabuleiro, Color.BRANCO));
		novaPeca('a', 2, new Peao(tabuleiro, Color.BRANCO));
		novaPeca('b', 2, new Peao(tabuleiro, Color.BRANCO));
		novaPeca('c', 2, new Peao(tabuleiro, Color.BRANCO));
		novaPeca('d', 2, new Peao(tabuleiro, Color.BRANCO));
		novaPeca('e', 2, new Peao(tabuleiro, Color.BRANCO));
		novaPeca('f', 2, new Peao(tabuleiro, Color.BRANCO));
		novaPeca('g', 2, new Peao(tabuleiro, Color.BRANCO));
		novaPeca('h', 2, new Peao(tabuleiro, Color.BRANCO));

		novaPeca('a', 8, new Torre(tabuleiro, Color.PRETO));
		novaPeca('b', 8, new Cavalo(tabuleiro, Color.PRETO));
		novaPeca('c', 8, new Bispo(tabuleiro, Color.PRETO));
		novaPeca('e', 8, new Rei(tabuleiro, Color.PRETO));
		novaPeca('f', 8, new Bispo(tabuleiro, Color.PRETO));
		novaPeca('g', 8, new Cavalo(tabuleiro, Color.PRETO));
		novaPeca('h', 8, new Torre(tabuleiro, Color.PRETO));
		novaPeca('a', 7, new Peao(tabuleiro, Color.PRETO));
		novaPeca('b', 7, new Peao(tabuleiro, Color.PRETO));
		novaPeca('c', 7, new Peao(tabuleiro, Color.PRETO));
		novaPeca('d', 7, new Peao(tabuleiro, Color.PRETO));
		novaPeca('e', 7, new Peao(tabuleiro, Color.PRETO));
		novaPeca('f', 7, new Peao(tabuleiro, Color.PRETO));
		novaPeca('g', 7, new Peao(tabuleiro, Color.PRETO));
		novaPeca('h', 7, new Peao(tabuleiro, Color.PRETO));

	}

}
