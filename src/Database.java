//// https://docs.microsoft.com/en-us/sql/connect/jdbc/step-3-proof-of-concept-connecting-to-sql-using-java?view=sql-server-2017
//import java.sql.*;
//import java.util.Map;
////https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15
////https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
////https://stackoverflow.com/questions/1582161/how-does-a-preparedstatement-avoid-or-prevent-sql-injection
//
//public class Database {
//    // Connect to your database.
//    // Replace server name, username, and password with your credentials
//    public static void main(String[] args) throws ClassNotFoundException {
//        Map<String, String> env = System.getenv();
//        String endpoint = env.get("db_connection"); // AWS Secrets is a more scalable option
//        System.out.println(endpoint);
//        String connectionUrl = // specifies how to connect to the database
//                "jdbc:sqlserver://" + endpoint + ";"
//                        + "database=prof;"
//                        + "user=MCON364;"
//                        + "password=Pesach20215;"
//                        + "encrypt=true;"
//                        + "trustServerCertificate=true;"
//                        + "loginTimeout=30;";
//        ResultSet resultSet = null;
//        try (Connection connection = DriverManager.getConnection(connectionUrl); // AutoCloseable
//             Statement statement = connection.createStatement())
//        {
//            // Create and execute a SELECT SQL statement.
//            String selectSql = "SELECT TOP 10 StudentId, FirstName, LastName FROM Students"; // Guardrails
//            resultSet = statement.executeQuery(selectSql);
//
//            // Print results from select statement
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//            String insertSql2 = "INSERT Students (FirstName, LastName) VALUES (?, ?);";
//            try (Connection connection = DriverManager.getConnection(connectionUrl);
//                 PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql2, Statement.RETURN_GENERATED_KEYS);) {
//                {
//                    for (int i=0;i<10;i++) {
//                        prepsInsertProduct.setString(1, randomFirstName());
//                        prepsInsertProduct.setString(2, randomLastName());
//                        prepsInsertProduct.execute();
//
//                        resultSet = prepsInsertProduct.getGeneratedKeys();
//                        while (resultSet.next()) {
//                            System.out.println(resultSet.getInt(1));
//                        }
//                    }
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//
//
//    }
//
//    private static String randomFirstName() {
//        String[] firstNames = {
//                "John", "Michael", "David", "Sarah", "Emily",
//                "Jessica", "Robert", "James", "Mary", "Jennifer"
//        };
//        return firstNames[new java.util.Random().nextInt(firstNames.length)];
//    }
//    private static String randomLastName() {
//        String[] lastNames = {
//                "Smith", "Johnson", "Williams", "Brown", "Jones",
//                "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
//                "Hernandez", "Lopez"
//        };
//        return lastNames[new java.util.Random().nextInt(lastNames.length)];
//    }
//}
//
////    String insertSql2 = "INSERT INTO Students (FirstName, LastName) VALUES "
////            + "(?, ?);";
////        try (Connection connection = DriverManager.getConnection(connectionUrl);
////                PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql2, Statement.RETURN_GENERATED_KEYS);) {
////                {
////                prepsInsertProduct.setString(1,"Joe");
////                prepsInsertProduct.setString(2,"Shmoe");
////                prepsInsertProduct.execute();
////                }} catch (SQLException throwables) {
////                throwables.printStackTrace();
////                }