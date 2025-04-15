package main.java.com.ejemplo;

import javax.swing.*;

public class Calculadora {

    public static void main(String[] args) {

        // Variables
        String operacion = "";
        int numeros;
        double resultadoOperacion = 0;

        // Bucle principal que permite realizar operaciones hasta que el usuario decida salir
        while (true) {
            try {
                // Solicita al usuario la operación a realizar
                operacion = JOptionPane.showInputDialog(null, "Ingresa la operación que quieres realizar: \nsuma o + \nresta o - \nmultiplicación o * \ndivisión o / \nEscribe 'salir' para terminar: ").toLowerCase();
            } catch (NullPointerException e) {
                // Si el usuario cierra el diálogo, finaliza el programa
                JOptionPane.showMessageDialog(null, "Programa Finalizado");
                System.exit(0);
            }

            // Se verifica si el usuario desea salir
            if (operacion.equals("salir")) {
                int confirmar = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    // Si el usuario no quiere salir, vuelve al inicio del bucle
                    continue;
                }

            }

            // Verifica si la operación ingresada es válida
            if (!operacionValida(operacion)) {
                JOptionPane.showMessageDialog(null, "Operación no valida");
                // Si no es válida, regresa al inicio del bucle
                continue;
            }
            try {
                // Solicita la cantidad de números a ingresar
                numeros = (int) obtenerNumero("Ingresa la cantidad de números a ingresar: ");

                if (numeros > 0) {

                    // Solicita el primer número
                    resultadoOperacion = obtenerNumero("Ingresa el primer número: ");

                    // Se realiza la operación
                    for (int i = 1; i < numeros; i++) {
                        double numero = obtenerNumero("Ingresa el siguiente número: ");
                        resultadoOperacion = realizarOperacion(resultadoOperacion, numero, operacion);

                    }
                } else {
                    // Verifica que el número ingresado por el usuario es mayor a cero
                    JOptionPane.showMessageDialog(null, "La cantidad de números debe ser mayor a 0");
                }
                // Se muestra el resultado de la operación
                JOptionPane.showMessageDialog(null, "El resultado de tu operación es: " + String.format("%.2f", resultadoOperacion));
            } catch (ArithmeticException e) {
                // Manejo de error para dividir por cero
                JOptionPane.showMessageDialog(null, "No se puede dividir un número entre 'cero'");
            } catch (NumberFormatException e) {
                // Manejo de error para una entrada no válida
                JOptionPane.showMessageDialog(null, "Ha ocurrido un problema");
            } catch (NullPointerException e) {
                // Finaliza el programa si el usuario cierra el diálogo
                JOptionPane.showMessageDialog(null, "Programa finalizado");
                System.exit(0);
            }
        }
    }

    // Método para obtener un número del usuario
    private static double obtenerNumero(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(JOptionPane.showInputDialog(null, mensaje));
            } catch (NumberFormatException e) {
                // Manejo de error para una entrada no válida
                JOptionPane.showMessageDialog(null, "Ingresa un número valido");
            } catch (NullPointerException e) {
                // Finaliza si programa si el usuario cierra el diálogo
                JOptionPane.showMessageDialog(null, "Programa finalizado");
                System.exit(0);
            }
        }
    }

    // Método para realizar la operación matemática
    private static double realizarOperacion(double resultado, double numero, String operacion) {
        switch (operacion) {
                // Suma
            case "suma":
            case "+":
                return resultado + numero;
                // Resta
            case "resta":
            case "-":
                return resultado - numero;
                // Multiplicación
            case "multiplicacion":
            case "*":
                return resultado * numero;
                // División
            case "division":
            case "/":
                // Lanza un error si el número ingresado es cero
                if (numero == 0) {
                    throw new ArithmeticException();
                }
                return resultado / numero;
            default:
                // Devuelve el resultado sin cambios si la operación no es valida
                JOptionPane.showMessageDialog(null, "Operación no valida");
                return resultado;
        }
    }

    // Método para validar la operación necesaria
    private static boolean operacionValida(String operacion) {
        return operacion.equals("suma") || operacion.equals("+") ||
                operacion.equals("resta") || operacion.equals("-") ||
                operacion.equals("multiplicacion") || operacion.equals("*") ||
                operacion.equals("division") || operacion.equals("/");
    }
}
