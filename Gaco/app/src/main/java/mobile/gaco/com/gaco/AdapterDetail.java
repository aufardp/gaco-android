package mobile.gaco.com.gaco;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.DetailHolder> {

    private List<Upload> mUploads;
    private Context mContext;

    public AdapterDetail(Context context, List<Upload> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gon_detail,parent, false);
        return new AdapterDetail.DetailHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterDetail.DetailHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.tvJudulGon.setText(uploadCurrent.getJudul());
        holder.tvIsiGon.setText(uploadCurrent.getIsi());
        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(holder.ivDetailGon);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder{
        public TextView tvJudulGon, tvIsiGon;
        public ImageView ivDetailGon;

        public DetailHolder(View itemView){
            super(itemView);

            tvJudulGon = itemView.findViewById(R.id.tv_judul_Gon);
            tvIsiGon = itemView.findViewById(R.id.tv_isi_Gon);
            ivDetailGon = itemView.findViewById(R.id.iv_detail_Gon);

        }
    }
}
