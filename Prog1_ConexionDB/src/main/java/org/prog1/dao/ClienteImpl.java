package org.prog1.dao;

import org.prog1.configuracion.AdministradorDeConexion;
import org.prog1.entities.Auto;
import org.prog1.entities.Cliente;
import org.prog1.entities.Marca;
import org.prog1.interfaces.AdmConexion;
import org.prog1.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteImpl implements DAO<Cliente,Integer> , AdmConexion {

    private static final String SQL_INSERT =
            "INSERT INTO cliente (idCliente, nombre, apellido, dni, correo, localidad)" +
                    "VALUES (?  ?  ?  ?  ?  ?)";

    private static final String SQL_UPDATE =  "UPDATE cliente SET " +
            "idCliente = ? , nombre = ?, apellido = ?, dni = ? " +
            ", correo = ?, localidad = ? " + " WHERE idCliente = ? ";

    private static final String SQL_DELETE = "DELETE  FROM cliente WHERE idCliente = ?";
    private static final String SQL_GETALL = " SELECT * FROM cliente ORDER BY idCliente";
    private static final String SQL_GETBYID= "SELECT * FROM cliente WHERE idCliente = ?";

    @Override
    public List<Cliente> getAll() {
        List<Cliente> lista = new ArrayList<>();
        return lista;
    }

    public void insert(Cliente objeto) {
        Connection conn = obtenerConexion();
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
    public void update(Cliente objeto) {

        Cliente cliente = objeto;
        Connection conn = obtenerConexion();
        if (this.existsById(objeto.getIdCliente())) {

            String sql = "UPDATE autos SET " +
                    "idCliente = '" + cliente.getIdCliente() + "', " +
                    "nombre = '" + cliente.getNombre() + "', " +
                    "apellido = " + cliente.getApellido() + ", " +
                    "DNI = " + cliente.getDni() + ", " +
                    "correo = '" + cliente.getCorreo() + "', " +
                    "localidad = '" + cliente.getLocalidad() + "' " +
                    "WHERE idCliente = " + cliente.getIdCliente();
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
    public void delete(Integer idCliente) {
        Connection conn = obtenerConexion();
        PreparedStatement pst=null;


        // 3 crear instruccion
        try {
            pst = conn.prepareStatement(ClienteImpl.SQL_DELETE);
            pst.setInt(1,idCliente);

            // 4 ejecutar instruccion
            pst.execute();
            // 5 cerrar conexion
            pst.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Cliente getById(Integer id) {
        Connection conn = obtenerConexion();
        String sql = "SELECT * FROM cliente WHERE idCliente = " + id;
        // se crea un statement
        Statement st = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();

        try {
            st = conn.createStatement(); // creo statement
            rs = st.executeQuery(sql); // ejecuto consulta
            // Si la consuta devuelve al menos un registro, existe
            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre((rs.getString("nombre")));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDni(rs.getInt("dni"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setLocalidad(rs.getString("localidad"));
            }
            rs.close();
            st.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    @Override
    public boolean existsById(Integer id) {
        Connection conn = AdministradorDeConexion.obtenerConexion();
        String sql = "SELECT * FROM cliente WHERE idCliente = " + id;
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
