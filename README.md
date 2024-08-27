# vacancy_scrapper

Very simple tool designed for me to get list of latest vacancies from https://jobs.dou.ua/. Don't take it seriously, was just having fun :)

## Configuration
This URL should be configured in config.properties corresponding your vacancies query, this one is for QA Automation vacancies with 5+ years of experience, it's set by default (for me, hehe):
```
jobListingsPage.url=https://jobs.dou.ua/vacancies/?category=QA&search=Automation&exp=5plus
```

Set those properties in config.properties file if you want to use SendGrid to send emails with vacancies list:
```
sendgrid.apiKey=<your_sendgrid_api_key>
sendgrid.fromEmail=<your_from_email_in_selengrid>
sendgrid.toEmail=<any_email_to_recieve_vacancies>
```

## How to run
Just run the main() method in Main.class. If you configured SendGrid, you will receive an email with vacancies list. If not, you will see the list of vacancies in console.
Also, you can execute it with maven: `mvn exec:java`

## PS
Idea was to run it on daily basis in Docker container, but there is an issue with my M1 MacBook running ChromeDriver correctly on aaarch64 architecture, I will try to fix it later.
