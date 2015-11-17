/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieplayer.hardware;

/**
 *
 * @author Sasa2905
 */
public class ActuatorSignal {
    private int id;
    //Start-time of signal in ms
    private double startTime;
    //End-time of signal in ms
    private double endTime;
    //Speed of vibration
    private int speed;
    //Intensity of vibration
    private int intensity;
    //position of actuator on body;
    private int position;
    //type of actuator
    private Actuator actuatorType;

    public ActuatorSignal(double startTime, double endTime, int speed, int intensity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.speed = speed;
        this.intensity = intensity;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public int getSpeed() {
        return speed;
    }

    public int getIntensity() {
        return intensity;
    }
    
    @Override
    public String toString() {
        double duration = endTime - startTime;
        return String.format("%f|%d|%d|", duration, speed,intensity);
    }

    
    
    
    
    
}
