# Arkkitehtuurikuvaus
## Rakenne
Ohjelma on kolmessa pakkauksessa: **ui** toteuteuttaa JavaFX käyttöliittymän, **domain** sisältää sovelluslogiikan ja **dao** tallennuksen.
## Käyttöliittymä
Käytössä on kolme erilaista näkymää.  
- "Uusi Harjoitus", johon täytetään tallennettavan harjoituksen tiedot
- "Harjoitukseni", johon koottu kaikki tallennetut harjoitukset
- "Tilastoja" kertoo yksinkertaista tilastotietoa harjoituksesta  

Näkymät toteutettu scene-olioilla, jotka ovat yksi kerrallaan toiminnassa. Käyttöliittymä on rakennettu luokassa diary.ui.Main ja siinä on pyritty käyttämään vain DiaryServicen metodeita ja näin eristämään käyttöliittymä muusta ohjelmasta.  
## Sovelluslogiikka
Sovelluslogiikka sisältää luokat Exercise ja DiaryService sekä enum-luokan SportType.
Exercise vastaa exercise-oliosta, joka on oikeastaan koko ohjelman pohja.
DiaryService on linkki käyttöliittymän ja datan tallennuksen välissä.

**Pakkauskaavio**

![alt 
text](https://github.com/Hilma-H/otm/blob/master/Dokumentaatio/Pakkauskaavio%20-%20(1).jpg) 
tähän kuva pakkausrakenteesta
## Tallennus
Ohjelman harjoitukset tallennetaan (ohjelman luomaan) paikalliseen  tietokantaan. Tietokanta on nimeltään Harjoitus ja sen sarakkeet ovat muotoa:
(PK id integer, laji varchar(20), km float, kesto float, pvm integer)
## Päätoiminnallisuudet
Päätoiminnallisuudet kuvattu sekvenssikaavioilla.

### Harjoituksen luominen (kuva)
### Tilastot (kuva)
## Puutteita/työn alla
- päivämäärän tallennus järkevämmin  
- kentät sallisivat myös tyhjäksi jättämisen (ainakin km)  
- harjoitusten poistaminen yksittäin
- kenttien täytön seuraaminen joka varmistaisi, että täytetty oikein (double/integer)
## Heikkoudet
Käyttöliittymä kokonaan start:issa
