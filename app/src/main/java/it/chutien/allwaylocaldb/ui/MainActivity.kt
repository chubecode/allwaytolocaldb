package it.chutien.allwaylocaldb.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import it.chutien.allwaylocaldb.R
import it.chutien.allwaylocaldb.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModel()
    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            setVariable(it.chutien.allwaylocaldb.BR.viewModel, viewModel)
        }

        insert_room.setOnClickListener {
            Snackbar.make(parent_view, "Inserted ${viewModel.insertRoomDb()}", Snackbar.LENGTH_SHORT).show()

        }

        insert_realm.setOnClickListener {
            Snackbar.make(parent_view, "Inserted ${ viewModel.insertRealmDb()}", Snackbar.LENGTH_SHORT).show()

        }

        get_room.setOnClickListener {
            viewModel.getRoomDb()
        }

        get_realm.setOnClickListener {
            viewModel.getRealmDb()
        }

    }
}
