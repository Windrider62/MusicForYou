
    #include <SPI.h>
    #include <Adafruit_VS1053.h>
    #include <ESP8266WiFi.h>
     
    char* ssid     = "{unknown}";
    const char* password = "jkw829kw91&";
     
    //  http://ice1.somafm.com/u80s-128-mp3
    //do not use  http:// but just the link
     String host = "icecast.omroep.nl";
     String path = "/3fm-bb-mp3";
    //const char *path = "/doomed-128-mp3";
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
      
    void setup() {
      Serial.begin(115200);
    
     


     

     
      /************************* INITIALIZE MP3 WING */
      if (! musicPlayer.begin()) { // initialise the music player
         Serial.println(F("Couldn't find VS1053, do you have the right pins defined?"));
         while (1) delay(10);
      }
     
      Serial.println(F("VS1053 found"));
      musicPlayer.sineTest(0x44, 500);    // Make a tone to indicate VS1053 is working
      
      // Set volume for left, right channels. lower numbers == louder volume!
      musicPlayer.setVolume(lastvol, lastvol);
     
      // don't use an IRQ, we'll hand-feed
     

      Serial.print("Connecting to SSID "); Serial.println(ssid);
      WiFi.begin(ssid, password);
      
      while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
      }
     
      Serial.println("WiFi connected");  
      Serial.println("IP address: ");  Serial.println(WiFi.localIP());
     
     
      /************************* INITIALIZE STREAM */
      Serial.print("connecting to ");  Serial.println(host);
        Serial.println(host+path);
      if (!client.connect(host, httpPort)) {
        Serial.println("Connection failed");
        return;
      }
      
      // We now create a URI for the request
      Serial.print("Requesting URL: ");
      Serial.println(path);
      
      // This will send the request to the server
      client.print(String("GET ") + path + " HTTP/1.1\r\n" +
                   "Host: " + host + "\r\n" + 
                   "Connection: close\r\n\r\n");
     
    }
     
    // our little buffer of mp3 data
    uint8_t mp3buff[32];   // vs1053 likes 32 bytes at a time
    bool playingMusic=true;

     
    void loop() {


      
      
if(Serial.available()>0){
    String Volume=Serial.readString();
    
    if(Volume=="+")
    {
      lastvol+=5;
      Serial.println("new volume = "+lastvol);
     musicPlayer.setVolume(lastvol, lastvol);
     
    }
    else if(Volume=="-")
    {
      lastvol-=5;
     Serial.println("new volume = "+lastvol);
     musicPlayer.setVolume(lastvol, lastvol);
    }
    else if(Volume=="stop")
    {
      Serial.println("music stopped");
       digitalWrite(VS1053_DCS, LOW); 
       playingMusic=false;
    }
     else if(Volume=="start")
    {
      Serial.println("music started");
       digitalWrite(VS1053_DCS, HIGH); 
       playingMusic=true;
    }
}
  if(playingMusic==true){
      // wait till mp3 wants more data
      if (musicPlayer.readyForData()) {
        //Serial.print("ready ");
        
        //wants more data! check we have something available from the stream
        if (client.available() > 0) {
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
    
