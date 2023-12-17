package fi.haagahelia.coolreads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.AppUserRepository;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;

@SpringBootApplication
public class CoolReadsApplication {
	@Autowired
	private AppUserRepository userRepository;
	@Autowired
	private CategoryRepository catRepos;
	@Autowired
	private ReadingRecommendationRepository readRepo;

	public static void main(String[] args) {
		SpringApplication.run(CoolReadsApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return (args) -> {
			AppUser newUser = new AppUser("danrey", "$2a$12$6xevbg2adIMMUUNfODlj9uTvHOgWQU3zGItR/5HNfOzsdzW0OaIGq",
					"USER");
			userRepository.save(newUser);

			Category newCat = new Category("IT", newUser);
			catRepos.save(newCat);

			ReadingRecommendation newRec = new ReadingRecommendation("myTitle",
					"https://www.linkedin.com/pulse/five-benefits-building-impactful-personal-brand-tracy-ho/",
					"description", newCat, newUser);
			readRepo.save(newRec);
		};
	}

}
