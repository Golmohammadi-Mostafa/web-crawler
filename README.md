# web-crawler
jdk 1.8
maven,Sqlite,jsoup
Spring boot
###################################
1- run this command for clean&package :
mvn clean package -DskipTests

2- At first, for finding all the links On Pages and save products into sqlite run this Test method :
testCrawling() in CrawlerSqliteApplicationTests class.

Fully executing this method may take several minutes
to store information in the database.
After some products are stored into the database you can run this method:testCrawledResult() to see the information

3-Run this method testCrawledResult() in CrawlerSqliteApplicationTests class  to display information of products 
 

