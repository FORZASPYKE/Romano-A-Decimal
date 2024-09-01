import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static int RomanoADecimal(String roman) {
        String PatronRomano = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        Pattern pattern = Pattern.compile(PatronRomano);
        Matcher comparador = pattern.matcher(roman);

        if (!comparador.matches()) {
            System.out.println("No es un numero romano: " + roman);
            return -1;
        }

        // metodo encargado de la asignacion de valores a la misma posicion tanto de los decimales como de los romanos
        int[] valoresdecimal = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] ValoresenRomano = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int decimal = 0;
        int i = 0;

        while (i < roman.length()) {
            for (int j = 0; j < ValoresenRomano.length; j++) {
                if (roman.startsWith(ValoresenRomano[j], i)) {
                    decimal += valoresdecimal[j];
                    i ++;
                    break;
                }
            }
        }

        return decimal;

    }

    public static void main(String[] args) {

        //aqui hago la lista para ir guardando los valores romanos como su equivalente en decimal
        List<String[]> infocsv = new ArrayList<>();

        //Se guarda el argumento en el string llamado romano y se llama al metodo RomanoAdecimal, dando como argumento romano
        for (String romano : args) {
            int decimal = RomanoADecimal(romano);
            infocsv.add(new String[]{romano, String.valueOf(decimal)});
        }

        // hacer el archivo .CVS y agregar tanto los numeros romanos como los decimales
        try (FileWriter writer = new FileWriter("Romano a Decimal.csv")) {
            for (String[] fila : infocsv) {
                writer.write(String.join(",", fila) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
