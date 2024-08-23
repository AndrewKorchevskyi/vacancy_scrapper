package com.vacancy_scrapper.email_service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.vacancy_scrapper.model.Job;
import com.vacancy_scrapper.utils.ConfigLoader;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@UtilityClass
public class EmailService {
    private static final String API_KEY = ConfigLoader.getProperty("sendgrid.apiKey");
    private static final String FROM_EMAIL = ConfigLoader.getProperty("sendgrid.fromEmail");
    private static final String TO_EMAIL = ConfigLoader.getProperty("sendgrid.toEmail");

    @SneakyThrows(IOException.class)
    public static void sendEmail(String subject, List<Job> jobs) {
        Email from = new Email(FROM_EMAIL);
        Email to = new Email(TO_EMAIL);
        Content content = new Content("text/html", generateHtmlContent(jobs));
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);

        log.info("Response Code: {}", response.getStatusCode());
    }

    private static String generateHtmlContent(List<Job> jobs) {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");

        jobs.forEach(job -> htmlContent
                .append("<div style='border: 1px solid #ddd; padding: 16px; margin-bottom: 16px;'>")
                .append("<h2>").append(job.getTitle()).append("</h2>")
                .append("<p><strong>Company:</strong> ").append(job.getCompany()).append("</p>")
                .append("<p><strong>Location:</strong> ").append(job.getLocation()).append("</p>")
                .append("<p><strong>Date Posted:</strong> ").append(job.getDatePosted()).append("</p>")
                .append("<p><a href='").append(job.getLink()).append("' target='_blank' style='color: blue;'>View Job</a></p>")
                .append("<p><strong>Description:</strong> ").append(job.getDescription()).append("</p>")
                .append("</div>"));

        htmlContent.append("</body></html>");
        return htmlContent.toString();
    }
}
