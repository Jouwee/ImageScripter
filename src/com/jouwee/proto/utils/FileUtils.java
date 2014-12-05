package com.jouwee.proto.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File utilities
 * 
 * @author Jouwee
 */
public class FileUtils {
    
    /**
     * Save the file content
     * 
     * @param content
     * @param file 
     * @throws java.io.IOException 
     */
    public static void save(String content, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }
    
    /**
     * Load the file content
     * 
     * @param file 
     * @return String
     * @throws java.io.IOException 
     */
    public static String load(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        }
    }
    
}
