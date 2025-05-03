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
    msg = ""
    with open('./data/books.json', 'r') as file:
        books = json.load(file)
    for book in books:
        try:
            logger.info("creating book with title %s" % book['title'])
            res = requests.post(url=backend_url, json=book)
        except ConnectionError:
            logger.error("not able to connect with backend service")
            msg += "not able to connect with backend service\n"
            return msg
        if res.status_code != 201:
            logger.error("failed to create book with title %s response from backend: status %s and text %s",
                         book['title'], res.status_code, res.text)
            msg += "failed to create book with title" + book['title'] + "\n"
        else:
            logger.info("successfully created book with title %s", book['title'])
            msg += "successfully created book with title %s", book['title'] + "\n"
    return msg


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
    logger.info("response from backend: " + res.text)
    return jsonify(res.text)


if __name__ == '__main__':
    app.run(server)
