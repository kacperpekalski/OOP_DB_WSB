PROJECT_PATH := Hotel

format:
	find $(PROJECT_PATH) -type f -name "*.java" \
	| xargs \
	clang-format -i

clean:
	mvn -f $(PROJECT_PATH) clean

compile:
	mvn -f $(PROJECT_PATH) compile

test:
	mvn -f $(PROJECT_PATH) test

package:
	mvn -f $(PROJECT_PATH) package

run:
	java -cp $(PROJECT_PATH)/target/hotel-1.0.0.jar pl.wsb.hotel.Main

all:
	make clean
# the `make package` will invoke all required steps
	make package
	make run

.PHONY:
	format \
	clean \
	compile \
	test \
	package \
	run \
	all
