#!/bin/ksh

${MJPG_STREAMER_HOME}/mjpg_streamer -o "${MJPG_STREAMER_HOME}/output_http.so -w ${MJPG_STREAMER_HOME}/www -p 8091" -i "${MJPG_STREAMER_HOME}/input_raspicam.so"
