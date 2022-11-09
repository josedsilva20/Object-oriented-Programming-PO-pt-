# Programação com Objetos

Contains notes from my journey trough the subject.

The exercises from the lab classes can be accessed by [this link](https://fenix.tecnico.ulisboa.pt/disciplinas/PO2/2022-2023/1-semestre/aulas-praticas). To do so, you need to be authenticated to the fenixEdu platform from IST.

## Notes

  The notes are organized in two sections: one for theoritical classes and one for lab classes. The notes from `theoretical classes` can be accessed [here](https://github.com/josedsilva20/Object-oriented-Programming-PO-pt-/tree/main/te%C3%B3ricas), and from the `lab classes` [here](https://github.com/josedsilva20/Object-oriented-Programming-PO-pt-/tree/main/lab).
  
 ## Course Project
 
 The project consists in the development of a desktop application. It manages a Communication Terminal Network with the name `prr` . 
 The program makes it possible to manage clients, its associated terminals and communications and make it possible to consult them.
 It has several services such as `register a client` , `show all terminals of a given client` and much more. It uses the [po-uilib interface](https://fenix.tecnico.ulisboa.pt/homepage/ist13501/framework-de-interaccao-po-uilib) as the `user interaction interface` to interact with the user. The project was developed with the help of my team mate [@s2furtado](https://github.com/s2furtado) .
 
 In [this section](https://github.com/josedsilva20/Object-oriented-Programming-PO-pt-/tree/main/projeto) is the project related content. The code related to the front-end part is in the section [prr/app](https://github.com/josedsilva20/Object-oriented-Programming-PO-pt-/tree/main/projeto/proj/prr/app) where it has many menus and features (not all of them completed). The code related to the back-end part is in the section [prr/core](https://github.com/josedsilva20/Object-oriented-Programming-PO-pt-/tree/main/projeto/proj/prr/core) with all the logic behind the problem.
 
 ### Compile and run the code
 
 To be able to compile and run the projet you need to be in a Linux/Unix environment with Java jdk installed (version 1.7 or higher). Then, download the project and reach the same directory that contains the folder `prr` . Once you've reached that directory, run the commands:
 
 ```javac -cp po-uilib.jar:. `find prr -name "*.java"` ```to compile.
 
 ```java -cp po-uilib.jar:. `find prr.app.App` ```to run.
 
Hope you guys enjoy this project, there's a lot of effort on that 😊 !
