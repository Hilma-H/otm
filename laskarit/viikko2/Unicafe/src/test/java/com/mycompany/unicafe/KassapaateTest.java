package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    public KassapaateTest() {
    }
    Kassapaate ka;
    Maksukortti k;
    Maksukortti kneg;

    @Before
    public void setUp() {
        ka = new Kassapaate();
        k = new Maksukortti(1000);
        kneg = new Maksukortti(10);
    }

    @Test
    public void kassassaRahaaOikein() {
        assertEquals(100000, ka.kassassaRahaa());
    }

    @Test
    public void kassaKasvaaVaihtorahatOikeinEdullinen() {
        assertEquals(60, ka.syoEdullisesti(300));
        assertEquals(100240, ka.kassassaRahaa());
        assertEquals(1, ka.edullisiaLounaitaMyyty());
    }

    @Test
    public void kassaKasvaaVaihtorahatOikeinMaukas() {
        assertEquals(100, ka.syoMaukkaasti(500));
        assertEquals(100400, ka.kassassaRahaa());
        assertEquals(1, ka.maukkaitaLounaitaMyyty());
    }

    @Test
    public void eiTarpeeksiRahaaEdullinen() {
        assertEquals(100, ka.syoEdullisesti(100));
        assertEquals(100000, ka.kassassaRahaa());
        assertEquals(0, ka.edullisiaLounaitaMyyty());
    }

    @Test
    public void eiTarpeeksiRahaaMaukas() {
        assertEquals(300, ka.syoMaukkaasti(300));
        assertEquals(100000, ka.kassassaRahaa());
        assertEquals(0, ka.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maksukorttiToimiiEdullinen() {
        assertTrue(ka.syoEdullisesti(k));
        assertEquals(100000, ka.kassassaRahaa());
        assertEquals(1, ka.edullisiaLounaitaMyyty());
    }

    @Test
    public void maksukorttiToimiiMaukas() {
        assertTrue(ka.syoMaukkaasti(k));
        assertEquals(100000, ka.kassassaRahaa());
        assertEquals(1, ka.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maksukorttiEiTarpeeksiRahaaEdullinen() {
        assertFalse(ka.syoEdullisesti(kneg));
        assertEquals(100000, ka.kassassaRahaa());
        assertEquals(0, ka.edullisiaLounaitaMyyty());
    }

    @Test
    public void maksukorttiEiTarpeeksiRahaaMaukas() {
        assertFalse(ka.syoMaukkaasti(kneg));
        assertEquals(100000, ka.kassassaRahaa());
        assertEquals(0, ka.maukkaitaLounaitaMyyty());
    }

    @Test
    public void KortinLatausToimii() {
        ka.lataaRahaaKortille(k, 100);
        assertEquals(1100, k.saldo());
        assertEquals(100100, ka.kassassaRahaa());

    }

    @Test
    public void KortinLatausNegatiivinen() {
        ka.lataaRahaaKortille(k, -100);
        assertEquals(1000, k.saldo());
        assertEquals(100000, ka.kassassaRahaa());

    }

    @Test
    public void hello() {
    }
}
