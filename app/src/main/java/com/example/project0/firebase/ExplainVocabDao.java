package com.example.project0.firebase;

import com.example.project0.pojo.ExplainVocabModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class ExplainVocabDao
{
    private DatabaseReference reference;

    public ExplainVocabDao()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference(ExplainVocabModel.class.getSimpleName());
    }

    public Task<Void> add(ExplainVocabModel explainVocabModel)
    {
        return reference.push().setValue(explainVocabModel);
    }

    /**

     public Task<Void> update(String key, HashMap<String, Object> hashMap)
     {
     return reference.child(key).updateChildren(hashMap);
     }

     public Task<Void> remove(String key)
     {
     return reference.child(key).removeValue();
     }

     **/

    public Query get()
    {
        return reference.orderByKey();
    }
}
