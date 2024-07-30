package com.kkjang.mvi

import com.kkjang.core.base.BaseActivity
import com.kkjang.mvi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(inflater = ActivityMainBinding::inflate)