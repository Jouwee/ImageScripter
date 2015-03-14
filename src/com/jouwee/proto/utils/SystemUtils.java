/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.utils;

import java.io.File;

/**
 * Utilities for interacting with the system
 * 
 * @author Jouwee
 */
public class SystemUtils {
    
    /**
     * Returns the local data path with trailing path separator
     * 
     * @see getLocalDataDir
     * @return String
     */
    public static String getLocalDataPath() {
        return getLocalDataDir().getAbsolutePath() + File.separator;
    }
    
    /**
     * Returns the local data dir.
     * </p>
     * For windows, it's gonna be wathever is in the APPDATA environment
     * variable. Otherwise, for a cross-platform solution, it's going to returnt
     * the {@code user.dir} System property.
     * 
     * @return File
     */
    public static File getLocalDataDir() {
        String localDataPath;
        // Windows specific implementation
        localDataPath = System.getenv("APPDATA");
        if (localDataPath != null && !localDataPath.isEmpty()) {
            File localDataDir = new File(localDataPath);
            if (localDataDir.exists()) {
                return localDataDir;
            }
        }
        // Cross-platform implementation
        localDataPath = System.getProperty("user.home");
        return new File(localDataPath);
    }
    
}
