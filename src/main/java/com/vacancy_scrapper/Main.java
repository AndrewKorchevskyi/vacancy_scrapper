package com.vacancy_scrapper;

import com.vacancy_scrapper.model.Job;
import com.vacancy_scrapper.model.page.JobListingsPage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        JobListingsPage jobListingsPage = new JobListingsPage();
        jobListingsPage.open();
        List<Job> jobs = jobListingsPage.getJobListings();

        jobs.forEach(System.out::println);
    }
}
