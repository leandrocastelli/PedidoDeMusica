package com.lcsmobileapps.pedidodemusicas.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MediaService extends Service implements SoundPlayer{

	public static final String LOG = "PedidoDeMusica";
    protected  boolean adLoaded = false;
    private int idPlaying = -1;
	public class LocalBinder extends Binder
	{
		public SoundPlayer getSoundPlayer() {
			return MediaService.this;
		}
	}
	private MediaPlayer player;
	public final IBinder connection = new LocalBinder();
	
	@Override
	public IBinder onBind(Intent arg0) {
		
		return connection;
	}
    InterstitialAd ads;
	@Override
	public void onCreate() {
		super.onCreate();

        ads = new InterstitialAd(this);
        ads.setAdUnitId("ca-app-pub-6157311586051121/5921794890");
        ads.loadAd(new AdRequest.Builder().build());
        ads.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                adLoaded = false;
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                if(player!=null) {
                    player.stop();
                    player.release();
                    player = null;
                    idPlaying = -1;
                }
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                adLoaded = true;
            }
        });
		
		
	}
	public void startPlaying(int soundID)
	{
        if (ads.isLoaded()) {
            ads.show();
        }
		try {
			if(player!= null)
			{
				if(player.isPlaying())
				{
					player.stop();
					player.reset();

				}
			}
			player = MediaPlayer.create(this,soundID);
			player.setOnCompletionListener(new OnCompletionListener() {

				public void onCompletion(MediaPlayer mp) {


					stopSelf();

				}
			});
            idPlaying = soundID;
			player.start();

		}
		catch (IllegalStateException e) {
			player = null;
			startPlaying(soundID);
		}
	}
	
	@Override
	public void onDestroy() {

		super.onDestroy();

		if(player != null) {
            player.stop();
			player.release();
            player = null;
            idPlaying = -1;
		}
	}
	
	public int onStartCommand(Intent intent, int flags, int startId)  {
		super.onStartCommand(intent, flags, startId);
	//	startPlaying();
		
		return 0;
	}
    public boolean isAdLoaded() {
        return adLoaded;
    }

    public int playingId() {
        return idPlaying;
    }

    public void stopPlaying() {
        if (player != null && player.isPlaying()) {
            player.stop();
            player.release();
            player = null;
            idPlaying = -1;
        }
    }
}
