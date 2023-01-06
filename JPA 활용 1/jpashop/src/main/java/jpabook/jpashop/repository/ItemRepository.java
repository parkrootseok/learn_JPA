package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item) {

        if (item.getId() == null) {     // 새로운 아이템 생성
            em.persist(item);
        } else {                        // 병합을 이용하여 기존에 존재하는 아이템을 수정 (단, 병합을 사용할 땐 null 주의)
            em.merge(item);
        }
    }

    public Item find(Long itemId) {
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

}
