package com.lcsmobileapps.pedidodemusicas.fragments;


import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.lcsmobileapps.pedidodemusicas.PedidosActivity;
import com.lcsmobileapps.pedidodemusicas.R;
import com.lcsmobileapps.pedidodemusicas.util.ImageHelper;

public class AboutFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		View v = inflater.inflate(R.layout.about, container,false);
		ImageView imageView = (ImageView)v.findViewById(R.id.about_pic);
		setHasOptionsMenu(true);
		ImageHelper.loadImage(imageView, R.drawable.ivan_davis, getActivity());
		((PedidosActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		TextView txt = (TextView)v.findViewById(R.id.text_about);
		txt.setTextColor(Color.BLUE);
		getActivity().setTitle("Sobre");
		TextView link = (TextView)v.findViewById(R.id.txt_link);
		String linkString = "Visite <a href='http://www.djivandavis.com.br'>DJ Ivan Davis</a> web page.";
		link.setText(Html.fromHtml(linkString));
		link.setMovementMethod(LinkMovementMethod.getInstance());




       /* Old Banner ads
        AdView adView = (AdView)v.findViewById(R.id.ad_about);
		String locationProvider = LocationManager.NETWORK_PROVIDER;
		LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
		Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
		AdRequest.Builder builder = new AdRequest.Builder();
        builder.setLocation(lastKnownLocation);

        AdRequest adRequest = builder.build();
		adView.loadAd(adRequest);
		
		*/
	
		TextView txtYoutube = (TextView)v.findViewById(R.id.txt_youtube);
		String linkYoutube = "Veja mais no <a href='http://www.youtube.com/channel/UCK_iMRZ8vNggF6D1Lgw2V7w'>Canal do Youtube</a>.";
		txtYoutube.setText(Html.fromHtml(linkYoutube));
		txtYoutube.setMovementMethod(LinkMovementMethod.getInstance());
		
		TextView txtLCS = (TextView)v.findViewById(R.id.txt_lcs);
		String linkLCS = "Confira os outros aplicativos do <a href='https://play.google.com/store/apps/developer?id=LCS+Mobile+Apps'>LCS Mobile Apps</a>.";
		txtLCS.setText(Html.fromHtml(linkLCS));
		txtLCS.setMovementMethod(LinkMovementMethod.getInstance());
		return v;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		
		
	case android.R.id.home: {
		getActivity().getSupportFragmentManager().popBackStack();
	}
		}
	
	return true;
	}
}
