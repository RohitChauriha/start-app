import json
import logging
import os
import sys

import requests
from flask import Flask, jsonify
from requests.exceptions import Timeout, ReadTimeout, ConnectTimeout, ConnectionError

app = Flask(__name__)
server = "0.0.0.0"
if len(sys.argv) > 1:
    environment = sys.argv[1]
    BACKEND_SERVICE_PORT = "8080"
    BACKEND_SERVICE_HOST = "127.0.0.1"
    BACKEND_SERVICE_URL = "http://" + BACKEND_SERVICE_HOST + ":" + BACKEND_SERVICE_PORT
    CONSUMER_SERVICE_PORT = "8080"
    CONSUMER_SERVICE_HOST = "127.0.0.1"
    CONSUMER_SERVICE_URL = "http://" + CONSUMER_SERVICE_HOST + ":" + CONSUMER_SERVICE_PORT
    file_handler = logging.FileHandler('../log/front-end-demo.log')
else:
    BACKEND_SERVICE_PORT = os.environ['BACKEND_PORT']
    BACKEND_SERVICE_HOST = os.environ['BACKEND_HOSTNAME']
    BACKEND_SERVICE_URL = "http://" + BACKEND_SERVICE_HOST + ":" + BACKEND_SERVICE_PORT
    CONSUMER_SERVICE_PORT = os.environ['CONSUMER_SERVICE_PORT']
    CONSUMER_SERVICE_HOST = os.environ['CONSUMER_SERVICE_HOST']
    CONSUMER_SERVICE_URL = "http://" + CONSUMER_SERVICE_HOST + ":" + CONSUMER_SERVICE_PORT

    file_handler = logging.FileHandler('/var/log/front-end-demo.log')
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
file_handler.setFormatter(formatter)
logger = logging.getLogger(__name__)
logger.addHandler(file_handler)
logger.setLevel('INFO')


@app.route('/create-books', methods=['POST'])
def create_books():
    api = "/book"
    backend_url = BACKEND_SERVICE_URL + api
    with open('./data/books.json', 'r') as file:
        books = json.load(file)
    logger.info("backend url is %s" % backend_url)
    success = True
    for book in books:
        try:
            logger.info("creating book with title %s" % book['title'])
            res = requests.post(url=backend_url, json=book)
            if res.status_code != 201:
                logger.error("failed to create book with title %s response from backend: status %s and text %s",
                             book['title'], res.status_code, res.text)
                success = False
            else:
                logger.info("successfully created book with title %s", book['title'] + "\n")
        except (Timeout, ReadTimeout, ConnectTimeout, ConnectionError) as ex:
            logger.error("not able to connect with backend service with exception %s ", ex)
            raise ex
    return str(success)


@app.route('/get-books', methods=['GET'])
def get_books():
    api = "/book"
    backend_url = BACKEND_SERVICE_URL + api
    try:
        logger.info("fetching all books")
        res = requests.get(url=backend_url)
    except (Timeout, ReadTimeout, ConnectTimeout, ConnectionError) as ex:
        logger.error("not able to connect with backend service with exception %s ", ex)
        raise ex
    if res.status_code != 200:
        logger.error("failed to fetch all books, response from backend: status %s and text %s", res.status_code,
                     res.text)
    return jsonify(res.text)


@app.route('/get-messages', methods=['GET'])
def get_messages():
    api = "/message"
    backend_url = BACKEND_SERVICE_URL + api
    try:
        logger.info("fetching all messages")
        res = requests.get(url=backend_url)
    except (Timeout, ReadTimeout, ConnectTimeout, ConnectionError) as ex:
        logger.error("not able to connect with backend service with exception %s ", ex)
        raise ex
    if res.status_code != 200:
        logger.error("failed to fetch all messages, response from backend: status %s and text %s", res.status_code,
                     res.text)
    return jsonify(res.text)


if __name__ == '__main__':
    app.run(server)
