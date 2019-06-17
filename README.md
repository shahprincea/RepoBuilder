### RepoBuilder

This project is intended to build package manager similar to yum or apt in java. Many time you want to install a package A that depedents on package B and C and that in turn can dependent on some other package say Z. So it should first install Z then B and C and A. Likewise if you delete A it should recursively delete B, C and Z. 

Needless to say we need to keep track of which package depedent on which and also reverse relationship that is which package is been used by which package. 

Here core ADT (Abstract Data Type) is Graph. Graph uses adjacency list to keep two way relationship. It also has a utility class GraphUtil which helps to detect if there is a cycle in the graph. 

Also, I have tried to used Singleton and Stragey design patterns to keep things loosely coupled. 



### Prereq
Install following tools on your laptop

- Maven 3.0+
- Git
- Java 8+
- Java IDE (I am using IntelliJ)

## Get the sourcecode

Change dir on your laptop where you want to checkout this project. 

```
cd ~/codebase
```
Clone the project
``` 
git clone 
```

## Build 

Once you have clone the project simply cd into it and run following command.

```
mvn clean package
```

In order to run from the jar file. This is what CF will use to run the app in the cloud. 
```
java -jar target/*.jar
```
