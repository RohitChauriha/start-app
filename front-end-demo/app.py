import os

import requests
from flask import Flask
import logging

app = Flask(__name__)
server = "0.0.0.0"
BACKEND_PORT = os.environ['BACKEND_PORT']
BACKEND_HOSTNAME = os.environ['BACKEND_HOSTNAME']
base_url = "http://" + BACKEND_HOSTNAME + ":" + BACKEND_PORT


@app.route('/hello')
def hello_world():
    logging.error("python hello world called")
    api = "/hello"
    backend_url = base_url + api
    logging.debug("backend url: " + backend_url)
    res = requests.get(url=backend_url)
    logging.info("backend url: " + backend_url)
    if res.status_code != 200:
        logging.error("java service not up")
        return "<html><head>" + "java service not up" + "</head></html>"
    logging.info("Response from backend: " + res.text)
    return "<html><head>" + res.text + "</head></html>"


@app.route('/')
def service_status():
    return "<html><head>" + "python service is up" + "</head></html>"


if __name__ == '__main__':
    app.run(server)
