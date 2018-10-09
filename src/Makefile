#JCFLAGS = -target 1.1 #(is default)

all: zplet zjapp

../classes:
	mkdir -p ../classes

zplet: ../classes
	javac $(JCFLAGS) -sourcepath $(PWD)/main/java -d ../classes main/java/org/zplet/Zplet.java

zjapp: ../classes
	javac $(JCFLAGS) -sourcepath $(PWD)/main/java -d ../classes main/java/org/zplet/ZJApp.java

zplet.jar: zplet zjapp
	cd ../classes && jar cvfm ../$@ ../manifest.txt org/zplet/*.class org/zplet/*/*.class org/zplet/*/*/*.class $(EXTRAJARFILES)

clean:
	find ../classes -type f -exec rm "{}" \;	
	find . -name "*~" -exec rm "{}" \;
