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

    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_search);
        back = findViewById(R.id.back);
        recjoblist=findViewById(R.id.recJobList);
        filtersContainer = findViewById(R.id.filtersContainer);
        filter = findViewById(R.id.filter);
        jobCategorySpinner = findViewById(R.id.jobCategorySpinner);

        fillExampleList();
        setUpRecyclerView();



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
        exampleList = new ArrayList<>();


        db = FirebaseFirestore.getInstance();

       // String catogory = 'db.collection("Jobs").whereEqualTo("jobCategory",jobCategorySpinner.getSelectedItem().toString())';

        CollectionReference collectionReference = db.collection("Jobs");

        Toast.makeText(getApplicationContext(),GlobalStorage.jobCatogory,Toast.LENGTH_SHORT).show();

        if(GlobalStorage.jobCatogory.equals("Any")) {
            category = "";
        }else {
            Toast.makeText(getApplicationContext(),"else",Toast.LENGTH_SHORT).show();

            //collectionReference.whereEqualTo("jobCategory", "Technology");
            category = GlobalStorage.jobCatogory;
        }

        //collectionReference.;


      //  collectionReference.orderBy("jobCategory").startAt(category).endAt(category+'\uf8ff')
                //collectionReference.whereEqualTo("language","Frenche")
                collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list1) {

                                PostJobPojo p = d.toObject(PostJobPojo.class);
                                p.setJobTitle(d.getString("jobTitle"));
                               // p.setCompanyName(d.getString("companyName"));
                                //p.setCityAddress(d.getString("cityAddress"));

                                exampleList.add(p);
                            }
                            RecyclerView recyclerView = findViewById(R.id.recJobList);
                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Jobs_search.this);
                            adapter = new JobSearchAdapter(getApplicationContext(),exampleList);

                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    private void setUpRecyclerView() {

    }

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