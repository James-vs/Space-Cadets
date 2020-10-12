import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

class NameFromUsername{

    private static URL url; // creates the url attribute
    private static String webText; // creates the webText attribute
    


    public static void main(String[] args) {
        Scanner userIdIn = new Scanner(System.in);
        System.out.print("Welcome! \nPlease enter a username:>> ");
        
        //^^ setting up the scanner to recieve the user input

        String userId = userIdIn.nextLine();
        userIdIn.close();

        // assigning userId the inputted value

        setURL(userId);

        // set the url attribute the value of the url to be searched

        System.out.println("The URL to be searched is: " + getURL());

        // using the getURL method to return the value of the target url

        getWebText();

        if (getUserName().length() > 50){
            System.out.println("Username has no webpage");
            /* if the username is longer than 50 characters then
            // the property="name" attribute is not present on 
            // the webpage as the getUserName() method has returned
            // more than 50 characters instead of a name */
        } 
        else {
             System.out.println("The user's name is: " + getUserName());
             // if the method returns less than 50 characters, then this
             // is a name so it can be displayed
        }        
    }

    public static void setURL(String userId) {
        try{ // use try-catch to prevent errors
            URL userUrl = new URL("https://www.ecs.soton.ac.uk/people/");
            url = new URL(userUrl, userId);

            // declaring a new generic URL as userURL
            // appending the 'url' atribute with a new specific URL path 
        }
        catch(Exception e){
            System.out.println("problem with setURL()");
            // prints error message and name of method to the console
        }
    }

    public static String getURL(){
        return url.toString();
        // gets the full url from the attribute 'url'
    }

    public static void getWebText() {

        try{
            URLConnection urlConnection = url.openConnection();
            // open an internet connection with the value in 'url'

            InputStream inputStream = urlConnection.getInputStream();
            // creates an input stream from the webpage to the program

            InputStreamReader inputStreamReader = 
            new InputStreamReader(inputStream);
            // creates a reader for the input stream

            int charReadCount; 
            // create a variable to hold the number of characters read

            char[] charArray = new char[1024]; 
            // creating a character array for the name

            StringBuffer stringBuffer = new StringBuffer(); 
            // creating a string buffer to hold the characters

            while((charReadCount = inputStreamReader.read(charArray)) > 0){
                // while the char count and equivalent position in the 
                // charArray are both greater than position 0

                stringBuffer.append(charArray, 0, charReadCount);
                // append the string buffer with the characters being read
            }

            webText = stringBuffer.toString();

        } catch (Exception e) {
            System.out.println("Error with getUsername()");
            // prints error to console and name of method
        } 
    }
    
    public static String getUserName(){
        Integer nameStartPos = webText.indexOf("property=\"name\">") + 16;
        // i add 17 to the variable as the indexOf() method only finds the start
        // of the word 'property' and there are then 16 characters til the name

        String nameText = webText.substring(nameStartPos);
        // removes unnecessary characters from the start of the string

        Integer nameEndPos = nameText.indexOf("<em property=");
        // get the position of the end of the name
    
        return nameText.substring(0, nameEndPos);
        // removes unnecessary characters after the name
        // then returns the value of the users name
        
    }
}