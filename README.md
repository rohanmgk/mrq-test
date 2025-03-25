# Bingo Ticket Generator

## Overview
The Bingo Ticket Generator is a Java based application designed to generate Bingo tickets in a predefined format. 
The application allows users to generate Bingo strips, with each strip containing a set of random tickets. 
This tool can be used for games, simulations, or testing purposes.

## Features
- **Random Ticket Generation**: Generates random Bingo tickets based on specified rules.
- **Multiple Strips**: Can generate multiple Bingo strips, each containing a set of tickets.
- **Efficient Performance**: Designed for generating a large number of tickets in a short period.

## Key Classes
- **BingoStripGenerator.java**: This is the main class which generates a random Bingo strip
- **PotentialPositionsGenerator.java**: This class creates a set of static positions which would meet the criteria of rules for Bingo tickets.
- **NumberPoolGenerator.java**: This class creates a pool of available numbers for each column.
- **BingoStripGeneratorTest.java**: This a unit test to verify the rules for the Bingo ticket.
- **BingoStripGeneratorLoadTest.java**: This a load test to ensure that it takes less than 1s to generate 10k Bingo strips.
 
## Requirements
- Java 21 or higher
- Maven (for building and running the project)

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/rohanmgk/mrq-test.git
```

### Run the tests
```bash
mvn clean test
```
### Run the application to pretty print the Bingo strip
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="org.example.BingoStripGenerator"
```
