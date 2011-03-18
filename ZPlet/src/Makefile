#JCFLAGS = -target 1.1 #(is default)

zplet:
	javac $(JCFLAGS) -sourcepath $(PWD) -d ../classes russotto/zplet/Zplet.java

zjapp:
	javac $(JCFLAGS) -sourcepath $(PWD) -d ../classes russotto/zplet/ZJApp.java

clean:
	find ../classes -type f -exec rm "{}" \;	
	find . -name "*~" -exec rm "{}" \;
