package com.example.myapplication.models;

public class company {
    int companyLogo;
    int companyImage;
    String name;
    int infomation;
    String codingLanguage;

    public company(int companyLogo, int companyImage, String name, int infomation, String codingLanguage) {
        this.companyLogo = companyLogo;
        this.companyImage = companyImage;
        this.name = name;
        this.infomation = infomation;
        this.codingLanguage = codingLanguage;
    }

    public int getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(int companyLogo) {
        this.companyLogo = companyLogo;
    }

    public int getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(int companyImage) {
        this.companyImage = companyImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInfomation() {
        return infomation;
    }

    public void setInfomation(int infomation) {
        this.infomation = infomation;
    }

    public String getCodingLanguage() {
        return codingLanguage;
    }

    public void setCodingLanguage(String codingLanguage) {
        this.codingLanguage = codingLanguage;
    }
}