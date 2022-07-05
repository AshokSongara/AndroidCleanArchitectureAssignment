package com.app.android_clean_architecture_assignment.presentation.characterDetail

import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.common.initViewModel
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModelFragment
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_character_detail.*

@AndroidEntryPoint
class CharacterDetailFragment : BaseViewModelFragment<CharacterDetailsViewModel>() {

    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun getContentResource() = R.layout.fragment_character_detail

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    override fun buildViewModel() = initViewModel<CharacterDetailsViewModel>()

    override fun initViews() {
        super.initViews()
        Glide.with(requireContext())
            .load(args.character.imageUrl)
            .into(ivCharacter)

        tvTitle.text = args.character.name

        tvDescription.text = args.character.name

        btnAdd.setOnClickListener {
            viewModel.insertCharacter(args.character)
            Toast.makeText(context, "Successfully Saved Data", Toast.LENGTH_SHORT).show()
        }
    }

}