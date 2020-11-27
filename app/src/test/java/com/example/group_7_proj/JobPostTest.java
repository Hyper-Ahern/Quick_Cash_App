package com.example.group_7_proj;
import com.example.group_7_proj.CustomDataTypes.JobPost;

import org.junit.Test;

import static org.junit.Assert.*;

public class JobPostTest
{

    @Test
    public void InvalidSalaryNotNumber()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","*","dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidSalary());
    }

    @Test
    public void ValidSalary()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidSalary());
    }

    @Test
    public void InValidSalaryByNull()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning",null,"dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidSalary());
    }

    @Test
    public void InValidEmployerName()
    {
        JobPost j = new JobPost("","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidEmployerName());
    }

    @Test
    public void ValidEmployerName()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidEmployerName());
    }

    @Test
    public void InValidJobTitle()
    {
        JobPost j = new JobPost("LTD","!","123.00","Cleaning","dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidJobTitle());
    }

    @Test
    public void ValidJobTitle()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidJobTitle());
    }
    @Test
    public void InValidJobDetails()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","");
        assertFalse(j.InvalidJobDetails());
    }

    @Test
    public void ValidJobDetails()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidJobDetails());
    }

    @Test

    public void ValidJobTypes(){

        JobPost j = new JobPost("LTD","Babysitter","Walking Dog","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidJobTypes());

        JobPost j2 = new JobPost("LTD","Babysitter","--Please select--","123.00","dajsdhkajgsdkjasgfakjsd");
        assertFalse(j2.InvalidJobTypes());


    }

    @Test
    public void checkJobGeoLocation(){
        JobPost j1 = new JobPost();
        j1.setGeoLoc(11,12);
        assertTrue(j1.validGeoLoc());

        // wrong data type
        JobPost j2 = new JobPost();
        j2.setGeoLoc('a',12);
        assertFalse(j2.validGeoLoc());
    }

}
