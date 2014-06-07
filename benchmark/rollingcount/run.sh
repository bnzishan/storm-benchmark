#!/bin/sh

# global configurations

CUR_DIR=`dirname "$0"`
BASE_DIR=$CUR_DIR/..
. $BASE_DIR/conf/config.sh
. $BASE_DIR/bin/functions.sh

# topology configurations

TOPOLOGY_CLASS=RollingCount
TOPOLOGY_NAME=RollingCount

WORKERS=4
ACKERS=$WORKERS
PENDING=200

COMPONENT=topology.component
SPOUT_NUM=4
BOLT_NUM=8

WINDOW_LEN=9
EMIT_FREQ=3

TOPOLOGY_CONF=$TOPOLOGY_CONF,topology.name=$TOPOLOGY_NAME,topology.workers=$WORKERS,topology.acker.executors=$ACKERS,topology.max.spout.pending=$PENDING,$COMPONENT.spout_num=$SPOUT_NUM,$COMPONENT.bolt_num=$BOLT_NUM,window.length=$WINDOW_LEN,emit.frequency=$EMIT_FREQ

echo "========== running RollingCount =========="
run_benchmark

