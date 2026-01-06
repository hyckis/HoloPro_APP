# HoloPro — Android Hologram Video Player

HoloPro is an Android application designed for hologram-style video playback and interactive control.  
The app supports multi-angle video rendering, FTP-based media transfer, device-side playback management, and gesture-controlled interaction via a Raspberry-Pi camera interface.

## ✨ Features

- **Multi-angle hologram video playback**
  - Simultaneous playback of **4 independent video streams**
  - Supports independent start times, reverse playback, looping, and synchronized output
- **FTP-based media transfer**
  - Upload source assets to the server
  - Download processed hologram videos back to the device
  - Multi-threaded streaming for continuous playback
- **Local media access**
  - Load hologram videos directly from device storage
- **Raspberry Pi gesture-control interface**
  - Connects to a Pi-based camera module
  - Maps **user hand gestures → playback controls**
  - Enables touch-free, interactive viewing experience
- **Bluetooth device pairing**
  - In-app Bluetooth management for Raspberry-Pi interface discovery and connection

## 🏗️ Architecture Overview

- **Android application**
  - Kotlin / Java
  - Multi-threaded media pipeline
- **Server / FTP workflow**
  - Upload → process → download hologram video assets
- **Edge device interface**
  - Raspberry Pi camera + gesture detection
  - Bluetooth communication with Android app

## 🔧 Technologies

- Android SDK  
- FTP networking & multi-threaded streaming  
- Bluetooth device integration  
- Video playback pipeline (multi-stream)  
- Raspberry Pi + camera-based gesture input

## 🚀 Use Cases

- Holographic display playback
- Interactive museum / exhibition content
- Immersive installation systems
- Hands-free media control interfaces

## 📌 Project Status

This project is an applied prototype exploring hologram media playback and interactive control across mobile and edge-device environments.

