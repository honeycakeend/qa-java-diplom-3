package user;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class UserGenerate {
    public static Faker faker = new Faker();

    @Step("Create random user")
    public static User createRandom() {
        return new User(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                faker.internet().password());
    }

}
