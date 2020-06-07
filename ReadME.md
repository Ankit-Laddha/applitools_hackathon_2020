## Applitools Hackathon 2020

#### <ins>**# Details**</ins>

```text
 Website under test: 
    v1: https://demo.applitools.com/gridHackathonV1.html
    v2: https://demo.applitools.com/gridHackathonV2.html
 Tool: Selenium Webdriver
 Language: Kotlin    
 Test Runner: TestNG
```
 Reporting: see [open report section](#open-reports) 
  
--------------------------
    
#### <ins>**# Setup / Pre-requisite**</ins>
 
  * Download following browsers on your local for our traditional tests: 
  
    ```Chrome V80+, Firefox v75.0+, Latest Edge Chromium```
 	
  * There is no need to configure drivers, framework will detect your browser version and download compatible driver to
  run the tests automatically
  
  * Set Applitools API Key environment variable
    - For unix shells:          
       ```export APPLITOOLS_API_KEY='YOUR_API_KEY'```
                   
    - For windows:     
        ```setx APPLITOOLS_API_KEY='YOUR_API_KEY'```
    
--------------------------   

#### <ins>**# Getting Started**</ins>
* Clone the repository

    ```bash
    git clone git@github.com:Ankit-Laddha/applitools_hackathon_2020.git
    ```    
* Modern tests are placed in package [here: click to open](src/test/kotlin/io/ankitladdha/aitests/tests/modern)
    * Tests for [v1](src/test/kotlin/io/ankitladdha/aitests/tests/modern/ModernTestsV1.kt) and [v2](src/test/kotlin/io/ankitladdha/aitests/tests/modern/ModernTestsV2.kt) version are identical. Hence I have disabled tests in V2.

* Traditional tests are placed in package [here: click to open](src/test/kotlin/io/ankitladdha/aitests/tests/traditional)
    * Tests for [v1](src/test/kotlin/io/ankitladdha/aitests/tests/traditional/TraditionalTestsV1.kt) and [v2](src/test/kotlin/io/ankitladdha/aitests/tests/modern/ModernTestsV2.kt) version are also identical.
    Hence I have disabled tests in V2.
    
    * <ins>**Important:**</ins> I have tried to make v1 tests very exhaustive. For few locator differences present in v2 version, I have highlighted them with a comment in the v1 tests  
       

-------------------------- 
#### <ins>**# How to execute**</ins>

 * We will use maven commands to trigger our tests.
 
    - To run modern tests against App version v1
        ```bash
        ./maven_runner.sh version="v1" tests="modern"
        ```
    - To run modern tests against App version v2
        ```bash
        ./maven_runner.sh version="v2" tests="modern"
        ```
    - To run traditional tests against App version v1
        ```bash
        ./maven_runner.sh version="v1" tests="traditional"
        ```
    - To run traditional tests against App version v2
        ```bash
        ./maven_runner.sh version="v2" tests="traditional"
        ```
      
--------------------------  
#### <ins>**#Open Reports**</ins>

 * For Modern tests report, go to [Applitools Dashboard](https://eyes.applitools.com/app/test-results/00000251810821313255/)
 * For Traditional tests 
    * Report for run against v1, open [Traditional-v1-TestResults.txt](Traditional-v1-TestResults.txt) 
    * Report for run against v2, open [Traditional-v2-TestResults.txt](Traditional-v2-TestResults.txt)
    * For Html report, open allure report using below command
        ```bash
        allure serve target/allure-results  
        ```
    * You can see the sample snapshot of html report by clicking [here](html-report-snapshot.png)
      
