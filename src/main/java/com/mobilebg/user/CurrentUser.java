package com.mobilebg.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class CurrentUser {
    private String name;
    private boolean loggedIn;

    public void clear() {
        loggedIn = false;
        name = null;
    }

    public boolean isAnonymous() {
        return !isLoggedIn();
    }
}
