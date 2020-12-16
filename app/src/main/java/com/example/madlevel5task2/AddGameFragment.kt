package com.example.madlevel5task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.database.Game
import com.example.madlevel5task2.database.GameRepository
import com.example.madlevel5task2.model.GameViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.util.*
import androidx.lifecycle.Observer

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels();

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //gameRepository = GameRepository(requireContext().applicationContext)

        view.findViewById<FloatingActionButton>(R.id.fab_add_game).setOnClickListener {
            val game = Game(
                et_title.text.toString(),
                parseDate(et_date_day.text.toString(), et_date_month.text.toString(), et_date_year.text.toString()),
                et_platform.text.toString()
            )

            viewModel.insertGame(game);
        }

        observeViewModel();
    }

    private fun observeViewModel() {
        viewModel.error.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, Observer { success ->
            //"pop" the backstack, this means we destroy this fragment and go back

            findNavController().popBackStack()
        })
    }

    private fun parseDate(day:String, month:String, year:String) : Date {
        //TODO: Actually process these variables into a date
        return Date()
    }

    private fun checkUserInput(game:Game) : Boolean {
        //Check if date is correct, otherwise return false and send a snack/toast
        //If its fine, add to database and return true
        return true;
    }
}