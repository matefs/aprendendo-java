///usr/bin/env jbang "$0" "$@"
//DEPS org.springframework.boot:spring-boot-starter-web:3.2.0
//DEPS org.springframework.boot:spring-boot-starter-validation:3.2.0
//DEPS org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0

package com.exemplo.jbang;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

record TarefaRegistroModelo(Long identificadorTarefa, String tituloTarefa, String descricaoTarefa, Boolean statusConclusao) {}

record CriacaoTarefaRequisicao(
        @NotBlank
        @Size(min = 3, max = 100)
        String tituloTarefa,

        @Size(max = 255)
        String descricaoTarefa
) {}

record AtualizacaoTarefaRequisicao(
        @NotBlank
        @Size(min = 3, max = 100)
        String tituloTarefa,

        @Size(max = 255)
        String descricaoTarefa,

        @NotNull
        Boolean statusConclusao
) {}

@Repository
class TarefaMemoriaRepositorio {
    private final Map<Long, TarefaRegistroModelo> baseDadosMemoria = new ConcurrentHashMap<>();
    private final AtomicLong sequencialIdentificador = new AtomicLong(1);

    public List<TarefaRegistroModelo> buscarTodas() {
        return new ArrayList<>(this.baseDadosMemoria.values());
    }

    public Optional<TarefaRegistroModelo> buscarPorIdentificador(Long identificadorTarefa) {
        return Optional.ofNullable(this.baseDadosMemoria.get(identificadorTarefa));
    }

    public TarefaRegistroModelo salvar(CriacaoTarefaRequisicao requisicao) {
        Long novoIdentificador = this.sequencialIdentificador.getAndIncrement();
        TarefaRegistroModelo novaTarefa = new TarefaRegistroModelo(
                novoIdentificador,
                requisicao.tituloTarefa(),
                requisicao.descricaoTarefa(),
                false
        );
        this.baseDadosMemoria.put(novoIdentificador, novaTarefa);
        return novaTarefa;
    }

    public Optional<TarefaRegistroModelo> atualizar(Long identificadorTarefa, AtualizacaoTarefaRequisicao requisicao) {
        if (!this.baseDadosMemoria.containsKey(identificadorTarefa)) {
            return Optional.empty();
        }
        TarefaRegistroModelo tarefaAtualizada = new TarefaRegistroModelo(
                identificadorTarefa,
                requisicao.tituloTarefa(),
                requisicao.descricaoTarefa(),
                requisicao.statusConclusao()
        );
        this.baseDadosMemoria.put(identificadorTarefa, tarefaAtualizada);
        return Optional.of(tarefaAtualizada);
    }

    public boolean remover(Long identificadorTarefa) {
        return this.baseDadosMemoria.remove(identificadorTarefa) != null;
    }
}

@Service
class TarefaOperacaoService {
    private final TarefaMemoriaRepositorio tarefaMemoriaRepositorio;

    public TarefaOperacaoService(TarefaMemoriaRepositorio tarefaMemoriaRepositorio) {
        this.tarefaMemoriaRepositorio = tarefaMemoriaRepositorio;
    }

    public List<TarefaRegistroModelo> listarTodasTarefas() {
        return this.tarefaMemoriaRepositorio.buscarTodas();
    }

    public Optional<TarefaRegistroModelo> obterTarefaPorId(Long identificadorTarefa) {
        return this.tarefaMemoriaRepositorio.buscarPorIdentificador(identificadorTarefa);
    }

    public TarefaRegistroModelo criarNovaTarefa(CriacaoTarefaRequisicao requisicao) {
        return this.tarefaMemoriaRepositorio.salvar(requisicao);
    }

    public Optional<TarefaRegistroModelo> alterarTarefaExistente(Long identificadorTarefa, AtualizacaoTarefaRequisicao requisicao) {
        return this.tarefaMemoriaRepositorio.atualizar(identificadorTarefa, requisicao);
    }

    public boolean excluirTarefa(Long identificadorTarefa) {
        return this.tarefaMemoriaRepositorio.remover(identificadorTarefa);
    }
}

@RestController
@RequestMapping("/tarefas")
class TarefaRecursoControlador {
    private final TarefaOperacaoService tarefaOperacaoService;

    public TarefaRecursoControlador(TarefaOperacaoService tarefaOperacaoService) {
        this.tarefaOperacaoService = tarefaOperacaoService;
    }

    @GetMapping
    public ResponseEntity<List<TarefaRegistroModelo>> listar() {
        return ResponseEntity.ok(this.tarefaOperacaoService.listarTodasTarefas());
    }

    @GetMapping("/{identificadorTarefa}")
    public ResponseEntity<TarefaRegistroModelo> buscarPorId(@PathVariable Long identificadorTarefa) {
        return this.tarefaOperacaoService.obterTarefaPorId(identificadorTarefa)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<TarefaRegistroModelo> criar(@RequestBody @Valid CriacaoTarefaRequisicao requisicao) {
        TarefaRegistroModelo tarefaCriada = this.tarefaOperacaoService.criarNovaTarefa(requisicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
    }

    @PutMapping("/{identificadorTarefa}")
    public ResponseEntity<TarefaRegistroModelo> atualizar(
            @PathVariable Long identificadorTarefa,
            @RequestBody @Valid AtualizacaoTarefaRequisicao requisicao) {
        return this.tarefaOperacaoService.alterarTarefaExistente(identificadorTarefa, requisicao)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{identificadorTarefa}")
    public ResponseEntity<Void> deletar(@PathVariable Long identificadorTarefa) {
        boolean foiRemovido = this.tarefaOperacaoService.excluirTarefa(identificadorTarefa);
        if (foiRemovido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

@SpringBootApplication
public class ServidorAplicacaoSpring {
    public static void main(String[] args) {
        SpringApplication.run(ServidorAplicacaoSpring.class, args);
    }
}
