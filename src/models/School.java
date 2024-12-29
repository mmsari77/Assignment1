package models;

import java.util.ArrayList;
import java.util.List;

public class School {
    public List<Person> members;

    public School() {
        this.members = new ArrayList<>();
    }

    public void addMember(Person person) {
        members.add(person);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        members.sort((p1, p2) -> p1.surname.compareToIgnoreCase(p2.surname));
        for (Person member : members) {
            sb.append(member.toString()).append("\n");
        }
        return sb.toString();
    }
}


