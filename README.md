# OOP_DB_WSB

## TODO

### Zadanie 1.
Diagram:
![Diagram](tasks/task1/diagram-1.png)

- [x] Zaimplementowano klasy reprezentujące domenę biznesową przedstawioną na diagramie.
- [x] W każdej klasie zaimplementowano potrzebny konstruktor.
- [x] Każda klasa ma wszystkie wymagane pola pozwalające przechować wszystkie wymagane dane.
- [x] Każda klasa ma wymagane poprawnie zaimplementowane metody.
- [x] Demo.
- [x] Przesłanie zadania.

### Zadanie 2.
Diagram:
![Diagram](tasks/task2/diagram-2.png)

- [x] *poza poleceniem*: Przygotowanie `devcontainer`.
- [x] *poza poleceniem*: Dodanie `clang-format` i jego konfiguracji.
- [x] *poza poleceniem*: Dodanie wrapperów `make` dla ujednolicenia CLI.
- [x] Implementacja `class SpecialClient extends Client`.
- [x] Implementacja `abstract class SpecialService`.
- [x] Implementacja `class TimeService extends SpecialService`.
- [x] Implementacja `class LuggageService extends SpecialService`.
- [x] Implementacja dodatkowych metod `class TimeService` i `class LuggageService`
- [x] Implementacja `class Hotel` przechowującego kolekcje
- [x] *poza poleceniem*: implementacja metod `*.prettyPrint*()`.
- [x] Wykorzystanie odpowiednich kolekcji.
- [x] Wykorzystanie polimorfizmu.
- [x] Demo.
- [x] Przesłanie zadania.

### Zadanie 3.
Diagram:
![Diagram](tasks/task3/diagram-3.png)

- [ ] *poza poleceniem*: Dodanie narzędzi do statycznej analizy kodu.
- [ ] *poza poleceniem*: Wykorzystanie `lombok`.
  w celu zwiększenia czytelności i uniknięcia potencjalnych błędów potencjalnych błędów.
- [ ] *poza poleceniem*: `*.prettyPrint*()` zmienić na `@Override .toString()`.
- [ ] *poza poleceniem*: w `class Hotel` przechowywać dane w kolekcjach typu `key : value`,
  gdzie `key` to `id` dodawanego obiektu, najlepiej `add/get/remove` o `O(1)`,
  ułatwi to implementację logiki nowych metod z `interface HotelCapability`.
- [x] Dodanie (pliki jako załączniki w zadaniu) `class *Exception` do `pl.wsb.hotel.exceptions`.
  **Bez modyfikacji pliku (w tym pominięcie podczas formatowania)!**
- [ ] Dodanie (plik jako załącznik w zadaniu) `interface HotelCapability` do `pl.wsb.hotel`.
  **Bez modyfikacji pliku (w tym pominięcie podczas formatowania)!**
- [ ] Implementacja `class Hotel implements HotelCapability`.
  - [ ] `addClient(firstName, lastName, birthDate)` - tworzy nowego klienta, dodaje go do `Hotel`
    i zwraca jego `clientId`.
  - [ ] `getClientFullName(clientId)` - zwraca pełne imię i nazwisko klienta o danym `clientId`.
  - [ ] `getNumberOfUnderageClients()` - zwraca liczbę niepełnoletnich klientów.
  - [ ] `addRoom(area, floor, hasKingSizeBed, description)` - jak `addClient()`.
  - [ ] `getRoomArea(roomId)` - jak `getClientFullName(clientId)`.
  - [ ] `getNumberOfRoomsWithKingSizeBed(floor)`-
    zwraca liczbę pokoi z łóżkiem king size na podanym piętrze.
  - [ ] `addNewReservation(clientId, roomId, date)` - tworzy nową rezerwację
    dla istniejącego klienta o `clientId`
    i pokoju `roomId` na podaną datę `date` oraz zwraca `reservationId`.
    - [ ] Jeśli klient o podanym `clientId` nie istnieje - `throw ClientNotFoundException`.
    - [ ] Jeśli pokój o podanym `roomId` nie istnieje - `throw RoomNotFoundException`.
    - [ ] Jeśli pokój o podanym `roomId` jest już zajęty - `throw RoomReservedException`.
  - [ ] `confirmReservation(reservationId)` - potwierdza rezerwację o danym `reservationId`
    i zwraca go.
    - [ ] Jeśli rezerwacja o podanym `reservationId` nie istnieje -
      `throw ReservationNotFoundException`.
  - [ ] `isRoomReserved(roomId, date)` - zwraca `boolean` czy pokój jest zarezerwowany danego dnia.
    - [ ] Jeśli pokój o podanym `roomId` nie istnieje - `throw RoomNotFoundException`.
  - [ ] `getNumberOfUnconfirmedReservations(date)` -
    zwraca liczbę niepotwierdzonych rezerwacji na podany dzień `date`.
  - [ ] `getRoomIdsReservedByClient(clientId)` -
    zwraca kolekcję unikalnych (bez powtórzeń) `roomId` pokoi,
    które kiedykolwiek zostały zarezerwowane przez klienta o danym `clientId`.
- [x] Dodanie dependency wymaganych do unit testów do `pom.xml`.
- [ ] Implementacja unit testów metod `class Hotel` z użyciem podejścia *given, when, then*.
- [ ] Implementacja unit testów pozostałych klas.
  (zgodnie z zasadami dobrego unit testowania nie testuje się DTOs - dopytać).
- [ ] Pokrycie testów minimum 75%.
- [ ] Demo.
- [ ] Przesłanie zadania.
