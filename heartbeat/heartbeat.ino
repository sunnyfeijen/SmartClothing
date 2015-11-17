void setup() {
  pinMode(6,OUTPUT);

}

void loop() {
  analogWrite(6,250);
  delay(70);
  analogWrite(6,0);
  delay(70);
  analogWrite(6,250);
  delay(70);
  analogWrite(6,0);
  delay(1500);

}
