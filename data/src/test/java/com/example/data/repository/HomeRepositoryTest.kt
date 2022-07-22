package com.example.data.repository


import com.example.data.entity.home.response.HomeResponseEntity
import com.example.data.factory.HomeResponseEntityFactory
import com.example.data.source.remote.HomeApi
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(MockitoJUnitRunner::class)
class HomeRepositoryTest {

    private lateinit var SUT: HomeRepository

    @Mock
    private lateinit var homeApi: HomeApi


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        SUT = HomeRepository(homeApi , true)
    }

    @Test
    fun `'homeData()' 'with source and api key' 'then check source and api key passed to endpoint'`() {

        //arrange
        val cp = ArgumentCaptor.forClass(String::class.java)
        val homeResponseEntity = HomeResponseEntityFactory.generateDataForHomeResponseEntity()
        val source = "source"
        val apiKey = "apiKey"
        //act
        returnHomeDataResponse(Observable.just(homeResponseEntity) , source , apiKey)
        SUT.homeData(source)

        //assert
        Mockito.verify(homeApi, Mockito.times(1)).getHomeData(cp.capture() ?: "", cp.capture() ?: "")

        val captures = cp.allValues
        assertEquals(captures[0], source)
        assertEquals(captures[1], apiKey)
    }

    @Test
    fun `'homeData()' 'with apiKey and source ' then return home response '`() {
        // Arrange
        val homeResponseEntity = HomeResponseEntityFactory.generateDataForHomeResponseEntity()
        val homeResponseDomain = HomeResponseEntityFactory.generateDataForHomeResponseDomain()
        val source = "source"
        val apiKey = "apiKey"

        // Act
        returnHomeDataResponse(Observable.just(homeResponseEntity),source, apiKey)
        val testObserver = SUT.homeData(source).test()

        // Assert
        Mockito.verify(homeApi, Mockito.times(1)).getHomeData(source, apiKey)
        testObserver.assertValue {
            it == homeResponseDomain
        }
    }

    private fun returnHomeDataResponse(
        observable: Observable<HomeResponseEntity>,
        source: String,
        apiKey:String
    ) {
        Mockito.`when`(homeApi.getHomeData(source,apiKey))
            .thenReturn(observable)
    }

}