
----------------------------------------------------------------------------------------------------------------
#To execute using bat file perform the following steps:
----------------------------------------------------------------------------------------------------------------
1. Copy the complete location of the file 
	e.g.  C:\Users\HP\workspace_new\identifyCourse


2. Right click on bat file

3. Click on edit

4. Replace the file location with new file location without altering any command

5. Save the file

6. Now double click on run.bat file to execute the program

----------------------------------------------------------------------------------------------------------------
#To change browser options perform the following steps:
----------------------------------------------------------------------------------------------------------------

1.  Open project folder and locate to:
    identifyCourse/src\test\java\tests
    
2.  Open courseSearch.java in any editor to edit the file.

3.  Go to Line 24 and change the name of the browser.
        
        For example: Before:
                        driver = getDriver("Chrome");
                     
                     After:
                        driver = getDriver("firefox");

NOTE: Available choices for browser are:
                                    1.  chrome
                                    2.  firefox
                                    3.  opera
                               
BROWSER NAMES ARE CASE INSENSITIVE



