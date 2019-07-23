package ru.aora.erp.model.entity.db;
import java.util.StringJoiner;

public class DbCounteragent {

    private String id;
    private String counteragentName;
    private String groupName;
    private String directorFirstName;
    private String directorSurname;
    private String directorPatronymic;
    private String phoneNumber;
    private String mail;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //@Override
    public String getCounteragentName() {
        return counteragentName;
    }

    public void setCounteragentName(String counteragentName) {
        this.counteragentName = counteragentName;
    }

    public String getGroupName() { return groupName; }

    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getDirectorFirstName() {
        return directorFirstName;
    }

    public void setDirectorFirstName(String directorFirstName) {
        this.directorFirstName = directorFirstName;
    }

    public String getDirectorSurname() { return directorSurname; }

    public void setDirectorSurname(String directorSurname) {
        this.directorSurname = directorSurname;
    }

    public String getDirectorPatronymic() {
        return directorPatronymic;
    }

    public void setDirectorPatronymic(String directorPatronymic) {
        this.directorPatronymic = directorPatronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static DbCounteragent.counteragentBuilder builder() {
        return new DbCounteragent.counteragentBuilder();
    }

    public static class counteragentBuilder {
        private String id;
        private String counteragentName;
        private String groupName;
        private String directorFirstName;
        private String directorSurname;
        private String directorPatronymic;
        private String phoneNumber;
        private String mail;
        private String address;

        private counteragentBuilder() {
        }

        public DbCounteragent.counteragentBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public DbCounteragent.counteragentBuilder withCounteragentName(String counteragentName) {
            this.counteragentName = counteragentName;
            return this;
        }

        public DbCounteragent.counteragentBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public DbCounteragent.counteragentBuilder withFirstName(String directorFirstName) {
            this.directorFirstName = directorFirstName;
            return this;
        }

        public DbCounteragent.counteragentBuilder withSurname(String directorSurname) {
            this.directorSurname = directorSurname;
            return this;
        }

        public DbCounteragent.counteragentBuilder withPatronymic(String directorPatronymic) {
            this.directorPatronymic = directorPatronymic;
            return this;
        }


        public DbCounteragent.counteragentBuilder withMail(String mail) {
            this.mail = mail;
            return this;
        }

        public DbCounteragent.counteragentBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public DbCounteragent.counteragentBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public DbCounteragent build() {
            DbCounteragent counteragent = new DbCounteragent();
            counteragent.setId(id);
            counteragent.setCounteragentName(counteragentName);
            counteragent.setGroupName(groupName);
            counteragent.setDirectorFirstName(directorFirstName);
            counteragent.setDirectorSurname(directorSurname);
            counteragent.setDirectorPatronymic(directorPatronymic);
            counteragent.setPhoneNumber(phoneNumber);
            counteragent.setMail(mail);
            counteragent.setAddress(address);
            return counteragent;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbCounteragent.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("counteragentName='" + counteragentName + "'")
                .add("groupName='" + groupName + "'")
                .add("directorFirstName='" + directorFirstName + "'")
                .add("directorSurname='" + directorSurname + "'")
                .add("directorPatronymic='" + directorPatronymic + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("mail='" + mail + "'")
                .add("address='" + address + "'")
                .toString();
    }
}


