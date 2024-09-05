Introduction-->
The Online Quiz Application is a Java-based system that allows users to take quizzes on various topics. The application provides features for user authentication, quiz management, quiz taking, scoring, and progress tracking. Additionally, it includes a leaderboard to display top scorers and uses a database for data persistence.

System Architecture-->
The system consists of the following components:

Presentation Layer: A Java-based GUI application that provides an interface for users to interact with the system.
Business Logic Layer: A Java-based layer that handles the logic for user authentication, quiz management, quiz taking, and scoring.
Data Access Layer: A Java-based layer that interacts with the database to store and retrieve data.
Database: A relational database management system ( MySQL) that stores user accounts, quizzes, questions, and quiz results.
User Authentication
The user authentication module provides the following features:

Registration: Users can create an account by providing a username, password, roles.
Login: Users can log in with their existing credentials then will be generate the jwt token and then it can be for authentication.
Password Hashing and Salting: Passwords are securely stored using password hashing and salting.

Quiz Management
The quiz management module provides the following features:

Quiz Creation: Administrators can create quizzes with multiple-choice questions.
Quiz Editing: Administrators can edit existing quizzes.
Quiz Deletion: Administrators can delete quizzes.
Question Management: Administrators can add, edit, and delete questions.
Quiz Taking
The quiz taking module provides the following features:

Quiz Selection: Users can select and take quizzes from the available list of topics.
Question Display: One question is displayed at a time with options for the user to select the answer(s).
Feedback: Immediate feedback is provided on each question (correct/incorrect) after the user submits their answer.
Scoring and Progress Tracking
The scoring and progress tracking module provides the following features:

Score Calculation: The user's score is calculated at the end of each quiz.
Progress Tracking: User progress is tracked by recording quiz attempts and scores.
Score Display: Users can view their past quiz attempts and scores.
Leaderboard
The leaderboard module provides the following features:

Top Scorers: Displays top scorers for each quiz or overall.
Ranking: Users are ranked based on their total scores or average scores.
Data Persistence
The data persistence module provides the following features:

Database Interaction: Uses JDBC for database interaction.
CRUD Operations: Implements create, read, update, and delete operations for user accounts, quizzes, questions, and quiz results.
Security Considerations
The system implements the following security measures:

Password Hashing and Salting: Passwords are securely stored using password hashing and salting.
Data Encryption: Data is encrypted to prevent unauthorized access.

Master Admin for login Credentials  
{
"username":"admin",
"password":"admin"
}
when you are login that credentials the generate the jwt token then provide the access for which aceess for admin and user accordingly.
 @PostConstruct
    public void addAdmin() {
        String username = env.getProperty(Constants.ADMIN_MASTER_USERNAME, "admin");
        String roles = env.getProperty(Constants.ADMIN_MASTER_ROLES, "ADMIN");
        String password = passwordEncoder.encode(env.getProperty(Constants.ADMIN_MASTER_PASSWORD, "admin"));
        String insertUser = "INSERT INTO user (username, password, roles) SELECT ?, ?, ? WHERE NOT EXISTS (Select username From user WHERE username = ?) LIMIT 1";
        int count = jdbcTemplate.update(insertUser, username, password, roles, username);
        LOGGER.info("Rows updated: {}", count);
    }
