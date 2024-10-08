package com.peludo.mapeando0bem


import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback


class TelaCadastrarActivity : AppCompatActivity() {


    private val api by lazy { ApiRetrofit().endPoint }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_cadastrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btniniciar = findViewById<TextView>(R.id.login)

        val btnCadastrar = findViewById<AppCompatButton>(R.id.btnentrar)

        val nick:String = findViewById<EditText>(R.id.inputusuario).text.toString()
        val email:String = findViewById<EditText>(R.id.inputemail).text.toString()
        val senha:String = findViewById<EditText>(R.id.inputsenha).text.toString()





        api.data().enqueue(object : Callback<Notemodel> {
            override fun onResponse(
                call: Call<Notemodel>,
                response: retrofit2.Response<Notemodel>
            ) {
                if(response.isSuccessful){
                    val Listdata = response.body()!!.notes

                    Listdata.forEach {
                        Log.e("TelaCadastrarActivity","nick ${it.nick}")
                    }



                }

            }

            override fun onFailure(call: Call<Notemodel>, t: Throwable) {
                Log.e("TelaCadastrarActivity",t.toString())
            }

        })


        btniniciar.setOnClickListener {


            val intent = Intent(this, TelaLoginActivity::class.java)
            startActivity(intent)
        }
    }
















}





