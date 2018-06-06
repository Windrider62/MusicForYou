# Werk met een lokaal omgeving of virtueel host: 

sudo su

voer wachtwoord in

cd /etc/

nano hosts:

host file:      

##
            # Host Database
            #
            # localhost is used to configure the loopback interface
            # when the system is booting.  Do not change this entry.
            127.0.0.1 localhost
            255.255.255.255 broadcasthost
            ::1       localhost
            127.0.0.1 arduino-app.local


cd apache2/
cd extra 
nano http-vhost-conf

<VirtualHost *:80>
        ServerName arduino-app.local
        DocumentRoot "/Users/mitch_walravens/Projecten/MusicForYou/Arduino/public/"
        <Directory "/Users/mitch_walravens/Projecten/MusicForYou/Arduino/public/">
                Options Indexes FollowSymLinks Includes ExecCGI
                AllowOverride All
                Require all granted
        </Directory>
</VirtualHost>


# Download composer globaal op: https://getcomposer.org/

# composer global require "laravel/installer"

# Terminal of command line opnenen en ga naar de root van je project:

# Cd Arduino

-Voer de volgende commando uit(Hierdoor worden verschillende packages geinstalleerd van Laravel).

# .env file aanpassen naar eigen omgeving: 

APP_NAME=Arduino
APP_ENV=local
APP_KEY=base64:jufS/WKtMLUxY62ko58wZkCnH5QSS9RBpbUdmpYUcqU=
APP_DEBUG=true
APP_URL=http://arduino-app.local

LOG_CHANNEL=stack

DB_CONNECTION=mysql
DB_HOST=127.0.0.1
DB_PORT=3306
DB_DATABASE=arduino
DB_USERNAME=root
DB_PASSWORD=root

BROADCAST_DRIVER=log
CACHE_DRIVER=file
SESSION_DRIVER=file
SESSION_LIFETIME=120
QUEUE_DRIVER=sync

REDIS_HOST=127.0.0.1
REDIS_PASSWORD=null
REDIS_PORT=6379

MAIL_DRIVER=smtp
MAIL_HOST=smtp.mailtrap.io
MAIL_PORT=2525
MAIL_USERNAME=null
MAIL_PASSWORD=null
MAIL_ENCRYPTION=null

PUSHER_APP_ID=
PUSHER_APP_KEY=
PUSHER_APP_SECRET=
PUSHER_APP_CLUSTER=mt1

MIX_PUSHER_APP_KEY="${PUSHER_APP_KEY}"
MIX_PUSHER_APP_CLUSTER="${PUSHER_APP_CLUSTER}"


# php artisan key:generate (hierdoor wordt er een key aangemaakt)

# composer install

# php artisan migrate

# php artisan db:seed (belangrijk dat er een database is aangemaakt in bijvoorbeerd Sequal pro: Bij mij is het arduino zoals je kunt zien in de env file)

# nu is de dabase gevuld met een test gebruiker

# php artisan serve 

# applicatie moet nu werken



