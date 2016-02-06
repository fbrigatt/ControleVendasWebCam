/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle.venda.model;


/**
 *
 * @author professor Melo
 */
public class Produto {
    private String codigoBarras;
    private String descricao;
    private float preco;
    private Imagem imagem;

    public Produto() {
        this.imagem = new Imagem();
    }
    
    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }
    /**
     * @param codigoBarras the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /**
     * @return the preco
     */
    public float getPreco() {
        return preco;
    }
    /**
     * @param preco the preco to set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }
}
