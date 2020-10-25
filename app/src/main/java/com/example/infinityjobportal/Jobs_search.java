package com.example.infinityjobportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infinityjobportal.adapter.JobSearchAdapter;
import com.example.infinityjobportal.model.PostJobPojo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Jobs_search extends AppCompatActivity {
    private RecyclerView recjoblist;
    ImageView back;
    SearchView searchView;
    private ArrayList<PostJobPojo> list=new ArrayList<PostJobPojo>();
    JobSearchAdapter adapter;
    FirebaseFirestore db;
    private List<PostJobPojo> exampleList;
    LinearLayout filtersContainer;
    TextView filter;
    private Spinner jobCategorySpinner;
    Query q;
    String category;
    int count=0;

    CollectionReference collectionReference ;
    Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_search);
        back = findViewById(R.id.back);
        recjoblist=findViewById(R.id.recJobList);
        filtersContainer = findViewById(R.id.filtersContainer);
        filter = findViewById(R.id.filter);
        jobCategorySpinner = findViewById(R.id.jobCategorySpinner);
        db= FirebaseFirestore.getInstance();
        collectionReference=db.collection("Jobs");

        fillExampleList();
       // setUpRecyclerView();



/*
        jobCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillExampleList();
                setUpRecyclerView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // String t = String.valueOf(filtersContainer.getVisibility());


                if(filtersContainer.getVisibility()== View.GONE)
                {
                    filtersContainer.setVisibility(View.VISIBLE);
                }
                else{
                    filtersContainer.setVisibility(View.GONE);
                }

              //  filtersContainer.setVisibility(View.VISIBLE);
            }
        });

    }




    private void fillExampleList() {
        db = FirebaseFirestore.getInstance();
        exampleList = new ArrayList<>();





/*
        if(GlobalStorage.language.equals("English") || GlobalStorage.language.equals("French") || GlobalStorage.language.equals("English & French"))
        {
            count++;

        }//language id end

*/
      //  collectionReference.orderBy("language").startAt(GlobalStorage.language).endAt(GlobalStorage.language+'\uf8ff')


        if (!GlobalStorage.language.equals("") && !GlobalStorage.jobCatogory.equals("Any")) {// botth active
            query=collectionReference.whereEqualTo("language", GlobalStorage.language).whereEqualTo("jobCategory",GlobalStorage.jobCatogory);

        } else if (!GlobalStorage.language.equals("") && GlobalStorage.jobCatogory.equals("Any")) {// only language active && category disabled {
            query=collectionReference.whereEqualTo("language", GlobalStorage.language);
        }
        else if (GlobalStorage.language.equals("") && !GlobalStorage.jobCatogory.equals("Any")) {// only language disabled && category active {
            query=collectionReference.whereEqualTo("jobCategory",GlobalStorage.jobCatogory);
        }else {
          query=collectionReference;
        }

          // .orderBy("jobCategory").startAt(category).endAt(category + '\uf8ff')
                  query .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    if (!queryDocumentSnapshots.isEmpty()) {

                        List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot d : list1) {

                            PostJobPojo p = d.toObject(PostJobPojo.class);
                            p.setJobTitle(d.getString("jobTitle"));
                            p.setCompanyName(d.getString("companyName"));
                            p.setCityAddress(d.getString("cityAddress"));
//                            p.setId(d.getId());

                            exampleList.add(p);
                        }
                        RecyclerView recyclerView = findViewById(R.id.recJobList);
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Jobs_search.this);
                        adapter = new JobSearchAdapter(getApplicationContext(), exampleList);

                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        } ;

    //fillExamplelist     end here


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}