CC=g++
CFLAGS=-Wall -g
LDFLAGS=
SOURCES= queue.h customer.cpp teller.cpp bank_branch.cpp simulation.cpp main.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=Simulation

all: $(SOURCES) $(EXECUTABLE)
	
$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o $(EXECUTABLE)
