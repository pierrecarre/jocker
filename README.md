# Jocker, a Java client library to Docker daemon API

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
