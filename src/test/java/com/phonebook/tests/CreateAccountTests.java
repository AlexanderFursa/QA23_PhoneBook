package com.phonebook.tests;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {


  @BeforeMethod
  public void ensurePrecondition() {
    if (!app.getHeader().isLoginLinkPresent()) {
      app.getHeader().clickOnSignOutButton();
    }
    //click on Login link
    app.getHeader().clickOnLoginLink();
  }

  @Test
  public void existedUserRegistrationNegativeTest() {

    app.getUser().fillLoginRegistrationForm(new User().setEmail("kpa@gmail.com").setPassword("Kan123$-_$"));

    app.getUser().clickOnRegistrationButton();

    Assert.assertTrue(app.getUser().isAlertPresent());
  }

}