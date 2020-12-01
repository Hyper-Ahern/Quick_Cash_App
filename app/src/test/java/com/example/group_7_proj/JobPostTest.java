package com.example.group_7_proj;
import com.example.group_7_proj.CustomDataTypes.GeoLocation;
import com.example.group_7_proj.CustomDataTypes.JobPost;

import org.junit.Test;

import static org.junit.Assert.*;

public class JobPostTest
{

    @Test
    public void InvalidSalaryNotNumber()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","*","dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertFalse(j.InvalidSalary());
    }

    @Test
    public void ValidSalary()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertTrue(j.InvalidSalary());
    }

    @Test
    public void InValidSalaryByNull()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning",null,"dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertFalse(j.InvalidSalary());
    }

    @Test
    public void InValidEmployerName()
    {
        JobPost j = new JobPost("","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertFalse(j.InvalidEmployerName());
    }

    @Test
    public void ValidEmployerName()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertTrue(j.InvalidEmployerName());
    }

    @Test
    public void InValidJobTitle()
    {
        JobPost j = new JobPost("LTD","!","123.00","Cleaning","dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertFalse(j.InvalidJobTitle());
    }

    @Test
    public void ValidJobTitle()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertTrue(j.InvalidJobTitle());
    }
    @Test
    public void InValidJobDetails()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","", 1, "Paid", "Completed");
        assertFalse(j.InvalidJobDetails());
    }

    @Test
    public void ValidJobDetails()
    {
        JobPost j = new JobPost("LTD","Babysitter","Cleaning","123.00","dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertTrue(j.InvalidJobDetails());
    }

    @Test
    public void ValidJobTypes(){

        JobPost j = new JobPost("LTD","Babysitter","Walking Dog","123.00","dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertTrue(j.InvalidJobTypes());

        JobPost j2 = new JobPost("LTD","Babysitter","--Please select--","123.00","dajsdhkajgsdkjasgfakjsd", 1, "Paid", "Completed");
        assertFalse(j2.InvalidJobTypes());


    }

    @Test
    public void checkJobGeoLocation(){
        JobPost j1 = new JobPost();
        GeoLocation g = new GeoLocation(11.0,12.0);
        j1.setGeoLocation(g);
        assertTrue(j1.validGeoLoc());

        JobPost j2 = new JobPost();
        g = new GeoLocation(-12.0,12.0);
        j2.setGeoLocation(g);
        assertTrue(j2.validGeoLoc());
    }

}
