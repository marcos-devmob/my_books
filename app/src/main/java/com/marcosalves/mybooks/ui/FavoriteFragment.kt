package com.marcosalves.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcosalves.mybooks.R
import com.marcosalves.mybooks.databinding.FragmentFavoriteBinding
import com.marcosalves.mybooks.helper.BookConstants
import com.marcosalves.mybooks.ui.adapter.BookAdapter
import com.marcosalves.mybooks.ui.listener.BookListener
import com.marcosalves.mybooks.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private val adapter: BookAdapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)


        binding.recyclerviewBooksFavorite.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewBooksFavorite.adapter = adapter

        attachListener()

        setObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun attachListener() {
        adapter.attachListener(object  : BookListener {
            override fun onClick(id: Int) {

                val bundle = Bundle()
                bundle.putInt(BookConstants.KEY.BOOK_ID, id)

                findNavController().navigate(R.id.navigation_details, bundle)

            }

            override fun onFavoriteClick(id: Int) {
                viewModel.favorite(id)
            }
        })
    }

    private fun setObservers() {
        viewModel.books.observe(viewLifecycleOwner){
            if (it.isEmpty()) {
                binding.recyclerviewBooksFavorite.visibility = View.GONE
                binding.textviewNoBooks.visibility = View.VISIBLE
                binding.imageviewNoBooks.visibility = View.VISIBLE
            }else {
                binding.recyclerviewBooksFavorite.visibility = View.VISIBLE
                binding.textviewNoBooks.visibility = View.GONE
                binding.imageviewNoBooks.visibility = View.GONE
                adapter.upDateBooks(it)
            }
        }
    }
}