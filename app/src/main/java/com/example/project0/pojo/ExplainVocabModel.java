package com.example.project0.pojo;

public class ExplainVocabModel
{
    String german;
    String arabic;
    String germanVoice;

    public ExplainVocabModel() {}

    public ExplainVocabModel(String german, String arabic, String germanVoice)
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

    public String getGermanVoice() {
        return germanVoice;
    }

    public void setGermanVoice(String germanVoice) {
        this.germanVoice = germanVoice;
    }
}
