#!/bin/sh

echo "nim project deploy . --exclude=web --incremental"

nim project deploy . --exclude=web --incremental
