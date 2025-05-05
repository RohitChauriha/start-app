FROM python:3.9-slim
WORKDIR /app
COPY . /app
RUN mkdir -p /var/data
RUN pip install --no-cache-dir -r requirements.txt
EXPOSE 5000
ENV BACKEND_SERVICE_PORT=8080
ENV BACKEND_SERVICE_HOST=backend
ENV CONSUMER_SERVICE_PORT=8080
ENV CONSUMER_SERVICE_HOST=consumer
ENTRYPOINT ["python", "app.py"]