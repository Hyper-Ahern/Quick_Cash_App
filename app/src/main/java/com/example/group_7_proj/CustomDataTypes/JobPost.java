package com.example.group_7_proj.CustomDataTypes;

public class JobPost{

    private String employerName;
    private String jobTitle;
    private String jobType;
    private String salary;
    private String jobDetails;
    private Integer userID;
    private GeoLocation geoLocation;
    private String PaymentStatus;
    private String CompletionStatus;

    public JobPost() {}
    public JobPost(String EN,String JT,String JT2,String salary,String JobDetails, int UID, String paymentStatus, String completionStatus)
    {
        this.employerName = EN;
        this.jobTitle =JT;
        this.jobType = JT2;
        this.salary =salary;
        this.jobDetails =JobDetails;
        this.userID = UID;

        //this.EmployeeID = employeeID;

        this.PaymentStatus = paymentStatus;
        this.CompletionStatus = completionStatus;
    }


    public String getEmployerName() {
        return employerName;
    }

    public String getJobDetails() {
        return jobDetails;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getSalary() {
        return salary;
    }

    public String getJobType(){
        return jobType;
    }

    public GeoLocation getGeoLocation(){
        return geoLocation;
    }

    public Integer getUserID(){
        return userID;
    }


    // AZ
    public String getPaymentStatus(){ return PaymentStatus;}

    public String getCompletionStatus(){return CompletionStatus;}


//    public int getUserID() {return UserID;}

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public void setJobDetails(String jobDetails) {
        this.jobDetails = jobDetails;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setPaymentStatus(String paymentStatus){ PaymentStatus = "Paid";}

    public void setCompletionStatus(String completionStatus) {CompletionStatus = "Completed";}

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public boolean InvalidSalary()
    {
        if (salary == null|| salary.equals("")) {
            return false;
        }
        try {
            double dbl = Double.parseDouble(salary);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public boolean InvalidEmployerName() {
        if (employerName == null|| employerName.equals("")) {
            return false;
        }
        return true;
    }

    public boolean InvalidJobTitle() {
        String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        if(jobTitle.equals(""))
            return false;
        for(int i=0;i<29;i++) {
            if (jobTitle.contains(Character.toString(specialCharactersString.charAt(i)))) {
                return false;
            }
        }
        return true;

    }

    public boolean InvalidJobDetails() {
        if (jobDetails == null || jobDetails.equals("")) {
            return false;
        }
        return true;
    }

    public boolean InvalidJobTypes() {
        if (jobType.equals("--Please select--")) {
            return false;
        }
        return true;
    }

    // type check
    public boolean validGeoLoc(){
        if((geoLocation.getLatitude() instanceof Double) && (geoLocation.getLongitude() instanceof Double)){
            return true;
        }
        return false;
    }


}
