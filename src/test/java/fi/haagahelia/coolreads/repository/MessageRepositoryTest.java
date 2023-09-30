package fi.haagahelia.coolreads.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fi.haagahelia.coolreads.model.Message;

@SpringBootTest
class MessageRepositoryTest {
	@Autowired
	private MessageRepository messageRepository;

	@BeforeEach
	void setUp() throws Exception {
		messageRepository.deleteAll();
	}

	@Test
	void findAllReturnsEmptyListWhenNoMessagesExist() {
		List<Message> messages = messageRepository.findAll();

		assertEquals(0, messages.size());
	}
	
	@Test
	void findAllReturnsListOfMessagesWhenMessagesExist() {
		messageRepository.save(new Message("Hello world!"));
		List<Message> messages = messageRepository.findAll();
		
		assertEquals(1, messages.size());
		assertEquals("Hello world!", messages.get(0).getContent());
	}

}
