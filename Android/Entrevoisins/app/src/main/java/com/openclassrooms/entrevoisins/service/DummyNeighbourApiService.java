package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavoritesNeighbours() {
        List<Neighbour> favoritesNeighbours = new ArrayList<>();
        for(int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).isFav())
                favoritesNeighbours.add(neighbours.get(i));
        }
        return favoritesNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNeighbourFav(int neighbourId, boolean bool) {
        for (Neighbour neighbour : neighbours)
            if (neighbour.getId() == neighbourId)
                neighbour.setFav(bool);
    }
}
