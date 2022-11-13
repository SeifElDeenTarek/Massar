package com.example.project0.firebase;

import com.example.project0.pojo.ExplainSentenceModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ExplainSentenceDao
{
    private DatabaseReference reference;

    public ExplainSentenceDao()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference(ExplainSentenceModel.class.getSimpleName());
    }

    /**

     public Task<Void> add(ExplainSentenceModel explainSentenceModel)
     {
     return reference.push().setValue(explainSentenceModel);
     }

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
