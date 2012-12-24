---
layout: default
title: ElasticSearch Script Path Plugin
---

ElasticSearch Script Path
=========================
Plugin for quering and filtering materialized paths
---------------------------------------------------

### Instalation

1. Run: `bin/plugin -install munkie/elasticsearch-script-path/1.0.2` .

2. Add following line to *elasticsearch.yml* config file:
<blockquote>
script.native:
    pathFilter.type: org.elasticsearch.script.path.PathFilterFactory
    pathQuery.type: org.elasticsearch.script.path.PathQueryFactory
</blockquote>

### Downloads
{% for download in site.categories.downloads %}
* {{ download.date | date: "%Y-%m-%d" }} - [v{{ download.version }}](downloads/elasticsearch-script-path-{{ download.version }}.zip)
{% endfor %}
