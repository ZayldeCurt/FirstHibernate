package sda.pl.repository;

import org.junit.Assert;
import org.junit.Test;
import sda.pl.domain.User;
import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void findByEmailAndPassword() {
        //Given
        User user1 = User.builder()
                .email("testowyMail12")
                .password("test8")
                .cityName("miasto")
                .firstName("Jan")
                .lastName("Nowak")
                .street("ulica")
                .build();
        UserRepository.saveOrUpdate(user1);
        User userResult=null;
        //When
        userResult=  UserRepository.findByEmailAndPassword("testowyMail12","test8").get();

        //Then
        Assert.assertEquals(user1,userResult);


    }

    @Test
    public void findUser() {
        //Given
        User user1 = User.builder()
                .email("testowyMail")
                .password("test")
                .cityName("miasto")
                .firstName("Jan")
                .lastName("Nowak")
                .street("ulica")
                .build();
        Long idExpect = UserRepository.saveOrUpdate(user1);
        User userResult=null;
        //When
        userResult=  UserRepository.findUser(idExpect).get();

        //Then
        Assert.assertEquals(idExpect,userResult.getId());

    }
}