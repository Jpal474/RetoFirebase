package com.moviles.retofirebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.moviles.retofirebase.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.reference
        var cont = 2 //Actualizar acorde a los valores de la base de datos
        binding.save.setOnClickListener{
            val Title = binding.title.text.toString()
            val Year = binding.year.text.toString()
            val imdbID = binding.imdb.text.toString()
            val Type = binding.type.text.toString()
            val Poster = binding.poster.text.toString()
            val Country = binding.country.text.toString()
            val Genre = binding.genre.text.toString()
            myRef.child("MOVIES").child(cont.toString()+"").setValue(Movie(
                 Title, Year, imdbID, Type, Poster, Country, Genre))

               cont++

            myRef.child("MOVIES").get().addOnSuccessListener { response ->
                val resmap = response.value as ArrayList<Map <String, String>>
                Log.d("firebaseResponse", resmap.toString())
                resmap.forEach{ movie ->
                    if(!movie.isNullOrEmpty()){
                        Log.d("firebaseResponse", "titulo : ${movie["title"]}")
                    }
                    val jsonarray = JSONArray(resmap)
                    binding.rvUserEntries.adapter = MainAdapter(jsonarray)
                }}.addOnFailureListener{
                Log.e("firebaseResponse", "Error getting data", it)
            }

        }


          // val myjson = JSONObject(response.value.toString())
           //val jsonArray = JSONArray()
           //val namesArray = myjson.names()
           //for(i in 0..namesArray.length()-1){
            //   jsonArray.put(myjson.getJSONObject(namesArray[i].toString()))
           //}

           /* Log.d("firebaseResponse",jsonArray.toString())
            binding.rvUserEntries.adapter = MainAdapter(jsonArray)
        }.addOnFailureListener{
            Log.e("firebaseResponse", "Error getting data", it)
        }*/
    }
}
