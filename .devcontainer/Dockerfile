# Pull the base image
FROM mcr.microsoft.com/devcontainers/java:1-21

# Set the shell to bash
SHELL ["/bin/bash", "-c"]

ARG USERNAME=vscode
ARG USER_UID=1000
ARG USER_GID=$USER_UID
ARG DEBIAN_FRONTEND=noninteractive

USER root

# Update package repositories
RUN apt update

# Set the devcoinatiner up
COPY library-scripts/*.sh /tmp/library-scripts/
RUN bash /tmp/library-scripts/common-debian.sh

# Install development tools
RUN apt install -y make \
                   bash-completion \
                   git tig \
                   clang-format

# Clear package cache
RUN rm -rf /var/lib/apt/lists/*

USER ${USERNAME}

ENTRYPOINT [ "/usr/local/share/docker-init.sh" ]
CMD [ "sleep", "infinity" ]
