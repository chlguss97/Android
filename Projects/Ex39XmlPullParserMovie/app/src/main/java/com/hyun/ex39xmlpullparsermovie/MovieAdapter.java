package com.hyun.ex39xmlpullparsermovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VH> {

    Context context;
    ArrayList<MovieItem> items;

    public MovieAdapter(Context context, ArrayList<MovieItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item,parent,false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MovieItem item= items.get(position);

        holder.tvRank.setText(item.rank);
        holder.tvMovieNm.setText(item.movieNm);
        holder.tvOpenDt.setText(item.openDt);
        holder.tvAudiAcc.setText(item.audiAcc+"명");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tvRank, tvMovieNm, tvOpenDt, tvAudiAcc;
        public VH(@NonNull View itemView) {
            super(itemView);

            tvRank= itemView.findViewById(R.id.tv_rank);
            tvMovieNm= itemView.findViewById(R.id.tv_move_nm);
            tvOpenDt= itemView.findViewById(R.id.tv_open_dt);
            tvAudiAcc= itemView.findViewById(R.id.tv_audi_acc);
        }
    }
}
