package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import Exception.CapacidadeExcedidaException;
import Exception.ServidorOfflineException;

public class Servidor {
    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;
    private List<Container> containers = new ArrayList<>();

    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
    }

    public void ligar() {
        this.statusLigado = true;
    }

    public void desligar() {
        this.statusLigado = false;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public boolean isStatusLigado() {
        return statusLigado;
    }

    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        if (this.statusLigado == false) {
            throw new ServidorOfflineException("Erro: Não é possível alocar containers em um servidor desligado.");
        }
        if (c == null) {
            throw new IllegalArgumentException("Container não deve ser NULO.");
        }
        if (this.ramTotal < c.getRamNecessaria() + calcularRamEmUso()) {
            throw new CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor "+this.ip);
        }
        containers.add(c);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Servidor servidor = (Servidor) o;
        return Objects.equals(ip, servidor.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ip);
    }
}