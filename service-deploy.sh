#!/bin/sh

echo "nim project deploy service --exclude=web --incremental"

nim project deploy service --exclude=web --incremental
