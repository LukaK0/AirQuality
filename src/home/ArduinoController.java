package home;

import org.sintef.jarduino.*;

public class ArduinoController extends JArduino {
    private int co2Zero = 55;

    public ArduinoController(String port){
        super(port);
    }

    @Override
    protected void setup() throws InvalidPinTypeException {
        pinMode(Pin.A_0, INPUT);
        pinMode(DigitalPin.PIN_2, INPUT);
    }

    @Override
    protected void loop(){
        int[] co2samples = new int[10];
        int co2current = 0;
        int co2comp = 0;
        int co2ppm = 0;
        int co2average = 0;

        for (int x = 0;x<10;x++){
            co2samples[x]=analogRead(AnalogPin.A_0);
            delay(200);
        }

        for (int x = 0;x<10;x++){
            co2average=co2average + co2samples[x];
        }
        co2current = co2average/10;
        co2comp = co2current - co2Zero;
        co2ppm = map(co2comp,0,1023,400,5000);
        ((Merac)Controller.readThread).setPpm(co2ppm);
    }
}
