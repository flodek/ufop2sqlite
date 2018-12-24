public class Ufop2sqlite {

    public static void main(String... param) {

        if (param == null || param.length < 2) {
            System.out.println("Please specify two parameters - input xml file and output SQLite database file");
            return;
        }

        try (SQLiteManager sqlite = new SQLiteManager(param[1])) {

            XMLManager.load(param[0], (Fop fop) ->
                    sqlite.insert(fop.getName(), fop.getAddress(), fop.getKved(), fop.getKvedDescription(), fop.getStatus())
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Process completed successfully");

    }

}
