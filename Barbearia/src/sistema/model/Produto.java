package sistema.model;

public class Produto {

  private Integer id;
  private Categoria categoria;
  private String nome;
  private String descricao;
  private Double valorCusto;
  private Double valorVenda;
  private Integer quantidade;
  private Integer tipo;
  private Integer status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Double getValorCusto() {
    return valorCusto;
  }

  public void setValorCusto(Double valorCusto) {
    this.valorCusto = valorCusto;
  }

  public Double getValorVenda() {
    return valorVenda;
  }

  public void setValorVenda(Double valorVenda) {
    this.valorVenda = valorVenda;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Integer getTipo() {
    return tipo;
  }

  public void setTipo(Integer tipo) {
    this.tipo = tipo;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}
