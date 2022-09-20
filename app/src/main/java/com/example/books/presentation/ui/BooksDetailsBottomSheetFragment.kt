package com.example.books.presentation.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.books.R
import com.example.books.domain.model.Book
import com.example.books.databinding.FragmentBooksDetailsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BooksDetailsBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBooksDetailsBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var _book: Book? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBooksDetailsBottomSheetBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setListeners()
        setUpBootSheetHeight()
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    private fun setUpBootSheetHeight() {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun setListeners() {
        binding.btClose.setOnClickListener {
            dismiss()
        }
    }


    private fun setupView() {
        binding.apply {

            tvTitle.text = _book?.name
            tvAuthor.text = _book?.author
            tvPageValue.text = _book?.pages
            tvEditorValue.text = _book?.editor
            tvYearValue.text = _book?.date
            tvLanguageValue.text = _book?.language
            tvTitleValue.text = _book?.name
            tvISBN10Value.text = _book?.isbn10
            tvISBN13Value.text = _book?.isbn13

            Glide
                .with(this@BooksDetailsBottomSheetFragment)
                .load(_book?.imageUrl)
                .fitCenter()
                .into(imgBook)

            val spannableString = SpannableString("   ${_book?.review}")
            val imageSpan = ImageSpan(requireContext(), R.drawable.ic_quotes)
            spannableString.setSpan(imageSpan, 0, 1, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
            tvEditorReview.text = spannableString
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(book: Book? = null): BooksDetailsBottomSheetFragment {
            return BooksDetailsBottomSheetFragment().apply {
                this._book = book
            }
        }
    }
}