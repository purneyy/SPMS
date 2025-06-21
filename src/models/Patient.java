package models;

public class Patient {
    private String name;
    private int age;
    private String gender;
    private String symptoms;
    

    public Patient(String name, int age, String gender, String symptoms) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.symptoms = symptoms;
        System.out.println("✅ ViewPatientsController initialized");
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getSymptoms() { return symptoms; }
   
    // ✅ Add these setters ↓↓↓
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    @Override
    public String toString() {
        return name + " | " + age + " | " + gender + " | " + symptoms;
    }

   


}

