import os

import requests
from flask import Flask
import logging

app = Flask(__name__)
server = "0.0.0.0"
BACKEND_PORT = os.environ['BACKEND_PORT']
BACKEND_HOSTNAME = os.environ['BACKEND_HOSTNAME']
base_url = "http://" + BACKEND_HOSTNAME + ":" + BACKEND_PORT
file_handler = logging.FileHandler('/var/log/front-end-demo.log')
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
file_handler.setFormatter(formatter)
logger.addHandler(file_handler)


@app.route('/hello')
def hello_world():
    api = "/hello"
    backend_url = base_url + api
    logger.debug("backend url: " + backend_url)
    try:
        logger.info("backend url: " + backend_url)
        res = requests.get(url=backend_url)
    except:
        logger.error("java service not up")
    if res.status_code != 200:
        logger.error("java service response is not correct")
        return "<html><head>" + "java service not up" + "</head></html>"
    logger.info("Response from backend: " + res.text)
    return "<html><head>" + res.text + "</head></html>"


@app.route('/')
def service_status():
    return "<html><head>" + "python service is up" + "</head></html>"


if __name__ == '__main__':
    app.run(server)
