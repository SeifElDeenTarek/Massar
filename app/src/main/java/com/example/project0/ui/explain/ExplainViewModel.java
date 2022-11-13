package com.example.project0.ui.explain;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project0.firebase.ExplainSentenceDao;
import com.example.project0.pojo.ExplainModel;

import java.util.ArrayList;
import java.util.List;

public class ExplainViewModel extends ViewModel
{
    public MutableLiveData<List<ExplainModel>> explainModelsList = new MutableLiveData<>();
    ExplainSentenceDao fireDao = new ExplainSentenceDao();

    public void getExplainVocabModelList()
    {
        explainModelsList.setValue(getExplainVocabModelListFromDatabase());
    }

    public void getExplainSentenceModelList()
    {
        explainModelsList.setValue(getExplainSentenceModelListFromDatabase());
    }

    public void getExplainConvModelList()
    {
        explainModelsList.setValue(getExplainConvModelListFromDatabase());
    }

    private ArrayList<ExplainModel> getExplainVocabModelListFromDatabase()
    {
        ArrayList<ExplainModel> list = new ArrayList<>();

        list.add(new ExplainModel("Hallo", "مرحبا",0));
        list.add(new ExplainModel("Guten Morgen", "صباح الخير",0));
        list.add(new ExplainModel("Guten Abend", "مساء الخير",0));
        list.add(new ExplainModel("Auf Wiedersehen", "إلى اللقاء",0));
        list.add(new ExplainModel("Tschüss", "مع السلامة",0));

        return list;
    }

    private ArrayList<ExplainModel> getExplainSentenceModelListFromDatabase()
    {
        ArrayList<ExplainModel> list = new ArrayList<>();

        list.add(new ExplainModel("Möchten Sie rauchen?", "هل تحب أن تدخن؟",0));
        list.add(new ExplainModel("Möchten Sie tanzen?", "هل تحب أن ترقص؟",0));
        list.add(new ExplainModel("Möchten Sie spazieren gehen?", "هل تحب أن تتنزه؟",0));
        list.add(new ExplainModel(" Wir möchten nach Hause fahren.", " نريد أن نذهب إلى البيت.",0));
        list.add(new ExplainModel(" Möchtet ihr ein Taxi?", "هل تريدون تكسي [سيارة أجرة]؟",0));

        return list;
    }

    private ArrayList<ExplainModel> getExplainConvModelListFromDatabase()
    {
        ArrayList<ExplainModel> fireList = new ArrayList<>();

        return fireList;
    }
}
