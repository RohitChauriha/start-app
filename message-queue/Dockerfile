FROM rabbitmq:latest

# Set Erlang cookie for cluster communication
ENV RABBITMQ_ERLANG_COOKIE="SHARED_SECRET_COOKIE"

# Copy custom configuration file (optional)
#COPY rabbitmq.conf /etc/rabbitmq/conf.d/rabbitmq.conf

# Copy advanced config file (optional)
#COPY advanced.config /etc/rabbitmq/advanced.config

# Expose default RabbitMQ ports
EXPOSE 5672 15672

# Entrypoint script to handle clustering (optional, for dynamic clustering)
COPY entrypoint.sh /docker-entrypoint-initdb.d/
RUN chmod +x /docker-entrypoint-initdb.d/entrypoint.sh

# Command to start RabbitMQ server
CMD ["rabbitmq-server"]
