#!/bin/ksh

LST_TO_KILL=$(pgrep "mjpg_streamer")
for pid in $LST_TO_KILL
do
	echo "Force to kill $pid"
	kill -9 $pid
done
