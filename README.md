

This repository follows this specification: https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c

This project was developed using: 

 - Java 9
 - Scala 2.12.4 (Play Framework dependency)
 - SBT
 - Playframework 2.6 
 - Swagger
 - QuickTheories (Property Based Testing)
 - Oleaster (Tests)

The steps to runs are:

 - Install Scala: https://scala-lang.org/download/
 - Install SBT https://www.scala-sbt.org/1.x/docs/index.html
 - Install Gradle: https://gradle.org/install/
 
This steps can be facilitated using Intellij

Into project directory:

  - Build and Download dependecies
  ``` sbt compile ```

  - Tests
  ``` sbt test ````

  - Run
  ``` sbt run ```

  Go to ``` localhost:9000/docs/ ```


The specification example:
```
{
  "plane": {
    "boundX": 5,
    "boundY": 5
  },
  "sounders": [
    {
      "direction": "N",
      "coordinate": {
        "x": 1,
        "y": 2
      },
      "actions": "LMLMLMLMM"
    }, {
      "direction": "E",
      "coordinate": {
        "x": 3,
        "y": 3
      },
      "actions": "MMRMMRMRRM"
    }
  ]
}
```

