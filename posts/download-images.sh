#!/bin/bash
# Run this script from the posts/ directory to download Medium CDN images locally.
# Usage: cd posts && bash download-images.sh

mkdir -p images

echo "Downloading State of SGX Development images..."
curl -sL -o images/sgx-enclave-diagram.png "https://cdn-images-1.medium.com/max/800/1*uM1eE030ybUXIewqF6sL8w.png"
curl -sL -o images/sgx-card.jpg "https://cdn-images-1.medium.com/max/800/0*C8tekcC01zmbHqWa.jpg"

echo "Downloading Better Defending Private Industry images..."
curl -sL -o images/nation-state-header.jpg "https://cdn-images-1.medium.com/max/800/0*1w8UzAPUM6Z1aVb0"
curl -sL -o images/dhs-org-chart.jpg "https://cdn-images-1.medium.com/max/800/0*Vnt77N1gXP1PZdCg"

echo "Downloading Security & Privacy Risks of ML Models images..."
curl -sL -o images/ml-training-phase.png "https://cdn-images-1.medium.com/max/800/1*mqFMBIs4gsECYL5eXyOcKw.png"
curl -sL -o images/ml-prediction-phase.png "https://cdn-images-1.medium.com/max/800/1*wXB-s2imSQcRcWm-or0XUA.png"
curl -sL -o images/ml-chihuahua-muffin.jpeg "https://cdn-images-1.medium.com/max/800/1*wlzDIbjqKKUbhVEBykSVIg.jpeg"
curl -sL -o images/ml-poisoning-attack.png "https://cdn-images-1.medium.com/max/800/1*W1fNFRYSkEZi_TW23MeZUA.png"
curl -sL -o images/ml-tay-chatbot.png "https://cdn-images-1.medium.com/max/800/1*WF5nsY5gitmpLdnWvVoxyQ.png"
curl -sL -o images/ml-evasion-attack.png "https://cdn-images-1.medium.com/max/800/1*dvkRhVBYPTsKg7_xia0_ZA.png"
curl -sL -o images/ml-adversarial-panda.png "https://cdn-images-1.medium.com/max/800/1*24f5-fbGPzgDv1Rhpkg0TQ.png"
curl -sL -o images/ml-memorization.png "https://cdn-images-1.medium.com/max/800/1*7NNA35V5C_9fp2GEgHZ-nA.png"

echo "Downloading Revisiting Radix Economy images..."
curl -sL -o images/radix-ring-counter.png "https://cdn-images-1.medium.com/max/800/1*5ySRqSvLvvtcK-0TXDWTKQ.png"
curl -sL -o images/radix-abacus.png "https://cdn-images-1.medium.com/max/800/1*T6ssr2Yz_jGVgM0lcD2M3w.png"
curl -sL -o images/radix-setun.jpeg "https://cdn-images-1.medium.com/max/800/1*CAo1euE7t6tJJIS3xMLutQ.jpeg"
curl -sL -o images/radix-ware-rand-report.png "https://cdn-images-1.medium.com/max/800/1*KI8aJB8ZT38aDMODhwtTgw.png"
curl -sL -o images/radix-two-of-five-code.png "https://cdn-images-1.medium.com/max/800/1*jLrivzLVhpzcfV9OF9Psgg.png"

echo "Done! Check images/ directory for downloaded files."
echo "Verify file sizes - any 0-byte files indicate failed downloads."
ls -la images/
