package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;


@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository Crepository;
	
	@Autowired
	private TestEntityManager Dell;

	@Test // testataan BookRepositoryn findByTitle()-metodin toimivuutta
	public void findByName() {
		List<Category> category = Crepository.findByName("Horror");
		assertThat(category).hasSize(1);
		assertThat(category.get(0).getName()).isEqualTo("Horror");
	}

	@Test
	public void createNewCategory() {
		Category category = new Category("Testi");

		Crepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}

	@Test
	void deleteById() {
		Category category = new Category("Testi");
		final Long id = Dell.persistAndGetId(category, Long.class);
		Crepository.deleteById(id);
		Dell.flush();
		Category Testi_kategoria = Dell.find(Category.class, id);
		assertThat(Testi_kategoria).isNull();
	}

}

