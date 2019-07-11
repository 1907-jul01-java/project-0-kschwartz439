FROM postgres:10
ENV POSTGRES_USER KellyCR
ENV POSTGRESS_PASSWORD p4ssw0rd
ADD schema.sql /docker-entrypoint-initdb.d
EXPOSE 5432