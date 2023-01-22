package com.seris.api.util;

import com.seris.api.enums.Gender;
import jakarta.servlet.http.Cookie;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Util
 *
 * @author Баярхүү.Лув, 2022.03.20 16:11
 */
public class Utils {
    public static String regNoFormat = "^[А-ЯӨҮ]{2}[0-9]{2}(0[1-9]||1[0-2]||2[0-9]||3[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{2}$";
    public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    public static DateTimeFormatter dateFormatFront = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getCurrentUserId() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .filter(f -> f.getAuthority().startsWith("id-"))
                .findFirst()
                .map(id -> id.getAuthority().substring(3))
                .orElse(null);
    }

    public static Gender getGenderByRegNo(String regNo) {
        return regNo.substring(8, 9).matches("^[13579]$") ? Gender.male : Gender.female;
    }

    public static Integer getAgeByRegNo(String regNo) {
        LocalDate birthday = generateBirthdayByRegnum(regNo);
        if (birthday == null) return -1;
        return (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }

    public static LocalDate generateBirthdayByRegnum(String regnum) {
        try {
            int year_prefix = 1900;
            String year = regnum.substring(2, 4);
            String month = regnum.substring(4, 6);
            String day = regnum.substring(6, 8);
            if (month.startsWith("2") || month.startsWith("3")) {
                year_prefix = 2000;
                month = ((Integer.parseInt(month.substring(0, 1)) - 2)) + month.substring(1);
            }
            return LocalDate.now().withYear(year_prefix + Integer.parseInt(year))
                    .withMonth(Integer.parseInt(month)).withDayOfMonth(Integer.parseInt(day));//reg.getTime();
        } catch (NumberFormatException | NullPointerException | DateTimeException e) {
            return null;
        }
    }

    public static LocalDateTime dateFromMin(LocalDate dateFrom) {
        return LocalDateTime.of(dateFrom, LocalTime.MIN);
    }

    public static LocalDateTime dateToMax(LocalDate dateTo) {
        return LocalDateTime.of(dateTo, LocalTime.MAX);
    }

    public static String removeFraction(double number) {
        if (number == 0) return "";
        String s = String.valueOf(number);
        if (s.charAt(s.length() - 2) == '.' && s.charAt(s.length() - 1) == '0') {
            return s.substring(0, s.length() - 2);
        }
        return s;
    }

    public static Cookie getCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(12 * 3600);//12 цаг
        cookie.setPath("/");
        return cookie;
    }

    public static String formatRange(LocalTime start, LocalTime end) {
        return start.format(Utils.timeFormat) + " - " + end.format(Utils.timeFormat);
    }

    public static String addLeadingZero(String value) {
        return value.length() == 1 ? "0" + value : value;
    }
}
