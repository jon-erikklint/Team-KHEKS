package reference;

public class ArticleRef extends AbstractReference implements Reference {

    public ArticleRef() {
        fields.put("publisher", null);
        fields.put("address", null);
        fields.put("journal", null);
        fields.put("number", null);
        fields.put("volume", null);
        fields.put("pages", null);
        fields.put("month", null);
        fields.put("note", null);
    }
    
    public ArticleRef(String title, String authors, String volume, String journal, String number, int year){
        this();
        
        fields.put("title", title);
        fields.put("authors", authors);
        fields.put("volume", volume);
        fields.put("journal", journal);
        fields.put("number", number);
        
        this.year = year;
    }

    /*
    public ArticleRef(String authors, String title, String journal, String year, String volume, String number, String publisher, String address, String pages) {
        fields.put("authors", authors);
        fields.put("title", title);
        fields.put("journal", journal);
        fields.put("volume", volume);
        fields.put("number", number);
        fields.put("publisher", publisher);  
        fields.put("address", address);
        fields.put("pages", pages);
        
        try {
            this.year = Integer.getInteger(year);
        } catch (Error e) {

        }
    }
     */
    @Override
    public boolean readyForDb() {
        return (isValidString("title", fields.get("title")) && isValidString("authors", fields.get("authors")) && isValidYear(year) && isValidString("volume", fields.get("volume")) && isValidString("journal", fields.get("journal")) && isValidString("number", fields.get("number")));
    }

}
