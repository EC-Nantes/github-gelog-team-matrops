package org.centrale.hceres;

import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.repository.ResearcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TestChercheurs implements CommandLineRunner {

    @Autowired
    private ResearcherRepository researcherRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestChercheurs.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("📋 Liste des chercheurs :");
        List<Researcher> chercheurs = researcherRepository.findAll();
        for (Researcher r : chercheurs) {
            System.out.println("- " + r.getResearcherName() + " " + r.getResearcherSurname());
        }
    }
}
