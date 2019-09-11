# SalesMessageProcessorVersion2

The way this assignment was implemented was by using a fairly simple user interface to control the way a user inputs the various types of sale messages. The interface guides the user inputting the messages and signals with an error if an incorrect statement or value has been input. Messages are processed in their singular form only and can accept either the pence or pound currency types. e.g. 10p or £0.10

The application flow can be described as follows,

1.) Welcome screen with a description of what the application does and the various input message types it will accept. It also describes how the user should input data for the different input fields.

2.) Once the user inputs a number from 1 to 3, the application will guide the user to input the various required fields for that specific type of message. If an incorrect input is detected, the application will print an error describing why the input is incorrect.

3.) If the application is happy that all input fields for the message type have been entered correctly, it will log the sale into a sale data table.

4.) The application will then return the user back to a short version of the main menu and prompt for another input to select a new message of a new type. A sales history is printed above to show which sales have been properly logged. All attempts at creating a message are also saved into a message history list. A counter counts the number of each input message even if it is incorrect. After every 10 messages, a summary of each product with the total value is printed on top of the console. Once 50 messages have been input, the application will pause all inputs indicating this below and additionally will print a summary of each product with the total value as well as a log of all adjustments made to each previous sale since the application started. 

5.) Once paused, the user is informed that to continue, they need to type in 'run'. This will run the application again and go back to the short version of the main menu and prompt for another input to select a new message of a new type.

Unfortunately, I am unfamiliar with unit tests and therefore did not add any. I tested the software manually, however, I do acknowledge their importance and will definitely be implementing them from now on.

I hope this assignment has been completed with your approval. Thank you kindly for the opportunity.
