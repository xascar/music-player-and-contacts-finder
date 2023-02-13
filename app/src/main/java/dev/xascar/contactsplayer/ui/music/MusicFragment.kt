package dev.xascar.contactsplayer.ui.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.xascar.contactsplayer.databinding.FragmentMusicBinding

class MusicFragment : Fragment() {

    private var _binding: FragmentMusicBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMusic



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}