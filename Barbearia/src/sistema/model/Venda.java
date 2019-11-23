package sistema.model;

import java.sql.Date;

public class Venda {
    private Integer id;
    private Cliente cliente;
    private Date vendaData;
    private Double vendaTotal;
    private Funcionario funcionario;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Date getVendaData() {
    return vendaData;
  }

  public void setVendaData(Date vendaData) {
    this.vendaData = vendaData;
  }

  public Double getVendaTotal() {
    return vendaTotal;
  }

  public void setVendaTotal(Double vendaTotal) {
    this.vendaTotal = vendaTotal;
  }

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }
    

    
}
