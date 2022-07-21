package com.example.data.entity

import com.example.data.entity.home.response.HomeResponseEntityMapper
import com.example.data.factory.HomeResponseEntityFactory
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class HomeResponseEntityMapperTest {

    @Test
    fun `'toDomainHomeResponse()' 'with data model is not null' 'then return domain model same as data model'`() {
        //arrange
        val homeResponseEntity = HomeResponseEntityFactory.generateDataForHomeResponseEntity()

        //act
        val homeResponseDomain = HomeResponseEntityMapper.mapper.toDomain(homeResponseEntity)

        //assert
        Assert.assertNotNull(homeResponseDomain)
        assertEquals(homeResponseDomain.source, homeResponseDomain.source)
        assertEquals(homeResponseDomain.sortBy, homeResponseDomain.sortBy)
        assertEquals(homeResponseDomain.status, homeResponseDomain.status)
        assertEquals(homeResponseDomain.articles?.get(0)?.id, homeResponseDomain.articles?.get(0)?.id)
        assertEquals(homeResponseDomain.articles?.get(0)?.author, homeResponseDomain.articles?.get(0)?.author)
        assertEquals(homeResponseDomain.articles?.get(0)?.url, homeResponseDomain.articles?.get(0)?.url)
        assertEquals(homeResponseDomain.articles?.get(0)?.title, homeResponseDomain.articles?.get(0)?.title)
        assertEquals(homeResponseDomain.articles?.get(0)?.description, homeResponseDomain.articles?.get(0)?.description)
        assertEquals(homeResponseDomain.articles?.get(0)?.publishedAt, homeResponseDomain.articles?.get(0)?.publishedAt)
        assertEquals(homeResponseDomain.articles?.get(0)?.urlToImage, homeResponseDomain.articles?.get(0)?.urlToImage)

    }

    @Test
    fun `'toDomainHomeResponse()' 'with data model is null' 'then return domain model is null'`() {
        //arrange
        val homeResponseEntity = null

        //act
        val homeResponseDomain = HomeResponseEntityMapper.mapper.toDomain(homeResponseEntity)

        //assert
        Assert.assertNull(homeResponseDomain)
    }

    @Test
    fun ` 'fromDomainHomeResponse()' 'with domain not equal null' 'return data model same as domain model'`() {
        //arrange
        val homeResponseDomain = HomeResponseEntityFactory.generateDataForHomeResponseDomain()

        //act
        val homeResponseEntity = HomeResponseEntityMapper.mapper.fromDomain(homeResponseDomain)

        //assert
        Assert.assertNotNull(homeResponseEntity)
        assertEquals(homeResponseEntity.source, homeResponseDomain.source)
        assertEquals(homeResponseEntity.sortBy, homeResponseDomain.sortBy)
        assertEquals(homeResponseEntity.status, homeResponseDomain.status)
        assertEquals(homeResponseEntity.articles?.get(0)?.id, homeResponseDomain.articles?.get(0)?.id)
        assertEquals(homeResponseEntity.articles?.get(0)?.author, homeResponseDomain.articles?.get(0)?.author)
        assertEquals(homeResponseEntity.articles?.get(0)?.url, homeResponseDomain.articles?.get(0)?.url)
        assertEquals(homeResponseEntity.articles?.get(0)?.title, homeResponseDomain.articles?.get(0)?.title)
        assertEquals(homeResponseEntity.articles?.get(0)?.description, homeResponseDomain.articles?.get(0)?.description)
        assertEquals(homeResponseEntity.articles?.get(0)?.publishedAt, homeResponseDomain.articles?.get(0)?.publishedAt)
        assertEquals(homeResponseEntity.articles?.get(0)?.urlToImage, homeResponseDomain.articles?.get(0)?.urlToImage)
    }

    @Test
    fun `'fromDomainHomeResponse()' 'with domain equal null' 'then return data model is null'`() {
        //arrange
        val homeResponseDomain = null

        //act
        val homeResponseEntity = HomeResponseEntityMapper.mapper.fromDomain(homeResponseDomain)

        //assert
        Assert.assertNull(homeResponseEntity)
    }
}