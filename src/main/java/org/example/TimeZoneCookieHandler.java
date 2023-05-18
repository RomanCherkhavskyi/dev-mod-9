package org.example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TimeZone;

public class TimeZoneCookieHandler {

    public static void saveTimeZone(HttpServletRequest request, HttpServletResponse response, String timezone) {
        if (timezone != null && !timezone.isEmpty()) {
            Cookie timeZoneCookie = new Cookie("lastTimezone", timezone);
            response.addCookie(timeZoneCookie);
        }
    }

    public static String getTimeZone(HttpServletRequest request) {
        String timeZone = request.getParameter("timezone");

        if (timeZone != null && !timeZone.isEmpty()) {
            saveTimeZone(request, null, timeZone);
            return String.valueOf(TimeZone.getTimeZone(timeZone));
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastTimezone")) {
                    return String.valueOf(TimeZone.getTimeZone(cookie.getValue()));
                }
            }
        }

        return String.valueOf(TimeZone.getTimeZone("UTC"));
    }
}
