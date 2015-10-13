package com.enclaveit.brea.common;

import java.io.Serializable;

public final class Const implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String DRIVER = "pentaho.dataSource.driverClassName";
    public static final String ERROR_REPORT = "Report generation failure";
    public static final String PWD = "pentaho.dataSource.password";
    public static final String URL = "pentaho.dataSource.url";
    public static final String USER = "pentaho.dataSource.user";
    public static final String FILE = "file";
    public static final String TEMP_DIR = "pentaho.directory.temp";
    /**
     The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
     and so on. Thus, the caller should be prevented from constructing objects of 
     this class, by declaring this private constructor. 
    */
    private Const()
    {
        
    }
}

