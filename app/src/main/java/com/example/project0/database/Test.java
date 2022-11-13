package com.example.project0.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity (tableName = "test_table")
public class Test {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ques")
    @Expose
    private String ques;
    @SerializedName("Rans")
    @Expose
    private String Rans;
    @SerializedName("ans1")
    @Expose
    private String ans1;
    @SerializedName("ans2")
    @Expose
    private String ans2;
    @SerializedName("ans3")
    @Expose
    private String ans3;
    @SerializedName("ans4")
    @Expose
    private String ans4;
    @SerializedName("selectedAnswer")
    @Expose
    private String selectedAnswer;

    public Test(){}

    public Test(String ques, String rans, String ans1, String ans2, String ans3, String ans4)
    {
        this.ques = ques;
        this.Rans = rans;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getRans() {
        return Rans;
    }

    public void setRans(String rans) {
        Rans = rans;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
