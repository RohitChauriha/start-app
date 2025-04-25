import os

import requests
from flask import Flask

app = Flask(__name__)
server = "0.0.0.0"
BACKEND_PORT = os.environ['BACKEND_PORT']
BACKEND_HOSTNAME = os.environ['BACKEND_HOSTNAME']
base_url = "http://" + BACKEND_HOSTNAME + ":" + BACKEND_PORT


@app.route('/hello')
def hello_world():
    api = "/hello"
    backend_url = base_url + api
    res = requests.get(url=backend_url)
    if res.status_code != 200:
        return "<html><head>" + "java service not up" + "</head></html>"
    return "<html><head>" + res.text + "</head></html>"


@app.route('/')
def service_status():
    return "<html><head>" + "python service is up" + "</head></html>"


if __name__ == '__main__':
    app.run(server)
