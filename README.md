# Project details 


## Background
I am a manager of a music school in Melbourne, and the music school have about 300 students currently and a learning management system is require for both students, teachers and admins. In the market, there are some learning management systems, such as Blackboard, Moodle and Canvas. Many users of these system use tools from the system to create contents, which then hosted on an LMS. Basically these LMS allows teachers to create and edit course materials, post tasks online, track student’s learning progress. In addition, communication forms and learning timelines is available to both students and teachers. The most important leverage of LMS is that it can deliver learning content straight to learners, so that it is able to reach marginalised groups, allowing learners to access their progress and thus an effectiveness learning and teaching are achieved. 

However, these learning management system are mostly designed of institutions and schools, which have more than 1000 users. These system are currently not suitable for our music school in terms of its features and prices (For example, LMS from Moodle may charge more than $1000 per year for 1000 users, which we consider as too expensive).  Therefore, I decide to develop a learning management system for music school, and specifically for our music school. 

## The current management system 
The current management system that is being used for the music school is called AirTable, which is a cloud collaboration service. This system is a spreadsheet-database hybrid, with the features of a database but applied to a spreadsheet [1].  This system is relatively easy to use as everyone only need to put the information in each tables, including student information, lesson record, course information and attendances.  This system has been used for about 3 months and we found that there are many disadvantages.

Problems with the current system are many. First, the security. By using Airtable, the system has no user access control and this means everyone in the school, including teachers, admins and course advisers can access to all the information of the system. Many personal information, which are not intended to be viewed by teachers or course advisors, are easily being exposed. Second is the user friendly, the system is very much like a database, and there is no user interface, and therefore, many teachers dislike to use this management system because it is hard for them to insert or update information. They highly recommend to implement a more user friendly management system for the piano school.  The third problem with the current management system is that the services provided by the Airtable are limited. For example,  for the FREE standard, there are only five tables are allowed for each project, only 2GB attachment space per base, and the records for each base is only limited to 1200. With the growing of the data for the piano school, the FREE feature is not capable for the piano school. Therefore, the piano school has to upgrade to system to PRO edition, which may charge about $100 or even more per month.  Even though with upgrading the system to the PRO edition, the features provided by the Airtable may still not suitable for the piano school. For example, this management system is only design for admin and teachers but not for students, and the piano school intend to provide with student access. Also, some AI features such as course recommendation is not provided by the Airtable. With these reasons, the piano school decided to develop a new management system specifically for the school with a better software architecture that is easily able to extend.

## Scope
To develop a web base learning management system for the piano school for both students and teachers. 

Essential features: 
*For students:*
1. Know about their course
2. Know about their feedback from teachers
3. Know some basic information about their teachers.
*For teachers:*
1. Get the list of her students.
2. Know the feedback history and course progress of her students
3. Make feedback to her students 
*For both* 
1. Login and Logout, every user has to log in before they actually being able to use the system.





## Developing method
There are many developing method can be used in a software developing project, including waterfall model, V- model, incremental model, spiral model, prototype model, and the most famous model, the Agile model. In terms of this project, I considered waterfall model, V- model and Agile for a while, and finally chose V - model, and here are my reasons. [3]

The V - model, the  Verification and Validation model. It is similar to waterfall model, but this model allows me to develop base on my test plan, and therefore increase the reliability of the system. The waterfall model is too simple and the test is at the end of the system implemented, which can add many uncertainty to the overall system, and it is very difficult to go back and change something that was not well-thought out in the concept stage, introducing  high amounts of risk and uncertainty.  However, the Agile model, it is suitable for new changes are needed to be implemented,  and it is recommended for the senior programmers with experienced resources, which is apparently not suitable for me. So, the V - model is the most suitable for me in this management system design.

1. It is relatively simple and easy to use
This is a one person developing system and the the scope of the management system is relatively simple and clear without any uncertain. 
Considering the V - model works well for small projects where requirement are easily understood, it is a good way to apply the V - model developing method.

2. Higher chance of success over the waterfall model
At the beginning, my plan is to apply  waterfall model as by developing method, but as I researched and compared these two models, I decide to change to V - model due to its reliability. In this way, the testing designing happens well before coding, and the also the defects can be found at early stage, avoiding the downward flow of the defects that may happen in the waterfall developing model. 

Therefore, the project will follow the basic requirement,  and then the high - level design with integration test plan, and then the low - level design , where the actual software components are designed with actual logic for each and every component of the piano school’s management system (the component tests are created in this phase as well). After all these design, it is followed by the implementation and the coding phrase, where the unit testing is being created and used by me to test the coding. 




## High level architecture
The overall architecture is base on The C4 model [2]. The following are three high level architecture diagram: system context diagram, container diagram, component diagram.

##### System Context diagram

Base on the scope,  a system context diagram has been shown in the figure, there are two majority user: student and teacher. Both of the are using one system: Learning management system, and there is no other system interaction. 

##### Container diagram

This is the container diagram. This diagram shows the high-level shape of the software architecture and how responsibilities are distributed across it.  It shown that front end and back end are separated. In terms of the front end, my technique solution is to use a front end server that handles with the Javascript and this is to be done by applying ReactJS framework. 

The reason that I apply this framework is that it can allows me to create reusable UI components,  and to create web applications that can change data without reloading the page. It is fast, scalable and simple. Further, ReactJS applications are easy to test, it will be helpful to test the management system. In the future, if the piano school wants to add more advanced features, it can be relatively  simple and easy due to the application of ReactJS. 

In terms of the back end, I chose Java as my server language. There are few reasons why I chose Java.  The first is its good at scalability.  Second is its cross-platform usage. The system can be used on all platforms that have a corresponding JVM. Third is the ecosystem. With the IntellIJ IDE, Maven and Spring framework, it is much easier to develop and maintain this management system.

As for the database, I use MySQL, which is fast, reliable and stable open source database management system.



##### Component diagram

This overall backend architecture are built by three main layers. Controller layer, service layer and the repository layer. The student controller’s main tasks are to handle student’s register, login, logout, find and reset password, get feedback, get teacher information, get course information and update information. The teacher controller’s main tasks are register, login, logout, find and reset password, get student enrolment information, get student list, get student number, get feedback list and make feedback. The service layer’s majority job is to provide service for both students and teachers, where this layer receive datasource from repository, the class for visit domain object, and the method names are compliant with Spring Data naming convention so this interface can easily extended. Each repository represent a domain class for the database. 

##### development environment 
The database is MySQL which is installed in my laptop with the communication port 3306. The backend design is implemented by java web application. The standard java library is java 1.8 and the server is apache tomcat. The front end server using port 3000 for communication. The browser is Chrome. 




## Detail architecture 
##### Database design

There are 8 tables in my database in MySQL. Basically, three are for the users: student table, teacher table, and admin table. These three tables are used for record the basic information of each user in different rules, including email, password, full name, phone number and so on. These three table are static data, in other words, they are not supposed to be updated frequently once they are created, and they are used majority for read.  The other five tables are enrolment, course, course and teacher (because of many to many ),  feedback and lessons. Student will enrol a course, where enrolment table will store the enrolment information including student, teacher and course. In this way, a student can enrol different course and with different teachers. Course table references to a particular  subject, such as beginner course for classic piano, beginner course for pop piano, or advanced course for classic piano, ect. Each course has many classes, in other words, it has many chapters, which are recorded in the lesson table. In the lesson table, it has lesson id, course id, lesson type and lesson code.  In terms of  the relationships with teacher and course, a teacher will teach many course and a course will be taught by many teachers, so it is a many to many relationship, and therefore, a course_teacher table is created to handle with this situation by recording the course id and teacher id. As for the feedback, each enrolment has many feedback, because teacher should make feedback to students many time during the course.  For every table in this database, it has two common columns: create_date and last_edit_date, they are used for tracking the log. Therefore, when something wrong happen to the system, it is easier to tack the history.  Here are the ER diagram for the database design.



##### class diagram for the back end 


By using Spring framework, back end design is becoming simple, and the main task is to implement the business logic in controller layer and service layer. The Spring will automatically transform the data into JSON type and also by extending JpaRepository [4], the Spring will generate MySQL query automatically.  Here, the class mainly handles with the business logic, including register, login and logout for all users, make feedback and get feedback for teachers, ect.

## Quality assurance
### Test cases
###### unit testing 
In the management system, I create some unit tests sets for my back end design, ensuring the classes that I created can perform correctly, insuring the performance of the system as a whole.

###### system integration testing 
1. Backend integration with database
The whole project working with the help with the JpaRepository, which can automatically generate MySQL query. The communication is testing through the service layer. According to the testing, each time the service call the database for information, the query is generated correctly.

2. Backend integration with backend
The system integration testing is tested by using Postman to ensure each API is working correctly between the back end and the front end. These are the RESTFUL API communicating with JSON format through TCP connection. According to the testing, every API can work correctly with the front end. 

###### usability and system testing 
After the system was developed, the manager of the piano school took a test for the whole system. The manager belief that the system has met the scope and it was good and easy to use.  They manger suggest me to add a new feature that enable him to see the attendances of every student, so that  he can know and remind a particular student to come to the piano school to take lesson

Teachers from the piano school used the system and they believe this system is easier to use compared with the previous system - Airtable, because they can access their students information quickly and easily because of the user interface of the new system. However, they suggest that the system should provide a page split feature so that when the data is growing and become more, they can view student’s information easily. 

In addition, I ask some students from the piano to register an account to test the system from student perspective.  Their feedback is that the current system is good and friendly to use, but they suggest that more features needed to be added in this learning management system, rather than only view their course and feedback from their teachers.  For example, they suggest to have a competition feature, where they can see the progress of other students where they know their ranks in the school. They also suggest that course recommendation feature is required, and in this they can know the next stage of their study and this can help them to set their goal in learning piano.

### security
As the system need to access the information of different users, including its  personal information, course information, and feedback, the system has be to meet the security standard ensuring that no other users access the management system without permission. To implement the security patten, there are three majority parts.

First is the password encoding.  The users’ password were encoded with the MD5 message-digest algorithm. With this coding method, the password would not be able to accessed by others rather than the user himself. However, there are still some methods to decode this MD5, a more appropriate method to encode the password is to add salt (random data) that is used as an additional input. 

Second is the token. When a user forget his password, and wants to change by answering the preset question. He will receive a token that can last for 30 minutes to change his password, in other words, he has only 30 minutes to reset the password. This has ensured the security level of the system.

Third is the session pattern. By using server session pattern, the user can only access his data and information with a unique session id. For example, when a teacher login, the server session will remember this teacher’s id, and  this teacher can only get her student list and make feedback to her student, rather than get information and making feedback from others. Also, with the help of the server session pattern, the student can only view the feedback but cannot update or delete feedback from the management system.


## Deployment plan
The overall code will be uploaded to GitHub where people can access to the source code and examine the code. In addition, the overall project is going to be deployed in the Heroku [6], which is a cloud platform where I can run the management system here with a unique domain.  As for the deployment of the database, it would be deployed in AWS. The problem associated with the AWS is that it can be a bit slow to querying the data on AWS cloud. 

## Lesson learned
Throughout this system management software project, I did learn many software engineering skills.

1. Selection the development method. Software engineering is not “hacking” where a clear plan and development method is essential . With the help of the V - model and the test cases driven, I can develop this management system in time.
2. Think about the domain model first. The database design is of the most importance in the overall system design. During this project, I did changed the design of database design for three time, which had wasted me some time if I better think about how the database model should look like.
3. Using popular framework. The popular framework is a good tool that can enable you to focus on the business logic. In my system, I applied Spring framework, which would save me time on writing queries and therefore, I can focus on my serves layer and controller layer. A good frame work can also decrease the probability of making mistakes. For example, the spring can deal with the concurrency problem of the system. 
4. Focus on architecture first. A good architecture design of the system can greatly decrease the coding time and increase the extendibility of the system. Previously, I focus on the business log rather than looking the big picture and design the architecture, this made me to rebuilt by project again and the time had been wasted.
5. Communication. The usability and system test really depend on the feedback of different customers, a better communication skill for software developer can help to find out more useful feedback from different perspective. For example,  due to sufficient communication, I know that student wants cause recommendation features and competition features in the system. Without communication with students, I can hardly think about these features.


## Reflection
The management system has meet the initial scope and requirement by the piano school. It allows student to view their course and feedback, and teachers to update, view and make feedback and view the progress of their students.

In addition, the new management system provide with better security than the previous system developed on the air table, due to the use access control feature, where a student or teacher has to log in first before doing any action. Moreover, a better user interface was developed compared with the previous management system, and this was confirmed by teachers and students from the usability and system test.  With a good architecture, this management system is able to be extended in the future to meet various requirements of the piano school in the future.  Finally, this system has been accepted by the piano school. 

## Future work
As discussed in the previous sections, there are some more features that will be implemented in the management project to meet the requirement of the piano school. The first feature is the page split for teachers to view students study history from feedback lists. Second is the course recommendation feature for students who want to enrol other piano courses. The third is the course reminder, which enable teachers to remind their students to go to the class.

























