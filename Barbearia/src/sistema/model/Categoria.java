package sistema.model;

public class Categoria {

  private Integer categoriaId;
  private String categoriaNome;
  private Integer categoriaStatus;

  public Integer getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Integer categoriaId) {
    this.categoriaId = categoriaId;
  }

  public String getCategoriaNome() {
    return categoriaNome;
  }

  public void setCategoriaNome(String categoriaNome) {
    this.categoriaNome = categoriaNome;
  }

  public Integer getCategoriaStatus() {
    return categoriaStatus;
  }

  public void setCategoriaStatus(Integer categoriaStatus) {
    this.categoriaStatus = categoriaStatus;
  }


}
