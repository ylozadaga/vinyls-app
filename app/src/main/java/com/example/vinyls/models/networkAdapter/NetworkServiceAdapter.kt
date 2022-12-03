package com.example.vinyls.models.networkAdapter
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinyls.models.Album
import com.example.vinyls.models.Collector
import com.example.vinyls.models.Musician
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://back-vinyls-team-31.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    suspend fun getAlbums() = suspendCoroutine<List<Album>>{ cont ->
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),
                        name = item.getString("name"),
                        cover = item.getString("cover"),
                        recordLabel = item.getString("recordLabel"),
                        releaseDate = item.getString("releaseDate"),
                        genre = item.getString("genre"),
                        description = item.getString("description"))
                    )
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }
    suspend fun getMusicians() = suspendCoroutine<List<Musician>>{ cont ->
        requestQueue.add(getRequest("musicians",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Musician>()
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Musician(
                        musicianId = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        description = item.getString("description"),
                        birthDate = item.getString("birthDate"))
                    )
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>>{ cont ->
        requestQueue.add(getRequest("collectors",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Collector(
                        collectorId = item.getInt("id"),
                        name = item.getString("name"),
                        telephone = item.getString("telephone"),
                        email = item.getString("email"))
                    )
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }
    fun getAlbumById(albumId:Int, onComplete:(resp: Album)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONObject(response)
                val album = Album(albumId = resp.getInt("id"),name = resp.getString("name"), cover = resp.getString("cover"), recordLabel = resp.getString("recordLabel"), releaseDate = resp.getString("releaseDate"), genre = resp.getString("genre"), description = resp.getString("description"))
                onComplete(album)
            },
            {
                onError(it)
            }))
    }

    fun getMusicianById(musicianId:Int, onComplete:(resp: Musician)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("musicians",
            { response ->
                val resp = JSONObject(response)
                val musician = Musician(musicianId = resp.getInt("id"),name = resp.getString("name"), image = resp.getString("image"), description = resp.getString("description"), birthDate = resp.getString("birthDate"))
                onComplete(musician)
            },
            {
                onError(it)
            }))
    }


    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL +path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL +path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL +path, body, responseListener, errorListener)
    }
}