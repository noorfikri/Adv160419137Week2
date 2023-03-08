package com.ubaya.adv160419137week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

private var num1 = 0
private var num2 = 0
private var score = 0
class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        generateNum(view)

        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            view.findViewById<TextView>(R.id.txtTurn).text = "$playerName Turn"
        }

        val btnAnswer = view.findViewById<Button>(R.id.btnAnswer)
        btnAnswer.setOnClickListener {
            var txtAnswer = view.findViewById<TextView>(R.id.txtAnswer).text.toString()
            if(txtAnswer.toInt() == num1 + num2){
                score++
            }else{
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    fun generateNum(view: View){
        num1 = Random.nextInt(0,99)
        num2 = Random.nextInt(0,99)

        view.findViewById<TextView>(R.id.txtNum1).text = num1.toString()
        view.findViewById<TextView>(R.id.txtNum2).text = num2.toString()
    }
}