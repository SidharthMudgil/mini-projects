package com.sidharth.vidyakhoj.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sidharth.vidyakhoj.databinding.FragmentWebviewBinding

class WebViewFragment: Fragment() {
    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWebviewBinding.inflate(inflater)
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(args.url)
        return binding.root
    }
}