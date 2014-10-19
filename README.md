# Jocker, a Java client library for Docker

Docker daemon expose a [well documented API](https://docs.docker.com/reference/api/docker_remote_api/)

## Why yet another docker library ?

[Docker-java](https://github.com/docker-java/docker-java) is a well known docker client library,
there's also [Spotify one](https://github.com/spotify/docker-client) and probably few others.

  - none of them do support the unix socket mode, that is the default one for use on
    same (unix) host.
  - They rely on Jersey or such heavy REST framework, not considering the API
    "tends to be REST" but is not a REST one, so require some complex hacks. For REST client
    implementation I prefer a plain old http client. This has demonstrated to make it easier
    to adapt to _subtle hacks_ used by some APIs. Not even considering ClassLoader conflicts
    it some other lib require another Jersey (incompatible) version.
  - docker 1.3.0 introduced TLS encrypted communication with client certificate,
    which is incredibly complex to setup within java standard API (sic), so even worst if
    you have a framework to hide (sorry "encapsulate") the underlying http library.

The intent of Jocker is to offer a lightweight, KISS client library to docker daemon.

  - No advanced REST framework is used, and only rely on a minimal set of dependencies,
    just apache [http client](http://hc.apache.org/) and [Boon](http://richardhightower.github.io/site/Boon/Welcome.html)
    for lightweight and efficient JSON binding.
  - Unix socket support is a requirement.
  - It target latest Docker API - 1.15 (Docker 1.3.0) at time writing - so address the TLS
    authentication requirement.

## Docker API support

 - containers
   - [x] [list](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#list-containers) aka `docker ps`
   - [ ] [create](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#create-a-container)
   - [ ] [inspect](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#inspect-a-container)
   - [ ] [list processes](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#list-processes-running-inside-a-container)
   - [ ] [get logs](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#get-container-logs)
   - [ ] [inspect changes](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#inspect-changes-on-a-containers-filesystem)
   - [ ] [export](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#export-a-container)
   - [ ] [resize TTY](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#resize-a-container-tty)
   - [ ] [start](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#start-a-container)
   - [ ] [stop](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#stop-a-container)
   - [ ] [restart](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#restart-a-container)
   - [ ] [kill](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#kill-a-container)
   - [ ] [pause](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#pause-a-container)
   - [ ] [unpause](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#unpause-a-container)
   - [ ] [attach](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#attach-to-a-container)
   - [ ] [wait](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#wait-a-container)
   - [ ] [remove](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#remove-a-container)
   - [ ] [copy file](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#copy-files-or-folders-from-a-container)
   - [ ] []()
   - [ ] []()

 - images
   - [ ] [list](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#list-images)
   - [ ] [create](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#create-an-image)
   - [ ] [inspect](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#inspect-an-image)
   - [ ] [history](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#get-the-history-of-an-image)
   - [ ] [push](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#push-an-image-on-the-registry)
   - [ ] [tag](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#tag-an-image-into-a-repository)
   - [ ] [remove](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#remove-an-image)
   - [ ] [search](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#search-images)

 - other
   - [ ] [build](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#build-an-image-from-dockerfile-via-stdin)
   - [ ] [check authentication](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#check-auth-configuration)
   - [ ] [info](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#display-system-wide-information)
   - [ ] [version](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#show-the-docker-version-information)
   - [ ] [ping](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#ping-the-docker-server)
   - [ ] [commit](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#create-a-new-image-from-a-containers-changes)
   - [ ] [events](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#monitor-dockers-events)
   - [ ] [export repository](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#get-a-tarball-containing-all-images-in-a-repository)
   - [ ] [export images](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#get-a-tarball-containing-all-images)
   - [ ] [export image](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#image-tarball-format)
   - [ ] [exec create](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#exec-create)
   - [ ] [exec start](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#exec-start)
   - [ ] [exec resize](https://docs.docker.com/reference/api/docker_remote_api_v1.15/#exec-resize)
