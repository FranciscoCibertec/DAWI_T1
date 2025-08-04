package com.cibertec.dawi_t1_vilchez_quispe.controller;

import com.cibertec.dawi_t1_vilchez_quispe.entity.Alumno;
import com.cibertec.dawi_t1_vilchez_quispe.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = "*")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    // Crear alumno
    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@RequestBody Alumno alumno) {
        if (alumnoService.existeAlumnoPorEmail(alumno.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        Alumno nuevoAlumno = alumnoService.guardarAlumno(alumno);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }

    // Obtener alumno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerAlumno(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoService.obtenerAlumnoPorId(id);
        return alumno.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener todos los alumnos
    @GetMapping
    public ResponseEntity<List<Alumno>> obtenerTodosLosAlumnos() {
        List<Alumno> alumnos = alumnoService.obtenerTodosLosAlumnos();
        return ResponseEntity.ok(alumnos);
    }

    // Buscar alumno por email
    @GetMapping("/por-email")
    public ResponseEntity<Alumno> buscarAlumnoPorEmail(@RequestParam String email) {
        Optional<Alumno> alumno = alumnoService.buscarAlumnoPorEmail(email);
        return alumno.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener alumnos paginados
    @GetMapping("/paginados")
    public ResponseEntity<Page<Alumno>> obtenerAlumnosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<Alumno> alumnos = alumnoService.obtenerAlumnosPaginados(
                page, size, sortBy, sortDir);
        return ResponseEntity.ok(alumnos);
    }

    // Actualizar alumno
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(
            @PathVariable Long id,
            @RequestBody Alumno alumno) {
        Optional<Alumno> alumnoExistente = alumnoService.obtenerAlumnoPorId(id);

        if (alumnoExistente.isPresent()) {
            alumno.setId(id);
            Alumno alumnoActualizado = alumnoService.guardarAlumno(alumno);
            return ResponseEntity.ok(alumnoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar alumno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoService.obtenerAlumnoPorId(id);

        if (alumno.isPresent()) {
            alumnoService.eliminarAlumno(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}