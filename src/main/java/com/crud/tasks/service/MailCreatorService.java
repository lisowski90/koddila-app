package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("function 1");
        functionality.add("function 2");
        functionality.add("function 3");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("bye", "Sincerely: " + adminConfig.getAdminName());
        context.setVariable("company", companyConfig.getCompanyName());
        context.setVariable("companyPhone", companyConfig.getCompanyPhone());
        context.setVariable("companyMail", companyConfig.getCompanyEmail());
        context.setVariable("showButton", false);
        context.setVariable("isFriend", true);
        context.setVariable("adminConfig", adminConfig);
        context.setVariable("app_function", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTasksCounterEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("bye", "Sincerely: " + adminConfig.getAdminName());
        context.setVariable("company", companyConfig.getCompanyName());
        context.setVariable("companyPhone", companyConfig.getCompanyPhone());
        context.setVariable("companyMail", companyConfig.getCompanyEmail());
        context.setVariable("isFriend", true);
        context.setVariable("adminConfig", adminConfig);
        return templateEngine.process("mail/tasks_count_mail", context);
    }
}
