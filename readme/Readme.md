DockerDesktop使用了代理
![img.png](img.png)
# 命令
部署deployments和创建service后
minikube service webdemo-service 获取url后访问服务（因为本机和k8s集群网络是不通的）

minikube service webdemo-service命令实际上做了以下几件事：
在后台创建了一个临时隧道，将你的本地机器连接到 Kubernetes 集群中的 Service 做了一个映射
![img_1.png](img_1.png)
## docker相关
构建语句 docker build -t webdemo:v1.0 .
-t 是打tag的意思 .是当前目录
运行语句 docker run -d -p 8080:8080 --name webdemo webdemo:v1.0
-d：--detach的缩写，表示 “后台运行容器”  如果不加-d，容器会在当前终端前台运行，关闭终端则容器停止。

## minikube
minikube start
minikube stop

加载镜像 minikube image load webdemo:v1.0
获取访问地址 minikube service webdemo-service --url
进入节点 minikube ssh -n minikube-m02

## k8s
查看pods kubectl get pods -o wide
查看deployments kubectl get deployments
查看services kubectl get services

部署 kubectl apply -f webdemo-deployment.yaml
创建service kubectl apply -f webdemo-service.yaml


#k8s删除 Deployment 后，它所管理的所有 Pod 会被自动终止并删除： kubectl delete deployment webdemo-deployment
#k8s删除service：kubectl delete service webdemo-service

# 知识
## 为什么一个node上两个相同的pods端口不会冲突
Kubernetes 中，每个 Pod 会被分配一个 唯一的集群内部 IP 地址（比如 10.244.1.5、10.244.1.6），且每个 Pod 的网络环境是完全隔离的：
Pod A（IP: 10.244.1.5）的容器监听 8080 端口，对外暴露的是 10.244.1.5:8080；
Pod B（IP: 10.244.1.6）的容器也监听 8080 端口，对外暴露的是 10.244.1.6:8080。
这就像 “两台独立的电脑”：即使都用 8080 端口，因为 IP 地址不同（一台是 192.168.1.10，另一台是 192.168.1.11）
彼此的 8080 端口也不会冲突 ——Node 上的两个 Pod 就是这个逻辑，它们的 8080 端口属于不同的 “网络身份”（IP + 端口），互不干扰。


## 
创建service后，访问service即可实现负载均衡

todo
#todo 现在应该固定service的外部访问方式了 生产是用云环境使用 LoadBalancer 类型服务（推荐）
#测试用minikube service webdemo-service 要了解下生产的方案 和Ingress的关系

四、云环境的 “主流架构”：Ingress + LoadBalancer 配合使用
在生产环境中，纯 LoadBalancer 或纯 Ingress 都较少见，更多是 “Ingress 作为七层入口，云 LoadBalancer 作为 Ingress 的四层入口”，形成 “分层负载均衡” 架构：