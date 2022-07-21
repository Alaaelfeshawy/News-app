package com.example.data.source.remote

import com.example.data.factory.HomeResponseEntityFactory
import com.example.data.utils.TestUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class HomeApiTest {
    private lateinit var SUT: HomeApi

    private lateinit var moshi: Moshi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiKey: String
    private lateinit var source: String

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        SUT = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HomeApi::class.java)

        apiKey = "apiKey"
        source = "source"
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getHomeData() 'with apiKey and source' 'then same apiKey and source is passed to endpoint'`() {

        //arrange
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(TestUtils.getJson("{}"))
        mockWebServer.enqueue(mockResponse)

        //act
        val testObserver = SUT.getHomeData(source, apiKey).test()
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        //assert
        val request = mockWebServer.takeRequest()
        val requestEntity = request.path?.split("?")?.get(1)?.split("&")
        Assert.assertEquals(requestEntity?.get(0)?.split("=")?.get(1), "source")
        Assert.assertEquals(requestEntity?.get(1)?.split("=")?.get(1), "apiKey")
    }

    @Test
    fun `getHomeData() 'with path' 'then success path passed to endpoint'`() {
        //arrange
        val path = "/articles?source=source&apiKey=apiKey"
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(TestUtils.getJson("{}"))
        mockWebServer.enqueue(mockResponse)

        //act
        val testObserver = SUT.getHomeData(source, apiKey).test()
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        //assert
        val request = mockWebServer.takeRequest()
        assertEquals(request.path, path)
    }

    @Test
    fun `getHomeData() 'with apiKey and source' 'then return home response'`() {

        //arrange
        val homeResponseEntity = HomeResponseEntityFactory.generateDataForHomeResponseEntity()
        // Mock a response with status 200 and sample JSON output
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(TestUtils.getJson("home_response.json"))

        // act
        mockWebServer.enqueue(mockResponse)
        val testObserver = SUT.getHomeData(source, apiKey).test()
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        // assert
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertValue { homeResponse ->
            (homeResponse.source == homeResponseEntity.source)

        }
    }


}