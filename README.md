# roomfinder
A JavaEE webapp with an Graph-Database.

Used Technologies
- JavaEE
- JUnit
- Neo4J Graph database
- Neo4J OGM

## Start Project
Start Wildfly & Neo4jdb
-> check if Project is packaged! (skip tests!)
> sudo docker-compose up

=====================================

### Neo4j
**No auth used in docker-compose configuration**
> Username: neo4j
> PW: roomfinder


Browser interface
http://localhost:7474/browser

Browser Console:
> [CALL dbms.security.createUser(username, password, requirePasswordChange)](https://neo4j.com/docs/operations-manual/current/reference/user-management-community-edition/#userauth-add-user-ce)

**In der Neo4jSessionfactory wird der User gesetzt**

[DEMO Video](https://github.com/Schloemicher/roomfinder/blob/master/neo4j_demo.mp4)
