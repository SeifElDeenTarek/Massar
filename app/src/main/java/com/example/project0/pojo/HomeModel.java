package com.example.project0.pojo;

public class HomeModel
{
    int sectionImage;
    int sectionName;

    public HomeModel(int sectionImage, int sectionName)
    {
        this.sectionImage = sectionImage;
        this.sectionName = sectionName;
    }

    public int getSectionImage()
    {
        return sectionImage;
    }

    public void setSectionImage(int sectionImage)
    {
        this.sectionImage = sectionImage;
    }

    public int getSectionName()
    {
        return sectionName;
    }

    public void setSectionName(int sectionName)
    {
        this.sectionName = sectionName;
    }
}
