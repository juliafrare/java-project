# java-project

To run docker container:

`docker-compose up`

To regenerate database:

`docker-compose down`

`docker volume rm $(docker volume ls -q)`

To run java program: you can compile and run normally through IDE (e.g. Eclipse). It requires an argument of 0 or 1, depending on the implementation you want to use (0 = Collections or 1 = SQL).
