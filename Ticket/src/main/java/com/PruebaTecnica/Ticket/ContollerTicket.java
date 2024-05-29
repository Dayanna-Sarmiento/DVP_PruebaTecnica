package com.PruebaTecnica.Ticket;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class ContollerTicket {

	@Autowired
	private RepoTicket repo;
	
	@PostMapping
	public InfoTicket crearTicket(@RequestBody InfoTicket ticket) {
		return repo.save(ticket);
	}
	
	@GetMapping("/{id}")
	public InfoTicket getTicketById(@PathVariable Long id) {
		return repo.findById(id).orElseThrow(()->new IllegalArgumentException("No se encontro ticket con el id: " + id));
    }
	
	@PutMapping("/{id}")
	public InfoTicket updateTicket(@PathVariable Long id, @RequestBody InfoTicket ticketDetails) {
		InfoTicket info = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontro ticket con el id: " + id));

        info.setUsuario(ticketDetails.getUsuario());
        info.setFechaActualizacion(new Date());
        info.setStatus(ticketDetails.isStatus());

        return repo.save(info);
    }
	
	@DeleteMapping("/{id}")
	public void EliminarTicket(@PathVariable Long id) {
		repo.deleteById(id);
	}
	
	@GetMapping
	public Page<InfoTicket> getAllTickets(Pageable pageable){
		return repo.findAll(pageable);
	}
	
	@GetMapping("/buscar")
	public InfoTicket getTicketByUser(@RequestParam String usuario) {
		return repo.findByUsuario(usuario).orElseThrow(()-> new IllegalArgumentException("No se encontro ticket para el ususario: " + usuario));
	}
}
