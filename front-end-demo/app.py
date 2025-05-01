import logging
import os
import socket

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
    logger.info("backend url: " + backend_url)
    front_end_hostname = socket.gethostname()
    msg = "<html><head>"
    msg_end = "</head></html>"
    msg += "<h1>" + front_end_hostname + "</h1>"
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
    msg += "<h1>" + res.text + "</h1>"
    msg += msg_end
    return msg


@app.route('/customer', methods=['POST'])
def create_customer():
    api = "/customer"
    backend_url = base_url + api
    try:
        logger.info("trying to contact backend url %s for creating customers" % backend_url)
        res = requests.post(url=backend_url)
    except ConnectionRefusedError:
        logger.error("java service connection error")
        return "<html><head> java service connection error </head></html>"
    logger.info("Response from backend: " + res.text)
    return "customers created"


@app.route('/customer', methods=['GET'])
def get_customer():
    api = "/customer"
    backend_url = base_url + api
    try:
        logger.info("trying to contact backend url %s for fetching customers" % backend_url)
        res = requests.get(url=backend_url)
    except ConnectionRefusedError:
        logger.error("java service connection error")
        return "<html><head> java service connection error </head></html>"
    logger.info("Response from backend: " + res.text)
    with open('data.json', 'w') as fp:
        json.dump(res.text, fp)
    return res.text


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
        msg += "<h1> java service connection error </h1>"
    msg += "<h1>" + res.text + "</h1>"
    msg += msg_end
    return msg


if __name__ == '__main__':
    app.run(server)
