
    #include <SPI.h>
    #include <Adafruit_VS1053.h>
    #include <ESP8266WiFi.h>
    #include <ESP8266WebServer.h> 
     
    char* ssid     = "{unknown}";
    const char* password = "jkw829kw91&";
     

     String host = "icecast.omroep.nl";
     String path = "/3fm-bb-mp3";
     int httpPort = 80;
     
    // These are the pins used
    #define VS1053_RESET   D3     // VS1053 reset pin (not used!)
    #define VS1053_CS      D8     // VS1053 chip select pin (output)
    #define VS1053_DCS     D1     // VS1053 Data/command select pin (output)
    #define VS1053_DREQ     D2     // VS1053 Data request, ideally an Interrupt pin

    

     
    int lastvol = 90;
     
    Adafruit_VS1053 musicPlayer =  Adafruit_VS1053(VS1053_RESET, VS1053_CS, VS1053_DCS, VS1053_DREQ);
     
    // Use WiFiClient class to create HTTP/TCP connection
    WiFiClient client;
    ESP8266WebServer server;
    
    void setup() {
      Serial.begin(115200);
    
     


     

     
      /************************* INITIALIZE MP3 WING */
      if (! musicPlayer.begin()) { // initialise the music player
         Serial.println(F("Couldn't find VS1053, do you have the right pins defined?"));
         while (1) delay(10);
      }
     
      
      musicPlayer.sineTest(0x44, 500);    // Make a tone to indicate VS1053 is working
      
      // Set volume for left, right channels. lower numbers == louder volume!
      musicPlayer.setVolume(lastvol, lastvol);
     
      
      //connect to the network with the ssid and the password
      WiFi.begin(ssid, password);
      
      while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
      }
     
      Serial.println("WiFi connected");  
      //print the ip adress
      Serial.println(WiFi.localIP());

      
     
      /************************* INITIALIZE STREAM */
     
      if (!client.connect(host, httpPort)) {
        Serial.println("Connection failed");
        return;
      }
      
      // We now create a URI for the request
      Serial.print("Requesting URL: ");
      Serial.println(host+path);
      
      // This will send the request to the server
      client.print(String("GET ") + path + " HTTP/1.1\r\n" +
                   "Host: " + host + "\r\n" + 
                   "Connection: close\r\n\r\n");
                   
       // wifi connection for hhtp requests
          WiFi.begin(ssid,password);
          while(WiFi.status() != WL_CONNECTED){
          delay(500);
          Serial.println(".");
          }
          server.begin();
          
          server.on("/", OpenHomePage);
          server.on("/start", StartMusic);
          server.on("/stop", StopMusic);
          server.on("/volumeplus", VolumePlus);
          server.on("/volumemin", VolumeMin);
          server.on("/changePath", ChangePath);
          server.onNotFound(handleNotFound);   
     
    }
     
    // our little buffer of mp3 data
    uint8_t mp3buff[32];   // vs1053 likes 32 bytes at a time
    bool playingMusic=true;

     
    void loop(void) 
    {
     // handle the http requests
     server.handleClient(); 

     
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
  
  if(server.arg("path")==""){
    server.send(400, "text/plain", "error: 400 no parameter was send");
  }
  else{
    path="/"+server.arg("path");
    server.send(200, "text/plain", server.arg("path"));
    
          client.print(String("GET ") + path + " HTTP/1.1\r\n" +
                   "Host: " + host + "\r\n" + 
                   "Connection: close\r\n\r\n");
                   
    Serial.print(host+path);          
  }
}

    
