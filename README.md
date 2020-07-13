# ArgumentParser
If you have read the Chapter 14 of Uncle Bob's Clean Code, you probably will be interested in this repo.

- To read Uncle Bob's solution with syntax highlight, check here.
- To execute his solution against tests, run `./gradlew test -DargsClass=solution.unclebob.Args`.
- If you can't resist to implement your own solution, follow this. 

### How do I implement and test a solution?
Although you should practice TDD on your own, this repo provides decent amount of tests to valid the final program.

1. Implement your solution  
    1.1 Subclass `base.Args`  
    1.2 Subclass `base.ArgsException`  
    1.3 Make sure your Args class have a constructor conforming `(String schema, String[] args) throws ArgsException`   
2. Run `./gradlew test -DargsClass={package.yourClass}`

### How do I add more tests?
If you think some test cases are missing, feel free to create a PR and add them.
