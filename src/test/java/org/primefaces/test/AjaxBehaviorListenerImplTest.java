package org.primefaces.test;

import org.hamcrest.Matchers;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.Graphene;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.test.util.SeleniumTest;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author David Matějček
 */
public class AjaxBehaviorListenerImplTest extends SeleniumTest {

    @FindBy(id = "orderAddMat:orderAddMatForm")
    private WebElement form;

    @FindBy(id = "orderAddMat:orderAddMatForm:orderAddMat_single_material_panel")
    private WebElement panel;

    @Test
    @RunAsClient
    public void test() {
        assertThat("title", this.browser.getTitle(), Matchers.equalTo("AutoComplete"));
        this.browser.navigate().to(contextUrl.toExternalForm() + "/autoCompleteTest.xhtml");
        assertThat("title", this.browser.getTitle(), Matchers.equalTo("AutoComplete from dialog"));

        final WebElement button = this.form.findElement(By.className("ui-button-icon-primary"));
        Graphene.guardAjax(button).click();

        // does not work on PF 9.0-SNAPSHOT + chromedriver
//        assertTrue("panel visible", panel.isDisplayed());
        final WebElement ul = this.panel.findElement(By.tagName("ul"));
        final WebElement li3 = ul.findElement(By.xpath("li[@data-item-value=\"3\"]"));
        Graphene.guardAjax(li3).click();
    }
}
