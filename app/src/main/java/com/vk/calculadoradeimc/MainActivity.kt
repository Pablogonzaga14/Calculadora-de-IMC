package com.vk.calculadoradeimc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vk.calculadoradeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btCalcular = binding.btCalcularImc
        val mensagem = binding.mensagem

        btCalcular.setOnClickListener {
            val peso = binding.editPeso.text.toString()
            val altura = binding.editAltura.text.toString()
            if (peso.isEmpty()){
                mensagem.setText("Informe o seu peso!")
            }else if (altura.isEmpty()){
                mensagem.setText("Informe a sua altura!")
            }else{
                CalculoDeIMC()
            }

        }
    }
    private fun CalculoDeIMC(){
        val pesoID = binding.editPeso
        val alturaID = binding.editAltura
        val peso = Integer.parseInt(pesoID.text.toString())
        val altura = java.lang.Float.parseFloat(alturaID.text.toString())
        val imc = peso / (altura * altura)
        val resultado = binding.mensagem

        val Mensagem = when{
            imc <= 18.5 -> "Abaixo do peso"
            imc <= 24.9 -> "Peso normal"
            imc <=29.9 -> "Sobrepeso"
            imc <=34.9 -> "Obesidade (Grau 1)"
            imc <=39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Morbida (Grau 3)"

        }
        imc.toString()
        resultado.setText("IMC: $imc \n $Mensagem")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset ->{
                val limparEditPeso = binding.editPeso
                val limparEditAltura = binding.editAltura
                val limparMensagem = binding.mensagem

                limparEditPeso.setText("")
                limparEditAltura.setText("")
                limparMensagem.setText("")
            }
        }

        return super.onOptionsItemSelected(item)
    }
}