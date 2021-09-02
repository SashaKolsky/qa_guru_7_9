package tests.demoqa.model;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import tests.demoqa.components.Calendar;

import java.io.File;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationForm {

    private final SelenideElement firstName = $("#firstName");
    private final SelenideElement lastName = $("#lastName");
    private final SelenideElement email = $("#userEmail");
    private final ElementsCollection genderOptions = $$("label[for*=gender-radio]");
    private final SelenideElement phone = $("#userNumber");
    private final SelenideElement subjectsContainer = $("#subjectsContainer");
    private final SelenideElement subjects = subjectsContainer.$("input");
    private final ElementsCollection hobbyOptions = $$x("//input[contains(@id,'hobbies')]/following-sibling::label");
    private final SelenideElement fileUpload = $("#uploadPicture");
    private final SelenideElement currentAddress = $("#currentAddress");
    private final SelenideElement state = $("#state input");
    private final SelenideElement city = $("#city input");
    private final SelenideElement submitButton = $("#submit");

    public StudentRegistrationForm fillFirstName(String value) {
        firstName.val(value);
        return this;
    }

    public StudentRegistrationForm fillLastName(String value) {
        lastName.val(value);
        return this;
    }

    public StudentRegistrationForm fillEmail(String value) {
        email.val(value);
        return this;
    }

    public StudentRegistrationForm selectGender(String option) {
        genderOptions.find(matchText(option)).click();
        return this;
    }

    public StudentRegistrationForm fillPhone(String value) {
        phone.val(value);
        return this;
    }

    public StudentRegistrationForm fillDateOfBirth(LocalDate date) {
        Calendar.setDate(date);
        return this;
    }

    public StudentRegistrationForm fillSubjects(List<String> values) {
        subjectsContainer.click();
        for (String subject : values) {
            subjects.val(subject).pressTab();
        }
        return this;
    }

    public StudentRegistrationForm fillHobbies(List<String> values) {
        for (String hobby : values) {
            hobbyOptions.find(text(hobby)).click();
        }
        return this;
    }

    public StudentRegistrationForm loadPicture(String resourceName) {
        File fileToUpload = null;
        try {
            fileToUpload = new File(getClass().getClassLoader().getResource(resourceName).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        fileUpload.uploadFile(fileToUpload);
        return this;
    }

    public StudentRegistrationForm fillCurrentAddress(String value) {
        currentAddress.val(value);
        return this;
    }

    public StudentRegistrationForm fillState(String value) {
        state.val(value).pressTab();
        return this;
    }

    public StudentRegistrationForm fillCity(String value) {

        city.val(value).pressTab();
        return this;
    }

    public void submitForm() {
        submitButton.scrollTo().click();
    }

}
