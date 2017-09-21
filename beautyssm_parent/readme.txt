1.启动redis、active-mq、zookeeper。
2.启动服务beautyssm_service_goods、beautyssm_service_user、beautyssm_mq_consumer（只是一个消费者，消息队列中的消息，并根据业务分发）。
3.启动消费者服务 beautyssm_web_goods、beautyssm_web_user。
4.启动nginx服务，作为动静分离,静态文件在beauty_ssm_cluster-master项目中。