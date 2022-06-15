package com.example.apaasdemo.rtmtoken;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
