package com.lcsmobileapps.pedidodemusicas;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.lcsmobileapps.pedidodemusicas.fragments.AboutFragment;
import com.lcsmobileapps.pedidodemusicas.fragments.MainFragment;
import com.lcsmobileapps.pedidodemusicas.service.MediaService;
import com.lcsmobileapps.pedidodemusicas.service.MediaService.LocalBinder;
import com.lcsmobileapps.pedidodemusicas.service.SoundPlayer;
import com.lcsmobileapps.pedidodemusicas.util.AppRater;
import com.lcsmobileapps.pedidodemusicas.util.ImageHelper;


public class PedidosActivity extends ActionBarActivity implements ServiceConnection {

	private SoundPlayer soundPlayer;
	public final static int FIRST = R.raw.a_vo_do_nelson_nuggets;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		bindService(new Intent(this,MediaService.class), this, Context.BIND_AUTO_CREATE);
		setContentView(R.layout.activity_pedidos);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setSubtitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
		final int maxMemory = (int)(Runtime.getRuntime().maxMemory() /1024);
		
		int cacheSize = maxMemory/12;
		ImageHelper.mMemoryCache = new LruCache<String, Bitmap>(cacheSize);
		
		AppRater.app_launched(this);
		if (savedInstanceState==null) {


			FragmentManager fm = getSupportFragmentManager();
			Fragment fragment = new MainFragment();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.add(R.id.linear_main, fragment);
			transaction.commit();
		}
	}



	public void onServiceConnected(ComponentName arg0, IBinder service) {
		LocalBinder localBinder = (LocalBinder) service;
		soundPlayer = localBinder.getSoundPlayer();

	}
	@Override
	public void onServiceDisconnected(ComponentName arg0) {
		soundPlayer = null;

	}
	public SoundPlayer getPlayer() {
		return soundPlayer;
	}
	@Override
	public void onStop() {
		super.onStop();
		boolean isBound = bindService(new Intent(this,MediaService.class), this, Context.BIND_AUTO_CREATE);
		if(isBound && !getPlayer().isAdLoaded()) {
            unbindService(this);
        }
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		bindService(new Intent(this,MediaService.class), this, Context.BIND_AUTO_CREATE);
		
	}
	
	
	

}
