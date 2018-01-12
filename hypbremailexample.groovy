// --------------------------------------------------------------------------------------------------------------
// -- NAME: hypbremailexample.groovy
// --
// -- DESCRIPTION: Groovy script to send email notifications from Hyperion business rules
// --
// -- CREATED ON: January 12, 2018
// --
// --------------------------------------------------------------------------------------------------------------

// --------------------------------------------------------------------------------------------------------------
// -- MODIFICATION HISTORY
// --------------------------------------------------------------------------------------------------------------
//
// 2018-01-12: Original script created (Taha Siddiqui, EPM Advisors Inc)
//
//

// External libraries
//
import java.util.Properties
import javax.mail.*
import javax.mail.internet.*

    // Output to logger file
    //
    println("Script started successfully!");

    // Define constants for Pilot environment
    //
    String FROM = "senderName@myCompany.com";               // Verified "From" address *** UPDATE THIS ***
	String TO = "recipientName@myCompany.com";              // Verified "To" address   *** UPDATE THIS ***
	String SUBJECT = "Hyperion System Notification";        // This is the email subject line

    // Generate the Body of the email
    //
    String BODY = "SYSTEM GENERATED EMAIL";
    BODY = BODY.concat("\n===========================================================================================================================");
    BODY = BODY.concat("\n\n\nYour email message is defined here.");
    BODY = BODY.concat("\n\nThis email was generated from a non-monitored email account - do not respond to this email!");
    BODY = BODY.concat("\n\nSincerely,");
    BODY = BODY.concat("\nHyperion System Administrator.");
    BODY = BODY.concat("\n\n\n===========================================================================================================================");
    BODY = BODY.concat("\nSYSTEM GENERATED EMAIL");

    // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
    //
    String SMTP_USERNAME = "userName";       // Replace with your SMTP username. *** UPDATE THIS ***
    String SMTP_PASSWORD = "passwordValue";  // Replace with your SMTP password. *** UPDATE THIS ***

    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    //
    String HOST = "smtp.myCompany.com"; // *** UPDATE THIS ***  

    // The port you will connect to on the Amazon SES SMTP endpoint. We are choosing port 25 because we will use
    // STARTTLS to encrypt the connection.
    //
    int PORT = 25; // May need to be changed depending on your SMTP server config
    
    // Create a Properties object to contain connection configuration information.
    //
    Properties props = System.getProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.port", PORT);

    // Set properties indicating that we want to use STARTTLS to encrypt the connection
    // The SMTP session will begin on an unencrypted connection, and then the client
    // will issue a STARTTLS command to upgrade to an encrypted connection
    //
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.starttls.required", "true");

    // Output to logger file
    //
    println("Attempting to create Session...");

    // Create a Session object to represent a mail session with the specified properties
    //
    Session session = Session.getDefaultInstance(props);

    // Output to logger file
    //
    println("Session created successfully!");

    // Create a message with the specified information
    //
    MimeMessage msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(FROM));
    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
    msg.setSubject(SUBJECT);
    msg.setContent(BODY,"text/plain");

    // Output to logger file
    //
    println("Message created successfully!");

    // Create a transport
    //
    Transport transport = session.getTransport();

    // Output to logger file
    //
    println("Transport created successfully!");

    // Send the message
    //
    try
       {
        // Output to logger file
        //
        println("Attempting to send an email through the Amazon SES SMTP interface...");

        // Connect to Amazon SES using the SMTP username and password you specified above.
        //
        transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

        // Output to logger file
        //
        println("Connected to Transport successfully!");

        // Send the email
        //
        transport.sendMessage(msg, msg.getAllRecipients());
        println("Email sent!");

        return 0;
        }
        catch (Exception ex) {

            println("The email was not sent.");
            println("Error message: " + ex.getMessage());
           	println("Exception Stack Trace: " + ex.getStackTrace());       
			//println("Message detail: " + msg.getTo() + " " + msg.getFrom());

            return -1;

        }
        finally
        {
            // Close and terminate the connection.
            //
            transport.close();           
			println("Script ended!");

        }