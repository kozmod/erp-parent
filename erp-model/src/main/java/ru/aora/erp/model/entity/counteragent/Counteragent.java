package ru.aora.erp.model.entity.counteragent;
import java.util.StringJoiner;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


@Entity
@Table(name = "counteragent")
public class Counteragent {

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

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

    public static ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder builder() {
        return new ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder();
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

        public ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder withCounteragentName(String counteragentName) {
            this.counteragentName = counteragentName;
            return this;
        }
        public ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder withFirstName(String directorFirstName) {
            this.directorFirstName = directorFirstName;
            return this;
        }

        public ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder withSurname(String directorSurname) {
            this.directorSurname = directorSurname;
            return this;
        }

        public ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder withPatronymic(String directorPatronymic) {
            this.directorPatronymic = directorPatronymic;
            return this;
        }


        public ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder withMail(String mail) {
            this.mail = mail;
            return this;
        }

        public ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ru.aora.erp.model.entity.counteragent.Counteragent.counteragentBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public ru.aora.erp.model.entity.counteragent.Counteragent build() {
            ru.aora.erp.model.entity.counteragent.Counteragent counteragent = new ru.aora.erp.model.entity.counteragent.Counteragent();
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
        return new StringJoiner(", ", ru.aora.erp.model.entity.counteragent.Counteragent.class.getSimpleName() + "[", "]")
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
