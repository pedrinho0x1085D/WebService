# WebService
Server to support the functionalities of the Difficulty Rater App (https://github.com/pedrinho0x1085D/LEI_Rater)
![Java](http://tinycode.hk/wp-content/uploads/2015/01/java-logo-png-300x300.png)
![Weka](http://professorvirtual.org/wp-content/uploads/2015/04/weka.png)

There are three routes available to the app

* Get, ./hello -> Connection testing. Mainly used to verify the online status of the server
* Post, ./insert -> Used to insert data. Receives, via JSON, the list of records to be added, transforms them to Weka Instances and adds them to the general trainset.
* Post, ./classify -> Difficulty classification. Receives, via JSON, the list of geographical points of the track, user parameters, date, time and load. With these, calculates distances, speeds, aggregate distances and average speeds, generating the records in the same schema as the trainset. Creates a J48 tree, trained with the trainset available at the moment, classifies the generated records, obtaining a list where each element contains Starting Latitude, Starting Longitude, Ending Latitude, Ending Longitude, Difficulty; sending this list back to the app, via JSON.
