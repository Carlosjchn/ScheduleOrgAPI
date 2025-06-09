package com.jpa1prueba.jpademo.services;

import org.springframework.stereotype.Service;

import com.jpa1prueba.jpademo.dto.evento.CreateEventoRequest;
import com.jpa1prueba.jpademo.dto.evento.EventoDetailDTO;
import com.jpa1prueba.jpademo.entities.Eventos;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.mappers.EventoMapper;
import com.jpa1prueba.jpademo.repositories.EventosRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventosService {

    private final EventosRepository eventosRepository;
    private final UsuarioService usuarioService;

    public EventosService(EventosRepository eventosRepository, UsuarioService usuarioService) {
        this.eventosRepository = eventosRepository;
        this.usuarioService = usuarioService;
    }

    public List<EventoDetailDTO> getEventosByIdUsuario(Long idUsuario) {
        Usuarios usuario = usuarioService.getUsuarioById(idUsuario);
        return eventosRepository.findByUsuarioAsociado(usuario)
            .stream()
            .map(EventoMapper::toEventoDetailDTO)
            .collect(Collectors.toList());
    }

    public List<EventoDetailDTO> getEventosByIdUsuarioAndMonth(Long idUsuario, int month) {
        Usuarios usuario = usuarioService.getUsuarioById(idUsuario);
        return eventosRepository.findByUsuarioAsociadoAndMonth(usuario, month)
            .stream()
            .map(EventoMapper::toEventoDetailDTO)
            .collect(Collectors.toList());
    }

    public Optional<EventoDetailDTO> getEventoByIdUsuarioAndFecha(Long idUsuario, String fechaText){
        Usuarios usuario = usuarioService.getUsuarioById(idUsuario);
        LocalDate fecha = LocalDate.parse(fechaText);
        return eventosRepository.findByUsuarioAsociadoAndFecha(usuario, fecha)
            .map(EventoMapper::toEventoDetailDTO);
    }

    public List<EventoDetailDTO> getAllEventos() {
        return eventosRepository.findAll()
                .stream()
                .map(EventoMapper::toEventoDetailDTO)
                .toList();
    }

    public Eventos createEvento(CreateEventoRequest request) {
        // Obtener el usuario por ID
        Usuarios usuario = usuarioService.getUsuarioById(request.getIdUsuario());
        
        // Crear la entidad Eventos con la información mínima
        Eventos evento = new Eventos();
        evento.setTitulo(request.getTitulo());
        evento.setUsuarioAsociado(usuario);
        evento.setFecha(request.getFecha());
        evento.setHoraInicio(request.getHoraInicio());
        evento.setHoraFin(request.getHoraFin());
        // horario queda como null (opcional)
        
        return eventosRepository.save(evento);
    }

    // Mantener el método original para compatibilidad con otros usos
    public Eventos createEvento(Eventos evento) {
        return eventosRepository.save(evento);
    }

    public void deleteEvento(Long idEvento) {
        eventosRepository.deleteById(idEvento);
    }

    public Eventos updateEvento(Long idEvento, Eventos evento) {
        Eventos existingEvento = eventosRepository.findById(idEvento)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    
        existingEvento.setFecha(evento.getFecha());
        existingEvento.setHoraInicio(evento.getHoraInicio());
        existingEvento.setHoraFin(evento.getHoraFin());
        existingEvento.setUsuarioAsociado(evento.getUsuarioAsociado());
    
        return eventosRepository.save(existingEvento);
    }
}