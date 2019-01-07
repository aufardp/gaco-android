package mobile.gaco.com.gaco;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DetailGonActivity extends AppCompatActivity {

    private TextView tvJudul, tvIsi;
    private ImageView ivKonten;
    private String receiverUploadID;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gon);

        receiverUploadID = getIntent().getExtras().get("detail").toString();
        Toast.makeText(this, "ID"+receiverUploadID, Toast.LENGTH_SHORT).show();

        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Game Online");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        tvJudul = findViewById(R.id.tv_judul_Gon);
        tvIsi = findViewById(R.id.tv_isi_Gon);
        ivKonten = findViewById(R.id.iv_detail_Gon);*/
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public static class DetailViewHolder extends RecyclerView.ViewHolder{
        public TextView tvJudul, tvIsi;
        public ImageView ivKonten;

        public DetailViewHolder(@NonNull View itemView){
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tv_judul_Gon);
            tvIsi = itemView.findViewById(R.id.tv_isi_Gon);
            ivKonten = itemView.findViewById(R.id.iv_detail_Gon);
        }

    }
}
