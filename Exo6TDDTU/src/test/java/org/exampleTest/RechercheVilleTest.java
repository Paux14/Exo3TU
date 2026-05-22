package org.exampleTest;

import org.example.NotFoundException;
import org.example.RechercheVille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RechercheVilleTest {

    private static final List<String> TOUTES_LES_VILLES = List.of(
            "Paris", "Budapest", "Skopje", "Rotterdam", "Valence", "Vancouver",
            "Amsterdam", "Vienne", "Sydney", "New York", "Londres", "Bangkok",
            "Hong Kong", "Dubaï", "Rome", "Istanbul"
    );

    private RechercheVille rechercheVille;

    @BeforeEach
    void setUp() {
        rechercheVille = new RechercheVille();
    }

    // Étape 1 : moins de 2 caractères → NotFoundException
    @ParameterizedTest
    @ValueSource(strings = {"", "a", " "})
    void etape1_rechercheMoinsDeDeuxCaracteres_leveNotFoundException(String mot) {
        assertThrows(NotFoundException.class, () -> rechercheVille.Rechercher(mot));
    }

    // Étape 2 : au moins 2 caractères → villes commençant par le texte de recherche
    @Test
    void etape2_rechercheParPrefixe_retourneVillesCommencantParTexte() {
        assertEquals(List.of("Valence", "Vancouver"), rechercheVille.Rechercher("Va"));
    }

    // Étape 3 : recherche insensible à la casse
    @Test
    void etape3_rechercheInsensibleALaCasse_retourneMemesResultats() {
        assertEquals(List.of("Valence", "Vancouver"), rechercheVille.Rechercher("va"));
        assertEquals(List.of("Paris"), rechercheVille.Rechercher("PARIS"));
    }

    // Étape 4 : le texte peut être une partie du nom (pas seulement un préfixe)
    @Test
    void etape4_recherchePartielle_retourneVilleContenantTexte() {
        assertEquals(List.of("Budapest"), rechercheVille.Rechercher("ape"));
    }

    // Étape 5 : astérisque → toutes les villes
    @Test
    void etape5_rechercheAsterisque_retourneToutesLesVilles() {
        assertEquals(TOUTES_LES_VILLES, rechercheVille.Rechercher("*"));
    }
}
