package monedero;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Esta clase representa un monedero que permite gestionar la cantidad de dinero disponible.
 */
public class Monedero {
    private double saldo;
    private ArrayList<String> historial;

    /**
     * Constructor que crea un monedero con un saldo inicial.saldoInicial El saldo inicial del monedero.
     *
     * @param saldoInicial
     */
    public Monedero(double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        saldo = saldoInicial;
        historial = new ArrayList<>();
    }

    /**
     * Agrega dinero al monedero y registra el movimiento en el historial.
     *
     * @param cantidad La cantidad de dinero a agregar.
     */
    public void meterDinero(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a meter no puede ser negativa.");
        }
        saldo += cantidad;
        historial.add("Se añadió Q" + cantidad + " al monedero.");
    }

    /**
     * Retira dinero del monedero y registra el movimiento en el historial.
     *
     * @param cantidad La cantidad de dinero a retirar.
     */
    public void sacarDinero(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a sacar no puede ser negativa.");
        }
        if (cantidad > saldo) {
            throw new IllegalArgumentException("No hay suficiente dinero en el monedero.");
        }
        saldo -= cantidad;
        historial.add("Se retiró Q" + cantidad + " del monedero.");
    }

    /**
     * Consulta el saldo disponible en el monedero.
     *
     * @return El saldo disponible en el monedero.
     */
    public double consultarDisponible() {
        return saldo;
    }

    /**
     * Muestra el historial de movimientos del monedero.
     */
    public void mostrarHistorial() {
        StringBuilder mensaje = new StringBuilder("Historial de movimientos:\n");
        for (String movimiento : historial) {
            mensaje.append(movimiento).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    /**
     * Método principal que prueba el funcionamiento del monedero.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        Monedero monedero = new Monedero(1000);

        while (true) {
            String opcion = JOptionPane.showInputDialog("""
                    Seleccione una opción:
                    1. Agregar dinero
                    2. Sacar dinero
                    3. Consultar saldo
                    4. Mostrar historial
                    5. Salir""");

            switch (opcion) {
                case "1":
                    double cantidadAgregar = ingresarDouble("Ingrese la cantidad a agregar:");
                    monedero.meterDinero(cantidadAgregar);
                    break;
                case "2":
                    double cantidadSacar = ingresarDouble("Ingrese la cantidad a sacar:");
                    try {
                        monedero.sacarDinero(cantidadSacar);
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Saldo disponible: Q" + monedero.consultarDisponible());
                    break;
                case "4":
                    monedero.mostrarHistorial();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Saliendo del programa.");
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    /**
     * Solicita al usuario que ingrese un valor numérico.
     *
     * @param mensaje El mensaje a mostrar al usuario.
     * @return El valor numérico ingresado por el usuario.
     */
    private static double ingresarDouble(String mensaje) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(mensaje);
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido.");
            }
        }
    }
}
