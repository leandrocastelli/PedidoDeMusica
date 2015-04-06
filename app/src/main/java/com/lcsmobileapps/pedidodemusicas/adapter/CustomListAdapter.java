package com.lcsmobileapps.pedidodemusicas.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcsmobileapps.pedidodemusicas.PedidosActivity;
import com.lcsmobileapps.pedidodemusicas.R;
import com.lcsmobileapps.pedidodemusicas.util.ImageHelper;


//
public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {

	private Context ctx;
	  
	public static class ViewHolder extends RecyclerView.ViewHolder{
		ImageView imageView;
		TextView txtName;
		CardView cardView;
        int position;

        public ViewHolder(View v, final Context ctx) {
            super(v);
            this.cardView = (CardView)v;
            imageView = (ImageView)v.findViewById(R.id.radio_icon);
            txtName = (TextView)v.findViewById(R.id.sound_name);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PedidosActivity activity = (PedidosActivity)ctx;
                    activity.getPlayer().startPlaying(PedidosActivity.FIRST+position);
                }
            });
        }
	}
	public CustomListAdapter (Context ctx) {
		super();
		this.ctx = ctx;
		
		
	}
	@Override
	public int getItemCount() {
		String[] array = ctx.getResources().getStringArray(R.array.musicas_list);
		return array.length;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(v,ctx);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtName.setText(ctx.getResources().getStringArray(R.array.musicas_list)[position]);
//		ImageHelper.loadImage(holder.imageView, R.id.radio_icon, ctx);
        holder.imageView.setImageResource(R.drawable.ic_radio);
        holder.position = position;



    }

}
