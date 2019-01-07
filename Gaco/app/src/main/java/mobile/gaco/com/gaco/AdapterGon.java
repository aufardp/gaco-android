package mobile.gaco.com.gaco;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterGon extends RecyclerView.Adapter<AdapterGon.GonHolder> {
    private List<Upload> mUploads;
    private Context mContext;
    private ClickListener itemClickListener;

    public AdapterGon(Context context, List<Upload> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public GonHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gon,parent, false);
        return new GonHolder(view);
    }

    @Override
    public void onBindViewHolder( GonHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.tvGon.setText(uploadCurrent.getJudul());
        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.ivGon);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class GonHolder extends RecyclerView.ViewHolder{
        public TextView tvGon, tvJudulGon, tvIsiGon;
        public ImageView ivGon, ivDetailGon;

        public GonHolder(View v){
            super(v);

            tvGon = v.findViewById(R.id.tvGon);
            ivGon = v.findViewById(R.id.ivGon);
            tvJudulGon = v.findViewById(R.id.tv_judul_Gon);
            tvIsiGon = v.findViewById(R.id.tv_isi_Gon);
            ivDetailGon = v.findViewById(R.id.iv_detail_Gon);

        }
    }

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(ClickListener listener){
        itemClickListener = listener;
    }


}
