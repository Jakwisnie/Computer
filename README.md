# Computer
Do uruchomienia możliwe że bedzie trzeba wygenerować baze danych mysql o nazwie localdatabase
Do uruchomienia wykorzystywany był Tomcat 9.0.68 w konfiguracji przed uruchomieniem ustawilem build 'Computer:war exploded' artifact
Aplikacja ma za zadanie obliczać wartość pln dla komputerów na podstawie zadanego api : http://api.nbp.pl

Większość działań znajduje się w Control.java 
W zaleznosci od potrzebnego dzialania uzytkownik inicjuje przy pomocy html inna funkcje
/addRandom - dodanie losowego komputera do bazy danych i pliku xml
/view - wyswietla wszystkie komputery z bazy danych
/viewbydateasc - wyswietla wszystkie komputery z bazy danychw kolejnosci zaleznej od daty komputera rosnaco
/viewbydatedesc - wyswietla wszystkie komputery z bazy danych w kolejnosci zaleznej od daty komputera malejaco
/viewbynameasc - wyswietla wszystkie komputery z bazy danych w kolejnosci zaleznej od nazwy komputera rosnaco
/viewbynamedesc - wyswietla wszystkie komputery z bazy danych w kolejnosci zaleznej od nazwy komputera malejaco
/ - uruchamia sie domyslnie , jest to formularz gdzie podajemy nazwe oraz cene usd 
/result - uruchamia sie domyslnie po uruchomieniu i uzupelnieniu formularza z adresu "/"
funkcja setPLN - wysyła zapytanie do api oraz oblicza wartosc pln na podstawie otrzymanego kursu
funkcja serializeToXML - zapisuje obiekt computer w pliku xml
funkcja add - zapisuje w bazie danych obiekt computer

Pozostałe:
ComputerData.java odpowiada za stworzenie randomowego komputera
Folder webapp oraz pliki jsp znajdujace sie w nim odpowiadaja za wyglad zmapowanych widokow z Control.java
