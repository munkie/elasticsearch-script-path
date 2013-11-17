Path Script for ElasticSearch
=============================

Native script for querying and filtering materialized paths


Installation
------------

1. Run:  
`bin/plugin -install elasticsearch-script-path -url http://munkie.github.com/elasticsearch-script-path/downloads/elasticsearch-script-path-1.0.2.zip`.

2. Add following line to *elasticsearch.yml* config file:
```
script.native:
    pathFilter.type: org.elasticsearch.script.path.PathFilterFactory
    pathQuery.type: org.elasticsearch.script.path.PathQueryFactory
```

Versions
--------

<table>
<thead>
<tr><th>Path Script</th><th>ElasticSearch</th></tr>
</thead>
<tbody>
<tr><td>master</td><td>0.90.7 -> master</td></tr>
<tr><td>1.0.3</td><td>0.90.7 -> master</td></tr>
<tr><td>1.0.2</td><td>0.20.1</td></tr>
<tr><td>1.0.1</td><td>0.19.11</td></tr>
<tr><td>1.0.0</td><td>0.19.11</td></tr>
</tbody>
</table>

Usage example
-------------

### Sort by path distance score

```
curl -XGET http://localhost:9200/path/test/_search -d '{
  "query": {
    "custom_score": {
      "query": {
        "match_all": {}
      },
      "script": "pathQuery",
      "params": {
        "path": "1.2.3.5.6",
        "field": "path"
      },
      "lang": "native"
    }
  }
}'
```

### Filter by path distance

```
curl -XGET http://localhost:9200/path/test/_search -d '{
  "query": {
    "match_all": {}
  },
  "filter": {
    "filter": {
      "script": "pathFilter",
      "lang": "native",
      "params": {
        "path": "1.2.3.5.6",
        "field": "path"
        "minLevel": 1,
        "maxLevel": 4
      }
    }
  }
}'
```

### Find parents by path
```
curl -XGET http://localhost:9200/path/test/_search -d '{
  "query": {
    "match_all": {}
  },
  "filter": {
    "filter": {
      "script": "pathFilter",
      "lang": "native",
      "params": {
        "path": "1.2.3.5.6",
        "field": "path",
        "direct": true
      }
    }
  }
}'
```
