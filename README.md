# Voting System Simulation
## Overview
This project simulates various voting systems to analyze their outcomes based on different criteria. It allows users to configure parameters, generate random voters and candidates, and choose from multiple voting methods to see how opinions evolve and results differ.

## Conception Phase
Initial Planning: Before starting to code, we conducted a design phase to establish a solid starting point and ensure good organization.
## Project Setup
- GitHub Configuration: After completing the design diagrams, we set up our project on GitHub for version control and collaboration.
- 
## Development
1. Creating Packages and Classes:
- We created the necessary packages and parameter classes.
- Implemented the initial voting system (UnTour) for preliminary testing.
2. Unit Testing Extension:
- We decided to implement unit tests from the beginning to allow for thorough testing.
- This choice proved beneficial, saving us time and providing confidence in our functions.
3. Evolving Opinions and Voting Methods:
- Coded the ability to evolve opinions based on different criteria.
- Implemented other voting methods.
4. Textual Interface:
- Developed a textual interface for large-scale testing and usability.
5. Adding Extensions:
- Added all proposed extensions except the graphical interface for voting mode analysis, which we found challenging to implement.
6. Final Testing and Corrections:
- Tested all functionalities and made necessary final corrections.

## Usage Scenario (Without Graphical Interface)
- Nominal Scenario
1. User Parameter Configuration:
Upon opening the application, the user chooses how to set parameters:
  - Use existing parameters from the configuration file.
  - Create new parameters manually.
2. Using Saved Parameters:
- If the user selects existing parameters, proceed to step 4.
3. Manual Configuration:
- Creating Axes:
The application prompts for the number of axes and their names.
- Configuring People:
After setting axes, the application asks to generate a random number of voters.
The user specifies the number of voters and candidates and their names.
For candidate parameters, the user can:
Generate random axis values.
Manually input values for each axis.
Choosing Voting Method:

The user selects a voting method from the five available options:
- Single-round majority vote
- Two-round majority vote
- Approval voting
- Alternative vote
- Borda count
## Project Benefits
- Improved organization and deadline management.
- Enhanced skills in Java and UML.
- Discovered useful extensions, notably Git.
- Created a professional project to showcase during internship searches and future endeavors.

## Team
- Developed by three team members during our training at Polytech.

## Acknowledgments
- Thanks to Polytech for the training and support.
- Inspired by various voting system studies and simulations.
