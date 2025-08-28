package org.prog1.dao;

import org.prog1.configuracion.AdministradorDeConexion;
import org.prog1.entities.Auto;
import org.prog1.entities.Marca;
import org.prog1.interfaces.AdmConexion;
import org.prog1.interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AutoImpl implements DAO<Auto, Integer> , AdmConexion{


  private static final String SQL_INSERT =
      "INSERT INTO autos (idAuto, patente, color, anio, kilometraje, marca, modelo)" +
          "VALUES (?  ?  ?  ?  ?  ?  ?)";


  private static final String SQL_UPDATE =  "UPDATE autos SET " +
      "patente = ? , color = ?, anio = ?, kilometraje = ? " +
      ", marca = ?, modelo = ? " + " WHERE idAuto = ? ";

  private static final String SQL_DELETE = "DELETE  FROM autos WHERE idAuto = ?";
  private static final String SQL_GETALL = " SELECT * FROM autos ORDER BY patente";
  private static final String SQL_GETBYID= "SELECT * FROM autos WHERE idAutos = ?";

  @Override
  public List<Auto> getAll() {
    List<Auto> lista = new ArrayList<>();
    return lista;
  }

   @Override
  public void insert(Auto objeto) {
    // 1 establecer conexion a la base de datos
     Connection conn = obtenerConexion();
  /*  // 2 Crear string de consulta SQL
    String sql =
        "INSERT INTO autos (idAuto, patente, color, anio, kilometraje, marca, modelo)" +
            "VALUES (" + objeto.getIdAuto() + "," +
            "'" + objeto.getPatente() + "'," +
            "'" + objeto.getColor() + "'," +
            objeto.getAnio() + "," +
            objeto.getKilometraje() + "," +
            "'" + objeto.getMarca() + "'," +
            "'" + objeto.getModelo() + "')";
    // 3 crear instruccion
    */
     PreparedStatement st = null;


    try {
      st = conn.prepareStatement(SQL_INSERT);
      // 4 ejecutar instruccion!!! st.execute(sql);
      // 5 cerrar conexion
      st.execute();
      st.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(Auto objeto) {

    Auto auto = objeto;
    Connection conn = obtenerConexion();
    if (this.existById(objeto.getIdAuto())) {

      String sql = "UPDATE autos SET " +
          "patente = '" + auto.getPatente() + "', " +
          "color = '" + auto.getColor() + "', " +
          "anio = " + auto.getAnio() + ", " +
          "kilometraje = " + auto.getKilometraje() + ", " +
          "marca = '" + auto.getMarca() + "', " +
          "modelo = '" + auto.getModelo() + "' " +
          "WHERE idAuto = " + auto.getIdAuto();
      Statement st = null;

      try {
        st = conn.createStatement();
        st.execute(sql);

        // cierro
        st.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("Error al crear el statement");
        throw new RuntimeException(e);
      }
    }

  }

  @Override
  public void delete(Integer idAuto) {
    Connection conn = obtenerConexion();
    PreparedStatement preparedStatement=null;



       // 3 crear instruccion
     try {
      pst = conn.createStatement(SQL_DELETE);
       pst.setInt(1,idAuto);

      // 4 ejecutar instruccion
      pst.execute();
      // 5 cerrar conexion
      st.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


  }

  @Override
  public Auto getById(Integer id) {
    conn = obtenerConexion();
    String sql = "SELECT * FROM autos WHERE idAuto = " + id;
    // se crea un statement
    Statement st = null;
    ResultSet rs = null;
    Auto auto = new Auto();

    try {
      st = conn.createStatement(); // creo statement
      rs = st.executeQuery(sql); // ejecuto consulta
      // Si la consuta devuelve al menos un registro, existe
      if (rs.next()) {
        auto.setIdAuto(rs.getInt("idAuto"));
        auto.setPatente(rs.getString("patente"));
        auto.setColor(rs.getString("color"));
        auto.setMarca(Marca.valueOf(rs.getString("marca")));
        auto.setAnio(rs.getInt("anio"));
        auto.setKilometraje(rs.getInt("kilometraje"));
        auto.setModelo(rs.getString("modelo"));
      }
      rs.close();
      st.close();
      conn.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return auto;
  }

  @Override
  public boolean existsById(Integer id) {
    return false;
  }



@Override
public boolean existsById(Integer id) {
  conn = AdministradorDeConexion.obtenerConexion();
  String sql = "SELECT * FROM autos WHERE idAuto = " + id;
  // se crea un statement
  Statement st = null;
  ResultSet rs = null;
  boolean existe = false;

  try {
    st = conn.createStatement(); // creo statement
    rs = st.executeQuery(sql); // ejecuto consulta
    // Si la consuta devuelve al menos un registro, existe
    if (rs.next()) {
      existe = true;
    }

    // CIERRO TODO SIEMPRE
    rs.close();
    st.close();
    conn.close();

  } catch (SQLException e) {
    throw new RuntimeException(e);
  }
  return existe;
}



}
