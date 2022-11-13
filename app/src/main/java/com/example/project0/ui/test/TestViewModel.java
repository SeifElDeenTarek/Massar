package com.example.project0.ui.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project0.database.Test;

import java.util.ArrayList;

public class TestViewModel extends ViewModel
{
    public MutableLiveData<ArrayList<Test>> testList = new MutableLiveData<>();

    public void getTestRulesList()
    {
        testList.setValue(getTestRulesListFromDatabase());
    }

    public void getTestSentenceList()
    {
        testList.setValue(getTestSentenceListFromDatabase());
    }

    public void getTestVocabList()
    {
        testList.setValue(getTestVocabListFromDatabase());
    }

     ArrayList<Test> getTestRulesListFromDatabase()
    {
        ArrayList<Test> list = new ArrayList<>();

        list.add(new Test("Laura ..... nach Wien gefahren", "ist", "seid", "ist", "bist", "sind"));
        list.add(new Test("Laura ..... nach Wien gefahren", "ist", "seid", "ist", "bist", "sind"));
        list.add(new Test("Laura ..... nach Wien gefahren", "ist", "seid", "ist", "bist", "sind"));

        return list;
    }

     ArrayList<Test> getTestSentenceListFromDatabase()
    {
        ArrayList<Test> list = new ArrayList<>();

        list.add(new Test("العفو,بكل سرور", "Bitte, gern geschehen", "Bis denn", "Bis später", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("العفو,بكل سرور", "Bitte, gern geschehen", "Bis denn", "Bis später", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("العفو,بكل سرور", "Bitte, gern geschehen", "Bis denn", "Bis später", "Keine Ursache", "Bitte, gern geschehen"));

        return list;
    }

     ArrayList<Test> getTestVocabListFromDatabase()
    {
        ArrayList<Test> list = new ArrayList<>();

        list.add(new Test("الذراع", "der Arm", "Tschüss", "Bitte", "Danke", "der Arm"));
        list.add(new Test("الذراع", "der Arm", "Tschüss", "Bitte", "Danke", "der Arm"));
        list.add(new Test("الذراع", "der Arm", "Tschüss", "Bitte", "Danke", "der Arm"));

        return list;
    }
}
