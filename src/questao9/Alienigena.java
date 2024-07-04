package questao9;

import java.time.LocalDateTime;

public class Alienigena {
    public long id;
    public String nome;
    public Especie especie;
    public int nivelPericulosidade;
    public LocalDateTime dataHoraEntrada;

    public Alienigena(String nome, Especie especie, int nivelPericulosidade) {
        this.id = System.currentTimeMillis();
        this.nome = nome;
        this.especie = especie;
        this.nivelPericulosidade = nivelPericulosidade + especie.nivelPericulosidadeBase;
        this.dataHoraEntrada = LocalDateTime.now();
    }
}
