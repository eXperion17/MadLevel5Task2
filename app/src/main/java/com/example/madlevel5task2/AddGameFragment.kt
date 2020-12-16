package com.example.madlevel5task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.fab_add_game).setOnClickListener {
            // Check if the date the user input is correct
            if (checkUserInput()) {
                //Add game to database!

                findNavController().navigate(R.id.action_addGameFragment_to_gameViewsFragment)
            }
        }
    }

    private fun checkUserInput() : Boolean {
        //Check if date is correct, otherwise return false and send a snack/toast
        //If its fine, add to database and return true
        return true;
    }
}