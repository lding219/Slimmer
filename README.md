# Pet slimmer

## Description
This application can help the users to **document the food** their pets have had in a day. Then put each day's data into a weekly report.
## Target Users
The *Target users* of this application include:
- Pet owners with pets experiencing health issues
- Pet owners who want to track and monitor their pets' food intake
- Veterinary clinics and animal hospitals
## My Interest
This application interests me because I am personally a cat owner. I feel anxious when I see my cat become chubby as he grew because of the potential heart issues he would have due to obesity. Therefore, I would like to make this application as my personal project to help pets owner like me.


## User Stories
- As a user, I want to be able to name a pet (X), and put it in a list of pets (Y) I owned. 
- As a user, I want to be able to document single food intake (X), label it as "treat", then add it to the list of food (Y) the pet have today. 
- As a user, I want to be able to view the list of foods the pet has had today.
- As a user, I want to be able to view the weekly report of food my pet has had.
- As a user, I want to be able to be given the option to save the information about the pets at home, and the food the have eaten.
- As a user, I want to be able to be given the option to reload my home.
## Instructions for End User
- First required action: To add a pet (X) to your home (Y), you can type the pet name in the text field next to the "Add Pet" button, then press the "Add Pet" button when you finish. And you are able to add mutiple pets to your home.
- Second required action: To remove a pet from home, you can press the "Remove Pet" button. Then a new window will pop up, letting you choose the pet you want to remove. After your selection, make sure you press the "Confirm Remove" button.
- To view the daily food intake report of a pet, you can press the "View Daily Report" button. A new window will pop up and it requires you to select the pet (X) from home (Y) you want to view the weekly report. After your selection, you can press the "View Daily" button. Then, it will show you the weekly food intake of the pet you selected. 
- To feed the pet, you can press the "Feed" button, then a new window will pop up, letting you choose the pet from home you want to feed and the day. You also need to fill in the text fields about the food name and amount you want to feed the pet. After all, please press the "Confirm" button.
- To view the weekly report, please press the "View Weekly Report" Button. Then, a new window will pop up, letting you choose the pet from home you want to view the weekly report. After your selection, make sure you press the "View" button, so the pet's weekly report window can pop up.
- To load the previous data, please press the "Load Previous Data" button.
- To save your progress, please press the "Save" button before you quit.
- You can locate my visual component by viewing pets at your home. Each name will come with a pet image.
## Phase 4: Task 2
- Sun Nov 24 13:41:10 PST 2024
- Successfully read data!
- Sun Nov 24 13:41:10 PST 2024
- Successfully added lucky
- Sun Nov 24 13:41:14 PST 2024
- View foods lucky ate on Monday
- Sun Nov 24 13:26:04 PST 2024
- Successfully saved data!
- Sun Nov 24 13:25:58 PST 2024
- Successfully removed eggy
- Sun Nov 24 14:06:08 PST 2024
- Feed lucky apple by 10
## Phase 4: Task 3
If I had more time, I would like to refactor my SlimmerUI class. There are too many fields in that class. I would like to refactor it as what the AlarmSystem did: divide the GUI into multiple classes, so that it would be clearer if I want to modify a specific functionality of the GUI.   
I also want to refactor the Home class by adding a field "List pets" in the Pet class. With this modification, it would be clearer. But this modification also has drawbacks. It is good to refactor it now because pets are the only thing at home, but if I want to add other things, for example, family members, it is good to have this home class.