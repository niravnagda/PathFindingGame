package Comm;

/**
 * Interface definition: CommPackage.
 * 
 * @author OpenORB Compiler
 */
public class CommPackagePOATie extends CommPackagePOA
{

    //
    // Private reference to implementation object
    //
    private CommPackageOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public CommPackagePOATie(CommPackageOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public CommPackagePOATie(CommPackageOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public CommPackageOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(CommPackageOperations delegate_)
    {
        _tie = delegate_;
    }

    /**
     * _default_POA method
     */
    public org.omg.PortableServer.POA _default_POA()
    {
        if (_poa != null)
            return _poa;
        else
            return super._default_POA();
    }

    /**
     * Operation get_route
     */
    public String get_route(String source, String destination)
    {
        return _tie.get_route( source,  destination);
    }

    /**
     * Operation shutdown
     */
    public void shutdown()
    {
        _tie.shutdown();
    }

}
