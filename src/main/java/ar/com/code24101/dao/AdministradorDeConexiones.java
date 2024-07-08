package ar.com.code24101.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {
    //metodo estatico: Clase.metodo(); no es neceario crear un objeto
    //para usar el metodo
    public static Connection conectar() {
        String url = "jdbc:mysql://localhost:3306/24101?serverTimeZone=UTC&userSSL=false";
        String user = "root";//System.getenv("nombre_variable")
        String password = "";

        //String url = "jdbc:mysql://uvmybuhajqkgb0kn:Q5hkoZuXRbdGW2CLjujk@b1nqnpm4iidq7zdt3gur-mysql.services.clever-cloud.com:3306/b1nqnpm4iidq7zdt3gur";
        //String user = "uvmybuhajqkgb0kn";//System.getenv("nombre_variable")
        //String password = "Q5hkoZuXRbdGW2CLjujk";

        String driver = "com.mysql.cj.jdbc.Driver";

        Connection con = null;
        try {
            Class.forName(driver);//carga el driver en la jvm
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectada a la DB");

        }catch(Exception e){
            System.err.println(e);
        }
        return con;
    }
    /*
    public static void main(String[] args) {
        //junit!: tph
        AdministradorDeConexiones.conectar();
    }
    */
    public static void desconectar(Connection connection) {
        try{
            connection.close();

        }catch(Exception e) {
            System.err.println(e);
        }
    }

}
