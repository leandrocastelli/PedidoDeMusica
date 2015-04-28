package com.lcsmobileapps.pedidodemusicas.service;

public interface SoundPlayer {
	public void startPlaying(int soundID);
    public boolean isAdLoaded();
    public int playingId();
    public void stopPlaying();
}
