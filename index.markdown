---
layout: default
title: ElasticSearch Script Path Plugin
---

ElasticSearch Script Path
=========================
Plugin for querying and filtering materialized paths
---------------------------------------------------

{% assign current = site.categories.downloads.first %}
{% assign download = current %}

### Installation

Run:
{% highlight bash %}
bin/plugin -url {% include zip.markdown %}
{% endhighlight %}

Add following lines to _elasticsearch.yml_ config file:
{% highlight yaml %}
script.native:
    pathFilter.type: org.elasticsearch.script.path.PathFilterFactory
    pathQuery.type: org.elasticsearch.script.path.PathQueryFactory
{% endhighlight %}

### Downloads

## Current version: **v{{ download.version }}** ( [zip]({% include zip.markdown %}) / [source]({% include source.markdown %}) )

## All versions:
{% for download in site.categories.downloads %}
* {{ download.date | date_to_string }} - **v{{ download.version }}** ( [zip]({% include zip.markdown %}) / [source]({% include source.markdown %}) )
{% endfor %}
