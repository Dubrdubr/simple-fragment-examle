package ru.dubr.fragmentsexamle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.javafaker.Faker
import ru.dubr.fragmentsexamle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val faker = Faker.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) {
            val fragment = CounterFragment.newInstance(
                counterValue = 1,
                quote = createQuote()
            )
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    fun createQuote(): String = faker.yoda().quote()

    fun getScreensCount() = supportFragmentManager.backStackEntryCount + 1

}