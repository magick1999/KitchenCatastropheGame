# 2.1 - Source code
Contains your application source code (the .java files and other resources).

## To compile and run the code:
1. Go to the "src" folder and run `dir /s /B *.java > sources.txt`
2. `javac -cp "C:\Program Files (x86)\Java\jre7\lib\jfxrt.jar" -d .. @sources.txt`
3. `java -cp "C:\Program Files (x86)\Java\jre7\lib\jfxrt.jar";.. group44.Main`

### 