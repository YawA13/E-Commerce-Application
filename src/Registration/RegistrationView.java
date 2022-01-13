package Registration;

/**
 * View of Registration
 */
public interface RegistrationView
{
    /**
     * Method after registration to the database was successful
     */
    void registrationSuccessful();

    /**
     * Method after registration to the database was failed
     */
    void registrationFailed();
}
