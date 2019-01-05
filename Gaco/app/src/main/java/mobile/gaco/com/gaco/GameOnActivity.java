package mobile.gaco.com.gaco;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GameOnActivity extends AppCompatActivity implements AdapterGon.OnItemClickListener {

    private RecyclerView recyclerView;
    private AdapterGon adapterGon;
    private ProgressBar progressBarGon;
    private DatabaseReference databaseRef;
    private List<Upload> mUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_on);

        recyclerView = findViewById(R.id.rv_gon);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBarGon = findViewById(R.id.progressBar_circle);

        mUpload = new ArrayList<>();
        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Upload upload = snapshot.getValue(Upload.class);
                    mUpload.add(upload);
                }
                adapterGon = new AdapterGon(GameOnActivity.this, mUpload);
                recyclerView.setAdapter(adapterGon);
                adapterGon.setOnItemClickListener(GameOnActivity.this);
                progressBarGon.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(GameOnActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                progressBarGon.setVisibility(View.INVISIBLE);

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent detail = new Intent(this, DetailGonActivity.class);
        
    }
}
