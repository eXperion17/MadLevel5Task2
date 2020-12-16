package com.example.madlevel5task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.database.Game
import com.example.madlevel5task2.ui.GameAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_game_views.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameViewsFragment : Fragment() {

    private val games = arrayListOf<Game>()
    private val gameResultExpandedAdapter = GameAdapter(games);

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_views, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            view.findViewById<FloatingActionButton>(R.id.fab_transition_add_game).setOnClickListener {
                findNavController().navigate(R.id.action_gameViewsFragment_to_addGameFragment)
        }

        initViews()
    }

    private fun initViews() {
        rv_games.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        rv_games.adapter = gameResultExpandedAdapter
        rv_games.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}