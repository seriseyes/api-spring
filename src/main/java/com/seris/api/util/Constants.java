package com.seris.api.util;

/**
 * Constants
 *
 * @author Баярхүү.Лув 2022.04.12 20:19
 */
public class Constants {
    public static String[] httpSecurityUrlAntMatchers = new String[]{
            "/auth/**"
    };

    /*
     * Linux: /home/bayarkhuu/Documents/seris
     * Windows: c:/seris
     * */
    public static String path = "c:/bassein";
    public static String path_font = path + "/files/fonts";
    public static String path_pdf = path + "/files/pdfs";
    public static String path_temp = path + "/temp";
}
