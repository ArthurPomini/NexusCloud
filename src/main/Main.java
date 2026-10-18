package main;

import model.*;
import Exception.*;

public class Main {
    public static void main(String[] args) {
        CloudManager nuvem = new CloudManager();

        Cliente c1 = new Cliente("111.222.333-44", "Wayne Enterprises");
        Servidor s1 = new Servidor("192.168.0.1", 16.0, 5.50);

        nuvem.registrarCliente(c1);
        nuvem.provisionarServidor(c1, s1);

        Container ct1 = new Container("C-01", "Container1", 10.0);
        Container ct2 = new Container("C-02", "Container2", 8.0);

        // s1.alocarContainer(ct1);
        s1.ligar();
        s1.alocarContainer(ct1);

        try {
            s1.alocarContainer(ct2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(nuvem.calcularFaturaAtiva(c1));
    }
}
