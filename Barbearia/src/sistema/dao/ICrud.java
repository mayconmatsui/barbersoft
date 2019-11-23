package sistema.dao;

import java.util.List;

/**
 *
 * @author matsui
 * @param <T>
 */
public interface ICrud<T> {
  public T salvar(T item);
  public T alterar(T item);
  public Boolean excluir(Integer id);
  public List<T> listar(String nome);
  public T listarId(Integer id);
}