const int motorPin = 3;
long currentTime = 0;
long delayTime = 1000;
long latestTime = 0;
int vibrationState = 1000;
void setup()
{
  Serial.begin(9600);
pinMode(motorPin, OUTPUT);
}

void loop()
{
  //delay
//  currentTime = millis();
//  if(currentTime - latestTime > delayTime) {
//    latestTime=currentTime;
//    //Serial.print(currentTime);
//    if(vibrationState == 50){
//      vibrationState = 1000;
//      delayTime=500;
//      }
//    else{
//      vibrationState = 50;
//      delayTime=500;
//    }
//    Serial.print(vibrationState);
//    analogWrite(motorPin,vibrationState);
//  }
  
//analogWrite(motorPin, 0);
//delay(100);
//analogWrite(motorPin, 100);
//delay(1000);

analogWrite(motorPin,150);
delay(100);
}
