package org.prog1.dao;

import org.prog1.configuracion.AdministradorDeConexion;
import org.prog1.entities.Auto;
import org.prog1.entities.Cliente;
import org.prog1.entities.Marca;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClienteDAO {

    /*CREATE TABLE ´cliente´ (
´idCliente´ int NOT NULL auto_increment,
´nombre´ varchar(45) default null,
´apellido´ varchar(45) default null,
´dni´ int default null,
´correo´ varchar(45) default null,
´localidad´ varchar(100) default null,
PRIMARY KEY(´idCliente´)
)ENGINE=InnoDB auto_increment=1 default*/

    private static Connection conn;

    public void update(Cliente cliente) {
        conn = AdministradorDeConexion.obtenerConexion();
        if (this.existById(cliente.getIdCliente())) {
            //
            String sql = "UPDATE autos SET " +
                    "idCliente = '" + cliente.getIdCliente() + "', " +
                    "nombre = '" + cliente.getNombre() + "', " +
                    "apellido = " + cliente.getApellido() + ", " +
                    "DNI = " + cliente.getDni() + ", " +
                    "correo = '" + cliente.getCorreo() + "', " +
                    "localidad = '" + cliente.getLocalidad() + "' " +
                    "WHERE idCliente = " + cliente.getIdCliente();
            conn = AdministradorDeConexion.obtenerConexion();
            Statement st = null;

            try {
                st = conn.createStatement();
                st.execute(sql);

                st.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("Error al crear el statement");
                throw new RuntimeException(e);
            }
        }
    }


        public boolean existById(int id){
            conn = AdministradorDeConexion.obtenerConexion();
            String sql = "SELECT * FROM cliente WHERE idCliente = " + id;

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

                // cierro
                rs.close();
                st.close();
                conn.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return existe;
        }

        public void insertarCliente(Cliente cliente){
            conn = AdministradorDeConexion.obtenerConexion();

            String sql =
                    "INSERT INTO cliente (idCliente, nombre, apellido, dni, correo, localidad)" +
                            "VALUES (" + cliente.getIdCliente() + "," +
                            "'" + cliente.getNombre() + "'," +
                            "'" + cliente.getApellido() + "'," +
                            cliente.getDni() + "," +
                            cliente.getCorreo() + "," +
                            "'" + cliente.getLocalidad() + "',";

            Statement st = null;
            try {
                st = conn.createStatement();

                st.execute(sql);

                st.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        public List<Cliente> findAll(){

        conn=AdministradorDeConexion.obtenerConexion();

        String sql = "SELECT * FROM cliente order by idCliente";

        Statement st=null;
        ResultSet rs=null;

        List<Cliente> listaClientes = new java.util.ArrayList<>();

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre((rs.getString("nombre")));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDni(rs.getInt("dni"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setLocalidad(rs.getString("localidad"));

                listaClientes.add(cliente);
            }

            rs.close();
            st.close();
            conn.close();


        } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return listaClientes;

    }


    public void delete(int idCliente) {
        conn = AdministradorDeConexion.obtenerConexion();
        String sql = "DELETE FROM cliente WHERE idCliente = " + idCliente;
        Statement st = null;
        try {
            st = conn.createStatement(); // creo el statement
            st.execute(sql); // ejecuto la consulta
            st.close(); // cierro statement
            conn.close(); // cierro conexion
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Cliente getById(int id) {
        conn = AdministradorDeConexion.obtenerConexion();
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

            // CIERRO TODO SIEMPRE
            rs.close();
            st.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }


}

