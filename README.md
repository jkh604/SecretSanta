# Secret Santa Java Project
## Purpose
The Purpose of this Java project is to take a list of emails, randomize the list, and send out a message to each email on who in this list of emails they have been given to purchase a gift for as their Secret Santa. After scrambling the list of emails, this implementation uses a Round Robin algorithm to efficiently ensure that there will be no email is given more than once, and that no email get themself as their Secret Santa. Assuming the one who owns the account sending the emails does not look at their email sending history, the entire process would allow for a truly anonymous Secret Santa.
## Requirements
1. A text file with the data to scan into the project is required. The formatting of the code looks for a ':' character within the text file to know where to seperate the user's name and email. Test.txt is provided to show the correct format.
2. This program requires JavaMail API. To run locally please ensure that the correct JAR file has been added to the Build Path. 
Secure Link to JavaMail API: https://javaee.github.io/javamail/ 
3. This program can only work in it's current implementation with a Gmail account to send the email. This Gmail account must also allow for use on Less Secure Apps, which can be located within a Gmail's settings.
