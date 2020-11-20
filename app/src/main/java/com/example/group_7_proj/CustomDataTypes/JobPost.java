package com.example.group_7_proj.CustomDataTypes;

public class JobPost{

    private String EmployerName;
    private String JobTitle;
    private String JobType;
    private String Salary;

    private String JobDetails;
    private int UserID;

    public JobPost() {}
    public JobPost(String EN,String JT,String JT2,String salary,String JobDetails, int UID)
    {
        this.EmployerName = EN;
        this.JobTitle=JT;
        this.JobType = JT2;
        this.Salary=salary;
        this.JobDetails=JobDetails;
        this.UserID = UID;
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

    public String getJobType(){
        return JobType;
    }

    public int getUserID() {return UserID;}

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

    public void setUserID(int userID) {UserID = userID;}

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

    public boolean InvalidJobTypes() {
        if (JobType.equals("--Please select--")) {
            return false;
        }
        return true;
    }



}
