FROM mysql:8.0.17

COPY ./db/ /docker-entrypoint-initdb.d/