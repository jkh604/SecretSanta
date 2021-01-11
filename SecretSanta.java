/* Created By: James Kyle Harrison
 * Date: 01/04/2021
 * Purpose: To take list of names linked to emails and scramble them.
 * After scrambling the list, the list will then send out the name to an email of a user using Round Robin.
 * Note: JavaMail API is required to process this. 
 * Note: This only allows for the email sender to send emails from a gmail account
 * Note: The gmail account must also allow for Less Secure App Access in Gmail settings
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
public class SecretSanta {
    
	private static String USER_NAME = "**********";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "**********"; // GMail password
	
	public static void main(String[] args)
	{
		String from = USER_NAME;
        String pass = PASSWORD;
        String subject = "Secret Santa";
        
		try{ 
		File file = new File("Test.txt");
		Scanner scan = new Scanner(file);
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> emails = new ArrayList<String>();
		
		while(scan.hasNextLine()) //Scans the data into an ArrayList of raw data
		{
			String line = scan.nextLine();
			data.add(line);
		}
		Collections.shuffle(data); //Randomizes the list of Emails
		
		for(int i = 0; i < data.size(); i++) //Splits the Arraylist of data into names and emails
		{
			String line = data.get(i);
			names.add(line.substring(0, line.indexOf(":")));
			emails.add(line.substring(line.indexOf(":")+1, line.length()));
		}
		
		for(int i = 0; i < names.size(); i++) //Outputs the user/email it is sent to and who they have for Secret Santa
		{
			System.out.print("Recipient: " + names.get(i));
			System.out.println(" -  Email: " + emails.get(i));
			if(i+1 == emails.size())
			{
				System.out.println("Secret Santa For: " + names.get(0));
				String[] to = { emails.get(i) }; //Populates the email that is getting emailed
				String body = "Secret Santa for: " + names.get(0);
				sendFromGMail(from, pass, to, subject, body);
			}
			else
			{
				System.out.println("Secret Santa For: " + names.get(i+1));
				String[] to = { emails.get(i) }; //Populates the email that is getting emailed
				String body = "Secret Santa for: " + names.get(i+1);
				sendFromGMail(from, pass, to, subject, body);
			}
		}
		
		scan.close();
		}
		
		catch (FileNotFoundException e)
		{
			System.out.print("The following Error occured: ");
			e.printStackTrace();
		} 
	}
	
	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) 
	{
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
