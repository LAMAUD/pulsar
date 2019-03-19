# pulsar


docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  -p 80:80 \
  -v $PWD/data:/pulsar/data \
  apachepulsar/pulsar-standalone:2.3.0 \
  bin/pulsar standalone
