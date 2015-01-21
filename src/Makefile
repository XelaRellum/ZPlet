#JCFLAGS = -target 1.1 #(is default)

all: zplet zjapp

../classes:
	mkdir -p ../classes

zplet: ../classes
	javac $(JCFLAGS) -sourcepath $(PWD)/main/java -d ../classes main/java/org/zplet/Zplet.java

zjapp: ../classes
	javac $(JCFLAGS) -sourcepath $(PWD)/main/java -d ../classes main/java/org/zplet/ZJApp.java

clean:
	find ../classes -type f -exec rm "{}" \;	
	find . -name "*~" -exec rm "{}" \;
