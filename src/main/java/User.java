public class User {
    String name;
    int age;
    Boolean isRegistered;

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public User(String name, int age,Boolean isRegistered) {
        this.name = name;
        this.age = age;
        this.isRegistered=isRegistered;
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
}
