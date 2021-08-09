package android.example.navigationdrawer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class departmentAdapter extends  Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<department_Items> arrayList;
    CustomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        arrayList.add(new department_Items("Dr.B.K.Sharma","Head of Department","drbknitra@gmail.com"));
        arrayList.add(new department_Items("Dr.(Mrs.)Meghna Tyagi","Professor",""));
        arrayList.add(new department_Items("Mr.K.K.Dewan","Assistant Professor","kkdewan@nitratextile.org"));
        arrayList.add(new department_Items("Mr.A.K.Sharma","Assistant Professor","avsharma@nitratextile.org"));
        arrayList.add(new department_Items("Mr.A.P.Srivastava","Assistant Professor","apsrivastava@nitra.ac.in"));
        arrayList.add(new department_Items("Mr.N.K.Sharma","Assistant Professor","nitra.nitin48@gmail.com"));
        arrayList.add(new department_Items("Mr.Saurabh Jain","Assistant Professor",""));
        arrayList.add(new department_Items("Mr.Sanjay Gupta","Assistant Professor",""));
        arrayList.add(new department_Items("Mr.R.C.Yadaw","Assistant Professor",""));

        adapter = new CustomAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        return view;
    }

   public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{


       ArrayList<department_Items> arrayList;

       // CustomAdapter Constructor

       public CustomAdapter(ArrayList<department_Items> arrayList) {
           this.arrayList = arrayList;
       }


       @NonNull
       @Override
       public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view = LayoutInflater.from(getContext()).inflate(R.layout.department_itemview, parent, false);
           return new ViewHolder(view);
       }

       @Override
       public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.name.setText(arrayList.get(position).getName());
            holder.designation.setText(arrayList.get(position).getDesignation());
            holder.email.setText(arrayList.get(position).getEmail());
       }

       @Override
       public int getItemCount() {
           return arrayList.size();
       }

       public class ViewHolder extends RecyclerView.ViewHolder{

           TextView name;
           TextView designation;
           TextView email;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name =(TextView) itemView.findViewById(R.id.psname);
                designation = (TextView) itemView.findViewById(R.id.designation);
                email = (TextView) itemView.findViewById(R.id.psemail);
            }
        }

   }

}

