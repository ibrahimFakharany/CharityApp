package app.fakharany.com.charityapp.Model.Data

import app.fakharany.com.charityapp.Model.DataClasses.ApiInterface
import app.fakharany.com.charityapp.POJO.RootObj
import io.reactivex.observers.TestObserver
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteModelImplTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiInterface: ApiInterface

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        mockWebServer.start()

        val okHttpClient = OkHttpClient.Builder()
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mockWebServer.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        apiInterface = retrofit.create(ApiInterface::class.java)

    }

    @Test
    fun testGetCharities() {
        val observer = TestObserver<RootObj>()
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody("{\n" +
                        "  \"charities\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"organization_name\": \"Abu Rish Hospital\",\n" +
                        "      \"organization_pic\": \"http://abolreesh-cusph.com/wp-content/uploads/2017/10/logo.png\",\n" +
                        "      \"organization_type\": \"Hospital\",\n" +
                        "      \"organization_desc\": \"Child Hospital\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")

        mockWebServer.enqueue(mockResponse)
        apiInterface.getCategories().subscribeWith(observer)
        observer.assertNoErrors()
        observer.assertValueCount(1)
        val path = "/7arka_get_charities"
        val request = mockWebServer.takeRequest()
        assertEquals(path, request.path)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}