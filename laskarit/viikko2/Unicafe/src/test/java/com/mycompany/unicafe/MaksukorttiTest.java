package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(100);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoOnOikein() {
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 2.0", kortti.toString());
    }

    @Test
    public void saldoVäheneeOikein() {
        kortti.otaRahaa(50);
        assertEquals("saldo: 0.50", kortti.toString());
    }

    @Test
    public void negatiivinenEiVähennäSaldoa() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 1.0", kortti.toString());
    }

}
