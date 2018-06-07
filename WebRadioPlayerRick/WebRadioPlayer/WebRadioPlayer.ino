    //import library's
    #include <SPI.h>
    #include <Adafruit_VS1053.h>
    #include <ESP8266WiFi.h>
    #include <ESP8266WebServer.h> 

   
//SCLK -> Arduino #13
//MOSI -> Arduino #11
//MISO -> Arduino #12
//CS -> Arduino #10
// define the pins used
//#define RESET 9      // VS1053 reset pin (output)                       
//#define CS 10        // VS1053 chip select pin (output)                 //CS
//#define DCS 8        // VS1053 Data/command select pin (output)         //xdcs
//#define CARDCS A0     // Card chip select pin                           //sdcs
//#define DREQ A1       // VS1053 Data request, ideally an Interrupt pin  
     
 
     
    // These are the pins used
    #define VS1053_RESET   4     // VS1053 reset pin (not used!)
    #define VS1053_CS      15     // VS1053 chip select pin (output)
    #define VS1053_DCS     16     // VS1053 Data/command select pin (output)
    //#define VS1053_CARDCS A0     // Card chip select pin
    #define VS1053_DREQ     5     // VS1053 Data request, ideally an Interrupt pin
    // swSPI: Adafruit_VS1053::Adafruit_VS1053(mosi, miso, clk, rst, cs, dcs, dreq)
    // hwSPI: Adafruit_VS1053::Adafruit_VS1053(rst, cs, dcs, dreq)
    Adafruit_VS1053 musicPlayer =  Adafruit_VS1053(VS1053_RESET, VS1053_CS, VS1053_DCS, VS1053_DREQ);
    //Adafruit_VS1053 musicPlayer =  Adafruit_VS1053(12,13,14,VS1053_RESET, VS1053_CS, VS1053_DCS, VS1053_DREQ);


    //Ricks aansluiting
    //    _miso = 12; default
    //    _mosi = 13; default
    //    _clk = 14;//SCK
    //    useHardwareSPI = true;
    //    _reset = 4; ??
    //    _cs = 15;
    //    _dcs = 0;??
    //    _dreq = 2;??

    //Glenns default
    //    _miso = D6; default
    //    _clk = D5;//SCK
    //    useHardwareSPI = true;
    //    _reset = D3;
    //    _cs = D8;//XCS
    //    _dcs = D1;//XDCS
    //    _dreq = D2;
    //  https://camo.githubusercontent.com/e90f991d89b5558a758dee5fe3ceed4a2b477c62/687474703a2f2f7777772e6b6c6f7070656e626f72672e6e65742f696d616765732f626c6f672f657370383236362f657370383236362d6e6f64652d6d63752d70696e6f75742e706e67


    //global propeties
    char* ssid     = "{unknown}";
    const char* password = "jkw829kw91&";
    //char* ssid     = "WNDR-XACT2";
    //const char* password = "vanillaraccoon554";
    String host = "icecast.omroep.nl";
    String path = "/3fm-bb-mp3";
    int httpPort = 80;
    int lastvol = 0;
    bool musicIsPlaying=true;

    //instantiate the servers
    WiFiClient client;
    ESP8266WebServer server;


    //start setup
    void setup() {
      
    Serial.begin(115200);
    
    // Set WiFi to station mode and disconnect from an AP if it was previously connected
    //WiFi.mode(WIFI_STA);
    //WiFi.disconnect();
   // delay(100);


       //connect to the network with the ssid and the password
       WiFi.begin(ssid,password); 

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
       Serial.println("Connected!");

       //http server start
       server.begin(); 
       //when one of those http requests are called then activate method  
       server.on("/", OpenHomePage);
       server.on("/start", StartMusic);
       server.on("/stop", StopMusic);
       server.on("/changevolume", ChangeVolume);
       server.on("/changepath", ChangePath);
       server.onNotFound(handleNotFound);   
     
    }   
void loop(void) {
// handle the http requests
     server.handleClient(); 
     
// our little buffer of mp3 data
    uint8_t mp3buff[32];   // vs1053 likes 32 bytes at a time
     
      if(musicIsPlaying==true)
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
       musicIsPlaying=true;
  
}
void StopMusic(){
  server.send(200, "text/plain", "music stopped");
  Serial.println("music stopped");
  digitalWrite(VS1053_DCS, LOW); 
  musicIsPlaying=false;
}
void ChangeVolume(){
  int newvol=0;
  if(server.arg("vol")=="")
  {
    server.send(400, "text/plain", "erro: 400 no parameter was send");
  }
  else{
    newvol=server.arg("vol").toInt();
    server.send(200, "text/plain", server.arg("vol"));
    lastvol=newvol;
  }
  
  
  server.send(200, "text/plain",  "volume="+String(lastvol));
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

    
