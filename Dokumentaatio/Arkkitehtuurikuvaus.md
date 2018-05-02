# Arkkitehtuurikuvaus
## Rakenne
Ohjelma on kolmessa pakkauksessa: **ui** JavaFX käyttöliittymä, **domain** sovelluslogiikka ja **dao** tallennus.
## Käyttöliittymä
Käytössä on kolme erilaista näkymää, jotka toteutettu scene-olioilla jotka ovat yksi kerrallaan toiminassa.
## Sovelluslogiikka
Tietojen tallennuksen "välikappale" ja exercise olio
## Tallennus
Ohjelman harjoitukset tallennetaan (ohjelman luomaan) paikalliseen  tietokantaan. Tietokanta on nimeltään Harjoitus ja sen sarakkeet ovat muotoa:
(PK id integer, laji varchar(20), km float, kesto float, pvm integer)
## Päätoiminnallisuudet
### Harjoituksen luominen (kuva)
### Tilastot (kuva)
## Puutteita/työn alla
päivämäärän kivempi tallennus  
kentät sallivat tyhjät (ainakin km)  
harjoitusten poistaminen yksittäin
## Heikkoudet
Käyttöliittymä kokonaan start:issa
