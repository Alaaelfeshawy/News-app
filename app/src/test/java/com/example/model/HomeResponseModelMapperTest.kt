package com.example.model

import com.example.factory.HomeResponseModelFactory
import com.example.newsapp.model.home.response.HomeResponseModelMapper
import org.junit.Assert
import org.junit.Test

class HomeResponseModelMapperTest {

    @Test
    fun ` 'fromDomainHomeResponse()' 'with domain not equal null' 'return  model same as domain model'`() {
        //arrange
        val homeResponseDomain = HomeResponseModelFactory.generateDataForHomeResponseDomain()

        //act
        val homeResponseModel = HomeResponseModelMapper.mapper.fromDomain(homeResponseDomain)

        //assert
        Assert.assertNotNull(homeResponseModel)
        Assert.assertEquals(homeResponseModel.source, homeResponseDomain.source)
        Assert.assertEquals(homeResponseModel.sortBy, homeResponseDomain.sortBy)
        Assert.assertEquals(homeResponseModel.status, homeResponseDomain.status)
        Assert.assertEquals(homeResponseModel.articles?.get(0)?.author, homeResponseDomain.articles?.get(0)?.author)
        Assert.assertEquals(homeResponseModel.articles?.get(0)?.url, homeResponseDomain.articles?.get(0)?.url)
        Assert.assertEquals(homeResponseModel.articles?.get(0)?.title, homeResponseDomain.articles?.get(0)?.title)
        Assert.assertEquals(homeResponseModel.articles?.get(0)?.description, homeResponseDomain.articles?.get(0)?.description)
        Assert.assertEquals(homeResponseModel.articles?.get(0)?.publishedAt, homeResponseDomain.articles?.get(0)?.publishedAt)
        Assert.assertEquals(homeResponseModel.articles?.get(0)?.urlToImage, homeResponseDomain.articles?.get(0)?.urlToImage)
    }

    @Test
    fun `'fromDomainHomeResponse()' 'with domain equal null' 'then return model is null'`() {
        //arrange
        val homeResponseDomain = null

        //act
        val homeResponseModel = HomeResponseModelMapper.mapper.fromDomain(homeResponseDomain)

        //assert
        Assert.assertNull(homeResponseModel)
    }
}