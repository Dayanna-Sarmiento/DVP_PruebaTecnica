package com.PruebaTecnica.Ticket;

import com.PruebaTecnica.Ticket.InfoTicket;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoTicket extends JpaRepository<InfoTicket, Long>{

	Optional<InfoTicket> findByUsuario(String usuario);

}
