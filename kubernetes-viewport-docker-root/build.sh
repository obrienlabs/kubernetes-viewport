#!/bin/bash
#############################################################################
#
# Copyright © 2018 Amdocs.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#        http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
#############################################################################
# v20180625
# https://wiki.onap.org/display/DW/Cloud+Native+Deployment
# source from https://jira.onap.org/browse/LOG-135
# Michael O'Brien

mkdir target
cp ../kubernetes-viewport-service/target/*.war target
docker build -t oomk8s/kubernetes-viewport -f DockerFile .
docker images | grep kubernetes-viewport
docker tag oomk8s/kubernetes-viewport oomk8s/kubernetes-viewport:0.0.1
docker login
docker push oomk8s/kubernetes-viewport:0.0.1

#docker stop kubernetes-viewport
#docker image rm oomk8s/kubernetes-viewport
#docker rmi $(docker images -f "dangling=true" -q)
#cd ..
#mvn clean install -U
#cd kubernetes-viewport-docker-root/
#./build.sh
#docker run -d -it --rm --name kubernetes-viewport -p 8888:8080 oomk8s/kubernetes-viewport:latest
#docker logs -f kubernetes-viewport
#curl http://127.0.0.1:8888/kubernetes-viewport/rest/health/health

