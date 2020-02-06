package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint pierre du mont à 5km", "+33 6 86 57 90 14", "www.facebook.fr/caroline",
                    "Bonjour! je souhaiterais faire de la marche nordique." +
                            "Pas initié, je recherche une ou plusieur personnes susceptible de m'accompagner! J'aime les jeux de cartes tels la belote et le tarot...", true),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", true),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "Une adresse ou un emplacement",
                    "Un numéro de télephone", "Une adresse vers réseau social", "Une description", false)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
