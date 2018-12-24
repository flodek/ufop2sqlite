import java.sql.*;

class SQLiteManager implements AutoCloseable {

    private static int transactionsCount = 0;
    private static final int commitAfterTransaction = 100_000;
    private static final String url = "jdbc:sqlite:";
    private Connection conn;

    SQLiteManager(String fileName) {
        try {

            conn = DriverManager.getConnection(url + fileName.replace("\\", "/"));

            if (conn != null) {
                createFOPTable();
                conn.setAutoCommit(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        conn.commit();
        conn.close();
    }

    private void createFOPTable() {

        String sql = "CREATE TABLE IF NOT EXISTS fop (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text,\n"
                + "	address text,\n"
                + "	kved text,\n"
                + "	kvedDescription text,\n"
                + "	status text\n"
                + ");";

        try (Statement statement = conn.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void insert(String name, String address, String kved, String kvedDescription, String status) {
        String sql = "INSERT INTO fop(name, address, kved, kvedDescription, status) VALUES(?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, kved);
            pstmt.setString(4, kvedDescription);
            pstmt.setString(5, status);
            pstmt.executeUpdate();

            if (++transactionsCount >= commitAfterTransaction) {
                transactionsCount = 0;
                conn.commit();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
