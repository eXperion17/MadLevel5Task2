package com.example.madlevel5task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.database.Game
import com.example.madlevel5task2.model.GameViewModel
import com.example.madlevel5task2.ui.GameAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_game_views.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameViewsFragment : Fragment() {
    private var games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games);

    private val viewModel: GameViewModel by viewModels();

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
        rv_games.adapter = gameAdapter
        rv_games.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        observeAddingGames()

        createItemOnTouchHelper().attachToRecyclerView(rv_games);
    }

    private fun createItemOnTouchHelper():ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val game = games[viewHolder.adapterPosition]
                viewModel.deleteGame(games[viewHolder.adapterPosition])
                //snackbar maybe?
            }
        }

        return ItemTouchHelper(callback)
    }

    private fun observeAddingGames() {
        viewModel.games.observe(viewLifecycleOwner, Observer { game -> game?.let {
            games.clear()
            games.addAll(game);
            gameAdapter.notifyDataSetChanged()
        } })
    }

}