# Automation_project
First small project for test automation using java, selenium, testng

To set up project you need to download:
* java 8
* gradle 7.1.1
* Chrome browser version 92.0.4515 
* IntelliJ IDEA 2021 or later
  
Optional:
* If you use Mac you need to download appropriate Chromedriver from <a href="https://chromedriver.storage.googleapis.com/index.html?path=92.0.4515.107/" title="here">here<a/> and replace with it the driver in resources folder. Also you need to delete ".exe" part at 25 line in src/main/web/Web.java

To run project:
* Open project in IDEA
* Go to Run->Egit Configurations
* Add new TestNG configuration
* Set 'Test kind' as 'Suite' 
* Choose /src/test/testng.xml in 'Suite'