# roomfinder
A JavaEE webapp with an Graph-Database.

Used Technologies
- JavaEE
- JUnit
- Neo4J Graph database
- Neo4J OGM

## Start Neo4J
```
docker run \
    --publish=7474:7474 --publish=7687:7687 \
    --volume=$HOME/neo4j/data:/data \
    --volume=$HOME/neo4j/logs:/logs \
    neo4j
```

> Username: roomfinder
> PW: 1234

Der User wird im Browser erstellt
http://localhost:7474/browser

**In der Neo4jSessionfactory wird der User gesetzt**