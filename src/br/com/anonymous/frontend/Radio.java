package br.com.anonymous.frontend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class Radio implements Runnable {

	public ArrayList<String> playlist;
	private Som som;
	private int index;
	
	public Radio(ArrayList<String> playlist) {
		this.playlist = playlist;
		som = new Som();
		Collections.shuffle(this.playlist);
		index = 0;
	}
	
	@Override
	public void run() {
		System.out.println("Comecou");
		som.playSound(this.playlist.get(index));
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				som.stopSound(playlist.get(index));
				if(index + 1 < playlist.size()) {
					index = 0;
				}else {
					index++;
				}
				som = new Som();
				System.out.println("Terminou");
			}
			
		}, som.getClipDuration()); 
	}

}
