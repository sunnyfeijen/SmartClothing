boolean started = false;
int values[3];
int motorPin = 6;
int analogValue = 255;
int increaseValue=0;
int duration = 0;
int speedMotor = 0;
int intensity = 0;
int rotation = 0;
int lastTime = 0;
int mappedValue = 0;
boolean increasing = false;
void setup() {
  pinMode(motorPin,OUTPUT);
}

void loop() {
  if(Serial.available() > 0) {
//    vibrateMotor();
    if(decodeMessage()){
       initVibration();
    }
  }
  
  if(started) {
    if(increasing) {
      incrementValue();
    }
    vibrateMotor();
  }
}

boolean decodeMessage() {
    for(int i = 0; i < 3; i++) {
      values[i] = Serial.readStringUntil('|').toInt();
    }
    started = values[0] != 1;
    return started;
}

void initVibration() {
     duration = values[0];
     speedMotor = values[1];
     intensity = values[2];
     increasing = rotation < (speedMotor+intensity);
     rotation = speedMotor+intensity;
     mappedValue =  map(rotation,0,8,0,255);
     if(!increasing)
      analogValue = mappedValue;
     else
      if(duration > 1000) {
      analogValue = 150;
     } else {
      analogValue = 50;
     }
     increaseValue = duration/mappedValue;
     Serial.println(duration);
     Serial.println(intensity);
     Serial.println(analogValue);
}

void tattooEffect() {
  analogValue++;
}

void incrementValue() {
//  if(millis() - lastTime > increaseValue) {
//    analogValue++;
//    lastTime = millis();
//  }
    if(analogValue < mappedValue)
      analogValue+=1.5;
}

void vibrateMotor() {
  analogWrite(motorPin,analogValue);
  delay(15);
  analogWrite(motorPin,0);
  delay(15);
}

