package com.peludo.mapeando0bem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback

class TelaLoginActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endPoint }

    private lateinit var inputEmail: EditText
    private lateinit var inputSenha: EditText
    private lateinit var buttonLogar: AppCompatButton








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupView()
        setupListener()



        val btniniciar = findViewById<TextView>(R.id.cadastrase)

        btniniciar.setOnClickListener {


            val intent = Intent(this, TelaCadastrarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupView(){

        inputEmail = findViewById(R.id.inputemail)
        inputSenha = findViewById(R.id.inputsenha)
        buttonLogar = findViewById(R.id.btnentrar)

    }

    private fun setupListener(){

        buttonLogar.setOnClickListener {
            if (inputEmail.text.toString().isNotEmpty() && inputSenha.text.toString().isNotEmpty() ){

                api.logar( inputEmail.text.toString(),inputSenha.text.toString())
                    .enqueue(object : Callback<SubmitModel> {
                        override fun onResponse(
                            call: Call<SubmitModel>,
                            response: retrofit2.Response<SubmitModel>
                        ) {
                            val submit = response.body()
                            val msn = submit!!.messagem
                            val valuecod = submit.cod

                            Sessoes.Username = submit.nick






                            if (response.isSuccessful){

                                if (valuecod == "007ex"){

                                    Toast.makeText(applicationContext, msn, Toast.LENGTH_SHORT).show()



                                }

                                else if(valuecod == "009ex"){

                                    Toast.makeText(applicationContext, msn, Toast.LENGTH_SHORT).show()

                                }
                                else if(valuecod == "008ex"){

                                    Toast.makeText(applicationContext, msn, Toast.LENGTH_SHORT).show()
                                    navegaparatelainiciar()

                                }
                                else{
                                    Toast.makeText(applicationContext, "Erro desconhecido", Toast.LENGTH_SHORT).show()

                                }








                            }
                            else{

                                Toast.makeText(applicationContext, valuecod , Toast.LENGTH_SHORT).show()

                            }

                        }

                        override fun onFailure(call: Call<SubmitModel>, t: Throwable) {

                        }

                    })








            }
            else{
                Toast.makeText(applicationContext, "Preencha todos os Campos", Toast.LENGTH_SHORT).show()
            }




        }
    }

    fun navegaparatelainiciar() {
        val intent = Intent(this, TelainicialActivity::class.java)
        this.startActivity(intent)
    }

    object Sessoes {
         var Username = ""

    }
}