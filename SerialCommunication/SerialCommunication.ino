boolean started = false;
int values[5];
int motorPin = 6;
int analogValue = 255;
int id = 0;
int increaseValue=0;
int duration = 0;
int speedMotor = 0;
int intensity = 0;
int rotation = 0;
int lastTime = 0;
int mappedValue = 0;
int startBeat = 200;
boolean increasing = false;
void setup() {
  pinMode(motorPin,OUTPUT);
}

void loop() {
  if(Serial.available() > 0) {
//    vibrateMotor();
    if(decodeMessage()){
      if(values[0] == 3) {
        initVibrationTattoo();
      } else {
        initVibrationHeart();
      }
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
    for(int i = 0; i < 5; i++) {
      values[i] = Serial.readStringUntil('|').toInt();
    }
    started = values[0] != 1;
    return started;
}

void initVibrationHeart() {
     initGeneral();
     analogValue = map(intensity,0,7,0,255);
}

void initGeneral() {
     id = values[0];
     duration = values[1];
     speedMotor = values[2];
     intensity = values[3];
     Serial.print(duration);
}
void initVibrationTattoo() {
     initGeneral();
     increasing = rotation < (speedMotor+intensity);
     rotation = speedMotor+intensity;
     mappedValue =  map(rotation,0,8,0,255);
     if(!increasing)
      analogValue = mappedValue;
     else
      if(duration > 5000) {
      analogValue = 50;
     } else {
      analogValue = 150;
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

void vibrateBeat() {
  int delayValue = (7-speedMotor)*40;
    Serial.print(analogValue);
    analogWrite(motorPin,analogValue);
    delay(startBeat+delayValue);
    analogWrite(motorPin,0);
    delay(startBeat+delayValue);
    analogWrite(motorPin,analogValue);
    delay(startBeat+delayValue);
    analogWrite(motorPin,0);
    if(speedMotor == 1){
      delay((startBeat+delayValue)*5);
    }
    else{
     delay((startBeat+delayValue)*2);
    }
}

void vibrateTattoo() {
    analogWrite(motorPin,analogValue);
    delay(15);
    analogWrite(motorPin,0);
    delay(15);
}

void vibrateMotor() {
  if(id == 3) {
    vibrateTattoo();
  }
  else if(id == 2) {
    vibrateBeat();
  }
}

