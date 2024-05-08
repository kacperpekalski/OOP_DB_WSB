PROJECT_PATH := Hotel
HOTEL_SOURCE_PATH := $(PROJECT_PATH)/src/main/java/pl/wsb/hotel

format:
	find $(PROJECT_PATH) \
		-type f \
		\( -path $(HOTEL_SOURCE_PATH)/exceptions/ClientNotFoundException.java \
			-o -path $(HOTEL_SOURCE_PATH)/exceptions/ReservationNotFoundException.java \
			-o -path $(HOTEL_SOURCE_PATH)/exceptions/RoomNotFoundException.java \
			-o -path $(HOTEL_SOURCE_PATH)/exceptions/RoomReservedException.java \
			-o -path $(HOTEL_SOURCE_PATH)/HotelCapability.java \) \
			-prune \
		-o -name "*.java"	\
		-print \
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
	make format
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
