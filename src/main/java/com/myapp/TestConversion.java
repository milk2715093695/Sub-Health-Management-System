package com.myapp;

import java.io.*;

public class TestConversion {
    public static void main(String[] args) {
        final String soffice = System.getenv("SOFFICE_HOME");
        String command = soffice + " --headless --convert-to pdf src/main/resources/templates/Doc2.docx --outdir src/main/resources/templates";

        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}