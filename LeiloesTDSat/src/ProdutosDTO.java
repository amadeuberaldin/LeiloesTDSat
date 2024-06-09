
import java.util.Objects;

public class ProdutosDTO {

    private Integer id;
    private String nome;
    private Integer valor;
    private String status;

    public ProdutosDTO() {
        // Construtor padrão
    }

    public ProdutosDTO(Integer id, String nome, Integer valor, String status) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public void setValor(Integer valor) {
        if (valor == null || valor < 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        this.valor = valor;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status não pode ser vazio");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProdutosDTO{"
                + "id=" + id
                + ", nome='" + nome + '\''
                + ", valor=" + valor
                + ", status='" + status + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProdutosDTO that = (ProdutosDTO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(nome, that.nome)
                && Objects.equals(valor, that.valor)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, status);
    }
}
