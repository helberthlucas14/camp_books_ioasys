package com.example.books.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.books.domain.model.Book
import com.example.books.databinding.FragmentBookListBinding
import com.example.books.domain.exception.EmptyBookListException
import com.example.books.presentation.adapter.BookClickListener
import com.example.books.presentation.adapter.BooksListAdapter
import com.example.books.presentation.viewmodel.BookListViewModel
import com.example.books.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookListFragment : Fragment(), BookClickListener {

    private lateinit var booksAdapter: BooksListAdapter
    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private val bookListViewModel: BookListViewModel by lazy { getViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBookListBinding.inflate(inflater, container, false)
        .apply {
            _binding = this
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBookListData()
        configureListeners()
        addObserver()
    }

    private fun configureListeners() {
        binding.edSearch.textChangedListener = { input ->
            bookListViewModel.search(input)
        }
    }

    private

    fun setBookListData() {
        booksAdapter = BooksListAdapter(this)
        binding.rvBooks.adapter = booksAdapter
        bookListViewModel.search()
    }

    private fun addObserver() {
        bookListViewModel.bookListViewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Success -> OnSuccess(state.data)
                is ViewState.Error -> OnError(state.throwable)
                else -> Unit
            }
        }
    }

    private fun OnSuccess(data: List<Book>) {
        booksAdapter.submitList(data)
        showErrorEmptyListError(false)
    }

    private fun OnError(throwable: Throwable) {
        when (throwable) {
            is EmptyBookListException -> {
                booksAdapter.submitList(listOf())
                showErrorEmptyListError(true)
            }
        }
    }

    private fun showErrorEmptyListError(hasError: Boolean) {
        binding.tvEmptyList.visibility = if (hasError) View.VISIBLE else View.GONE
    }


    override fun onBookClickListener(book: Book) {
        BooksDetailsBottomSheetFragment.newInstance(book).show(childFragmentManager, "book")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}