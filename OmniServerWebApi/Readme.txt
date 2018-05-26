NODEINFO:
get all the nodes:
	type= get
	path="nodeinfo/getnodes"
	parameter= null
	return= List<node>={"name":"room","ip":"http://172.20.10.55"}

Add new node:
	type=post
	path="nodeinfo/addnode"
	parameter= Node node =
	{
	"name":"room",
	"ip":"http://172.20.10.55"
	}
	return=bool
	
rename node:
	type=post
	path="nodeinfo/renamenode/{oldname}/{newname}"
	parameter=string oldname, string newname
	return=String name
	
remove node:
	type=delete
	path="nodeinfo/deletenode/{nodename}"
	parameter=string nodename
	return=bool
	
NODEMUSICOPERATIONS
start music:
	type=post
	path="node/start"
	parameter=List<string> nodeIps "http://192.168.0.4"
	return=String

stop music:
	type=post
	path="node/stop"
	parameter=List<string> nodeIps [{"http://192.168.0.4"}]
	return=string
	
Change volume:
	type=post
	path="node/changevolume/{volume}
	parameter=List<string>[{"http://192.168.0.4"}], nodeIps,int volume
	return=String
	
change radio station:
	type=post
	path="node/changeradiostation/{stationname}"
	parameter=List<string> nodeIps [{"http://192.168.0.4"}],string stationname
	return=String
	
RADIOSTATION
get radio station names:
	type=get
	path="stationnames
	parameter=null
	return=List<string> stationNames

get radio station by name
	type=get
	path="stationbyname/{name}"
	parameter=string name
	return= RadioStationModel radiostation=
	{
    "name": "radio 1",
    "host": "icecast.omroep.nl",
    "path": "radio1-bb-aac",
    "country": "holland"
	}
	
USER
autenticate user login:
	type=post
	path="user/login"
	parameter=UserModel user=
	{
	"name":"admin",
	"password":"admin"
	}
	return=bool
	
	
	
	
NODE HTTPREQUESTS
start the music
	type= get
	path= ipadress+”/start”
	parameter= null
	return= String and response code

stop the music
	type= get
	path= ipadress+”/stop”
	parameter= null
	return= String and response code

change the volume
	type= get
	path= ipadress+”/changevolume?vol={int volume}
	parameter= int volume
	return= String and response code

change the station
	type= get
	path= ipadress+”/changepath?hots={host url}&path={path url}
	parameter= string host, string path
	return= String and response code
	
	
	
	
	
	

	
	