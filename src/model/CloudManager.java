package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CloudManager {
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public CloudManager() {
    }

    public void registrarCliente(Cliente c) {
        if (c == null) {
            throw new IllegalArgumentException("Cliente não pode ser NULO.");
        }
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    public void provisionarServidor(Cliente c, Servidor s) {
        if (c == null || s == null) {
            throw new IllegalArgumentException("Cliente e Servidor não podem ser NULOS.");
        }
        if (!bancoDeDados.containsKey(c)) {
            throw new IllegalArgumentException("Cliente não está registrado.");
        }
        bancoDeDados.get(c).add(s);
    }

    public double calcularFaturaAtiva(Cliente c) {
        if (c == null || !bancoDeDados.containsKey(c)) {
            throw new IllegalArgumentException("Cliente inválido ou não registrado.");
        }
        return bancoDeDados.get(c).stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
}