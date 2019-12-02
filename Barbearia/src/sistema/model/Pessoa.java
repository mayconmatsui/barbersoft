package sistema.model;

import java.time.LocalDate;



/**
 *
 * @author matsui
 */
public abstract class Pessoa {

  private Integer id;
  private String nome;
  private LocalDate dataNascimento;
  private String CPF;
  private String RG;
  private String telefone;
  private String email;
  private String endereco;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String Nome) {
    this.nome = Nome;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
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
    return telefone;
  }

  public void setTelefone(String Telefone) {
    this.telefone = Telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String Email) {
    this.email = Email;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String Endereco) {
    this.endereco = Endereco;
  }

}
