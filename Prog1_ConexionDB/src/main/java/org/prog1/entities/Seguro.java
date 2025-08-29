package org.prog1.entities;

import java.util.Objects;

public class Seguro {
  private int idSeguro;
  private String tipo;
  private int costoMensual;
  private String compania;

  public Seguro(){

  }
  public Seguro(int idSeguro, String tipo, int costoMensual, String compania) {
    this.idSeguro = idSeguro;
    this.tipo = tipo;
    this.costoMensual = costoMensual;
    this.compania = compania;
  }

  public int getIdSeguro() {
    return idSeguro;
  }

  public void setIdSeguro(int idSeguro) {
    this.idSeguro = idSeguro;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public int getCostoMensual() {
    return costoMensual;
  }

  public void setCostoMensual(int costoMensual) {
    this.costoMensual = costoMensual;
  }

  public String getCompania() {
    return compania;
  }

  public void setCompania(String compania) {
    this.compania = compania;
  }


  @Override
  public String toString() {
    return "Seguro{" +
        "idSeguro=" + idSeguro +
        ", tipo='" + tipo + '\'' +
        ", costoMensual=" + costoMensual +
        ", compania='" + compania + '\'' +
        '}';
  }


  @Override
  public int hashCode() {
    return Objects.hash(idSeguro);
  }

}
