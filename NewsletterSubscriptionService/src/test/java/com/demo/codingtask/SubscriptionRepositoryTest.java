package com.demo.codingtask;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.codingtask.entity.Subscriptions;
import com.demo.codingtask.repository.SubscriptionRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=validate" })
class SubscriptionRepositoryTest {
	@Autowired
	private SubscriptionRepository repository;

	@Test
	public void testSubscriptionRepository() {
		repository.save(new Subscriptions(1000L,1));
		assertThat(repository.findByUserId(1000L)).isNotNull();
	}

}
