package com.example.bottomnavigationview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bottomnavigationview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {

                    changeFragment(HomeFragment())
                    //Tıklandığında badge'i kaldırması için gerekli olan kod.
                    binding.bottomNavigationView.removeBadge(R.id.home)
                }
                R.id.search -> changeFragment(SearchFragment())
                R.id.profile -> changeFragment(ProfileFragment())
            }
            true
        }
        //Badge eklemek için aşağıdakikodu kullanıyoruz.
        binding.bottomNavigationView.getOrCreateBadge(R.id.home).apply {
            this.isVisible = true
            this.number = 99
        }

    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView2, fragment) //İşlemin geri alınabilmesi için ekliyoruz aşağıdaki kodu.
            addToBackStack(null)
            commit()
        }
    }
}