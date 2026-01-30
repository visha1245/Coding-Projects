CC=g++
CFLAGS=-Wall -g
LDFLAGS=
SOURCES=user.cpp transaction.cpp ledger.cpp linked_list.h main.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=Assignment1

all: $(SOURCES) $(EXECUTABLE)
	
$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o $(EXECUTABLE)
