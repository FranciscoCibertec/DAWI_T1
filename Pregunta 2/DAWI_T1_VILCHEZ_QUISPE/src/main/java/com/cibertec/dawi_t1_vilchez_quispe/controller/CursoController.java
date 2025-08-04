package com.cibertec.dawi_t1_vilchez_quispe.controller;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Curso;
import com.cibertec.dawi_t1_vilchez_quispe.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Crear curso
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.guardarCurso(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    // Obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.obtenerCursoPorId(id);
        return curso.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener todos los cursos
    @GetMapping("/listarCursos")
    public ResponseEntity<List<Curso>> obtenerTodosLosCursos() {
        List<Curso> cursos = cursoService.obtenerTodosLosCursos();
        return ResponseEntity.ok(cursos);
    }
    // Buscar cursos por nombre con paginación
    @GetMapping("/buscarByNombre")
    public ResponseEntity<List<Curso>> buscarCursos(
            @RequestParam String nombre
) {
        List<Curso> cursos = cursoService.buscarCursosPorNombre(nombre);
        return ResponseEntity.ok(cursos);
    }
    // Buscar cursos por nombre con paginación
    @GetMapping("/buscarByNombrePaginado")
    public ResponseEntity<Page<Curso>> buscarCursos(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<Curso> cursos = cursoService.buscarCursosPorNombrePaginado(
                nombre, page, size, sortBy, sortDir);
        return ResponseEntity.ok(cursos);
    }

    // Obtener cursos paginados
    @GetMapping("/paginados")
    public ResponseEntity<Page<Curso>> obtenerCursosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Curso> cursos = cursoService.obtenerCursosPaginados(page, size);
        return ResponseEntity.ok(cursos);
    }

    // Buscar cursos por créditos mínimos
    @GetMapping("/por-creditos")
    public ResponseEntity<Page<Curso>> buscarCursosPorCreditos(
            @RequestParam int minCreditos,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Curso> cursos = cursoService.buscarCursosPorCreditos(
                minCreditos, page, size);
        return ResponseEntity.ok(cursos);
    }

    // Actualizar curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(
            @PathVariable Long id,
            @RequestBody Curso curso) {
        Optional<Curso> cursoExistente = cursoService.obtenerCursoPorId(id);

        if (cursoExistente.isPresent()) {
            curso.setId(id);
            Curso cursoActualizado = cursoService.guardarCurso(curso);
            return ResponseEntity.ok(cursoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.obtenerCursoPorId(id);

        if (curso.isPresent()) {
            cursoService.eliminarCurso(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
