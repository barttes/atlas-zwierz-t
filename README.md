# atlas-zwierząt

==============INFORMACJE WSTĘPNE O PROEJKCIE======================

Projekt zaliczeniowy na przedmiot "programowanie obiektowe". Projekt miał polegać na wykonaniu 
atlasu zwierząt katalogującego informacje nt. gatunków, rodzin i typów. Wyróżniał także szczególny rodzaj
gatunku -> krzyżówkę. 

Funkcjonalność aplikacji:
- dodawanie / usuwanie / modyfikacja informacji o elementach atlasu;
- dodawanie zdjęć do gatunku / krzyżówki;
- zapisywanie / odczytywanie atlasu do pliku za pomocą serializacji binarnej.
- zbiorcze wyświetlanie informacji o elementach atlasu.

Więcej informacji nt. wymagań projektowych: http://www.cs.put.poznan.pl/dbrzezinski/students.php


======================REALIZACJA PROJEKTU==========================

Projekt napisany z wykorzystaniem JavaFXML w środowisku NetBeans IDE 8.2. Projekt starałem się wykonać w oparciu
o wcześniej przygotowany schemat diagramów klas UML (o nazwie atlasZwierzat-uml.jpg). Komponenty graficzne zrealizowane
z użyciem aplikacji SceneBuilder.

=======================STRUKTURA PROJEKTU==========================

-> src					 - pliki źródłowe aplikacji
		->atlasapp   	 - główny program aplikacji uruchamiający główny kontroler "EdycjaWygladController.java"
		->model 		 - klasy modelu aplikacji
		->util			 - klasy pomocnicze (m.in. zapisywanie, odczytywanie z pliku ..)
		->viem			 - pliki wyglądów *.fxml
		
-> dist 				 - plik *.jar
-> nbproject			 - pliki konfiguracyjne środowiska NetBeans