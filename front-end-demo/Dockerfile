FROM python:3.9-slim
WORKDIR /app
COPY . /app
VOLUME /var/log/start-app/frontend:/var/log/
RUN pip install --no-cache-dir -r requirements.txt
EXPOSE 5000
ENV BACKEND_PORT=8080
ENV BACKEND_HOSTNAME=backend
ENTRYPOINT ["python", "app.py"]