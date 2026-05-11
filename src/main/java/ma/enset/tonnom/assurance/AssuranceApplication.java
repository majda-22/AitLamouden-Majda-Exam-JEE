package ma.enset.tonnom.assurance;

import ma.enset.tonnom.assurance.entities.*;
import ma.enset.tonnom.assurance.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;

@SpringBootApplication
public class AssuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssuranceApplication.class, args);
    }

    
    @Bean
    CommandLineRunner initDatabase(
            ClientRepository clientRepo,
            ContratAssuranceRepository contratRepo,
            PaiementRepository paiementRepo) {

        return args -> {

            
            Client c1 = Client.builder()
                    .nom("Ahmed Bennani")
                    .email("ahmed@gmail.com")
                    .build();

            Client c2 = Client.builder()
                    .nom("Fatima Zahra")
                    .email("fatima@gmail.com")
                    .build();

            clientRepo.save(c1);
            clientRepo.save(c2);
            System.out.println("✅ Clients créés : " + clientRepo.findAll());

            
            ContratAutomobile auto = new ContratAutomobile();
            auto.setDateSouscription(LocalDate.now());
            auto.setStatut(StatutContrat.EN_COURS);
            auto.setMontantCotisation(500.0);
            auto.setDureeContrat(12);
            auto.setTauxCouverture(80.0);
            auto.setClient(c1);
            auto.setNumeroImmatriculation("12345-AB-06");
            auto.setMarqueVehicule("Toyota");
            auto.setModeleVehicule("Corolla");
            contratRepo.save(auto);

            
            ContratHabitation hab = new ContratHabitation();
            hab.setDateSouscription(LocalDate.now());
            hab.setStatut(StatutContrat.VALIDE);
            hab.setMontantCotisation(300.0);
            hab.setDureeContrat(24);
            hab.setTauxCouverture(90.0);
            hab.setClient(c2);
            hab.setTypeLogement(TypeLogement.APPARTEMENT);
            hab.setAdresse("12 Rue Hassan II, Casablanca");
            hab.setSuperficie(85.0);
            contratRepo.save(hab);

            System.out.println("✅ Contrats créés : " + contratRepo.findAll().size());

           
            Paiement p1 = Paiement.builder()
                    .date(LocalDate.now())
                    .montant(500.0)
                    .type(TypePaiement.MENSUALITE)
                    .contrat(auto)
                    .build();
            paiementRepo.save(p1);

            System.out.println("✅ Paiement créé");
            System.out.println("🚀 Base de données initialisée avec succès !");
        };
    }
}