package mobile.gaco.com.gaco;

import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class DetailGonActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterGon adapterGon;
    private ProgressBar progressBarGon;
    private DatabaseReference databaseRef;
    private List<Upload> mUpload;

    private TextView tvJudul, tvIsi;
    private ImageView ivKonten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gon);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Game Online");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        tvJudul = findViewById(R.id.tv_judul_Gon);
        tvIsi = findViewById(R.id.tv_isi_Gon);
        ivKonten = findViewById(R.id.iv_detail_Gon);
    }
}
