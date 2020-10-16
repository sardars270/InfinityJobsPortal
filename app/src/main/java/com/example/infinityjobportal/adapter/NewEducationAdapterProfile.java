package com.example.infinityjobportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.PojoAddNewEducation;

import java.util.List;

public class NewEducationAdapterProfile extends RecyclerView.Adapter<NewEducationAdapterProfile.EducationViewHolder> {


    private Context context;
    private List<PojoAddNewEducation> educationList;

    // public NewEducationAdapter(Context context, List<pojoAddNewEducation> educationList) {
    //  this.context = context;
    // this.educationList = educationList;
    // }

    public NewEducationAdapterProfile(Context context, List<PojoAddNewEducation> educationList) {
        this.context = context;
        this.educationList = educationList;
    }


    @NonNull
    @Override
    public NewEducationAdapterProfile.EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EducationViewHolder(LayoutInflater.from(context).inflate(R.layout.education_item_recyclerview_profile,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull NewEducationAdapterProfile.EducationViewHolder holder, final int position) {
        final PojoAddNewEducation pojoAddNewEducation = educationList.get(position);
        holder.school.setText(pojoAddNewEducation.getSchool());
        holder.details.setText(pojoAddNewEducation.getDegree() +","+"  " +"("+pojoAddNewEducation.getFieldOfStudy()+")");
//        holder.grade.setText("Grade"+"  "+pojoAddNewEducation.getGrade());
  //      holder.timeperiod.setText("From"+"  " +pojoAddNewEducation.getStartDate()+"  "+ "To"+"  "+pojoAddNewEducation.getEndDate());
    //    holder.extraacts.setText("Activities and societies"+" - "+pojoAddNewEducation.getExtraActs());
      //  holder.description.setText("Description"+" - "+pojoAddNewEducation.getdescription());
       /* holder.update_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // pojoAddNewEducation pojoAddNewEducation = educationList.get(getAdapterPosition());
                Intent intent = new Intent(context, EditEducation.class);
                intent.putExtra("Education", pojoAddNewEducation);
                context.startActivity(intent);

            }
        });

        */

    }



    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public class EducationViewHolder extends RecyclerView.ViewHolder  {

        private TextView school;
        private TextView details;
        private TextView grade;
        private TextView extraacts;
        private TextView description;
        private TextView timeperiod;
        private Button update_education;

        public EducationViewHolder(View view) {
            super(view);
            school = itemView.findViewById(R.id.institution_name);
            details = itemView.findViewById(R.id.details_0f_education);
          //  grade = itemView.findViewById(R.id.txtgrade);
           // extraacts = itemView.findViewById(R.id.txtextraAct);
            //description = itemView.findViewById(R.id.txtdescription);
            //timeperiod=itemView.findViewById(R.id.timeperiod);
            //update_education=itemView.findViewById(R.id.update_education);


        }


    }
}
