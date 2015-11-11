const int motorPin = 3;
const int motorPin2 = 5;
long currentTime = 0;
long delayTime = 1000;
long latestTime = 0;
int vibrationState = 1000;
void setup()
{
  Serial.begin(9600);
  pinMode(motorPin, OUTPUT);
  pinMode(motorPin2, OUTPUT);
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

analogWrite(motorPin,255);
delay(300);
analogWrite(motorPin, 0);
delay(1000);
}
