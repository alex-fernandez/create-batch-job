package com.alexfrndz.listener;


import javax.batch.api.chunk.listener.ItemReadListener;

public class ReadListener implements ItemReadListener {


    @Override
    public void beforeRead() throws Exception {
        System.out.println("Before Reading");
    }

    @Override
    public void afterRead(Object o) throws Exception {
        System.out.println("Before Reading");
    }

    @Override
    public void onReadError(Exception e) throws Exception {
        e.printStackTrace();

    }
}
