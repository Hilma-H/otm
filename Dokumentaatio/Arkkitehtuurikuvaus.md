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
## Tallennus
Ohjelman harjoitukset tallennetaan (ohjelman luomaan) paikalliseen  tietokantaan. Tietokanta on nimeltään Harjoitus ja sen sarakkeet ovat muotoa:
(PK id integer, laji varchar(20), km float, kesto float, pvm integer)
## Päätoiminnallisuudet
Päätoiminnallisuudet kuvattu sekvenssikaavioilla.
 
![alttext](https://github.com/Hilma-H/otm/blob/master/Dokumentaatio/Harjoituksen%20luominen.png)
![alttext](https://github.com/Hilma-H/otm/blob/master/Dokumentaatio/Harjoituksen%20poistaminen.png)
## Puutteita/työn alla
- päivämäärän tallennus järkevämmin  
- kentät sallisivat myös tyhjäksi jättämisen (ainakin km)
## Heikkoudet
Käyttöliittymä kokonaan start:issa
