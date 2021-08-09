package android.example.navigationdrawer;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link studyMaterial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class studyMaterial extends Fragment {


    Constants constants;
    final static int PICK_PDF_CODE = 2342;
    EditText editText;
    Button button;
    Spinner branch;
    Spinner semeter;

    StorageReference storageReference;
    DatabaseReference databaseReference;;
    public static final int RESULT_OK = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_study_material, container, false);

        editText = (EditText) view.findViewById(R.id.title);
        button = view.findViewById(R.id.upload);
        branch = view.findViewById(R.id.branch);
        semeter = view.findViewById(R.id.semester);


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(constants.upload_path);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectPDF_file();
            }
        });
        return view;
    }

    private void SelectPDF_file() {

        // int c = 0;  <------- for rquest code
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File"),PICK_PDF_CODE);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PDF_CODE  && resultCode == RESULT_OK  && data !=null && data.getData() != null ){
            uploadPDFFile(data.getData());
        }
    }

    private void uploadPDFFile(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        // take new storageRefrence
        StorageReference reference = storageReference.child("Files/"+branch.getSelectedItem().toString()+"/"+semeter.getSelectedItem().toString()+"/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uri=taskSnapshot.getStorage().getDownloadUrl();
                        while (!uri.isComplete());    // Important condition because this will wait till the access token of url is generated.
                        Uri url = uri.getResult();   // Gets us the url of the file stored in Firebase Storage
                        UploadPDF uploadPDF = new UploadPDF(editText.getText().toString(),url.toString(),branch.getSelectedItem().toString(),semeter.getSelectedItem().toString());   // Storing this key value pair in Realtime Database
                        databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
                        Toast.makeText(getActivity(),"File Uploaded Successfully",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getActivity(),"Failed", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploading "+(int)progress+"%");

            }
        });

    }

    public void Viewfunction(View view){
        Intent intent =new Intent(getContext(),ViewPDF.class);

        // Using bundle to send out options (selected by user through spinner) to ViewPDF class

        Bundle bundle = new Bundle();
        bundle.putString(ViewPDF.ITEM_BRANCH,branch.getSelectedItem().toString());
        bundle.putString(ViewPDF.ITEM_SEMESTER,semeter.getSelectedItem().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}