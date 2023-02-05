public class Person {
    private String username;
    private String email;
    private String password;

    public Person(){
        username = "";
        email = "";
        password = "";
    }
    public Person(String name,String email,String password){
        this.username = name;
        this.email = email;
        this.password = password;
    }

    public void setName(String name){
        this.username = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getName() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
