/*
 * @(#)CommPackageOperations.java   13/10/20
 * 
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */



package Comm;

/**
 * Interface definition: CommPackage.
 *
 * @author OpenORB Compiler
 */
public interface CommPackageOperations {

    /**
     * Operation get_route
     */
    public String get_route(String source, String destination);

    /**
     * Operation shutdown
     */
    public void shutdown();
}


//~ Formatted by Jindent --- http://www.jindent.com
