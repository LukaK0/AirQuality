package home;

import eu.hansolo.medusa.Gauge;

public class Merac extends Thread{
    private volatile boolean run = true;
    private Gauge gauge;
    private int ppm;
    public Merac(Gauge gauge){
        this.gauge=gauge;
    }

    public int getPpm() {
        return ppm;
    }

    public void setPpm(int ppm) {
        this.ppm = ppm;
    }

    @Override
    public void run() {
        while(run){
            gauge.setValue(((Merac)Controller.readThread).getPpm());
        }

    }

}
