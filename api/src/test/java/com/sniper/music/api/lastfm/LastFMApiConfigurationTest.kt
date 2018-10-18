package com.sniper.music.api.lastfm

import org.junit.Test

import org.junit.Assert.*

class LastFMApiConfigurationTest {

    @Test
    fun `test LastFM API configuration has correct base URL for debug builds`() {
        //given
        val tested = LastFMApiConfiguration(true)
        val expected = "http://ws.audioscrobbler.com/2.0/"
        //when
        val result = tested.baseURL
        //test
        assertEquals(expected, result)
    }
    @Test
    fun `test LastFM API configuration has correct base URL for production builds`() {
        //given
        val tested = LastFMApiConfiguration(false)
        val expected = "http://ws.audioscrobbler.com/2.0/"
        //when
        val result = tested.baseURL
        //test
        assertEquals(expected, result)
    }

    @Test
    fun `test LastFM API configuration has correct certificate pinners`() {
        //given
        val tested = LastFMApiConfiguration(true)
        val expectedCertificate1 = "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA="
        val expectedCertificate2 = "sha256/BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB="

        //when
        val resultCertificate1 = tested.pinners[0]
        val resultCertificate2 = tested.pinners[1]
        //test
        assertEquals(expectedCertificate1, resultCertificate1)
        assertEquals(expectedCertificate2, resultCertificate2)
    }
}