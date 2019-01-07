package mobile.gaco.com.gaco;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
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

public class FragGameON extends Fragment {

    private View viewGon;
    private RecyclerView GonList;
    private ProgressBar mProgressBarCircle;
    private DatabaseReference databaseRef;

    private FirebaseAuth auth;
    private String currentUserId;
    private Context context;
    private List<Upload> mUploads;

    /*private static final String TAG = FragGameON.class.getSimpleName();
    private RecyclerView GonRecyclerview;
    private LinearLayoutManager linearLayoutManager;
    private AdapterGon adapterGon;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;*/

    public FragGameON(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewGon = inflater.inflate(R.layout.frag_game_on, container, false);

        GonList = viewGon.findViewById(R.id.rv_gon);
        GonList.setLayoutManager(new LinearLayoutManager(getContext()));
        //mUploads = new ArrayList<>();


        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        //auth = FirebaseAuth.getInstance();
        //currentUserId = auth.getCurrentUser().getUid();

        return viewGon;
    }


    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Upload> options =
                new FirebaseRecyclerOptions.Builder<Upload>()
                .setQuery(databaseRef, Upload.class)
                .build();

        FirebaseRecyclerAdapter<Upload, GameOnViewholder> adapter =
                new FirebaseRecyclerAdapter<Upload, GameOnViewholder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull GameOnViewholder holder, final int position, @NonNull Upload model) {
                        holder.tvGon.setText(model.getJudul());
                        Picasso.get().load(model.getImageUrl()).into(holder.ivGon);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String detail = getRef(position).getKey();

                                Intent godetail = new Intent(getActivity(), DetailGonActivity.class);
                                godetail.putExtra("detail", detail);
                                getActivity().startActivity(godetail);
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public GameOnViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View viewGon = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gon, parent, false);
                        GameOnViewholder viewholder = new GameOnViewholder(viewGon);
                        return  viewholder;
                    }
                };

        GonList.setAdapter(adapter);
        adapter.startListening();
        /*FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Upload>()
                .setQuery(databaseRef, Upload.class)
                .build();

        final FirebaseRecyclerAdapter<Upload, GameOnViewholder> adapter
                = new FirebaseRecyclerAdapter<Upload, GameOnViewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final GameOnViewholder holder, int position, @NonNull Upload model) {
                //final String post = getRef(position).getKey();
                final Upload uploadCurrent = mUploads.get(position);

                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("uploads")){
                            String image = dataSnapshot.child("imageUrl").getValue().toString();
                            String nama = dataSnapshot.child("name").getValue().toString();

                            holder.tvGon.setText(nama);
                            Picasso.with(getContext()).load(image).into(holder.ivGon);
                        }

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Upload upload = snapshot.getValue(Upload.class);
                            mUploads.add(upload);
                            Picasso.get()
                                    .load(uploadCurrent.getImageUrl())
                                    .placeholder(R.mipmap.ic_launcher)
                                    .fit()
                                    .centerCrop()
                                    .into(holder.ivGon);

                        }

                        mProgressBarCircle.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        mProgressBarCircle.setVisibility(View.INVISIBLE);

                    }
                });
            }

            @NonNull
            @Override
            public GameOnViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View viewGon = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_new_post, parent, false);
                GameOnViewholder viewholder = new GameOnViewholder(viewGon);
                return  viewholder;
            }
        };

        GonList.setAdapter(adapter);
        adapter.startListening();*/
    }

    public static class GameOnViewholder extends RecyclerView.ViewHolder{
        public TextView tvGon;
        public ImageView ivGon;

        public GameOnViewholder(@NonNull View itemView){
            super(itemView);

            tvGon = itemView.findViewById(R.id.tvGon);
            ivGon = itemView.findViewById(R.id.ivGon);
        }

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Game Online Terkini");
    }


    /*private void DaftarItem(){
        int count = 0;
        for (String nama : Nama){
            arrayList.add(new DataFilter(nama, Gambar[count]));
            count++;
        }
    }*/

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_game_on, container, false);
        getActivity().setTitle("Game Online");
        linearLayoutManager = new LinearLayoutManager(getActivity());
        GonRecyclerview = (RecyclerView)view.findViewById(R.id.rv_gon);
        GonRecyclerview.setHasFixedSize(true);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        childRef = mDatabaseRef.child("uploads");
        adapterGon = new AdapterGon(RecipeObject.class, R.layout.item_gon, GonHolder.class, childRef, getContext());
        GonRecyclerview.setLayoutManager(linearLayoutManager);
        GonRecyclerview.setAdapter(adapterGon);
        return view;
    }*/


    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_game_on);

        mRecyclerView = findViewById(R.id.rv_gon);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressBarCircle = findViewById(R.id.progressBar_circle);

        mUploads = new ArrayList<>();

        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);

                    adapterGon = new AdapterGon(FragGameON.this, mUploads);

                    mRecyclerView.setAdapter(adapterGon);
                    mProgressBarCircle.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FragGameON.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBarCircle.setVisibility(View.INVISIBLE);
            }
        });

    }*/

}
