package controle.venda.model;

/**
 * Classe responsável por mapear os campos da Tabela Clientes
 * @author frede
 */
public class Cliente {
    
    private String cpf;  // Campo cpf da Tabela Clientes - String e pk
    private String nome; // Campo nome da Tabela Clientes - String
    
    // gets e sets

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
