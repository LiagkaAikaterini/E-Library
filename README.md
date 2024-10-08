# E-Library
Developed in Java, as part of the NTUA course Multimedia Technology 2023-2024, this project implements an electronic library, enabling administrators to manage library resources and users to search, borrow books, and provide feedback.

## Configuration 
To run this project locally on your machine, follow these steps:

**1.** Make sure you have downloaded the appropriate javafx-sdk for your Operating System by the [official website](https://gluonhq.com/products/javafx/).

**2.** Clone this GitHub Repository using the command:
   ```bash
   git clone https://github.com/LiagkaAikaterini/E-Library.git

**3.** Delete the .vscode/launch.json file: This file is specific to the original environment and must be recreated for your setup.

**4.** Recreate the .vscode/launch.json file. If you are using Visual Studio Code, which is the environment in which the project was developed in, you can simply use the "Add Configuration" option to generate a new launch.json file.

**5.** Add a vmArgs line: In the newly created .vscode/launch.json file, add the following line under configurations:
   ```bash
   "vmArgs": "--module-path local-path-to-your-javafx-sdk/lib --add-modules javafx.controls,javafx.fxml"
Where the local-path-to-your-javafx-sdk should be replaced with the local path to the JavaFX SDK in your machine.
