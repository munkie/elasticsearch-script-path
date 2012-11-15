Path Script for ElasticSearch
=============================

Provides native script to calculate score between two paths

Install
-------

1. Run:
`bin/plugin -install munkie/elasticsearch-script-path/1.0.0`.

2. Add following line to *elasticsearch.yml* config file:
`script.native.pathscript.type: org.elasticsearch.script.path.PathScriptFactory`

    -------------------------------------
    | Path Script   | Elasticsearch     |
    -------------------------------------
    | master        | 0.19.11 -> master |
    -------------------------------------
    | 1.0.0         | 0.19.11 -> master |
    -------------------------------------

Usage example
-------------

```
curl -XGET http://localhost:9200/path/test/_search -d '{
  "query": {
    "custom_score": {
      "query": {
        "match_all": {}
      },
      "script": "pathscript",
      "params": {
        "path": "1.2.3.5.6",
        "field": "path"
      },
      "lang": "native"
    }
  }
}'
```