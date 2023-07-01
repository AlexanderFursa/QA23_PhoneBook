package com.phonebook.tests;

import com.phonebook.fw.DataProviders;
import com.phonebook.model.Contact;
import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateContactTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (!app.getHeader().isLoginLinkPresent()) {
      app.getHeader().clickOnSignOutButton();
    }
    app.getHeader().clickOnLoginLink();
    app.getUser().fillLoginRegistrationForm(new User().setEmail("kpa@gmail.com").setPassword(
        "Nw12345$"));
    app.getUser().clickOnLoginButton();
  }

  @Test
  public void addContactPositiveTest() {

    app.getHeader().clickOnAddLink();

    app.getContact().fillAddContactForm(new Contact()
        .setName("Karl")
        .setLastname("Adam")
        .setPhone("1234567890")
        .setEmail("adam@gm.com")
        .setAddress("Koblenz")
        .setDesc("goalkeeper"));

    app.getContact().clickOnSaveButton();

    Assert.assertTrue(app.getContact().isContactCreated("Karl"));

  }

  @Test(dataProviderClass = DataProviders.class,dataProvider = "addContactFromCsvFile")
  public void addContactFromCsvFilePositiveTest(Contact contact) {

    app.getHeader().clickOnAddLink();
    app.getContact().fillAddContactForm(contact);

    app.getContact().clickOnSaveButton();

  }

  @AfterMethod
  public void postCondition() {
    app.getContact().removeContact();
  }

}