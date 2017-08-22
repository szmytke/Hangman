import java.util.ArrayList;


public class Capital {

    private String name;
    private String country;
    private static ArrayList<Capital> listCapital = new ArrayList<Capital>(); 

    public Capital(String name, String country){
        this.name = name;
        this.country = country;

    }
    public String getName(){
        return this.name;
    }

    public String getCountry(){
        return this.country;
    }

    public static void addCapital(Capital capital){
        listCapital.add(capital);
    }
    public static ArrayList<Capital> getListCapital(){
        return listCapital;
    }
}