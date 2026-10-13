

# levanta en contenedor
sudo docker compose up -d

# accede por bash al contenedor
sudo docker exec -it mi-tomcat /bin/bash

# copiar fichero del contenedor a local
sudo docker cp -a  mi-tomcat:/usr/local/tomcat/webapps/docs/META-INF/ docs/.

# baja el contenedor y eliminalo
sudo docker compose down
