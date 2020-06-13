package br.com.anonymous.frontend;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Som {

	private Clip clip;
	
	public Som() {
		try {
			clip = AudioSystem.getClip();
			
		} catch (Exception ex) {
			System.out.println("Erro ao executar SOM!");
			ex.printStackTrace();
			
		}
 	}
    
	// Com loop
    public void playSound(String soundName, int loop) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip.open(audioInputStream);
            clip.loop(loop);
            clip.start();
            
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }
    
    // Sem loop
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }
    
    
    public void playSound(String soundName, int loop, float volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip.open(audioInputStream);
            clip.loop(loop);
            
            FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(volume);
            
            clip.start();
            
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }
    
    public void stopSound(String soundName) {
    	try {
            clip.close();
            
		} catch (Exception ex) {
			System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
            
		}
    }
    
    public long getClipDuration() {
    	return clip.getMicrosecondLength();
    }
    
}