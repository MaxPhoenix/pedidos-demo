package com.example.pedidosdemo.cache;

public class BumexMemCached {

    private static BumexMemCached instance ;

    private BumexMemCached(){
    }

    public static  BumexMemCached getInstance(){
        if(instance == null)
            instance = new BumexMemCached();
        return instance;
    }

    public void set(String key, Object value){

    }

    public Object get(String key){
        return null;
    }

    public void delete(String key){

    }
}
