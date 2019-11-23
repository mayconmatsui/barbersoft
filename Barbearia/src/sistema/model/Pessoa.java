package sistema.model;

import java.util.Date;

/**
 *
 * @author matsui
 */
public abstract class Pessoa {

  private Integer Id;
  private String Nome;
  private Date DataNascimento;
  private String CPF;
  private String RG;
  private String Telefone;
  private String Email;
  private String Endereco;

  public Integer getId() {
    return Id;
  }

  public void setId(Integer Id) {
    this.Id = Id;
  }

  public String getNome() {
    return Nome;
  }

  public void setNome(String Nome) {
    this.Nome = Nome;
  }

  public Date getDataNascimento() {
    return DataNascimento;
  }

  public void setDataNascimento(Date DataNascimento) {
    this.DataNascimento = DataNascimento;
  }

  public String getCPF() {
    return CPF;
  }

  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  public String getRG() {
    return RG;
  }

  public void setRG(String RG) {
    this.RG = RG;
  }

  public String getTelefone() {
    return Telefone;
  }

  public void setTelefone(String Telefone) {
    this.Telefone = Telefone;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String Email) {
    this.Email = Email;
  }

  public String getEndereco() {
    return Endereco;
  }

  public void setEndereco(String Endereco) {
    this.Endereco = Endereco;
  }

}
