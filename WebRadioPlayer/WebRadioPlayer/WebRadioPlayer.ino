    //import library's
    #include <SPI.h>
    #include <Adafruit_VS1053.h>
    #include <ESP8266WiFi.h>
    #include <ESP8266WebServer.h> 
     
 
     
    // These are the pins used
    #define VS1053_RESET   D3     // VS1053 reset pin (not used!)
    #define VS1053_CS      D8     // VS1053 chip select pin (output)
    #define VS1053_DCS     D1     // VS1053 Data/command select pin (output)
    #define VS1053_DREQ     D2     // VS1053 Data request, ideally an Interrupt pin
    Adafruit_VS1053 musicPlayer =  Adafruit_VS1053(VS1053_RESET, VS1053_CS, VS1053_DCS, VS1053_DREQ);

    //global propeties
    char* ssid     = "{unknown}";
    const char* password = "jkw829kw91&";
    String host = "icecast.omroep.nl";
    String path = "/3fm-bb-mp3";
    int httpPort = 80;
    int lastvol = 0;
    bool playingMusic=true;

    //instantiate the servers
    WiFiClient client;
    ESP8266WebServer server;


    //start setup
    void setup() {
      Serial.begin(115200);

      
       //connect to the network with the ssid and the password
       WiFi.begin(ssid, password);

       //check if the wifi connection is made
       while (WiFi.status() != WL_CONNECTED) {
       delay(500);
       Serial.print(".");
       }
     
       Serial.println("WiFi connected");  
       //print the ip adress
       Serial.println(WiFi.localIP());


       //muisc player setup call
       MusicPlayerSetup();
          
       // wifi connection for hhtp requests
       WiFi.begin(ssid,password);
       while(WiFi.status() != WL_CONNECTED){
       delay(500);
       Serial.println(".");
       }

       //http server start
       server.begin(); 
       //when one of those http requests are called then activate method  
       server.on("/", OpenHomePage);
       server.on("/start", StartMusic);
       server.on("/stop", StopMusic);
       server.on("/volumeplus", VolumePlus);
       server.on("/volumemin", VolumeMin);
       server.on("/changepath", ChangePath);
       server.onNotFound(handleNotFound);   
     
    }   
void loop(void) {
// handle the http requests
     server.handleClient(); 
     
// our little buffer of mp3 data
    uint8_t mp3buff[32];   // vs1053 likes 32 bytes at a time
     
      if(playingMusic==true)
      {
        // wait till mp3 wants more data
        if (musicPlayer.readyForData()) 
        {
        
        
//wants more data! check we have something available from the stream
           if (client.available() > 0) 
            {
//Serial.print("set ");
// yea! read up to 32 bytes
              uint8_t bytesread = client.read(mp3buff, 32);
              // push to mp3
              musicPlayer.playData(mp3buff, bytesread);   
              //Serial.println("stream!");
            }
        }
      }
    }
void MusicPlayerSetup(){
    if (!client.connect(host, httpPort)) {
        Serial.println("Connection failed");
        return;
      }
      
  if (! musicPlayer.begin()) { // initialise the music player
         Serial.println(F("Couldn't find VS1053, do you have the right pins defined?"));
         while (1) delay(10);
      }
     
 // Make a tone to indicate VS1053 is working     
 //musicPlayer.sineTest(0x44, 500);    
      
// Set volume for left, right channels. lower numbers == louder volume!
      musicPlayer.setVolume(lastvol, lastvol);


      Serial.print("Requesting URL: ");
      Serial.println(host+path);
      
// This will send the request to the server
      client.print(String("GET ") + path + " HTTP/1.1\r\n" +
                   "Host: " + host + "\r\n" + 
                   "Connection: close\r\n\r\n");
}
void OpenHomePage(){
  server.send(200, "text/plain", "hello are yah toking to me? tok tok");
}
void StartMusic(){
  server.send(200, "text/plain", "music started");
    Serial.println("music started");
       digitalWrite(VS1053_DCS, HIGH); 
       playingMusic=true;
  
}
void StopMusic(){
  server.send(200, "text/plain", "music stopped");
  Serial.println("music stopped");
  digitalWrite(VS1053_DCS, LOW); 
  playingMusic=false;
}
void VolumePlus(){
  int newvol=0;
  if(server.arg("vol")=="")
  {
    server.send(400, "text/plain", "erro: 400 no parameter was send");
  }
  else{
    newvol=server.arg("vol").toInt();
    server.send(200, "text/plain", server.arg("vol"));
  }
  
  if(lastvol-newvol>=0)
  {
  lastvol-=newvol;
  }
  else{
    lastvol=0;
  }
  server.send(200, "text/plain",  "volume="+String(lastvol));
  musicPlayer.setVolume(lastvol, lastvol);
  
}
void VolumeMin(){

  int newvol=0;
  if(server.arg("vol")=="")
  {
    server.send(400, "text/plain", "error: 400 no parameter was send");
  }
  else{
    newvol=server.arg("vol").toInt();
    server.send(200, "text/plain", server.arg("vol"));
  }
  
  if(lastvol+newvol<=150){
  lastvol+=newvol;
  }
  else
  {
    lastvol=150;
  }
  server.send(200, "text/plain","volume="+String(lastvol));
  musicPlayer.setVolume(lastvol, lastvol);
}
void handleNotFound(){
  server.send(404, "text/plain", "404: Not found"); // Send HTTP status 404 (Not Found) when there's no handler for the URI in the request
}
void ChangePath(){
  
  if(server.arg("path")=="" ||server.arg("host")==""){
    server.send(400, "text/plain", "error: 400 missing a parameter ");
  }
  else{
    host=server.arg("host");
    path="/"+server.arg("path");
    MusicPlayerSetup();
    server.send(200,"text/plain","new radio ="+host+path);               
    Serial.print(host+path);          
  }
}

    
