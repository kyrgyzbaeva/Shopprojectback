package com.shopApp;

import com.shopApp.entity.Bag;
import com.shopApp.repository.BagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BagRepositoryTest {

    @Autowired
    private BagRepository bagRepository;

    @Test
    public void testFindAll() {
        // Given
        Bag bag1 = new Bag("Brand1", 100.0, "China", "Leather");
        Bag bag2 = new Bag("Brand2", 150.0, "Italy", "Canvas");
        bagRepository.save(bag1);
        bagRepository.save(bag2);

        // When
        List<Bag> bags = bagRepository.findAll();

        // Then
        assertThat(bags).isNotNull();
        assertThat(bags.size()).isEqualTo(2);
    }
}
