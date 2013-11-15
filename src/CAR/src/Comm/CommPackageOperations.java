package Comm;

/**
 * Interface definition: CommPackage.
 * 
 * @author OpenORB Compiler
 */
public interface CommPackageOperations
{
    /**
     * Operation get_route
     */
    public String get_route(String source, String destination);

    /**
     * Operation shutdown
     */
    public void shutdown();

}
