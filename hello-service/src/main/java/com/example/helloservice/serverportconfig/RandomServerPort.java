package com.example.helloservice.serverportconfig;


import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

public class RandomServerPort {

    private int serverPort;

    private final int start = 0;
    private final int end = 65535;

    public int nextValue(int start) {
        return nextValue(start, end);
    }

    public int nextValue(int start, int end) {
        start = start < this.start? this.start: start;
        end = end > this.end? this.end: end;

        if (serverPort == 0){
            synchronized (this){
                if (serverPort == 0){
                    serverPort = RandomUtils.nextInt(end);
                    while (serverPort <= start){
                        serverPort = RandomUtils.nextInt(end);
                    }
                }
            }
        }
        return serverPort;
    }
}