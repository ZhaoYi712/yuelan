#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20231130.sql ./mysql/db
cp ../sql/ry_config_20231204.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../yuelan-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy yuelan-gateway "
cp ../yuelan-gateway/target/yuelan-gateway.jar ./ruoyi/gateway/jar

echo "begin copy yuelan-auth "
cp ../yuelan-auth/target/yuelan-auth.jar ./ruoyi/auth/jar

echo "begin copy yuelan-visual "
cp ../yuelan-visual/yuelan-monitor/target/yuelan-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy yuelan-modules-system "
cp ../yuelan-modules/yuelan-system/target/yuelan-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy yuelan-modules-file "
cp ../yuelan-modules/yuelan-file/target/yuelan-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy yuelan-modules-job "
cp ../yuelan-modules/yuelan-job/target/yuelan-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy yuelan-modules-gen "
cp ../yuelan-modules/yuelan-gen/target/yuelan-modules-gen.jar ./ruoyi/modules/gen/jar

