package com.vacancy_scrapper.model.page;

import com.vacancy_scrapper.model.Job;
import com.vacancy_scrapper.utils.ConfigLoader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class JobListingsPage extends BasePage {
    private final String url = ConfigLoader.getProperty("jobListingsPage.url");
    private final String vacancyXpath = "//li[@class='l-vacancy' or @class='l-vacancy __hot']";

    public void open() {
        log.info("Opening URL: {}", url);
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(vacancyXpath)));
    }

    public List<Job> getJobListings() {
        log.info("Fetching job listings.");
        List<Job> jobs = new LinkedList<>();
        List<WebElement> jobCards = driver.findElements(By.xpath(vacancyXpath));

        jobCards.forEach(jobCard -> {
            String title = jobCard.findElement(By.cssSelector("a.vt")).getText();
            String company = jobCard.findElement(By.cssSelector("a.company")).getText();
            String location = jobCard.findElement(By.cssSelector("span.cities")).getText();
            String link = jobCard.findElement(By.cssSelector("a.vt")).getAttribute("href");
            String datePosted = jobCard.findElement(By.cssSelector("div.date")).getText();
            if (datePosted.isBlank()) {
                datePosted = "Hot Vacancy";
            }
            String description = jobCard.findElement(By.cssSelector("div.sh-info")).getText();

            jobs.add(new Job(title, company, location, link, datePosted, description));
        });
        log.info("Number of jobs fetched: {}", jobs.size());
        return jobs;
    }
}
