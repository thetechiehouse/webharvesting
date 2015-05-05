package com.htmlunit.sample;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;


public class Htmlunit {
	@Test
	public void homePage() throws Exception {
	    final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	    Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

	    final String pageAsXml = page.asXml();
	    Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

	    final String pageAsText = page.asText();
	    Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));

	    webClient.closeAllWindows();
	}
	
	@Test
	public void homePage_Firefox() throws Exception {
	    final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
	    final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	    Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

	    webClient.closeAllWindows();
	}
	
	@Test
	public void submittingForm() throws Exception {
	    final WebClient webClient = new WebClient();

	    // Get the first page
	    final HtmlPage page1 = webClient.getPage("http://jaunt-api.com/examples/signup2.htm");

	    // Get the form that we are dealing with and within that form, 
	    // find the submit button and the field that we want to change.
	    final HtmlForm form = page1.getFormByName("signup");

	    final HtmlSubmitInput button = form.getInputByName("action");
	    final HtmlTextInput textField = form.getInputByName("email");

	    // Change the value of the text field
	    textField.setValueAttribute("prithvi.atal@adp.com");

	    // Now submit the form by clicking the button and get back the second page.
	    final HtmlPage page2 = button.click();
	    System.out.println(page2.getBody().asText());
	    webClient.closeAllWindows();
	}
}

