package mobile.gaco.com.gaco;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailGonActivity extends AppCompatActivity {

    private TextView tvJudul, tvIsi;
    private ImageView ivKonten;
    private String receiverUploadID;
    private DatabaseReference databaseRef;

    private RecyclerView detailView;
    private AdapterDetail adapterDetail;
    private ProgressBar progressBarGon;
    private List<Upload> mUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gon);

        receiverUploadID = getIntent().getExtras().get("detail").toString();
        Toast.makeText(this, "ID"+receiverUploadID, Toast.LENGTH_SHORT).show();

        detailView = findViewById(R.id.rv_detail);
        detailView.setHasFixedSize(true);
        detailView.setLayoutManager(new LinearLayoutManager(this));


        progressBarGon = findViewById(R.id.progressBar_circle);

        mUpload = new ArrayList<>();

        tvJudul = findViewById(R.id.tv_judul_Gon);
        tvIsi = findViewById(R.id.tv_isi_Gon);
        ivKonten = findViewById(R.id.iv_detail_Gon);

        databaseRef = FirebaseDatabase.getInstance().getReference();
        databaseRef.child("uploads").child(receiverUploadID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //Upload upload = snapshot.getValue(Upload.class);
                    //mUpload.add(upload);
                    //tvJudul.setText();
                }
                adapterDetail = new AdapterDetail(DetailGonActivity.this, mUpload);
                detailView.setAdapter(adapterDetail);
                //adapterGon.setOnItemClickListener(GameOnActivity.this);
                progressBarGon.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailGonActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                progressBarGon.setVisibility(View.INVISIBLE);            }
        });


        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Game Online");
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);

        /*tvJudul = findViewById(R.id.tv_judul_Gon);
        tvIsi = findViewById(R.id.tv_isi_Gon);
        ivKonten = findViewById(R.id.iv_detail_Gon);*/
    }



    /*public static class DetailViewHolder extends RecyclerView.ViewHolder{
        public TextView tvJudul, tvIsi;
        public ImageView ivKonten;

        public DetailViewHolder(@NonNull View itemView){
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tv_judul_Gon);
            tvIsi = itemView.findViewById(R.id.tv_isi_Gon);
            ivKonten = itemView.findViewById(R.id.iv_detail_Gon);
        }

    }*/
}
