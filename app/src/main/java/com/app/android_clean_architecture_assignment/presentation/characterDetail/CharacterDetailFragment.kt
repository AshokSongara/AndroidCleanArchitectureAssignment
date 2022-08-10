package com.app.android_clean_architecture_assignment.presentation.characterDetail

import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.common.initViewModel
import com.app.android_clean_architecture_assignment.databinding.FragmentCharacterDetailBinding
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModelFragment
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseViewModelFragment<CharacterDetailsViewModel>() {

    private lateinit var binding: FragmentCharacterDetailBinding

    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun getContentResource() = R.layout.fragment_character_detail

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    override fun buildViewModel() = initViewModel<CharacterDetailsViewModel>()

    override fun initViews() {
        super.initViews()
        binding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_character_detail)

        Glide.with(requireContext())
            .load(args.character.imageUrl)
            .into(binding.ivCharacter)

        binding.tvTitle.text = args.character.name

        binding.tvDescription.text = args.character.name

    }

}