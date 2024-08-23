package com.vacancy_scrapper;

import com.vacancy_scrapper.email_service.EmailService;
import com.vacancy_scrapper.model.Job;
import com.vacancy_scrapper.model.page.JobListingsPage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var jobListingsPage = new JobListingsPage();
        jobListingsPage.open();
        var jobs = jobListingsPage.getJobListings();

        jobs.forEach(System.out::println);
        EmailService.sendEmail("New Job Listings", jobs);
    }
}
