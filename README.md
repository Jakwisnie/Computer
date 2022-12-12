# Computer
Aplikacja była pisana w środowisku InteliJIdea
Do uruchomienia możliwe że bedzie trzeba wygenerować baze danych mysql o nazwie localdatabase
Do uruchomienia wykorzystywany był Tomcat 9.0.68 w konfiguracji przed uruchomieniem ustawilem build 'Computer:war exploded' artifact
Aplikacja ma za zadanie obliczać wartość pln dla komputerów na podstawie zadanego api : http://api.nbp.pl
Mozliwe ze pojawi sie data o jeden dzien do tylu po dodaniu jest to spowodowane prawdopodobnie zla wersja Mysql 

Większość działań znajduje się w Control.java 
W zaleznosci od potrzebnego dzialania uzytkownik inicjuje przy pomocy html inna funkcje
/add - formularz do recznego dodadania do bazy komputera
/addRandom - dodanie losowego komputera do bazy danych i pliku xml
/view - wyswietla wszystkie komputery z bazy danych
/viewbydateasc - wyswietla wszystkie komputery z bazy danych w kolejnosci zaleznej od daty komputera rosnaco
/viewbydatedesc - wyswietla wszystkie komputery z bazy danych w kolejnosci zaleznej od daty komputera malejaco
/viewbynameasc - wyswietla wszystkie komputery z bazy danych w kolejnosci zaleznej od nazwy komputera rosnaco
/viewbynamedesc - wyswietla wszystkie komputery z bazy danych w kolejnosci zaleznej od nazwy komputera malejaco
/ & /home- wyswietla przyciski przenoszace do interesujacych nasz widokow 
/result - uruchamia sie domyslnie po uruchomieniu i uzupelnieniu formularza z adresu "/add"
/delete/{id} - usuniecie komputera o {id} z bazy danych
/update/{id} - formularz do edycji komputera o {id} z bazy danych
/udpdateAfter - wyswietla jaki komputer zostal zaktualizowany, w tle zachodzi cala edycja komputera i wstawienie do bazy danych
funkcja setPLN - wysyła zapytanie do api oraz oblicza wartosc pln na podstawie otrzymanego kursu
funkcja add - zapisuje w bazie danych obiekt komputer

Pozostałe:
ComputerData.java odpowiada za stworzenie randomowego komputera
Folder webapp oraz pliki jsp znajdujace sie w nim odpowiadaja za wyglad zmapowanych widokow z Control.java
