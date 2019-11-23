/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author matsui
 */
public class Agenda {
  private Integer agendId;
  private Cliente cliente;
  private List<Produto> produtos;
  private String agendaDescricao;
  private Date agendaDatahoraInicial;
  private Date agendaDatahoraFinal;
  private Funcionario agendaFuncionario;
  private Funcionario funcionario;

  public Integer getAgendId() {
    return agendId;
  }

  public void setAgendId(Integer agendId) {
    this.agendId = agendId;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
  }

  public String getAgendaDescricao() {
    return agendaDescricao;
  }

  public void setAgendaDescricao(String agendaDescricao) {
    this.agendaDescricao = agendaDescricao;
  }

  public Date getAgendaDatahoraInicial() {
    return agendaDatahoraInicial;
  }

  public void setAgendaDatahoraInicial(Date agendaDatahoraInicial) {
    this.agendaDatahoraInicial = agendaDatahoraInicial;
  }

  public Date getAgendaDatahoraFinal() {
    return agendaDatahoraFinal;
  }

  public void setAgendaDatahoraFinal(Date agendaDatahoraFinal) {
    this.agendaDatahoraFinal = agendaDatahoraFinal;
  }

  public Funcionario getAgendaFuncionario() {
    return agendaFuncionario;
  }

  public void setAgendaFuncionario(Funcionario agendaFuncionario) {
    this.agendaFuncionario = agendaFuncionario;
  }

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }

}
