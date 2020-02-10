package estacion;

import bicicleta.Bicicleta;
import java.util.concurrent.ThreadLocalRandom;
import tarjetaUsuario.TarjetaUsuario;

public class Estacion {
    
    private final int id;
    private final String direccion;
    private final int numeroAnclajes;
    public Bicicleta[] anclajes;
    private Bicicleta bici;
    private TarjetaUsuario targeta;
    
    
    public Estacion(int id, String direccion, int numeroAnclajes) {
        this.id = id;
        this.direccion = direccion;
        this.numeroAnclajes = numeroAnclajes;
        this.anclajes = new Bicicleta[numeroAnclajes];
    }
    
    public void consultarEstacion() {
        System.out.print("Id: " + id + '\n');
        System.out.print("Dirección: " + direccion + '\n');
        System.out.print("Número de anclajes: " + numeroAnclajes + '\n');
    
    }
    
    public int anclajesLibres() {
        int anclajesLibres = 0;
        for (Bicicleta anclaje : anclajes) {
            if (anclaje == null) {
                anclajesLibres++;
            }
        }
        return anclajesLibres;
    }
    
    public void consultarAnclajes() {
        int contador = 0;
        for (Bicicleta anclaje : anclajes) {
            if (anclaje != null) {
                System.out.print("Anclaje número: " + contador + '\n');
                System.out.print("Bici id: " + anclaje.id + '\n');
                
                contador ++;
            } 
            else {
                System.out.print("Anclaje número: " + contador + '\n');
                System.out.print("Bici id: vacio" + '\n');
                contador ++;
            }
        }
    }
        
    public void anclarBicicleta(Bicicleta bici) {
        int posicion = 0;
        for (Bicicleta anclaje : anclajes) {
            if (anclajesLibres() == 0) {
                System.out.print("No hay anclajes libres!" + '\n' );
            }
            else if (anclajes[posicion] == null) {
                anclajes[posicion] = bici; 
                System.out.print("Bicicleta anclada!" + '\n');
                break;
            }
            else {
                posicion ++;
            }
        }
        mostrarAnclaje(bici, posicion);
    }
    
    public void mostrarAnclaje(Bicicleta bici, int numeroAnclaje) {
        System.out.print("Bicicleta Id: " + bici.id + '\n');
        System.out.print("Anclaje: " + numeroAnclaje + '\n' + '\n');
    }
    
    public boolean leerTarjetaUsuario(TarjetaUsuario tarjeta) {
        return tarjeta.activada;
    }
    
    public void retirarBicicleta(TarjetaUsuario tarjeta) {
        if (leerTarjetaUsuario(tarjeta) == true) {
            int anclajeRandom = generarAnclaje();
            if (anclajes[anclajeRandom] != null){
                Bicicleta bicis = new Bicicleta(anclajes[anclajeRandom].id);
                anclajes[anclajeRandom] = null;
                mostrarBicicleta(bicis, anclajeRandom);
                }
            else {
                retirarBicicleta(tarjeta);
            }
        }
        else {
            System.out.print("La targeta no esta activada!" + '\n');
        }
    }
    
    private void mostrarBicicleta(Bicicleta bici, int numeroAnclaje) {
        System.out.print("Bicicleta Id: " + bici.id + '\n');
        System.out.print("Ultimo Anclaje: " + numeroAnclaje + '\n' + '\n');
    }
    
    private int generarAnclaje() {
        // 48 en ASCII = 0, 57 en ASCII = 9
        int limit = 48 + anclajes.length - anclajesLibres();
        int randomCharNum;
        randomCharNum = ThreadLocalRandom.current().nextInt(48, limit);
        
        char randomChar = (char) randomCharNum;
        int randomInt = Character.getNumericValue(randomChar);
        return randomInt;
    }
}
