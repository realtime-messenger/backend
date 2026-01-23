# Before start
```sh
git submodule update --init
```

# Set up java alternatives
```sh
sudo update-alternatives --config java
```
Select something like /usr/lib/jvm/java-25-openjdk/bin/java

# Docker deploy
```sh
export CR_PAT=YOUR_TOKEN
echo $CR_PAT | docker login ghcr.io -u "$USER" --password-stdin

docker build --no-cache -t ghcr.io/realtime-messenger/backend:latest .
docker push ghcr.io/realtime-messenger/backend:latest
```
