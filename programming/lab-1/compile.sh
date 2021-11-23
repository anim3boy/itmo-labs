#!/bin/bash

javac Main.java
jar cfe main.jar Main Main.class
java -jar main.jar
