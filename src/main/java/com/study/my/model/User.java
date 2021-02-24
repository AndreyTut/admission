package com.study.my.model;


import java.util.List;
import java.util.Set;

public class User {

    private Integer id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String city;

    private String region;

    private String schoolName;

    private Diploma diploma;

    private boolean enabled;

    private Faculty faculty;

//    private List<StudentMark> marks;

    private List<Faculty> faculties;

    private byte[] diplomImage;

    private Set<Role> roles;

    public User(String email) {
        this.email = email;
    }

    public User(Integer id, String email, String password, String firstName, String lastName, String patronymic, String city, String region, String schoolName, Faculty faculty, byte[] diplomImage, boolean enabled, List<Faculty> faculties) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.city = city;
        this.region = region;
        this.schoolName = schoolName;
        this.faculty = faculty;
        this.diplomImage = diplomImage;
        this.enabled = enabled;
        this.faculties = faculties;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public byte[] getDiplomImage() {
        return diplomImage;
    }

    public void setDiplomImage(byte[] diplomImage) {
        this.diplomImage = diplomImage;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }

    public Diploma getDiploma() {
        return diploma;
    }

    public void setDiploma(Diploma diploma) {
        this.diploma = diploma;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Integer id;
        private Faculty faculty;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String patronymic;
        private String city;
        private String region;
        private String schoolName;
        private Diploma diploma;
        private boolean enabled;
        //        private List<StudentMark> marks;
        private List<Faculty> faculties;
        private byte[] diplomImage;
        private Set<Role> roles;

        UserBuilder() {
        }

        public UserBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder patronymic(String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public UserBuilder city(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder region(String region) {
            this.region = region;
            return this;
        }

        public UserBuilder schoolName(String schoolName) {
            this.schoolName = schoolName;
            return this;
        }

        public UserBuilder diploma(Diploma diploma) {
            this.diploma = diploma;
            return this;
        }

        public UserBuilder isEnabled(boolean isEnabled) {
            this.enabled = isEnabled;
            return this;
        }

        //        public UserBuilder marks(List<StudentMark> marks) {
//            this.marks = marks;
//            return this;
//        }
//
        public UserBuilder faculties(List<Faculty> faculties) {
            this.faculties = faculties;
            return this;
        }

        public UserBuilder diplomImage(byte[] diplomImage) {
            this.diplomImage = diplomImage;
            return this;
        }

        public UserBuilder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            return new User(id, email, password, firstName, lastName, patronymic, city, region, schoolName, faculty, diplomImage, enabled, faculties); //, rolesdiploma, isEnabled$value, marks, faculties);
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", isEnabled=" + enabled +
                ", diplomImage size:" + (diplomImage == null ? 0 : diplomImage.length) +
                ", roles=" + roles +
                '}';
    }
}
