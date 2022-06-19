package com.hamdaankhalid.rpi.scheduler;

import com.hamdaankhalid.rpi.converter.IConverter;
import com.hamdaankhalid.rpi.generator.IGenerator;

import java.security.InvalidParameterException;
import java.util.List;

public class Scheduler {
    private final static int ONE_HOUR = 3600000;
    private final IConverter converter;
    private final IGenerator generator;

    public Scheduler(IConverter converter, IGenerator generator) {
        this.converter = converter;
        this.generator = generator;
    }
    public void run() {
        try {
            while (true) {
                List<String> output = this.converter.convert(
                        this.generator.generate()
                );
                System.out.println(output.toString());

                try {
                    Thread.sleep(ONE_HOUR);
                } catch (InterruptedException e) {};
            }
        } catch(InvalidParameterException e) {
            System.out.println("Screen size too small for generated quote to be displayed");
        }
    }
}
