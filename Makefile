all: hello_alice
clean:
	$(RM) hello_alice
hello_alice: main.c
	$(CC) -o $@ main.c

