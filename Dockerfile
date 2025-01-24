FROM ubuntu:latest
LABEL authors="chaym"

ENTRYPOINT ["top", "-b"]