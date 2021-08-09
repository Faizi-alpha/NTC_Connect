package android.example.navigationdrawer;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewPDF extends AppCompatActivity {


    public static final String ITEM_BRANCH = "branch";
    public static final String ITEM_SEMESTER = "semester";

    Bundle bundle;


    Constants cons;
    ListView listView;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    List<UploadPDF> list;

    String receivedBranch;
     String receivedSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_p_d_f);

        listView = findViewById(R.id.listPDF);
        list = new ArrayList<>();

        // Bundle to get user input through spinners, which is being passed to this class from MainActivity's Viewfunction (OnClick of View PDF 's button)

        bundle = getIntent().getExtras();
        assert bundle !=null;
        receivedBranch = bundle.getString(ITEM_BRANCH);
        receivedSemester = bundle.getString(ITEM_SEMESTER);



        // Call to display files in a list
        viewAllFiles();

        // Listener to open pdf when user taps on any item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                UploadPDF uploadPDF = list.get(position);
                Intent intent = new Intent();
                intent.setDataAndType(Uri.parse(uploadPDF.getUrl()),intent.ACTION_VIEW);  // ACTION_VIEW to open PDF within the app
                startActivity(intent);
            }
        });

    }

    private void viewAllFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference(cons.upload_path);   // cons.upload_path contains the general storage path of files
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Retrieve data
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    UploadPDF uploadPDF = postSnapshot.getValue(UploadPDF.class);
                    if(uploadPDF.getBranch().equals(receivedBranch)) {
                        if (uploadPDF.getSemester().equals(receivedSemester)) {
                            list.add(uploadPDF);
                        }
                    }
                }
                if (list.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No PDFs Found", Toast.LENGTH_LONG).show();
                }
                else {

                    Collections.reverse(list);   // So that the latest PDF or file shows first in the list
                    String[] uploads = new String[list.size()];

                    for(int i=0;i<uploads.length;i++)
                    {
                        uploads[i] = list.get(i).getFilename();  // Only displaying name int the listView.
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){        // simpl_list_item_1 to just display name however we can use custom layout as well

                        // This override method is used to make listview item text black in color.
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                            View view = super.getView(position,convertView,parent);
                            TextView mytext = (TextView) view.findViewById(android.R.id.text1);
                            mytext.setTextColor(Color.BLACK);
                            return view;
                        }
                    };

                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}