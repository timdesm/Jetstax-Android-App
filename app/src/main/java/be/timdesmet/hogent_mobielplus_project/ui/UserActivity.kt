package be.timdesmet.hogent_mobielplus_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import be.timdesmet.hogent_mobielplus_project.R

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportActionBar?.hide()

    }
}