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
import com.google.android.material.button.MaterialButton
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class TelaCadastrarActivity : AppCompatActivity() {


    private val api by lazy { ApiRetrofit().endPoint }

    private lateinit var inputUsuario: EditText
    private lateinit var inputEmail: EditText
    private lateinit var inputSenha: EditText
    private lateinit var buttonCradastrar: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_cadastrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnlogin = findViewById<TextView>(R.id.login)

        setupView()
        setupListener()



        btnlogin.setOnClickListener {


            val intent = Intent(this, TelaLoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        // getNote()
    }

/*
    private fun getNote(){
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
    }


 */

    private fun setupView(){
        inputUsuario = findViewById(R.id.inputusuario)
        inputEmail = findViewById(R.id.inputemail)
        inputSenha = findViewById(R.id.inputsenha)
        buttonCradastrar = findViewById(R.id.btncadastrar)
    }

    private fun setupListener(){

        buttonCradastrar.setOnClickListener {

            if (inputEmail.text.toString().isNotEmpty() && inputUsuario.text.toString().isNotEmpty() && inputSenha.text.toString().isNotEmpty() ){

                Log.e("TelaCadastrarActivity",inputUsuario.text.toString())
                Log.e("TelaCadastrarActivity",inputEmail.text.toString())
                Log.e("TelaCadastrarActivity",inputSenha.text.toString())

                api.create( inputEmail.text.toString(),inputSenha.text.toString(),inputUsuario.text.toString() )
                    .enqueue(object : Callback<SubmitModel>{
                        override fun onResponse(
                            call: Call<SubmitModel>,
                            response: retrofit2.Response<SubmitModel>
                        ) {
                            val submit = response.body()
                            val msn = submit!!.messagem
                            val erro = submit.cod


                            if (response.isSuccessful){

                                Toast.makeText(applicationContext, msn, Toast.LENGTH_SHORT).show()

                            }
                            else{

                                Toast.makeText(applicationContext, erro , Toast.LENGTH_SHORT).show()

                            }

                        }

                        override fun onFailure(call: Call<SubmitModel>, t: Throwable) {

                        }

                    })


            }
            else
            {
                Toast.makeText(applicationContext, "Preencha todos os Campos", Toast.LENGTH_SHORT).show()
            }




        }

    }




















}





