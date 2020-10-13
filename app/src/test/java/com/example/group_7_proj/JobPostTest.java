package com.example.group_7_proj;
import org.junit.Test;

import static org.junit.Assert.*;

public class JobPostTest
{

    @Test
    public void InvalidSalaryNotNumber()
    {
        JobPost j = new JobPost("LTD","Babysitter","*","dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidSalary());
    }

    @Test
    public void ValidSalary()
    {
        JobPost j = new JobPost("LTD","Babysitter","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidSalary());
    }

    @Test
    public void InValidSalaryByNull()
    {
        JobPost j = new JobPost("LTD","Babysitter",null,"dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidSalary());
    }

    @Test
    public void InValidEmployerName()
    {
        JobPost j = new JobPost("","Babysitter","123.00","dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidEmployerName());
    }

    @Test
    public void ValidEmployerName()
    {
        JobPost j = new JobPost("LTD","Babysitter","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidEmployerName());
    }

    @Test
    public void InValidJobTitle()
    {
        JobPost j = new JobPost("LTD","!","123.00","dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidJobTitle());
    }

    @Test
    public void ValidJobTitle()
    {
        JobPost j = new JobPost("LTD","Babysitter","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidJobTitle());
    }
    @Test
    public void InValidJobDetails()
    {
        JobPost j = new JobPost("LTD","Babysitter","123.00","");
        assertFalse(j.InvalidJobDetails());
    }

    @Test
    public void ValidJobDetails()
    {
        JobPost j = new JobPost("LTD","Babysitter","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidJobDetails());
    }

}
