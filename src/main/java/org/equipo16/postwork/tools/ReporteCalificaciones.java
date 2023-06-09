package org.equipo16.postwork.tools;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.equipo16.postwork.model.Curso;
import org.equipo16.postwork.model.Estudiante;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ReporteCalificaciones {
    @Data
    @RequiredArgsConstructor
    static class Reporte {
       private final String nombreEstudiante;
       private final Integer calificacion;
    }

    public List<Reporte> alfabetico(Curso curso){
        return generaLista(curso.getCalificaciones(), Comparator.comparing(Reporte::getNombreEstudiante));
    }

    public List<Reporte> calificacion(Curso curso){
        return generaLista(curso.getCalificaciones(), (r1, r2 )-> r2.getCalificacion().compareTo(r1.getCalificacion()));
    }

    private List<Reporte> generaLista(Map<Estudiante, Integer> calificaciones, Comparator<Reporte> comparator){
        return calificaciones.entrySet().stream()
                .map(e -> new Reporte(e.getKey().getNombreCompleto(), e.getValue()))
                .sorted(comparator)
                .collect(Collectors.toList());

    }

}
