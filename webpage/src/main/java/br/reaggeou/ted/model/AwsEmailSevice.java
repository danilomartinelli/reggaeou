package br.reaggeou.ted.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.amazonaws.services.simpleemail.model.Template;



public class AwsEmailSevice {
	private static final String FROM = "lemosmgabriel@gmail.com";
	private static final String SUBJECT = "Não perca tempo! Confira quais eventos acontecerão!";
	private static final String HTMLPART = "<div style='display:flex;flex-direction:column'>\\r\\n<h1>Shows e Eventos<\\/h1>\\r\\n<hr \\/>\\r\\n{{#each event}}\\r\\n<div>\\r\\n<img src={{folder}}\\/>\\r\\n<div>\\r\\n<h2>{{title}}<\\/h2>\\r\\n<div>\\r\\n<div>\\r\\n<label>{{year}}<\\/labe>\\r\\n<label>{{day}}{{month}}<\\/label>\\r\\n<\\/div>\\r\\n<p>{{local}}<\\/p>\\r\\n<\\/div>\\r\\n<a href={{href}}>Ver detalhes<\\/a>\\r\\n <\\/div>\\r\\n<\\/div>\\r\\n{{\\/each}}\\r\\n<\\/div>";

	private User user;
	private List<Event> events;

	public AwsEmailSevice(User user, List<Event> events) {
		super();
		this.user = user;
		this.events = events;
	}
	
	
	public String createTemplatedData() {
		JSONArray templateData = new JSONArray("{event:[]}");
		
		for(Event event : events) {
			JSONObject data = new JSONObject();
			data.put("folder", event.getFolder());
			data.put("title", event.getTitle());
			data.put("year", event.getDate().toString().substring(0,4));
			data.put("day", event.getDate().toString().substring(8));
			data.put("month", event.getDate().toString().substring(4,6));
			data.put("local", event.getLocal());
			data.put("href", event.getHref());
			templateData.put(data);
		}
		
		return templateData.toString();
	}
	
	
	public void sendEmail() {
		try {
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withRegion(Regions.US_EAST_2).build();
			
			Template t = new Template();
			t.setTemplateName("templateEmail");
			t.setSubjectPart(SUBJECT);
			t.setHtmlPart(HTMLPART);
			t.setTextPart("");

			SendTemplatedEmailRequest request = new SendTemplatedEmailRequest().withDestination(new Destination().withToAddresses(user.getEmail()))
					.withTemplate("templateEmail")
					.withTemplateData(createTemplatedData())
					.withSource(FROM);
			// Comment or remove the next line if you are not using a
			// configuration set
			// .withConfigurationSetName(CONFIGSET);
			client.sendTemplatedEmail(request);
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
		}
	}
}
