# Dungeon_Adventure
Final Project for TCSS 360: Software Development And Quality Assurance Technique at University of Washington
## Gameplay
Navigate a maze full of monsters and collect 4 pillars of OO (Abstraction, Encapsulation, Inheritance, and Polymorphism) and take them to the exit to win the game.
## Control
Using your keyboard to enter your selection.
#### Navigating the maze
```
.__   __.      ___   ____    ____  __    _______      ___   .___________. __    ______   .__   __. 
|  \ |  |     /   \  \   \  /   / |  |  /  _____|    /   \  |           ||  |  /  __  \  |  \ |  | 
|   \|  |    /  ^  \  \   \/   /  |  | |  |  __     /  ^  \ `---|  |----`|  | |  |  |  | |   \|  | 
|  . `  |   /  /_\  \  \      /   |  | |  | |_ |   /  /_\  \    |  |     |  | |  |  |  | |  . `  | 
|  |\   |  /  _____  \  \    /    |  | |  |__| |  /  _____  \   |  |     |  | |  `--'  | |  |\   | 
|__| \__| /__/     \__\  \__/     |__|  \______| /__/     \__\  |__|     |__|  \______/  |__| \__|


Current inventory: 
	You don't have any pillar right now.
The map: (P is the player current location)
+++
+P+
+ +
Please choose the direction you wishes to go: (0 to go back to pause menu)
3. South v

```
#### Combat

```
     _______  __    _______  __    __  .___________. __
    |   ____||  |  /  _____||  |  |  | |           ||  |
    |  |__   |  | |  |  __  |  |__|  | `---|  |----`|  |
    |   __|  |  | |  | |_ | |   __   |     |  |     |  |
    |  |     |  | |  |__| | |  |  |  |     |  |     |__|
    |__|     |__|  \______| |__|  |__|     |__|     (__)

Name: 1                                     __                                                    Name: Gremlin            
Class: Warrior                            \   /                                                   Hit Points: 80.00        
Hit Points: 650.00                          \\                                                    Attack Speed: 5          
Attack Speed: 3                               \\                                                  Chance to hit: 0.80      
Chance to hit: 0.50         ___________________||/\_                                              Minimum Damage: 10       
Chance to block: 0.30      (___________________()| _||||||||||||||||||||||||||||||||||||||||||>   Maximum Damage: 25       
Minimum Damage: 30                             ||\/                                               Chance to self-heal: 0.20
Maximum Damage: 40                            //                                                                           
Your mana amount: 50.0                      //                                                                             
Your healing potions: 5                   /__\                                                                             

Notification: Turn 1
Fight!
Please enter your choice: 
	0. Attack
	1. Special attack (Will fail if mana is not 100)
	2. Use Potion

```