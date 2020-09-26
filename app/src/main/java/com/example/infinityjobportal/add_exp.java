package com.example.infinityjobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.infinityjobportal.model.LOEModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class add_exp extends AppCompatActivity {
 EditText title,companyname;
 ImageView img;
 Spinner chooseemployer,choosecountry;
 Button submit;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 71;
    FirebaseFirestore db;
    StorageReference mstorageRef;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageinbtye;
    String encodedImage;
    //String encodedImage;
    private Bitmap imagetostore;
    String a;
    String b;
    String userpic;
    public Uri imguri;
    private StorageTask uploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exp);
        title = findViewById(R.id.et_title);
        companyname = findViewById(R.id.et_compname);
        chooseemployer = findViewById(R.id.sp_emp);
        choosecountry = findViewById(R.id.sp_country);
        submit = findViewById(R.id.submit);
        img = findViewById(R.id.img);
        db = FirebaseFirestore.getInstance();
        mstorageRef = FirebaseStorage.getInstance().getReference("Images");



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileChooser();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "submit called..", Toast.LENGTH_SHORT).show();
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(getApplicationContext(), "Upload in progress", Toast.LENGTH_LONG).show();

                } else {
                    Fileuploader();
                }


              LOEModel gh =new LOEModel();

                gh.setCompanyName(companyname.getText().toString());
                gh.setTitle(title.getText().toString());
                gh.setEmploymentType(chooseemployer.getSelectedItem().toString());
                gh.setChooseCountry(choosecountry.getSelectedItem().toString());


              gh.setImg(a + "." + b);

                db.collection("LOE").add(gh)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"LOE added successfull",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),ListOfExperienceActiviy.class);
                               startActivity(i);
                            }


                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

                    }
                })
                ;

            }
        });





        }
    private void Fileuploader() {

        //StorageReference ref = mstorageRef.child(System.currentTimeMillis()
        b = getExtension(imguri);
        a = String.valueOf(System.currentTimeMillis());
        StorageReference ref = mstorageRef.child(a
                + "." + getExtension(imguri));
        uploadTask = ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getUploadSessionUri();  //getDownloadUrl();
                        // Toast.makeText(getContext(),
                        //"Image uploaded", Toast.LENGTH_LONG).show();
                        //toserveraswell();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            img.setImageURI(imguri);

        }
    }



    private String getExtension(Uri uri) {
        ContentResolver cr = getApplicationContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));

    }
    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);


    }
}