package com.example.group_7_proj;

public class JobPost{

    private String EmployerName;
    private String JobTitle;
    private String Salary;
    private String JobDetails;

    public JobPost() {}
    public JobPost(String EN,String JT,String salary,String JobDetails)
    {
        this.EmployerName = EN;
        this.JobTitle=JT;
        this.Salary=salary;
        this.JobDetails=JobDetails;
    }

    public String getEmployerName() {
        return EmployerName;
    }

    public String getJobDetails() {
        return JobDetails;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public String getSalary() {
        return Salary;
    }

    public void setEmployerName(String employerName) {
        EmployerName = employerName;
    }

    public void setJobDetails(String jobDetails) {
        JobDetails = jobDetails;
    }

    public void setJobTitle(String JT) {
        this.JobTitle = JT;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public boolean InvalidSalary()
    {
        if (Salary == null|| Salary.equals("")) {
            return false;
        }
        try {
            double dbl = Double.parseDouble(Salary);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public boolean InvalidEmployerName() {
        if (EmployerName == null||EmployerName.equals("")) {
            return false;
        }
        return true;
    }

    public boolean InvalidJobTitle() {
        String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        if(JobTitle.equals(""))
            return false;
        for(int i=0;i<29;i++) {
            if (JobTitle.contains(Character.toString(specialCharactersString.charAt(i)))) {
                return false;
            }
        }
        return true;

    }

    public boolean InvalidJobDetails() {
        if (JobDetails == null || JobDetails.equals("")) {
            return false;
        }
        return true;
    }
}
