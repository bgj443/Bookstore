package fi.haagahelia.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class); // uusi loggeriattribuutti

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// testi datan luominen h2-tietokanta
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository,
			UserRepository userRepository) {
		return (args) -> {

			log.info("save a couple of categories");
			categoryRepository.save(new Category("Sci-Fi"));
			categoryRepository.save(new Category("Fantasy"));
			categoryRepository.save(new Category("Horror"));
			categoryRepository.save(new Category("History"));
			categoryRepository.save(new Category("Poetry"));
			categoryRepository.save(new Category("Non-fiction"));

			log.info("save a couple of books");
			bookRepository.save(new Book("Kauhujen kirja", "Tommi Tommila", "isbn", 2020, 19,
					categoryRepository.findByName("Horror").get(0)));
			bookRepository.save(new Book("Keittion salat", "Keijo Keittaja", "isbn", 2019, 21,
					categoryRepository.findByName("Non-fiction").get(0)));
			bookRepository.save(new Book("Tietokoneiden Historia", "Riitta Riittala", "isbn", 2020, 35,
					categoryRepository.findByName("History").get(0)));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
