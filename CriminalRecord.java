public class CriminalRecord {
    String name;
    int age;
    String crime;
    String date;
    int userId;

    public CriminalRecord(String name, int age, String crime, String date, int userId) {
        this.name = name;
        this.age = age;
        this.crime = crime;
        this.date = date;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Crime: " + crime + ", Date: " + date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
