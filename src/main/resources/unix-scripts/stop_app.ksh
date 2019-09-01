#!/bin/ksh

. ${PRODUCT_APPLI}/config/autofeed.cfg

PID=$(ps -ef | grep "${JAR_FILE}" | grep -v grep | awk '{print $2}')

if [ -z "${PID}" ]
then
    echo "The process ${JAR_FILE} is not started"
    exit 1
fi

echo "[INFO] $(date '+%y%m%d %H:%M:%S') : Arrêt du serveur."
kill "${PID}" || exit 1
echo "[INFO] $(date '+%y%m%d %H:%M:%S') : Serveur arrêté avec succès."