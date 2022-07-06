package com.evosouza.animation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.evosouza.animation.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.Main) {
            animation()
        }

    }

    private suspend fun animation(){
        repeat(1000){
            binding.ivAnimin1.apply {
                animate()
                    .scaleX(2f)
                    .scaleY(2f)
                    .alpha(.1f)
                    .setDuration(800)
                    .withEndAction{
                        binding.ivAnimin1.scaleX = .3f
                        binding.ivAnimin1.scaleY = .3f
                        binding.ivAnimin1.alpha = 1f
                    }
            }

            binding.ivAnimin2.apply {
                animate()
                    .scaleX(1.5f)
                    .scaleY(1.5f)
                    .alpha(.2f)
                    .setDuration(750)
                    .withEndAction{
                        binding.ivAnimin2.scaleX = .7f
                        binding.ivAnimin2.scaleY = .7f
                        binding.ivAnimin2.alpha = 1f
                    }
            }

            val scale = ObjectAnimator.ofPropertyValuesHolder(
                binding.cvFoto,
                PropertyValuesHolder.ofFloat("scaleX", .6f),
                PropertyValuesHolder.ofFloat("scaleY", .6f)
            )

            scale.duration = 500
            scale.repeatCount = ObjectAnimator.INFINITE
            scale.repeatMode = ObjectAnimator.REVERSE
            scale.start()

            delay(950)

            scale.repeatMode = ObjectAnimator.REVERSE
            scale.start()
        }
    }
}