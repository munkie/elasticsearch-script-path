---
layout: default
title: ElasticSearch Script Path Plugin
---

ElasticSearch Script Path
=========================
Plugin for quering and filtering materialized paths
---------------------------------------------------

### Instalation

1. Run:
`bin/plugin -install munkie/elasticsearch-script-path/1.0.2`.

2. Add following line to *elasticsearch.yml* config file:
```
script.native:
    pathFilter.type: org.elasticsearch.script.path.PathFilterFactory
    pathQuery.type: org.elasticsearch.script.path.PathQueryFactory
```
