---
layout: default
title: ElasticSearch Script Path Plugin
---

ElasticSearch Script Path
=========================
Plugin for querying and filtering materialized paths
---------------------------------------------------

### Instalation

1. Run:
{% highlight sh %}
bin/plugin -install munkie/elasticsearch-script-path/1.0.2
{% endhighlight %}

2. Add following lines to *elasticsearch.yml* config file:
{% highlight yaml %}
script.native:
    pathFilter.type: org.elasticsearch.script.path.PathFilterFactory
    pathQuery.type: org.elasticsearch.script.path.PathQueryFactory
{% endhighlight %}

### Downloads
{% for download in site.categories.downloads %}
* {{ download.date | date: "%Y-%m-%d" }} - [v{{ download.version }}](downloads/elasticsearch-script-path-{{ download.version }}.zip)
{% endfor %}
