package com.example.quan_ly_cau_thu_jpa.repository;

import com.example.quan_ly_cau_thu_jpa.entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PlayerRepository implements IPlayerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> findAll() {
        TypedQuery<Player> typedQuery = entityManager.createQuery("from Player", Player.class);
        return typedQuery.getResultList();
    }

    @Override
    public boolean add(Player player) {
        try {
            entityManager.persist(player);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Player findById(int id) {
        return entityManager.find(Player.class, id);
    }

    @Override
    public void deleteById(int id) {
        Player player = findById(id);
        if (player != null) {
            entityManager.remove(player);
        }
    }
}
