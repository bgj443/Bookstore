package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

//Test create, delete and search functionalities

//@RunWith(SpringRunner.class)  //JUnit4
@ExtendWith(SpringExtension.class) // JUnit5 eli Jupiter
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository Brepository;

	@Autowired
	private TestEntityManager Del;

	@Test // testataan BookRepositoryn findByTitle()-metodin toimivuutta
	public void findByTitle() {
		List<Book> books = Brepository.findByTitle("Keittion salat");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Keittion salat");
	}

	@Test
	public void createNewCategory() {
		Book book = new Book("Kauhujen kauhu", "Henny Horrori", "isbn", 2020, 19, null);

		Brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	void deleteById() {
		Book book = new Book("Kauhujen Poisto", "Henny Deletoija", "isbn", 2020, 19, null);
		final Long id = Del.persistAndGetId(book, Long.class);
		Brepository.deleteById(id);
		Del.flush();
		Book after = Del.find(Book.class, id);
		assertThat(after).isNull();
	}

}
