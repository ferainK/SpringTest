package com.example.springTest.ioc;


public class Encoder implements IEncoder{
  private IEncoder iEncoder;

  public Encoder(IEncoder iEncoder){
    this.iEncoder = iEncoder;
  }

  public void setIEncoder(IEncoder iEncoder) {
    this.iEncoder = iEncoder;
  }

  @Override
  public String encode(String message) {
    return iEncoder.encode(message);
  }
}
