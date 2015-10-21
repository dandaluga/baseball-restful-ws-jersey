A Restful application using the Jersey framework.

* Start up the jetty server: mvn jetty:run
* Make an HTTP request through a browser (or Postman, a Chrome plugin) or use the JUnit tests that are included. Note that the JUnit tests require the container to be running.

URL CRUD Examples:

* Read   = Get      http://localhost:8080/webapi/boxscores (Return all boxscores)
* Read   = Get      http://localhost:8080/webapi/boxscores/1234 (Return a specific boxscore)
* Create = Post     http://localhost:8080/webapi/boxscores/boxscore
* Update = Put      http://localhost:8080/webapi/boxscores/1234 (Update existing record, need whole object)
* Update = Patch    http://localhost:8080/webapi/boxscores/1234 (Don't need to send the whole object, not in Jersey yet)
* Delete = Delete   http://localhost:8080/webapi/boxscores/1234

URL Search Examples:

* Read   = Get      http://localhost:8080/webapi/search/boxscores?description=name1&descrption=name2 (Search example with GET)
* Read   = Post     http://localhost:8080/webapi/search/boxscores 

Note that for a search, the Get style can cause alot of havoc in the code since you have to pass the get parameters along to each layer.
The Post style is not as Restful but will have much less impact on the code since the search parameters are submitted as Post parameters.
