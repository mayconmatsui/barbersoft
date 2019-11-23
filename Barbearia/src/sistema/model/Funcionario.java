package sistema.model;

import sistema.dao.IAutenticacao;

public class Funcionario extends Pessoa{

  private String Tipo;
  private String Senha;

  public String getTipo() {
    return Tipo;
  }

  public void setTipo(String Tipo) {
    this.Tipo = Tipo;
  }

  public String getSenha() {
    return Senha;
  }

  public void setSenha(String Senha) {
    this.Senha = Senha;
  }

}
