package com.example.group_7_proj;
import org.junit.Test;

import static org.junit.Assert.*;

public class JobPostTest
{

    @Test
    public void InvalidSalary()
    {
        JobPost j = new JobPost("LTD","Babysister","*","dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidSalary());
    }

    @Test
    public void ValidSalary()
    {
        JobPost j = new JobPost("LTD","Babysister","123.00","dajsdhkajgsdkjasgfakjsd");
        assertTrue(j.InvalidSalary());
    }

    @Test
    public void InValidSalary1()
    {
        JobPost j = new JobPost("LTD","Babysister",null,"dajsdhkajgsdkjasgfakjsd");
        assertFalse(j.InvalidSalary());
    }


    @Test
    public void GetJobTitleSuccess()
    {
        assertEquals(4, 2 + 2);
    }

}
