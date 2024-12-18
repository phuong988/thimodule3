package model;

public class Student {
    private String id;
    private String name;
    private String className;

    public Student(String id, String name, String className) {
        this.id = id;
        this.name = name;
        this.className = className;
    }

    public Student(String className, String name) {
        this.className = className;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String classRoom) {
        this.className = classRoom;
    }
}
