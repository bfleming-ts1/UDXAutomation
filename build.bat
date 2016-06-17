javac^
 -cp "C:\eclipse\selenium-2.53.0\selenium-java-2.53.0.jar;C:\eclipse\selenium-2.53.0\libs\*"^
 src\automationFramework\*.java^
 -d classes\
jar cvfe udxautomator.jar automationFramework.BasicTestCase classes\automationFramework\*.class
java -cp . automationFramework.BasicTestCase