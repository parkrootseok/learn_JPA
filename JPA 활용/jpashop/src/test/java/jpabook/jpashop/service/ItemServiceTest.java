package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;



    public ItemServiceTest() {}

    @Test
    public void 상품_저장() throws Exception {
        //given
        Item item = new Item();
        item.setName("item1");

        //when
        itemService.saveItem(item);

        //then
        assertEquals(item, itemService.findItem(item.getId()));
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품_재고_없음() throws Exception {
        //given
        Item item = new Item();
        item.setName("item1");

        //when
        itemService.saveItem(item);
        item.removeStock(1);

        //then
        fail("예외 발생");
    }


}