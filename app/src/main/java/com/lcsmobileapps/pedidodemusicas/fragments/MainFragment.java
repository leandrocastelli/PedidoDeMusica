package com.lcsmobileapps.pedidodemusicas.fragments;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lcsmobileapps.pedidodemusicas.PedidosActivity;
import com.lcsmobileapps.pedidodemusicas.R;
import com.lcsmobileapps.pedidodemusicas.adapter.CustomListAdapter;
import com.lcsmobileapps.pedidodemusicas.filemanager.FileManager;
import com.lcsmobileapps.pedidodemusicas.filemanager.FileManager.Props;


public class MainFragment extends Fragment{
	public final static int FIRST = R.raw.a_vo_do_nelson_nuggets;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		final View v = inflater.inflate(R.layout.pedidos_fragment, container,false);
		setHasOptionsMenu(true);

        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);

        /*
        ListView listView = (ListView)v.findViewById(R.id.list_view);
		listView.setAdapter(new CustomListAdapter(getActivity()));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				PedidosActivity activity = (PedidosActivity)getActivity();
				activity.getPlayer().startPlaying(FIRST+position);
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Vibrator vibe = (Vibrator)v.getContext().getSystemService(Context.VIBRATOR_SERVICE);
				vibe.vibrate(100);
				createDialog(position).show();
				return false;
			}
			
		});

		*/

        
		//Old Banner Ads
			//AdView adView = (AdView)v.findViewById(R.id.ad);
			//String locationProvider = LocationManager.NETWORK_PROVIDER;
			//LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
			//Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
			//adView.loadAd(new AdRequest.Builder().setLocation(lastKnownLocation).build());
		FileManager.map.put(0,Props.RINGTONE);
		FileManager.map.put(1,Props.NOTIFICATION);
		FileManager.map.put(2,Props.ALARM);
		FileManager.map.put(3,Props.SEND);
		((PedidosActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		return v;
	}
	public AlertDialog.Builder createDialog(final int position) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getString(R.string.dialog_set_title));

		builder.setNegativeButton(getString(R.string.cancel), new AlertDialog.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});

		List<String> listString = new ArrayList<String>(3);
		listString.add(getString(R.string.ringtone));
		listString.add(getString(R.string.notification));
		listString.add(getString(R.string.alarm));
		listString.add(getString(R.string.share));
		
		
		
		
		final ArrayAdapter<String>  adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, android.R.id.text1, listString);
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				
					String path;
					boolean result = false;
					InputStream in = getResources().openRawResource(FIRST+position);
					Props selection = FileManager.map.get(arg1);
					
					path = FileManager.getInstance().copyFile(adapter.getContext(), selection, in,position);
					
					if((path.length()>0)) { //API Lvl 8 doesnt have isEmpty
						if (selection.equals(Props.SEND)) {
							Intent share = new Intent(Intent.ACTION_SEND);
							share.setType("audio/*");
							share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///" + path));
							startActivity(share);
						}
						result = FileManager.getInstance().setAs(path,selection, adapter.getContext());
					}
					
					if(result) {
						Toast.makeText(adapter.getContext(), adapter.getItem(arg1)+getString(R.string.set_success),Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(adapter.getContext(), adapter.getItem(arg1)+getString(R.string.set_fail),Toast.LENGTH_SHORT).show();
					}
				
				arg0.dismiss();

			}
		});
		return builder;
	}
	@Override
	public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
		
		inflater.inflate(R.menu.pedidos, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_about: {
			
			Vibrator vb = (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
			vb.vibrate(100);
			FragmentManager fm = getActivity().getSupportFragmentManager();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.setCustomAnimations(R.anim.abc_slide_in_top,0, 0, R.anim.abc_slide_out_top );
//			transaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
			Fragment currentFragment = new AboutFragment();
//			transaction.attach(currentFragment);
			transaction.replace(R.id.linear_main, currentFragment);
			
			transaction.addToBackStack(null);
			transaction.commit();
		}
		}
		return true;
	}

}
