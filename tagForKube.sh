#!/bin/bash
git tag --delete kube || true && git push --delete origin kube || true && git tag -a kube -m "Pripravljen za na kubernetes"