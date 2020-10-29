package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.bookstore.web.BookController;
import fi.haagahelia.bookstore.web.CategoryController;


//@RunWith(SpringRunner.class) // JUnit4
@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@SpringBootTest
public class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;
	
	@Autowired
	private CategoryController CategoryController;
	
	@Test
	public void contextLoads1() {
		assertThat(bookController).isNotNull();
	}
	
	@Test
	public void contextLoads() {
		assertThat(CategoryController).isNotNull();
	}

}
