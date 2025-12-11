package com.example.tabs;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.content.Context;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLHelper {
    private static final String URL = "jdbc:mysql://34.30.2.143:3306/nahuatl";
    private static final String USER = "admin";
    private static final String PASSWORD = "nahuatl";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Log.d("MySQL", "Conexión exitosa");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MySQL", "Error de conexión: " + e.getMessage());
            return null;
        }
    }


    public static void obtenerUsuarios(final Context context, final TextView textView) {
        new Thread(() -> {
            try {
                // Conexión a la base de datos
                Connection conn = conectar();
                if (conn != null) {
                    Statement stmt = conn.createStatement();

                    // Haciendo la consulta a la tabla 'prueba' y obteniendo la columna 'confirmacion'
                    ResultSet rs = stmt.executeQuery("SELECT * FROM prueba");

                    StringBuilder confirmaciones = new StringBuilder();
                    while (rs.next()) {
                        // Extraemos el valor de la columna 'confirmacion'
                        String confirmacion = rs.getString("confirmacion");
                        Log.d("MySQL", "Confirmacion: " + confirmacion);  // Log para verificar el contenido
                        confirmaciones.append("Confirmación: ").append(confirmacion).append("\n");
                    }

                    if (confirmaciones.length() == 0) {
                        Log.d("MySQL", "No se encontraron datos en la columna 'confirmacion'");
                    }

                    // Usamos runOnUiThread para actualizar la UI en el hilo principal
                    ((Activity) context).runOnUiThread(() -> {
                        if (confirmaciones.length() > 0) {
                            textView.setText(confirmaciones.toString());
                        } else {
                            textView.setText("No se encontraron datos.");
                        }
                    });

                    conn.close();
                }
            } catch (Exception e) {
                Log.e("MySQL", "Error al obtener datos: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}
