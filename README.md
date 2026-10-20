# StardewValleyCropPlanner
This program is a work in progress. It is to be used alongside Stardew Valley and takes your farming level, year, and season as input and outputs the most cost effective crops to plant. 

## Files

NoFertilizer, BasicFertilizer, QualityFertilizer, and DeluxeFertilizer: Store a list of crop quality chance based on farming level

CropsRegrowing and CropsRegular: Store a list of crops, their sell prices, seed prices, grow time, regrow time (if applicable), and season

Farms: Stores a list of the different in game farms. This is not implemented yet. 

DevelopmentHistory: Before I was aware of the existence of GitHub, I made a small file detailing what changes came in what order.

## Development Plans

One major goal of the next version is to apply the principles of object oriented programming. Originally, the entire program was contained in a single file. Refactoring by separating classes into different files will reflect OOP.

In the future, I hope to add a way of incorporating your farm type and current money balance. 