docker network connect <network-name> <container-name>

docker commit CONT_ID IMAGE_NAME


//SYS --------------------------------------------------------------------------------------------------------------------------------------------------
jps -m

taskkill -f /PID 4604

tasklist

dos2unix scripy.sh

 
//SQL --------------------------------------------------------------------------------------------------------------------------------------------------
sqlcmd -S localhost -U SA -P SaUser123456
  
#RUN apt-get install mssql-tools unixodbc-dev  						    # SQLCMD
#RUN echo 'export PATH="$PATH:/opt/mssql-tools/bin"' >> ~/.bash_profile # Сохраняем путь к тулкиту в PATH bash_profile для работы из логин-сессий
#RUN echo 'export PATH="$PATH:/opt/mssql-tools/bin"' >> ~/.bashrc       # Сохраняем путь к тулкиту в PATH bashrc для интерактивных bash-сессий
#RUN source ~/.bashrc 												    # Чтобы не переоткрывать терминал, грузим файл bashrc
  
RESTORE DATABASE Test
from DISK='/var/opt/mssql/data/BackUp_2018-06-30_16.38.28.Bak'
WITH MOVE 'Test' TO '/var/opt/mssql/data/Test.mdf',
MOVE 'Test_Log' TO '/var/opt/mssql/data/Test_Log.ldf'
GO


USE [master]; BACKUP DATABASE Test TO DISK = /opt/mssql/backups/BackUp_1.Bak  WITH FORMAT, MEDIANAME = 'Test_beckup', NAME = 'Full Backup of Test';
 