package com.research;
import org.springframework.stereotype.*;

import javax.persistence.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table(name="person_data")
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
   
    String firstName;
    
    String lastName;
    
    public String getFullName() {
        String fullName = new String();
        // if(getFirstName()!=null && !getFirstName().isEmpty())
        //     fullName.concat(getFirstName());
        // if(getLastName()!=null  && !getLastName().isEmpty())
        //   fullName.concat(" "+getLastName());
       fullName =  getFirstName() + " "+ getLastName();
        return fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}

@Service
class PersonService {
    @Autowired
    PersonRepository repository;
    
    
    List<Person> findAll() {
        return repository.findAll();
    }

}



@Repository
 interface PersonRepository 
        extends JpaRepository<Person, Long> {
 
}