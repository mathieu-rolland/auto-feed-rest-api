#!/bin/ksh 

. ${PRODUCT_APPLI}/config/autofeed.cfg
PID=$(ps -ef | grep "${JAR_FILE}")

PROGNAME=$(basename $0 .ksh)
DATE_EXEC="$(date '+%y%m%d')"

LOG_FILE="${VARSOFT_APPLI}/logs/${PROGNAME}_${DATE_EXEC}.log"

if [ ! -z "${PID}" ]
then
    echo "The process ${JAR_FILE} is already started"
    exit 1
fi

nohup \
    java -jar -Dspring.profiles.active=${PROFILE} \
    -Dspring.config.location=${PRODUCT_APPLI}/config/ \
    "${PRODUCT_APPLI}/app/${JAR_FILE}" \
    2>&1 1>"${LOG_FILE}" &
