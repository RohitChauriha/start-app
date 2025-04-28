import logging
import os

import requests
from flask import Flask

app = Flask(__name__)
server = "0.0.0.0"
BACKEND_PORT = os.environ['BACKEND_PORT']
BACKEND_HOSTNAME = os.environ['BACKEND_HOSTNAME']
base_url = "http://" + BACKEND_HOSTNAME + ":" + BACKEND_PORT
file_handler = logging.FileHandler('/var/log/front-end-demo.log')
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
file_handler.setFormatter(formatter)
logger = logging.getLogger(__name__)
logger.addHandler(file_handler)
logger.setLevel('INFO')


@app.route('/hello')
def hello_world():
    api = "/hello"
    backend_url = base_url + api
    logger.debug("backend url: " + backend_url)
    try:
        logger.info("backend url: " + backend_url)
        res = requests.get(url=backend_url)
    except ConnectionRefusedError:
        logger.error("java service connection error")
        return "<html><head> java service connection error </head></html>"
    if res.status_code != 200:
        logger.error("java service response %s", res.status_code)
        return "<html><head>" + "java service not up" + "</head></html>"
    logger.info("Response from backend: " + res.text)
    return "<html><head>" + res.text + "</head></html>"


@app.route('/')
def service_status():
    backend_url = base_url + "/"
    logger.debug("backend url: " + backend_url)
    msg = "<html><head>"
    msg_end = "</head></html>"
    msg += "<h1>python service is up</h1>"
    res = ""
    try:
        logger.info("backend url: " + backend_url)
        res = requests.get(url=backend_url)
    except ConnectionRefusedError:
        logger.error("java service connection error")
        msg += "<h2> java service connection error </h2>"
    msg += "<h2>" + res.text + "</h2>"
    msg += msg_end
    return msg


if __name__ == '__main__':
    app.run(server)
