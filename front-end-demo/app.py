import json

import requests
from flask import Flask, jsonify
import os, logging, sys

app = Flask(__name__)
server = "0.0.0.0"
if len(sys.argv) > 1:
    environment = sys.argv[1]
    BACKEND_PORT = "8080"
    BACKEND_HOSTNAME = "localhost"
    base_url = "http://" + BACKEND_HOSTNAME + ":" + BACKEND_PORT
    file_handler = logging.FileHandler('./log/front-end-demo.log')
else:
    BACKEND_PORT = os.environ['BACKEND_PORT']
    BACKEND_HOSTNAME = os.environ['BACKEND_HOSTNAME']
    base_url = "http://" + BACKEND_HOSTNAME + ":" + BACKEND_PORT
    file_handler = logging.FileHandler('/var/log/front-end-demo.log')
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
file_handler.setFormatter(formatter)
logger = logging.getLogger(__name__)
logger.addHandler(file_handler)
logger.setLevel('INFO')


@app.route('/create-books', methods=['POST'])
def create_books():
    api = "/book"
    backend_url = base_url + api
    try:
        logger.info("trying to contact backend url %s for creating books" % backend_url)
        with open('books.json', 'r') as file:
            books = json.load(file)
            for book in books:
                res = requests.post(url=backend_url, json=book)
    except ConnectionRefusedError:
        logger.error("java service connection error")
        return "<html><head> java service connection error </head></html>"
    if res.status_code != 201:
        logger.info("Response from backend: status %s and text %s", res.status_code, res.text)
        return "<html><head> failed to create books at backend </head></html>"
    return "book created"


@app.route('/get-books', methods=['GET'])
def get_books():
    api = "/book"
    backend_url = base_url + api
    try:
        logger.info("trying to contact backend url %s for getting books" % backend_url)
        res = requests.get(url=backend_url)
    except ConnectionRefusedError:
        logger.error("java service connection error")
        return "<html><head> java service connection error </head></html>"
    logger.info("Response from backend: " + res.text)
    return jsonify(res.text)


if __name__ == '__main__':
    app.run(server)
