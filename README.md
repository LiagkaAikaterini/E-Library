# E-Library
Developed in Java, as part of the NTUA course Multimedia Technology 2023-2024, this project implements an electronic library, enabling administrators to manage library resources and users to search, borrow books, and provide feedback.

## Presentation of the E-Library UI

https://github.com/user-attachments/assets/da1d89a2-2747-4283-92c8-a7490ee833a6


## Configuration 
To run this project locally on your machine, follow these steps:

**1.** Make sure you have downloaded the appropriate `javafx-sdk` for your Operating System by the [official website](https://gluonhq.com/products/javafx/).

**2.** Clone this GitHub Repository using the command:
   ```bash
   git clone https://github.com/LiagkaAikaterini/E-Library.git
```

**3.** Delete the `.vscode/launch.json` file: This file is specific to the original environment and must be recreated for your setup.

**4.** Recreate the `.vscode/launch.json` file. If you are using Visual Studio Code, which is the environment in which the project was developed in, you can simply use the "Add Configuration" option to generate a new `launch.json` file.

**5.** Add a `vmArgs` line: In the newly created `.vscode/launch.json` file, add the following line under configurations:
   ```bash
   "vmArgs": "--module-path local-path-to-your-javafx-sdk/lib --add-modules javafx.controls,javafx.fxml"
   ```
   Where the local-path-to-your-javafx-sdk should be replaced with the local path to the JavaFX SDK in your machine.
      
**6.** To run the project, simply execute the `App.java` file.

## Implementation Details
All the core requirements outlined in the project specifications have been successfully implemented. For additional assumptions made during development and an explanation of the structure and logic followed, please refer to the following files:

- [project_requirements.pdf](project_requirements.pdf): Description of implementation details and requirements.
- [medialab_report.pdf](medialab_report.pdf): Explanation of the overall structure and logic, as well as additional assumptions made during development.

This documents can provide a clearer understanding of the choices made throughout the project.

## Login - Signup
In this E-Library, when the project is running, any user can create a new account as a User or an Admin. But we sould note 2 things:
- To create a new Admin account you will be prompted to enter an **Admin Registration Password** , which is supposedly provided by the Library. This password is set to: `d4yur7g` 
- There is a default admin saved into the system with the following credentials:

  **Username:**    `medialab` 

  **Password:**    `medialab_2024`

- There are also some dummy users created, so that there are Borrows, Ratings and Reviews in our system. To access any of these users someone can use one of the following Usernames with the same Password:

  **Username:**    `john_doe` `jane_smith` `mike_jones` `emma_brown` `michael_smith`

  **Password:**    `11111`
