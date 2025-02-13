package com.example.PawsitiveAPI.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

@Component
public class DBInit {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initializeDatabase() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM quote", Integer.class);
        
        if (count == 0) {
        	List<String> quotes = Arrays.asList(
                    "La vie est comme un ours en peluche : douce, réconfortante et pleine de surprises.",
                    "Chaque jour est une nouvelle opportunité de câliner un ours en peluche.",
                    "La persévérance est la clé du succès, tout comme la douceur est la clé d'un bon ours en peluche.",
                    "La vie est un ours en peluche : douce en apparence, mais pleine de coutures qui tiennent le tout ensemble.",
                    "Un ours en peluche n'est pas juste un jouet, c'est un récepteur de secrets murmurés et de chagrins silencieux.",
                    "L'optimisme, c'est croire que le monde est un grand ours en peluche, même quand il gronde.",
                    "Un cœur doux est comme un ours en peluche : il offre réconfort même lorsqu'il est usé par les épreuves.",
                    "La nostalgie est l'odeur d'un vieil ours en peluche : un parfum de moments précieux et de souvenirs oubliés.",
                    "Trouver la paix intérieure, c'est se blottir contre l'ours en peluche de son âme.",
                    "La sagesse, c'est savoir que même les ours en peluche les plus doux ont besoin de temps pour se réparer.",
                    "Le bonheur, c'est un ours en peluche sous le bras, prêt à affronter toutes les aventures.",
                    "L'amitié, c'est partager son ours en peluche avec quelqu'un d'autre.",
                    "L'amour, c'est réparer l'ours en peluche de l'autre quand il est déchiré.",
                    "L'innocence, c'est voir le monde à travers les yeux d'un ours en peluche.",
                    "La résilience, c'est se relever, même après avoir été jeté au fond du coffre à jouets.",
                    "La gratitude, c'est chérir chaque câlin de son ours en peluche.",
                    "L'espoir, c'est croire que même l'ours en peluche le plus usé peut encore apporter du réconfort.",
                    "La gentillesse, c'est offrir son ours en peluche à quelqu'un qui en a besoin.",
                    "La simplicité, c'est trouver la joie dans un simple ours en peluche.",
                    "L'authenticité, c'est être soi-même, même si on est un ours en peluche un peu bizarre.",
                    "Le courage, c'est tenir son ours en peluche fermement, même quand on a peur.",
                    "L'empathie, c'est comprendre la tristesse dans les yeux d'un ours en peluche abandonné.",
                    "La vie est un voyage, et parfois, tout ce dont on a besoin, c'est un ours en peluche pour tenir la route."
                );
        	for (String quote : quotes) {
        		jdbcTemplate.update("INSERT INTO quote (content) VALUES (?)", quote);
        	}
                
            System.out.println("Initialisation de la base de données avec des citations inspirantes...");
        } else {
            System.out.println("La base de données contient déjà des citations. Pas d'initialisation nécessaire.");
        }
    }
}
