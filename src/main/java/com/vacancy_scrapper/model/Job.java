package com.vacancy_scrapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Job {
    private final String title;
    private final String company;
    private final String location;
    private final String link;
    private final String datePosted;
    private final String description;
}
