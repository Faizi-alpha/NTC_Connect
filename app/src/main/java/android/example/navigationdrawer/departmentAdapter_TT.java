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

public class departmentAdapter_TT extends Fragment {

    View view2;
    RecyclerView recyclerView2;
    ArrayList<department_Items> arrayList2;
    CustomAdapter2 adapter2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view2 = inflater.inflate(R.layout.recycler_view,container,false);
        recyclerView2 = view2.findViewById(R.id.recycler_view);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        arrayList2 = new ArrayList<>();
        arrayList2.add(new department_Items("Dr.Neeraj Agarwal","Head of Department",""));
        arrayList2.add(new department_Items("Mr.Vivek Agarwal","Assistant Professor",""));
        arrayList2.add(new department_Items("Mrs.Nidhi Sisodia","Assistant Professor",""));
        arrayList2.add(new department_Items("Mr.V.S.Khoiwal","Assistant Professor",""));
        arrayList2.add(new department_Items("Mr.M.K.Bansal","Assistant Professor",""));
        arrayList2.add(new department_Items("Ms.Ruvinder Kumari","Assistant Professor",""));
        arrayList2.add(new department_Items("Mrs.Shweta Chauhan","Assistant Professor",""));
        arrayList2.add(new department_Items("Mr.Girendra Pal Singh","Assistant Professor",""));

        adapter2 = new CustomAdapter2(arrayList2);
        recyclerView2.setAdapter(adapter2);
        return view2;
    }

    public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder> {


        ArrayList<department_Items> arrayList;

        // CustomAdapter Constructor

        public CustomAdapter2(ArrayList<department_Items> arrayList) {
            this.arrayList = arrayList;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view2 = LayoutInflater.from(getContext()).inflate(R.layout.department_itemview, parent, false);
            return new ViewHolder(view2);
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
