package it.epicode;

import it.epicode.dao.EventoDAO;
import it.epicode.entities.EventType;
import it.epicode.entities.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class GestioneEventi {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager(); // Entity Manager è responsabile della gestione delle interazioni col DB
        EventoDAO eventoDAO = new EventoDAO(entityManager);

        Evento evento1 = new Evento("Live Green Day", LocalDate.now(),"Concerto Live dei Green Day a Roma", EventType.PUBBLICO, 10000);
        Evento evento2 = new Evento("Live Machine Gun Kelly", LocalDate.now(),"Concerto Live di Machine Gun Kelly a Milano", EventType.PUBBLICO, 5000);

        eventoDAO.save(evento1);
        eventoDAO.save(evento2);

        long idForTest = 1;

        Evento eventoDB = eventoDAO.getById(idForTest);
        if (eventoDB != null) {
            System.out.println(eventoDB.toString());
        } else {
            System.out.println("Evento con id " + idForTest + " non trovato");
        }

        eventoDAO.deleteById(idForTest);
    }
}
