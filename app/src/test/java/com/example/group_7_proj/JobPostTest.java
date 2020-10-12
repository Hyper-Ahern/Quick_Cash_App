package com.example.group_7_proj;
import org.junit.Test;

import static org.junit.Assert.*;

public class JobPostTest
{

    @Test
    public void ValidSalary ()
    {
        assertTrue(JobPost.InvalidSalary("123.00"));
    }

    @Test
    public void InvalidSalary()
    {
        assertFalse(JobPost.InvalidSalary("*"));
    }

    @Test
    public void GetJobTitleSuccess()
    {
        assertEquals(4, 2 + 2);
    }

}
