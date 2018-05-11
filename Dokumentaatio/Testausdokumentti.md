# Testaus
Testit on tehty JUnit:illa.  
Testit on tehty vain luokasta Exercise.  
Luokat Dao ja SportType eivät "tee" mitään, joten testien tekeminen tuntui turhalta.  
Luokat Database ja DiaryService käsittelevät pääosin tietokantaa ja useimmissa metodeissa on jo "throws (SQL)Exception" jolla virhetilanteita voi käsitellä.
# Asennus ja konfigurointi  
Tiedoston latausta on kokeiltu windows-koneella (,koska ainoa kone joka minulla on).  
# Toiminnallisuus
Toiminnallisuudet on testattu ja valitettavasti sovellus ei osaa käsitellä tyhjiä syötekenttiä.
