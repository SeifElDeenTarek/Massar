package com.example.project0.pojo;

public class ExplainConvModel
{
    String german;
    String arabic;
    int germanVoice;

    public ExplainConvModel() {}

    public ExplainConvModel(String german, String arabic, int germanVoice)
    {
        this.german = german;
        this.arabic = arabic;
        this.germanVoice = germanVoice;
    }

    public String getGerman() {
        return german;
    }

    public void setGerman(String german) {
        this.german = german;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public int getGermanVoice() {
        return germanVoice;
    }

    public void setGermanVoice(int germanVoice) {
        this.germanVoice = germanVoice;
    }
}
